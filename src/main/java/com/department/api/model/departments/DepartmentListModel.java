package com.department.api.model.departments;

import java.util.List;

public class DepartmentListModel {
    private Integer id;

    private String departmentName;

    private String photoVendor;

    private Boolean activated;

    private List<Object> infoFields;

    private List<Object> contactPersonFields;

    public DepartmentListModel(Integer id, String departmentName, String photoVendor, Boolean activated, List<Object> infoFields, List<Object> contactPersonFields) {
        this.id = id;
        this.departmentName = departmentName;
        this.photoVendor = photoVendor;
        this.activated = activated;
        this.infoFields = infoFields;
        this.contactPersonFields = contactPersonFields;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhotoVendor() {
        return photoVendor;
    }

    public void setPhotoVendor(String photoVendor) {
        this.photoVendor = photoVendor;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public List<Object> getInfoFields() {
        return infoFields;
    }

    public void setInfoFields(List<Object> infoFields) {
        this.infoFields = infoFields;
    }

    public List<Object> getContactPersonFields() {
        return contactPersonFields;
    }

    public void setContactPersonFields(List<Object> contactPersonFields) {
        this.contactPersonFields = contactPersonFields;
    }


}
