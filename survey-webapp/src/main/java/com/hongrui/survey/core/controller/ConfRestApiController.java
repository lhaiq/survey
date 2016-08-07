package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.model.TaskTypeModel;
import com.hongrui.survey.core.service.ConfService;
import com.hongrui.survey.core.vo.ConfVO;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/survey")
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

    @PostMapping(value = "/conf/{id}")
    public ResponseEnvelope<String> updateTaskType(@PathVariable Long id,
                                                   @RequestBody TaskTypeModel taskTypeModel) {
        confService.updateTaskType(id,taskTypeModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


    @DeleteMapping(value = "/conf/{id}")
    public ResponseEnvelope<Integer> deleteConfByPrimaryKey(@PathVariable Long id) {
        Integer result = confService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @GetMapping(value = "/conf/{id}")
    public ResponseEnvelope<ConfModel> findByPrimaryKey(@PathVariable Long id) {
        ConfModel confModel = confService.findByPrimaryKey(id);
        ResponseEnvelope<ConfModel> responseEnv = new ResponseEnvelope<>(confModel, true);
        return responseEnv;
    }


    @PutMapping(value = "/conf/{id}")
    public ResponseEnvelope<Integer> updateConf(@PathVariable Long id,
                                                ConfVO confVO) {
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

    @GetMapping(value = "/conf/validate/{type}")
    public void validateConf(@PathVariable Integer type,
                             @RequestParam String name,
                             HttpServletResponse response) {
        try {
            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
            ConfModel param = new ConfModel();
            param.setType(type);
            param.setName(name);
            long count = confService.selectCount(param);
            response.getWriter().write(String.valueOf(count == 0 ? true : false));
            response.getWriter().close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/photoType")
    public ResponseEnvelope<String> createPhotoType(ConfModel confModel) {
        confService.createSelective(confModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


}
