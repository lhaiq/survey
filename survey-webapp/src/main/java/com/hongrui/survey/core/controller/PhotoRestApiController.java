package com.hongrui.survey.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.annotation.RestApiController;

import com.hongrui.survey.core.service.PhotoService;
import com.hongrui.survey.core.model.PhotoModel;
import com.hongrui.survey.core.vo.PhotoVO;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class PhotoRestApiController {

	private final Logger logger = LoggerFactory.getLogger(PhotoRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private PhotoService photoService;

	@GetMapping(value = "/core/photo/{id}")
	public ResponseEnvelope<PhotoVO> getPhotoById(@PathVariable Long id){
		PhotoModel photoModel = photoService.findByPrimaryKey(id);
		PhotoVO photoVO =beanMapper.map(photoModel, PhotoVO.class);
		ResponseEnvelope<PhotoVO> responseEnv = new ResponseEnvelope<>(photoVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/core/photo")
    public ResponseEnvelope<Page<PhotoModel>> listPhoto(PhotoVO photoVO,Pageable pageable){

		PhotoModel param = beanMapper.map(photoVO, PhotoModel.class);
        List<PhotoModel> photoModelModels = photoService.selectPage(param,pageable);
        long count=photoService.selectCount(param);
        Page<PhotoModel> page = new PageImpl<>(photoModelModels,pageable,count);
        ResponseEnvelope<Page<PhotoModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/core/photo")
	public ResponseEnvelope<Integer> createPhoto(@RequestBody PhotoVO photoVO){
		PhotoModel photoModel = beanMapper.map(photoVO, PhotoModel.class);
		Integer  result = photoService.create(photoModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/core/photo/{id}")
	public ResponseEnvelope<Integer> deletePhotoByPrimaryKey(@PathVariable Long id){
		Integer  result = photoService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/core/photo/{id}")
	public ResponseEnvelope<Integer> updatePhotoByPrimaryKeySelective(@PathVariable Long id,
					@RequestBody PhotoVO photoVO){
		PhotoModel photoModel = beanMapper.map(photoVO, PhotoModel.class);
		photoModel.setId(id);
		Integer  result = photoService.updateByPrimaryKeySelective(photoModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
