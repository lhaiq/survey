package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Task;
import com.hongrui.survey.core.repository.TaskRepository;
import com.hongrui.survey.core.model.TaskModel;
import com.hongrui.survey.core.service.TaskService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public List<TaskModel> selectPage(TaskModel taskModel, Pageable pageable) {
        Task task = beanMapper.map(taskModel, Task.class);
        return beanMapper.mapAsList(taskRepo.selectPage(task, pageable), TaskModel.class);
    }

    @Override
    public Page searchPage(TaskModel taskModel, Pageable pageable) {
        String sql = "select t.*,u.account,c.name from task t,user u,customer c\n" +
                "where t.customer_id=c.id\n" +
                "and t.surveyor_id=u.id\n" +
                "order by t.create_time desc\n" +
                "limit ?,?";

        String countSql = "SELECT count(1)  from task t,user u,customer c\n" +
                "where t.customer_id=c.id\n" +
                "and t.surveyor_id=u.id\n";
        List<Map<String, Object>> content = jdbcTemplate.queryForList(sql, pageable.getPageNumber(), pageable.getPageSize());
        Long count = jdbcTemplate.queryForObject(countSql, Long.class);
        Page page = new PageImpl(content, pageable, count);
        return page;
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
