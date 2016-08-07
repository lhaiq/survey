package com.hongrui.survey.core.service.impl;

import com.hongrui.survey.core.service.TaskService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Photo;
import com.hongrui.survey.core.repository.PhotoRepository;
import com.hongrui.survey.core.model.PhotoModel;
import com.hongrui.survey.core.service.PhotoService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhotoServiceImpl implements PhotoService {

	private final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private PhotoRepository photoRepo;

	@Autowired
	private TaskService taskService;

	@Value("${photo.base.url}")
	private String baseDirectory;

	@Transactional
	@Override
	public int create(PhotoModel photoModel) {
		return photoRepo.insert(beanMapper.map(photoModel, Photo.class));
	}

	@Transactional
	@Override
	public int createSelective(PhotoModel photoModel) {
		taskService.checkCanEdit(photoModel.getTaskId());

		Photo photo = beanMapper.map(photoModel, Photo.class);
		int retVal = photoRepo.insertSelective(photo);
		photoModel.setId(photo.getId());
		return retVal;
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return photoRepo.deleteByPrimaryKey(id);
	}

	@Override
	public int deletePhoto(Long id) {
		PhotoModel photoModel = findByPrimaryKey(id);
		taskService.checkCanEdit(photoModel.getTaskId());
		try {
			FileUtils.forceDelete(new File(baseDirectory + "/" + photoModel.getPath()));
			deleteByPrimaryKey(id);
		} catch (IOException e) {
			logger.error("unexpected error,", e);
		}
		return 0;
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
	public List<PhotoModel> selectPage(PhotoModel photoModel, Pageable pageable) {
		Photo photo = beanMapper.map(photoModel, Photo.class);
		return beanMapper.mapAsList(photoRepo.selectPage(photo, pageable), PhotoModel.class);
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

	@Override
	public Map<Long, List<PhotoModel>> getPhotoAndTypeByTaskId(Long taskId) {

		Photo photo = new Photo();
		photo.setTaskId(taskId);

		Pageable pageable = new PageRequest(0, 99999);
		List<Photo> ps = photoRepo.selectPage(photo, pageable);

		List<PhotoModel> pms = beanMapper.mapAsList(ps, PhotoModel.class);

		Map<Long, List<PhotoModel>> ret = new HashMap<Long, List<PhotoModel>>();

		for (int i = 0; i < pms.size(); i++) {
			PhotoModel p = pms.get(i);
			Long type = p.getPhotoType();
			if (!ret.containsKey(type)) {
				ret.put(type, new ArrayList<PhotoModel>());
			}
			ret.get(type).add(p);
		}

		return ret;

	}

}
