package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Photo;
import com.hongrui.survey.core.repository.PhotoRepository;
import com.hongrui.survey.core.model.PhotoModel;
import com.hongrui.survey.core.service.PhotoService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private PhotoRepository photoRepo;

	@Transactional
	@Override
	public int create(PhotoModel photoModel) {
		return photoRepo.insert(beanMapper.map(photoModel, Photo.class));
	}

	@Transactional
	@Override
	public int createSelective(PhotoModel photoModel) {
		return photoRepo.insertSelective(beanMapper.map(photoModel, Photo.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return photoRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public PhotoModel findByPrimaryKey(Long id) {
		Photo photo = photoRepo.selectByPrimaryKey(id);
		return beanMapper.map(photo, PhotoModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(PhotoModel photoModel) {
		return photoRepo.selectCount(beanMapper.map(photoModel, Photo.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<PhotoModel> selectPage(PhotoModel photoModel,Pageable pageable) {
		Photo photo = beanMapper.map(photoModel, Photo.class);
		return beanMapper.mapAsList(photoRepo.selectPage(photo,pageable),PhotoModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(PhotoModel photoModel) {
		return photoRepo.updateByPrimaryKey(beanMapper.map(photoModel, Photo.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(PhotoModel photoModel) {
		return photoRepo.updateByPrimaryKeySelective(beanMapper.map(photoModel, Photo.class));
	}

}
