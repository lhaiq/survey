package com.hongrui.survey.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.TaskStatus;
import com.hongrui.survey.core.UserRole;
import com.hongrui.survey.core.entity.Task;
import com.hongrui.survey.core.model.*;
import com.hongrui.survey.core.service.*;
import org.apache.commons.lang3.StringUtils;
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

import com.hongrui.survey.core.vo.TaskVO;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/survey")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SignService signService;

    @Autowired
    private ConfService confService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/task/addTypeUI")
    public String addTypeUI(Model model) {
        model.addAttribute("templates", confService.selectConfByType(ConfType.TEMPLATE.getType()));
        model.addAttribute("photoTypes", confService.selectConfByType(ConfType.PHOTO.getType()));
        return "task/add_task_type";
    }

    @GetMapping(value = "/task/editTypeUI/{id}")
    public String editTypeUI(@PathVariable Long id, Model model) {
        model.addAttribute("templates", confService.selectConfByType(ConfType.TEMPLATE.getType()));
        model.addAttribute("photoTypes", confService.selectConfByType(ConfType.PHOTO.getType()));
        model.addAttribute("data", confService.findConfById(id));
        return "task/edit_task_type";
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
    public String taskReport(@PathVariable Long id, Model model) {
        //更新状态
        TaskModel taskModel = taskService.findByPrimaryKey(id);
        if (TaskStatus.COMMIT.getCode() == taskModel.getStatus()) {
            TaskModel param = new TaskModel();
            param.setId(id);
            param.setStatus(TaskStatus.CHECKING.getCode());
            taskService.updateByPrimaryKeySelective(param);
        }

        // task details
        TaskDetailModel td = taskService.taskDetail(id);
        model.addAttribute("td", td);

        model.addAttribute("surveyor", userService.findByPrimaryKey(td.getSurveyorId()));

        // sign
        SignModel sign = signService.getByTaskId(id);
        model.addAttribute("sign", sign);

        return "task/task_report";
    }

    @GetMapping(value = "/core/task")
    public String listTask(@SessionAttribute("user") UserModel user, TaskVO taskVO, Pageable pageable, Model model) {
        try {
            if (StringUtils.isNoneEmpty(taskVO.getCustomerName())) {
                String name = new String(taskVO.getCustomerName().getBytes("ISO-8859-1"), "utf-8");
                taskVO.setCustomerName(name);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Long syndicId = null;
        if (user.getRole() == UserRole.SYNDIC.getCode()) {
            syndicId = user.getId();
        }
        TaskModel param = beanMapper.map(taskVO, TaskModel.class);
        model.addAttribute("data", taskService.searchPage(param, syndicId, pageable));
        return "task/task_list";
    }

    @GetMapping(value = "/addTaskUI/{customerId}")
    public String addTaskUI(@PathVariable Long customerId, Model model) {
        ConfModel confParam = new ConfModel();
        confParam.setType(ConfType.SURVEY.getType());
        model.addAttribute("types", confService.selectPage(confParam, new PageRequest(0, Integer.MAX_VALUE)));

        UserModel userParam = new UserModel();
        userParam.setRole(UserRole.SURVEYOR.getCode());
        model.addAttribute("surveyors", userService.selectPage(userParam, new PageRequest(0, Integer.MAX_VALUE)));

        model.addAttribute("customer", customerService.findByPrimaryKey(customerId));
        return "task/add_task";
    }

    @GetMapping(value = "/reallocateTaskUI/{customerId}")
    public String reallocateTaskUI(@PathVariable Long customerId, Model model) {
        ConfModel confParam = new ConfModel();
        confParam.setType(ConfType.SURVEY.getType());
        model.addAttribute("types", confService.selectPage(confParam, new PageRequest(0, Integer.MAX_VALUE)));

        UserModel userParam = new UserModel();
        userParam.setRole(UserRole.SURVEYOR.getCode());
        model.addAttribute("surveyors", userService.selectPage(userParam, new PageRequest(0, Integer.MAX_VALUE)));

        model.addAttribute("customer", customerService.findByPrimaryKey(customerId));
        return "task/reallocate_task";
    }

    @GetMapping(value = "/editTaskUI/{id}")
    public String editTaskUI(@PathVariable Long id, Model model) {
        ConfModel confParam = new ConfModel();
        confParam.setType(ConfType.SURVEY.getType());
        model.addAttribute("types", confService.selectPage(confParam, new PageRequest(0, Integer.MAX_VALUE)));

        UserModel userParam = new UserModel();
        userParam.setRole(UserRole.SURVEYOR.getCode());
        model.addAttribute("surveyors", userService.selectPage(userParam, new PageRequest(0, Integer.MAX_VALUE)));

        TaskModel taskModel = taskService.findByPrimaryKey(id);
        model.addAttribute("task", taskModel);

        model.addAttribute("customer", customerService.findByPrimaryKey(taskModel.getCustomerId()));
        return "task/edit_task";
    }







    @GetMapping(value = "/taskExtInfo/{taskId}")
    public String taskExtInfoUI(@PathVariable Long taskId, Model model) {
        TaskModel task=taskService.findByPrimaryKey(taskId);


        JSONObject extInfo = JSON.parseObject(task.getExtInfo());;

        model.addAttribute("extInfo", extInfo    );
         return "task/task_ext_info";
    }

}
