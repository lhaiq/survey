package com.hongrui.survey.core.controller;

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

@Controller
@RequestMapping("/survey")
public class TaskController {

	private final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private TaskService taskService;

	@GetMapping(value = "/core/task/{id}")
	public ResponseEnvelope<TaskVO> getTaskById(@PathVariable Long id){
		TaskModel taskModel = taskService.findByPrimaryKey(id);
		TaskVO taskVO =beanMapper.map(taskModel, TaskVO.class);
		ResponseEnvelope<TaskVO> responseEnv = new ResponseEnvelope<>(taskVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/task")
    public String listTask(TaskVO taskVO,Pageable pageable,Model model){
		TaskModel param = beanMapper.map(taskVO, TaskModel.class);
        List<TaskModel> taskModelModels = taskService.selectPage(param,pageable);
        long count=taskService.selectCount(param);
        Page<TaskModel> page = new PageImpl<>(taskModelModels,pageable,count);
		model.addAttribute("data",page);
        return "task_list";
    }

	@PostMapping(value = "/core/task")
	public ResponseEnvelope<Integer> createTask(@RequestBody TaskVO taskVO){
		TaskModel taskModel = beanMapper.map(taskVO, TaskModel.class);
		Integer  result = taskService.create(taskModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/core/task/{id}")
	public ResponseEnvelope<Integer> deleteTaskByPrimaryKey(@PathVariable Long id){
		Integer  result = taskService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/core/task/{id}")
	public ResponseEnvelope<Integer> updateTaskByPrimaryKeySelective(@PathVariable Long id,
					@RequestBody TaskVO taskVO){
		TaskModel taskModel = beanMapper.map(taskVO, TaskModel.class);
		taskModel.setId(id);
		Integer  result = taskService.updateByPrimaryKeySelective(taskModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
