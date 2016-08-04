package com.hongrui.survey.core.model;

import java.util.List;

/**
 * Created by haiquanli on 16/8/3.
 */
public class TaskTypeModel {

    private List<String> name;
    private List<String> photoTypes;
    private List<String> pixels;
    private List<String> templateNames;
    private List<String> templateContents;
    private List<String> desc;


    public List<String> getPhotoTypes() {
        return photoTypes;
    }

    public void setPhotoTypes(List<String> photoTypes) {
        this.photoTypes = photoTypes;
    }

    public List<String> getPixels() {
        return pixels;
    }

    public void setPixels(List<String> pixels) {
        this.pixels = pixels;
    }

    public List<String> getTemplateContents() {
        return templateContents;
    }

    public void setTemplateContents(List<String> templateContents) {
        this.templateContents = templateContents;
    }

    public List<String> getTemplateNames() {
        return templateNames;
    }

    public void setTemplateNames(List<String> templateNames) {

        this.templateNames = templateNames;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
