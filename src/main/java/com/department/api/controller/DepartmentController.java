package com.department.api.controller;

import com.department.api.model.departments.DepartmentUserModel;
import com.department.api.service.DepartmentUserService;
import com.department.api.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController implements ErrorController {

    private final DepartmentUserService  departmentUserService;

    private final JWTUtil jwtUtil;

    public DepartmentController(DepartmentUserService departmentUserService, JWTUtil jwtUtil) {
        this.departmentUserService = departmentUserService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404 | Not Found";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500 | Internal Server Error";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
