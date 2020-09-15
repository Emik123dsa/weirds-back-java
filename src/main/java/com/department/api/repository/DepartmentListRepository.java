package com.department.api.repository;

import com.department.api.model.departments.DepartmentListModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DepartmentListRepository extends MongoRepository<DepartmentListModel, Long> {
    List<DepartmentListModel> findAllByUuid(UUID uuid);
    DepartmentListModel findByUuid(UUID uuid);
    DepartmentListModel findByUuidAndId(UUID uuid, Long id);
    void deleteByUuidAndId(UUID uuid, Long id);
}
