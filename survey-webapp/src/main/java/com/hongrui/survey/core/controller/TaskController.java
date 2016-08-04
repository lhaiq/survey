package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.model.TaskTypeModel;
import com.hongrui.survey.core.service.ConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.annotation.RestApiController;

import com.hongrui.survey.core.service.TaskService;
import com.hongrui.survey.core.model.TaskModel;
import com.hongrui.survey.core.vo.TaskVO;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/survey/core")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ConfService confService;

    @GetMapping(value = "/core/task/{id}")
    public ResponseEnvelope<TaskVO> getTaskById(@PathVariable Long id) {
        TaskModel taskModel = taskService.findByPrimaryKey(id);
        TaskVO taskVO = beanMapper.map(taskModel, TaskVO.class);
        ResponseEnvelope<TaskVO> responseEnv = new ResponseEnvelope<>(taskVO, true);
        return responseEnv;
    }

    @GetMapping(value = "/task/addTypeUI")
    public String addTypeUI(Model model) {
        model.addAttribute("templates", confService.selectTemplateList());
        return "task/add_task_type";
    }

    @GetMapping(value = "/task/editTypeUI/{id}")
    public String editTypeUI(@PathVariable Long id, Model model) {
        model.addAttribute("templates", confService.selectTemplateList());
        model.addAttribute("data", confService.findConfById(id));
        return "task/edit_task_type";
    }

    @GetMapping(value = "/template/addUI")
    public String addTemplateUI() {
        return "template/add_template";
    }


    @PostMapping(value = "/task/taskType")
    public ResponseEnvelope<String> createTaskType(@RequestBody TaskTypeModel taskTypeModel) {
        confService.createTaskType(taskTypeModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

    @GetMapping(value = "/task/taskType")
    public String listTaskType(Pageable pageable, Model model) {
        List<Map<String, Object>> maps = confService.selectConf(pageable);
        long count = confService.selectConfCount();
        Page<Map<String, Object>> page = new PageImpl<>(maps, pageable, count);
        model.addAttribute("data", page);
        return "task/task_type_list";
    }

    @GetMapping(value = "/task/report/{id}")
    public String taskReport(@PathVariable Long id) {
        return "task/task_report";
    }

    @PostMapping(value = "/core/task")
    public ResponseEnvelope<Integer> createTask(@RequestBody TaskVO taskVO) {
        TaskModel taskModel = beanMapper.map(taskVO, TaskModel.class);
        Integer result = taskService.create(taskModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @GetMapping(value = "/task")
    public String listTask(TaskVO taskVO, Pageable pageable, Model model) {
        TaskModel param = beanMapper.map(taskVO, TaskModel.class);
        model.addAttribute("data", taskService.searchPage(param, pageable));
        return "task/task_list";
    }

    @DeleteMapping(value = "/core/task/{id}")
    public ResponseEnvelope<Integer> deleteTaskByPrimaryKey(@PathVariable Long id) {
        Integer result = taskService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/core/task/{id}")
    public ResponseEnvelope<Integer> updateTaskByPrimaryKeySelective(@PathVariable Long id,
                                                                     @RequestBody TaskVO taskVO) {
        TaskModel taskModel = beanMapper.map(taskVO, TaskModel.class);
        taskModel.setId(id);
        Integer result = taskService.updateByPrimaryKeySelective(taskModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }

}
