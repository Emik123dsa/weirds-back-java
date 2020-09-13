package com.department.api.repository;

import com.department.api.model.departments.DepartmentUserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentUserRepository extends MongoRepository<DepartmentUserModel, Long> {
    DepartmentUserModel findByUsername(String username);
    DepartmentUserModel findByEmail(String email);
}
