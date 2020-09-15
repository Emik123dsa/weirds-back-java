package com.department.api.model.departments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString

@Document(collection = "dp_list")
public class DepartmentListModel {
    @Id
    private Long id;

    private String department_name;

    private UUID uuid;

    private String photo_vendor;

    private Boolean activated;

    private List<Object> info_fields;

    private List<Object> contact_person_fields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPhoto_vendor() {
        return photo_vendor;
    }

    public void setPhoto_vendor(String photo_vendor) {
        this.photo_vendor = photo_vendor;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public List<Object> getInfo_fields() {
        return info_fields;
    }

    public void setInfo_fields(List<Object> info_fields) {
        this.info_fields = info_fields;
    }

    public List<Object> getContact_person_fields() {
        return contact_person_fields;
    }

    public void setContact_person_fields(List<Object> contact_person_fields) {
        this.contact_person_fields = contact_person_fields;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
