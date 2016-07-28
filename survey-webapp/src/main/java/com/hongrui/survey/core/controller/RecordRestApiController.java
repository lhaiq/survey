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

import com.hongrui.survey.core.service.RecordService;
import com.hongrui.survey.core.model.RecordModel;
import com.hongrui.survey.core.vo.RecordVO;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class RecordRestApiController {

	private final Logger logger = LoggerFactory.getLogger(RecordRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private RecordService recordService;

	@GetMapping(value = "/core/record/{id}")
	public ResponseEnvelope<RecordVO> getRecordById(@PathVariable Long id){
		RecordModel recordModel = recordService.findByPrimaryKey(id);
		RecordVO recordVO =beanMapper.map(recordModel, RecordVO.class);
		ResponseEnvelope<RecordVO> responseEnv = new ResponseEnvelope<>(recordVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/core/record")
    public ResponseEnvelope<Page<RecordModel>> listRecord(RecordVO recordVO,Pageable pageable){

		RecordModel param = beanMapper.map(recordVO, RecordModel.class);
        List<RecordModel> recordModelModels = recordService.selectPage(param,pageable);
        long count=recordService.selectCount(param);
        Page<RecordModel> page = new PageImpl<>(recordModelModels,pageable,count);
        ResponseEnvelope<Page<RecordModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/core/record")
	public ResponseEnvelope<Integer> createRecord(@RequestBody RecordVO recordVO){
		RecordModel recordModel = beanMapper.map(recordVO, RecordModel.class);
		Integer  result = recordService.create(recordModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/core/record/{id}")
	public ResponseEnvelope<Integer> deleteRecordByPrimaryKey(@PathVariable Long id){
		Integer  result = recordService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/core/record/{id}")
	public ResponseEnvelope<Integer> updateRecordByPrimaryKeySelective(@PathVariable Long id,
					@RequestBody RecordVO recordVO){
		RecordModel recordModel = beanMapper.map(recordVO, RecordModel.class);
		recordModel.setId(id);
		Integer  result = recordService.updateByPrimaryKeySelective(recordModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
