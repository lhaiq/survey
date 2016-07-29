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

import com.hongrui.survey.core.service.ReportService;
import com.hongrui.survey.core.model.ReportModel;
import com.hongrui.survey.core.vo.ReportVO;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class ReportRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ReportRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ReportService reportService;

	@GetMapping(value = "/core/report/{id}")
	public ResponseEnvelope<ReportVO> getReportById(@PathVariable Long id){
		ReportModel reportModel = reportService.findByPrimaryKey(id);
		ReportVO reportVO =beanMapper.map(reportModel, ReportVO.class);
		ResponseEnvelope<ReportVO> responseEnv = new ResponseEnvelope<>(reportVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/core/report")
    public ResponseEnvelope<Page<ReportModel>> listReport(ReportVO reportVO,Pageable pageable){

		ReportModel param = beanMapper.map(reportVO, ReportModel.class);
        List<ReportModel> reportModelModels = reportService.selectPage(param,pageable);
        long count=reportService.selectCount(param);
        Page<ReportModel> page = new PageImpl<>(reportModelModels,pageable,count);
        ResponseEnvelope<Page<ReportModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/core/report")
	public ResponseEnvelope<Integer> createReport(@RequestBody ReportVO reportVO){
		ReportModel reportModel = beanMapper.map(reportVO, ReportModel.class);
		Integer  result = reportService.create(reportModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/core/report/{id}")
	public ResponseEnvelope<Integer> deleteReportByPrimaryKey(@PathVariable Long id){
		Integer  result = reportService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/core/report/{id}")
	public ResponseEnvelope<Integer> updateReportByPrimaryKeySelective(@PathVariable Long id,
					@RequestBody ReportVO reportVO){
		ReportModel reportModel = beanMapper.map(reportVO, ReportModel.class);
		reportModel.setId(id);
		Integer  result = reportService.updateByPrimaryKeySelective(reportModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
