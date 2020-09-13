package com.department.api.model.departments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@ToString

@Document(collection = "dp_users")
public class DepartmentUserModel {

    @Id
    private Long id;
    private UUID uuid;
    private String email;
    private String password;
    private String username;
    private Date created_at;
    private Date updated_at;
    private Set<DepartmentUserTagsModel> tags;

    public Set<DepartmentUserTagsModel> getTags() {
        return tags;
    }

    public void setTags(Set<DepartmentUserTagsModel> tags) {
        this.tags = tags;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
