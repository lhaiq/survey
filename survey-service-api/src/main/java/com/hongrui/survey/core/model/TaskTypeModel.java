package com.hongrui.survey.core.model;

import java.util.List;

/**
 * Created by haiquanli on 16/8/3.
 */
public class TaskTypeModel {

    private List<String> name;
    private List<String> photoTypes;
    private List<String> pixels;
    private List<String> templates;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

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

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }
}
