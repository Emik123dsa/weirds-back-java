package com.department.api.validator;

import com.department.api.model.departments.DepartmentUserModel;
import com.department.api.repository.DepartmentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class DepartmentUserValidator implements Validator {
    private final DepartmentUserRepository departmentUserRepository;

    public DepartmentUserValidator(DepartmentUserRepository departmentUserRepository) {
        this.departmentUserRepository = departmentUserRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return DepartmentUserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object pseudoUserDepartment, Errors errors) {
        DepartmentUserModel departmentUserModel = (DepartmentUserModel) pseudoUserDepartment;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Should be not empty");
        if (departmentUserModel.getUsername().length() < 6 && departmentUserModel.getUsername().length() > 32) {
            errors.rejectValue("username", "Username Error!");
        }
    }
}
