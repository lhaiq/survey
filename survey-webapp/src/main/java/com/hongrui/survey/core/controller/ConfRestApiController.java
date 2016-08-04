package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.model.TaskTypeModel;
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

import com.hongrui.survey.core.service.ConfService;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.vo.ConfVO;

import java.util.List;

@RestController
@RequestMapping("/survey/core")
public class ConfRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ConfRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ConfService confService;


	@PostMapping(value = "/conf")
	public ResponseEnvelope<String> createTaskType(@RequestBody TaskTypeModel taskTypeModel){
		confService.createTaskType(taskTypeModel);
		ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok",true);
        return responseEnv;
	}

    @DeleteMapping(value = "/core/conf/{id}")
	public ResponseEnvelope<Integer> deleteConfByPrimaryKey(@PathVariable Long id){
		Integer  result = confService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/core/conf/{id}")
	public ResponseEnvelope<Integer> updateConfByPrimaryKeySelective(@PathVariable Long id,
					@RequestBody ConfVO confVO){
		ConfModel confModel = beanMapper.map(confVO, ConfModel.class);
		confModel.setId(id);
		Integer  result = confService.updateByPrimaryKeySelective(confModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
