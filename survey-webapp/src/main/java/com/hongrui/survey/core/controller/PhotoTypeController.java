package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.service.ConfService;
import com.hongrui.survey.core.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by haiquanli on 16/7/18.
 */
@Controller
@RequestMapping("/survey")
public class PhotoTypeController {

    private final Logger logger = LoggerFactory.getLogger(ConfRestApiController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private ConfService confService;


    @GetMapping(value = "/photoType/addUI")
    public String addPhotoTypeUI() {
        return "photo_type/add_photo_type";
    }


    @GetMapping(value = "/photoType/editUI/{id}")
    public String editUI(@PathVariable Long id, Model model) {
        model.addAttribute("data", confService.findByPrimaryKey(id));
        return "photo_type/edit_photo_type";
    }

    @GetMapping(value = "/photoType")
    public String selectPhotoType(Pageable pageable, Model model) {
        ConfModel param = new ConfModel();
        param.setType(ConfType.PHOTO.getType());
        List<ConfModel> content = confService.selectPage(param, pageable);
        long count = confService.selectCount(param);
        Page<ConfModel> page = new PageImpl<>(content, pageable, count);
        model.addAttribute("data", page);
        return "photo_type/photo_type_list";
    }

}
