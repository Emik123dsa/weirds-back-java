package com.department.api.resource;

import com.department.api.DepartmentSecurityConfig;
import com.department.api.model.departments.DepartmentUserModel;
import com.department.api.model.request.AuthenticationRequest;

import com.department.api.model.response.JsonWebTokenResponseModel;
import com.department.api.model.response.SchemaResponseModel;
import com.department.api.repository.DepartmentUserRepository;
import com.department.api.service.DepartmentUserService;
import com.department.api.util.JWTUtil;

import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DepartmentUserResource {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentUserService.class);

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final DepartmentUserService departmentUserService;

    @Autowired
    public DepartmentUserResource(AuthenticationManager authenticationManager, JWTUtil jwtUtil, DepartmentUserService departmentUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.departmentUserService = departmentUserService;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception, JwtException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new SchemaResponseModel(2, "Failed", authenticationRequest));
        }

        final UserDetails userDetails = departmentUserService.loadUserByUsername(authenticationRequest.getUsername());

        try {
            final String token = jwtUtil.generateToken(userDetails);
            logger.debug("User %s has been successfully authorized", authenticationRequest.getUsername());

            return ResponseEntity.ok(new SchemaResponseModel(1, "Success", new JsonWebTokenResponseModel(token)));
        } catch(JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new SchemaResponseModel(2, "Failed", userDetails));
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewUserDepartment(@RequestBody DepartmentUserModel departmentUserModel) throws Exception{

        if (departmentUserModel.getId() == null || departmentUserModel.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new SchemaResponseModel(2, "Failed", departmentUserModel));
        }

        if(departmentUserService.findByEmail(departmentUserModel) != null && departmentUserService.findById(departmentUserModel) != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new SchemaResponseModel(2, "Failed", departmentUserModel));
        } else {
            departmentUserService.save(departmentUserModel);

            logger.debug("User %s has been successfully signed up", departmentUserModel.getUsername());

            return ResponseEntity.ok(new SchemaResponseModel(1, "Successfully singed up", departmentUserModel));
        }
    }

}
