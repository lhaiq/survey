
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.TaskDetailModel;
import com.hongrui.survey.core.model.TaskModel;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    public int create(TaskModel taskModel);

    public int createSelective(TaskModel taskModel);

    public TaskModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(TaskModel taskModel);

    public int updateByPrimaryKeySelective(TaskModel taskModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(TaskModel taskModel);

    public List<TaskModel> selectPage(TaskModel taskModel, Pageable pageable);

    public Page searchPage(TaskModel taskModel,Pageable pageable);

    public void startTask(Long id);

    public TaskDetailModel taskDetail(Long id);


}