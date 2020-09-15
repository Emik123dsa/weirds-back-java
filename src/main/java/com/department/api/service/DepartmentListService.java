package com.department.api.service;

import com.department.api.model.departments.DepartmentListModel;
import com.department.api.model.departments.DepartmentUserModel;
import com.department.api.repository.DepartmentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class DepartmentListService {

    private final DepartmentListRepository departmentListRepository;

    @Autowired
    public DepartmentListService(DepartmentListRepository departmentListRepository) {
        this.departmentListRepository = departmentListRepository;
    }

    public void save(Object departmentUserList) throws BadCredentialsException {
        DepartmentListModel departmentListModel = (DepartmentListModel) departmentUserList;

        try {
            if (departmentListRepository.findById(departmentListModel.getId()) != null) {
                departmentListRepository.save(departmentListModel);
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials: " + e);
        }
    }

    public void update(Object departmentUserList) throws BadCredentialsException {
        DepartmentListModel departmentListModel = (DepartmentListModel) departmentUserList;

        final Optional<DepartmentListModel> foundDepartment = departmentListRepository.findById(departmentListModel.getId());

        try {
            if (foundDepartment != null) {
              final DepartmentListModel updatedDepartment = new DepartmentListModel();

              updatedDepartment.setContact_person_fields(new ArrayList<>());

              updatedDepartment.setInfo_fields(new ArrayList<>());

              departmentListRepository.save(updatedDepartment);
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials: " + e);
        }
    }

    public List<DepartmentListModel> getAllDepartments(UUID uuid) {
        return departmentListRepository.findAllByUuid(uuid);
    }

    public DepartmentListModel getDepartment(UUID uuid, Long id) {
        return departmentListRepository.findByUuidAndId(uuid, id);
    }

    public void deleteDepartment(UUID uuid, Long id) {
        departmentListRepository.deleteByUuidAndId(uuid, id);
    }
}
