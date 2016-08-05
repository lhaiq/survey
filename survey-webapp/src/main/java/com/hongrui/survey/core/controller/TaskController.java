package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.UserRole;
import com.hongrui.survey.core.model.*;
import com.hongrui.survey.core.service.*;
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

		// task details
		TaskDetailModel td = taskService.taskDetail(id);
		model.addAttribute("td", td);

		// sign
		SignModel sign = signService.getByTaskId(id);
		model.addAttribute("sign", sign);

		// photo
		// Map<Long, List<PhotoModel>> pt = photoService.getPhotoAndTypeByTaskId(id);
		// model.addAttribute("pt", pt);

		// reports

		// model.addAttribute("fms", fms);

		return "task/task_report";
	}

	@GetMapping(value = "/core/task")
	public String listTask(TaskVO taskVO, Pageable pageable, Model model) {
		TaskModel param = beanMapper.map(taskVO, TaskModel.class);
		model.addAttribute("data", taskService.searchPage(param, pageable));
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

	@GetMapping(value = "/core/task/template/{id}")
	public String taskTemplate(@PathVariable Long id, Model model) {
		model.addAttribute("data", taskService.taskTemplate(id));
		return "task/task_list";
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

}
