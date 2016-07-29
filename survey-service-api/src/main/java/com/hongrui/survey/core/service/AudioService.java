
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.AudioModel;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AudioService {

    public int create(AudioModel audioModel);

    public int createSelective(AudioModel audioModel);

    public AudioModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(AudioModel audioModel);

    public int updateByPrimaryKeySelective(AudioModel audioModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(AudioModel audioModel);

    public List<AudioModel> selectPage(AudioModel audioModel, Pageable pageable);

//    public void mergeAudio(Long id,Integer lastIndex);

}