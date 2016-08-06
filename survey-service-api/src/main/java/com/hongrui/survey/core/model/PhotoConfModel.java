package com.hongrui.survey.core.model;

import java.util.List;

/**
 * Created by haiquanli on 16/8/3.
 */
public class PhotoConfModel {

    private String pixel;
    private Long photoType;
    private List<PhotoModel> contents;

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }


    public String getPixel() {
        return pixel;
    }

    public List<PhotoModel> getContents() {
        return contents;
    }

    public void setContents(List<PhotoModel> contents) {
        this.contents = contents;
    }

    public void setPhotoType(Long photoType) {
        this.photoType = photoType;
    }

    public Long getPhotoType() {
        return photoType;
    }
}
