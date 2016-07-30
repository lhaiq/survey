package com.hongrui.survey.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hongrui.survey.core.HRErrorCode;
import com.hongrui.survey.core.TaskStatus;
import com.hongrui.survey.core.model.*;
import com.hongrui.survey.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Task;
import com.hongrui.survey.core.repository.TaskRepository;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AudioService audioService;

    @Autowired
    private PhotoService photoService;

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
        String sql = "select t.id,t.start_time as startTime,t.end_time as endTime,t.comment,t.point,t.status," +
                "u.account,c.name as customerName,c.id as customerId," +
                "c.company,c.address,c.id_card as idCard from task t,user u,customer c\n" +
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
    public void startTask(Long id) {
        TaskModel taskModel = findByPrimaryKey(id);
        if (TaskStatus.CREATED.getCode() != taskModel.getStatus()) {
            HRErrorCode.throwBusinessException(HRErrorCode.TASK_STATUS_INCORRECT);
        }

        TaskModel param = new TaskModel();
        param.setId(id);
        param.setStartTime(new Date());
        param.setStatus(TaskStatus.STARTED.getCode());
        updateByPrimaryKeySelective(param);
    }

    @Override
    public TaskDetailModel taskDetail(Long id) {
        TaskModel taskModel = findByPrimaryKey(id);
        TaskDetailModel taskDetailModel = beanMapper.map(taskModel, TaskDetailModel.class);

        //reports
        ReportModel reportParam = new ReportModel();
        reportParam.setTaskId(id);
        List<ReportModel> reports = reportService.selectPage(reportParam, new PageRequest(0, Integer.MAX_VALUE));
        taskDetailModel.setReports(reports);

        //audios
        AudioModel audioParam = new AudioModel();
        audioParam.setTaskId(id);
        List<AudioModel> audios = audioService.selectPage(audioParam, new PageRequest(0, Integer.MAX_VALUE));
        taskDetailModel.setAudios(audios);

        //photos
        taskDetailModel.setPhotos(findPhotos(id, taskModel.getType()));

        return taskDetailModel;
    }


    private Map<String, List<PhotoModel>> findPhotos(Long taskId, String type) {
        String sql = "SELECT photo_type from conf where name = ? and type = 0";
        String photoTypes = jdbcTemplate.queryForObject(sql, String.class, type);
        JSONArray jsonArray = JSON.parseArray(photoTypes);

        Map<String, List<PhotoModel>> photos = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Long photoTypeId = jsonArray.getLong(i);
            String name = jdbcTemplate.queryForObject("select name from conf where id = ?", String.class, photoTypeId);
            PhotoModel photoParam = new PhotoModel();
            photoParam.setTaskId(taskId);
            photoParam.setPhotoType(photoTypeId);
            List<PhotoModel> photoModels = photoService.selectPage(photoParam, new PageRequest(0, Integer.MAX_VALUE));
            photos.put(name, photoModels);
        }

        return photos;

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
