package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.model.TaskDetailModel;
import com.hongrui.survey.core.model.TaskModel;
import com.hongrui.survey.core.service.TaskService;
import com.hongrui.survey.core.vo.TaskVO;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class TaskRestApiController {

    private final Logger logger = LoggerFactory.getLogger(TaskRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/task/{id}")
    public ResponseEnvelope<TaskDetailModel> taskDetail(@PathVariable Long id) {
        TaskDetailModel taskDetailModel = taskService.taskDetail(id);
        ResponseEnvelope<TaskDetailModel> responseEnv = new ResponseEnvelope<>(taskDetailModel, true);
        return responseEnv;
    }

    @GetMapping(value = "/task")
    public ResponseEnvelope<List<TaskModel>> listTask(@RequestAttribute Long userId,
                                                      @RequestParam Integer status) {
        TaskModel param = new TaskModel();
        param.setSurveyorId(userId);
        param.setStatus(status);
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.DESC, "create_time");
        Page<TaskModel> page = taskService.searchPage(param, pageable);
        ResponseEnvelope<List<TaskModel>> responseEnv = new ResponseEnvelope<>(page.getContent(), true);
        return responseEnv;
    }

    @GetMapping(value = "/start/task/{id}")
    public ResponseEnvelope<String> startTask(@PathVariable Long id) {
        taskService.startTask(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


}
