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

import com.hongrui.survey.core.service.ConfService;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.vo.ConfVO;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class ConfRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ConfRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ConfService confService;

	@GetMapping(value = "/core/conf/{id}")
	public ResponseEnvelope<ConfVO> getConfById(@PathVariable Long id){
		ConfModel confModel = confService.findByPrimaryKey(id);
		ConfVO confVO =beanMapper.map(confModel, ConfVO.class);
		ResponseEnvelope<ConfVO> responseEnv = new ResponseEnvelope<>(confVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/core/conf")
    public ResponseEnvelope<Page<ConfModel>> listConf(ConfVO confVO,Pageable pageable){

		ConfModel param = beanMapper.map(confVO, ConfModel.class);
        List<ConfModel> confModelModels = confService.selectPage(param,pageable);
        long count=confService.selectCount(param);
        Page<ConfModel> page = new PageImpl<>(confModelModels,pageable,count);
        ResponseEnvelope<Page<ConfModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/core/conf")
	public ResponseEnvelope<Integer> createConf(@RequestBody ConfVO confVO){
		ConfModel confModel = beanMapper.map(confVO, ConfModel.class);
		Integer  result = confService.create(confModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
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
