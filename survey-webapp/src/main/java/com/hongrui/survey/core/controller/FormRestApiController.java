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

import com.hongrui.survey.core.service.FormService;
import com.hongrui.survey.core.model.FormModel;
import com.hongrui.survey.core.vo.FormVO;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class FormRestApiController {

	private final Logger logger = LoggerFactory.getLogger(FormRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private FormService formService;

	@GetMapping(value = "/core/form/{id}")
	public ResponseEnvelope<FormVO> getFormById(@PathVariable Long id){
		FormModel formModel = formService.findByPrimaryKey(id);
		FormVO formVO =beanMapper.map(formModel, FormVO.class);
		ResponseEnvelope<FormVO> responseEnv = new ResponseEnvelope<>(formVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/core/form")
    public ResponseEnvelope<Page<FormModel>> listForm(FormVO formVO,Pageable pageable){

		FormModel param = beanMapper.map(formVO, FormModel.class);
        List<FormModel> formModelModels = formService.selectPage(param,pageable);
        long count=formService.selectCount(param);
        Page<FormModel> page = new PageImpl<>(formModelModels,pageable,count);
        ResponseEnvelope<Page<FormModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/core/form")
	public ResponseEnvelope<Integer> createForm(@RequestBody FormVO formVO){
		FormModel formModel = beanMapper.map(formVO, FormModel.class);
		Integer  result = formService.create(formModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/core/form/{id}")
	public ResponseEnvelope<Integer> deleteFormByPrimaryKey(@PathVariable Long id){
		Integer  result = formService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/core/form/{id}")
	public ResponseEnvelope<Integer> updateFormByPrimaryKeySelective(@PathVariable Long id,
					@RequestBody FormVO formVO){
		FormModel formModel = beanMapper.map(formVO, FormModel.class);
		formModel.setId(id);
		Integer  result = formService.updateByPrimaryKeySelective(formModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
