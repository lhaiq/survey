package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Task;
import com.hongrui.survey.core.repository.TaskRepository;
import com.hongrui.survey.core.model.TaskModel;
import com.hongrui.survey.core.service.TaskService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private TaskRepository taskRepo;

	@Transactional
	@Override
	public int create(TaskModel taskModel) {
		return taskRepo.insert(beanMapper.map(taskModel, Task.class));
	}

	@Transactional
	@Override
	public int createSelective(TaskModel taskModel) {
		return taskRepo.insertSelective(beanMapper.map(taskModel, Task.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return taskRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public TaskModel findByPrimaryKey(Long id) {
		Task task = taskRepo.selectByPrimaryKey(id);
		return beanMapper.map(task, TaskModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(TaskModel taskModel) {
		return taskRepo.selectCount(beanMapper.map(taskModel, Task.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<TaskModel> selectPage(TaskModel taskModel,Pageable pageable) {
		Task task = beanMapper.map(taskModel, Task.class);
		return beanMapper.mapAsList(taskRepo.selectPage(task,pageable),TaskModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(TaskModel taskModel) {
		return taskRepo.updateByPrimaryKey(beanMapper.map(taskModel, Task.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(TaskModel taskModel) {
		return taskRepo.updateByPrimaryKeySelective(beanMapper.map(taskModel, Task.class));
	}

}
