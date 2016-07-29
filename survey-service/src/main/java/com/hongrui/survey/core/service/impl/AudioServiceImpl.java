package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Audio;
import com.hongrui.survey.core.repository.AudioRepository;
import com.hongrui.survey.core.model.AudioModel;
import com.hongrui.survey.core.service.AudioService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class AudioServiceImpl implements AudioService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private AudioRepository audioRepo;

	@Transactional
	@Override
	public int create(AudioModel audioModel) {
		return audioRepo.insert(beanMapper.map(audioModel, Audio.class));
	}

	@Transactional
	@Override
	public int createSelective(AudioModel audioModel) {
		return audioRepo.insertSelective(beanMapper.map(audioModel, Audio.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return audioRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public AudioModel findByPrimaryKey(Long id) {
		Audio audio = audioRepo.selectByPrimaryKey(id);
		return beanMapper.map(audio, AudioModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(AudioModel audioModel) {
		return audioRepo.selectCount(beanMapper.map(audioModel, Audio.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<AudioModel> selectPage(AudioModel audioModel,Pageable pageable) {
		Audio audio = beanMapper.map(audioModel, Audio.class);
		return beanMapper.mapAsList(audioRepo.selectPage(audio,pageable),AudioModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(AudioModel audioModel) {
		return audioRepo.updateByPrimaryKey(beanMapper.map(audioModel, Audio.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(AudioModel audioModel) {
		return audioRepo.updateByPrimaryKeySelective(beanMapper.map(audioModel, Audio.class));
	}

}
