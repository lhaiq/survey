package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.model.TaskTypeModel;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/survey/")
public class ConfRestApiController {

    private final Logger logger = LoggerFactory.getLogger(ConfRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ConfService confService;

    @GetMapping(value = "/conf")
    public ResponseEnvelope<Page<Map<String, Object>>> selectConf(Pageable pageable) {
        List<Map<String, Object>> maps = confService.selectConf(pageable);
        long count = confService.selectConfCount();
        Page<Map<String, Object>> page = new PageImpl<>(maps, pageable, count);
        ResponseEnvelope<Page<Map<String, Object>>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }


    @PostMapping(value = "/conf")
    public ResponseEnvelope<String> createTaskType(@RequestBody TaskTypeModel taskTypeModel) {
        confService.createTaskType(taskTypeModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

    @DeleteMapping(value = "/core/conf/{id}")
    public ResponseEnvelope<Integer> deleteConfByPrimaryKey(@PathVariable Long id) {
        Integer result = confService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/core/conf/{id}")
    public ResponseEnvelope<Integer> updateConfByPrimaryKeySelective(@PathVariable Long id,
                                                                     @RequestBody ConfVO confVO) {
        ConfModel confModel = beanMapper.map(confVO, ConfModel.class);
        confModel.setId(id);
        Integer result = confService.updateByPrimaryKeySelective(confModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @PostMapping(value = "/template")
    public ResponseEnvelope<String> createTemplate(@RequestParam("file") CommonsMultipartFile file,
                                                   @RequestParam("name") String name) {
        ConfModel confModel = new ConfModel();
        confModel.setName(name);
        String content = "";
        try {
            content = IOUtils.toString(file.getInputStream());
        } catch (IOException e) {
            logger.error("unexpected error", e);
        }
        confModel.setContent(content);

        confModel.setType(ConfType.TEMPLATE.getType());
        confService.createSelective(confModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


    @GetMapping(value = "/template")
    public ResponseEnvelope<Page<ConfModel>> selectTemplate(Pageable pageable) {
        ConfModel param = new ConfModel();
        param.setType(ConfType.TEMPLATE.getType());
        List<ConfModel> content = confService.selectPage(param, pageable);
        long count = confService.selectCount(param);
        Page<ConfModel> page = new PageImpl<>(content, pageable, count);
        ResponseEnvelope<Page<ConfModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }

}
