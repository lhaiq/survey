package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.annotation.IgnoreAuth;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.service.ConfService;
import com.hongrui.survey.core.service.TaskService;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by haiquanli on 16/7/18.
 */
@Controller
@RequestMapping("/survey")
public class TemplateController {

    private final Logger logger = LoggerFactory.getLogger(ConfRestApiController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private ConfService confService;


    @GetMapping(value = "/template/addUI")
    public String addTemplateUI() {
        return "template/add_template";
    }

    @IgnoreAuth
    @GetMapping(value = "/core/task/template/{id}")
    public String taskTemplate(@PathVariable Long id, Model model) {
        model.addAttribute("data", taskService.taskTemplate(id));
        return "task/template";
    }


    @GetMapping(value = "/template")
    public String selectTemplate(Pageable pageable,Model model) {
        ConfModel param = new ConfModel();
        param.setType(ConfType.TEMPLATE.getType());
        List<ConfModel> content = confService.selectPage(param, pageable);
        long count = confService.selectCount(param);
        Page<ConfModel> page = new PageImpl<>(content, pageable, count);
        model.addAttribute("data",page);
        return "template/template_list";
    }

}
