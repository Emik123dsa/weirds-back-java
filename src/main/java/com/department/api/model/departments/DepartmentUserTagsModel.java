package com.department.api.model.departments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "dp_tags")
public class DepartmentUserTagsModel {

    @Id
    private String tagName;
    private boolean activatedTag;

    public DepartmentUserTagsModel(String tagName, boolean activatedTag) {
        this.tagName = tagName;
        this.activatedTag = activatedTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isActivatedTag() {
        return activatedTag;
    }

    public void setActivatedTag(boolean activatedTag) {
        this.activatedTag = activatedTag;
    }
}
