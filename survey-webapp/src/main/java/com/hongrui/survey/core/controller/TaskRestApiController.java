package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.CustomerStatus;
import com.hongrui.survey.core.TaskStatus;
import com.hongrui.survey.core.model.CustomerModel;
import com.hongrui.survey.core.model.TaskDetailModel;
import com.hongrui.survey.core.model.TaskModel;
import com.hongrui.survey.core.model.TaskTypeModel;
import com.hongrui.survey.core.service.ConfService;
import com.hongrui.survey.core.service.CustomerService;
import com.hongrui.survey.core.service.TaskService;
import com.hongrui.survey.core.vo.SummaryVO;
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

import javax.ws.rs.PathParam;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/survey")
public class TaskRestApiController {

    private final Logger logger = LoggerFactory.getLogger(TaskRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ConfService confService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/task/{id}")
    public ResponseEnvelope<TaskDetailModel> taskDetail(@PathVariable Long id) {
        TaskDetailModel taskDetailModel = taskService.taskDetail(id);
        ResponseEnvelope<TaskDetailModel> responseEnv = new ResponseEnvelope<>(taskDetailModel, true);
        return responseEnv;
    }


    @PostMapping(value = "/task")
    public ResponseEnvelope<String> createTask(TaskVO taskVO) {
        TaskModel taskModel = beanMapper.map(taskVO, TaskModel.class);
        taskService.addTask(taskModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

    @PutMapping(value = "/task/{id}")
    public ResponseEnvelope<String> updateTask(@PathVariable Long id, TaskVO taskVO) {
        TaskModel taskModel = beanMapper.map(taskVO, TaskModel.class);
        taskModel.setId(id);
        taskService.updateByPrimaryKeySelective(taskModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

    @DeleteMapping(value = "/task/{id}")
    public ResponseEnvelope<String> deleteTask(@PathVariable Long id) {
        taskService.deleteByPrimaryKey(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


    @GetMapping(value = "/task")
    public ResponseEnvelope<Page<TaskModel>> listTask(@RequestAttribute Long userId,
                                                      @RequestParam(required = false) Integer status,
                                                      Pageable pageable) {
        TaskModel param = new TaskModel();
        param.setSurveyorId(userId);
        param.setStatus(status);
        Page<TaskModel> page = taskService.searchPage(param, pageable);
        ResponseEnvelope<Page<TaskModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }


    @GetMapping(value = "/start/task/{id}")
    public ResponseEnvelope<String> startTask(@PathVariable Long id) {
        taskService.startTask(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

    @PostMapping(value = "/commit/task/{id}")
    public ResponseEnvelope<String> commitTask(@PathVariable Long id,
                                               @RequestBody SummaryVO summaryVO) {
        taskService.commitTask(id, summaryVO.getComment());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

    @GetMapping(value = "/comment/task/{id}")
    public ResponseEnvelope<String> commentTask(@PathVariable Long id,
                                                @RequestParam(required = false) String comment,
                                                @RequestParam String type) {
        try {
            comment = new String(comment.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("error", e);
        }

        TaskModel param = new TaskModel();
        param.setId(id);
        param.setComment(comment);

        if ("pass".equals(type)) {
            param.setStatus(TaskStatus.SUCCESS.getCode());
        } else if ("refuse".equals(type)) {
            taskService.failureTask(param);
        } else if ("discard".equals(type)) {

            //废弃
            TaskModel taskModel = taskService.findByPrimaryKey(id);
            CustomerModel customerParam = new CustomerModel();
            customerParam.setId(taskModel.getCustomerId());
            customerParam.setStatus(CustomerStatus.UNALLOT.getCode());
            customerService.updateByPrimaryKeySelective(customerParam);
            param.setStatus(TaskStatus.DISCARD.getCode());
        }

        taskService.updateByPrimaryKeySelective(param);

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


    @PostMapping(value = "/task/status")
    public ResponseEnvelope<Map<Long, Integer>> tastStatus(@RequestBody List<Long> taskIds) {
        Map<Long, Integer> statuses = taskService.taskStatus(taskIds);
        ResponseEnvelope<Map<Long, Integer>> responseEnv = new ResponseEnvelope<>(statuses, true);
        return responseEnv;
    }


    @PostMapping(value = "/task/taskType")
    public ResponseEnvelope<String> createTaskType(@RequestBody TaskTypeModel taskTypeModel) {
        confService.createTaskType(taskTypeModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


}
