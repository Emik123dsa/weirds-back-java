package com.department.api.controller;

import com.department.api.model.departments.DepartmentListModel;
import com.department.api.model.departments.DepartmentUserModel;
import com.department.api.model.response.SchemaResponseModel;
import com.department.api.service.DepartmentListService;
import com.department.api.service.DepartmentUserService;
import com.department.api.util.JWTUtil;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(allowCredentials = "*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentUserService departmentUserService;

    private final DepartmentListService departmentListService;

    private final JWTUtil jwtUtil;

    @RequestMapping(value = "/lists", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listDepartments(@RequestHeader(name = "Authorization") String authorizationToken) {
        String token = authorizationToken.substring(7);

        String username = jwtUtil.extractUsername(token);

        final DepartmentUserModel departmentUserModel = departmentUserService.findByUsername(username);

        if (departmentUserModel != null) {
            return ResponseEntity.ok(new SchemaResponseModel(1, "Found", departmentListService.getAllDepartments(departmentUserModel.getUuid())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SchemaResponseModel(2, "Unauthorized", "Oops!"));
        }
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> currentDepartment(@PathVariable(name = "id") Long id, @RequestHeader(name = "Authorization") String authorizationToken) {
        String token = authorizationToken.substring(7);

        String username = jwtUtil.extractUsername(token);

        final DepartmentUserModel departmentUserModel = departmentUserService.findByUsername(username);

        if (departmentUserModel != null) {
            final DepartmentListModel departmentListModel = departmentListService.getDepartment(departmentUserModel.getUuid(), id);
             if(departmentListModel != null) {
                 return ResponseEntity.status(HttpStatus.OK).body(new SchemaResponseModel(1, "Success", departmentListModel));
             } else {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SchemaResponseModel(2, "Not found", "Oops!"));
             }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SchemaResponseModel(2, "Unauthorized", "Oops!"));
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentListModel departmentListModel, @RequestHeader(name = "Authorization") String authorizationToken) throws BadCredentialsException {
        String token = authorizationToken.substring(7);

        String username = jwtUtil.extractUsername(token);

        final DepartmentUserModel departmentUserModel = departmentUserService.findByUsername(username);

        try {
            if (departmentUserModel != null) {
                departmentListModel.setUuid(departmentUserModel.getUuid());
                departmentListService.save(departmentListModel);
                return ResponseEntity.ok(new SchemaResponseModel(1, "Success", departmentListModel));
            }
        } catch(BadCredentialsException e) {
            throw new BadCredentialsException("Invalid: " + e);
        }

        return  ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable(name = "id") Long id, @RequestHeader(name = "Authorization") String authorizationToken) throws BadCredentialsException {
        String token = authorizationToken.substring(7);

        String username = jwtUtil.extractUsername(token);

        final DepartmentUserModel departmentUserModel = departmentUserService.findByUsername(username);

        try {
            if (departmentUserModel != null) {
                departmentListService.deleteDepartment(departmentUserModel.getUuid(), id);
                return ResponseEntity.ok(new SchemaResponseModel(1, "Success", "Department " + id + "has been erased"));
            }
        } catch(BadCredentialsException e) {
            throw new BadCredentialsException("Invalid: " + e);
        }

        return ResponseEntity.badRequest().build();
    }

    @Autowired
    public DepartmentController( DepartmentUserService departmentUserService, JWTUtil jwtUtil, DepartmentListService departmentListService) {
        this.departmentUserService = departmentUserService;
        this.jwtUtil = jwtUtil;
        this.departmentListService = departmentListService;
    }
}
