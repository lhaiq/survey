
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.PhotoModel;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhotoService {

    public int create(PhotoModel photoModel);

    public int createSelective(PhotoModel photoModel);

    public PhotoModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(PhotoModel photoModel);

    public int updateByPrimaryKeySelective(PhotoModel photoModel);

    public int deleteByPrimaryKey(Long id);

    public int deletePhoto(Long id);

    public long selectCount(PhotoModel photoModel);

    public List<PhotoModel> selectPage(PhotoModel photoModel, Pageable pageable);

}