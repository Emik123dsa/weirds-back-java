package com.department.api.service;

import com.department.api.model.departments.DepartmentUserModel;
import com.department.api.model.departments.DepartmentUserTagsModel;
import com.department.api.repository.DepartmentUserRepository;

import com.department.api.repository.DepartmentUserTagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class DepartmentUserService implements UserDetailsService {
    private final DepartmentUserTagsRepository departmentUserTagsRepository;
    private final DepartmentUserRepository departmentUserRepository;
    /**
     *  departmentUser Service init
     * @param departmentUserRepository
     */
    @Autowired
    public DepartmentUserService(DepartmentUserRepository departmentUserRepository, DepartmentUserTagsRepository departmentUserTagsRepository) {
        this.departmentUserRepository = departmentUserRepository;
        this.departmentUserTagsRepository = departmentUserTagsRepository;

    }
    /**
     *  Implementation for user details
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DepartmentUserModel departmentUserModel = departmentUserRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(DepartmentUserTagsModel departmentUserTagsModel : departmentUserModel.getTags()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(departmentUserTagsModel.getTagName()));
        }

        return new User(departmentUserModel.getUsername(), departmentUserModel.getPassword(), grantedAuthorities);
    }
    /**
     *  Find all of the required users
     * @return
     */
    public List<DepartmentUserModel> findAll() {
        return departmentUserRepository.findAll();
    }
    /**
     *  Save new credentials for user
     * @param departmentUserModel
     * @return
     */
    public DepartmentUserModel save(DepartmentUserModel departmentUserModel) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        departmentUserModel.setPassword(bCryptPasswordEncoder.encode(departmentUserModel.getPassword()));
        departmentUserModel.setUuid(UUID.randomUUID());
        departmentUserModel.setCreated_at(new Date(System.currentTimeMillis()));
        departmentUserModel.setUpdated_at(new Date(System.currentTimeMillis()));
        departmentUserModel.setTags(new HashSet<>());
        return departmentUserRepository.save(departmentUserModel);
    }

    public DepartmentUserModel findByEmail(DepartmentUserModel departmentUserModel) {
        return departmentUserRepository.findByEmail(departmentUserModel.getEmail());
    }

    public Optional<DepartmentUserModel> findById(DepartmentUserModel departmentUserModel) {
        return departmentUserRepository.findById(departmentUserModel.getId());
    }

    public DepartmentUserModel findByUsername(String token) {
        return departmentUserRepository.findByUsername(token);
    }
}
