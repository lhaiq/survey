package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.UserRole;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.model.TaskTypeModel;
import com.hongrui.survey.core.model.UserModel;
import com.hongrui.survey.core.service.ConfService;
import com.hongrui.survey.core.service.CustomerService;
import com.hongrui.survey.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

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


    @GetMapping(value = "/task")
    public String listTask(TaskVO taskVO, Pageable pageable, Model model) {
        TaskModel param = beanMapper.map(taskVO, TaskModel.class);
        model.addAttribute("data", taskService.searchPage(param, pageable));
        return "task/task_list";
    }

    @GetMapping(value = "/task/template/{id}")
    public String taskTemplate(@PathVariable Long id, Model model) {
        model.addAttribute("data", taskService.taskTemplate(id));
        return "task/template";
    }


    @GetMapping(value = "/addTaskUI/{id}")
    public String addTaskUI(@PathVariable Long id,Model model) {
        ConfModel confParam = new ConfModel();
        confParam.setType(ConfType.SURVEY.getType());
        model.addAttribute("types",confService.selectPage(confParam,new PageRequest(0,Integer.MAX_VALUE)));

        UserModel userParam = new UserModel();
        userParam.setRole(UserRole.SURVEYOR.getCode());
        model.addAttribute("surveyors", userService.selectPage(userParam, new PageRequest(0, Integer.MAX_VALUE)));

        model.addAttribute("customer",customerService.findByPrimaryKey(id));
        return "task/add_task";
    }

}
