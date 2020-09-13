package com.department.api.repository;

import com.department.api.model.departments.DepartmentUserTagsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentUserTagsRepository extends MongoRepository<DepartmentUserTagsModel, Long> {
}
