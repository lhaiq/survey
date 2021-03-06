package com.hongrui.survey.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hongrui.survey.core.CustomerStatus;
import com.hongrui.survey.core.HRErrorCode;
import com.hongrui.survey.core.TaskStatus;
import com.hongrui.survey.core.model.*;
import com.hongrui.survey.core.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import java.util.*;

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
    private CustomerService customerService;

    @Autowired
    private SignService signService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

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
    public Page searchPage(TaskModel taskModel, Long syndicId, Pageable pageable) {
        StringBuffer sb = new StringBuffer();
        sb.append("select t.id,t.create_time as createTime,t.check_time as checkTime,t.start_time as startTime,t.end_time as endTime,t.comment,t.summary,c.point,t.status,t.type," +
                "u.account,u.nick_name as nickName,c.name as customerName,c.id as customerId," +
                "c.company,c.address,c.id_card as idCard ,c.mobile_number as mobileNumber,c.telephone_number as telephoneNumber,s.account as syndicName \n" +
                " from task t,user u,customer c,user s\n" +
                "where t.customer_id=c.id\n" +
                "and t.surveyor_id=u.id\n" +
                "and c.syndic_id=s.id \n");

        StringBuffer countSql = new StringBuffer();
        countSql.append("SELECT count(1)  from task t,user u,customer c\n" +
                "where t.customer_id=c.id\n" +
                "and t.surveyor_id=u.id\n");

        if (null != taskModel.getStatus()) {
            sb.append("and t.status = ?\n");
            countSql.append("and t.status = ?\n");
        } else {
            sb.append("and t.status != 6\n");
            countSql.append("and t.status != 6\n");
        }


        if (StringUtils.isNoneEmpty(taskModel.getCustomerName())) {
            String customerName = taskModel.getCustomerName();
            sb.append(" and  c.name like '%" + customerName + "%' ");
            countSql.append(" and  c.name like '%" + customerName + "%' ");
        }


        if (null != taskModel.getSurveyorId()) {
            sb.append("and t.surveyor_id = ?\n");
            countSql.append("and t.surveyor_id = ?\n");
        }

        if (null != syndicId) {
            sb.append("and s.id= ?\n");
            countSql.append("and c.syndic_id= ?\n");
        }

        sb.append("order by t.id desc\n" +
                "limit ?,?");


        List<Object> pageParam = new ArrayList<>();
        List<Object> countParam = new ArrayList<>();
        if (null != taskModel.getStatus()) {
            pageParam.add(taskModel.getStatus());
            countParam.add(taskModel.getStatus());
        }

        if (null != taskModel.getSurveyorId()) {
            pageParam.add(taskModel.getSurveyorId());
            countParam.add(taskModel.getSurveyorId());
        }

        if (null != syndicId) {
            pageParam.add(syndicId);
            countParam.add(syndicId);
        }

        pageParam.add(pageable.getOffset());
        pageParam.add(pageable.getPageSize());

        List<Map<String, Object>> content = jdbcTemplate.queryForList(sb.toString(), pageParam.toArray());
        Long count = jdbcTemplate.queryForObject(countSql.toString(), Long.class, countParam.toArray());
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
    public void commitTask(Long id, String summary) {
        TaskModel taskModel = findByPrimaryKey(id);
        if (TaskStatus.STARTED.getCode() != taskModel.getStatus() &&
                TaskStatus.COMMIT.getCode() != taskModel.getStatus()) {
            HRErrorCode.throwBusinessException(HRErrorCode.TASK_STATUS_INCORRECT);
        }

        TaskModel param = new TaskModel();
        param.setId(id);
        param.setEndTime(new Date());
        param.setStatus(TaskStatus.COMMIT.getCode());
        param.setSummary(summary);
        updateByPrimaryKeySelective(param);
    }

    @Transactional
    @Override
    public void addTask(TaskModel taskModel) {
        //taskModel.setStatus(TaskStatus.CREATED.getCode());

        //更改客户状态
        CustomerModel param = new CustomerModel();
        param.setId(taskModel.getCustomerId());
        param.setStatus(CustomerStatus.ALLOT.getCode());
        customerService.updateByPrimaryKeySelective(param);

        taskModel.setCreateTime(new Date());
        createSelective(taskModel);
    }




    @Override
    public TaskDetailModel taskDetail(Long id) {
        TaskModel taskModel = findByPrimaryKey(id);
        TaskDetailModel taskDetailModel = beanMapper.map(taskModel, TaskDetailModel.class);


        //
        if( StringUtils.isNotBlank( taskModel.getExtInfo() )   ){
            taskDetailModel.setExtUrl("/survey/task/extinfo/"+id);
        }



        //reports
        taskDetailModel.setReports(findReportsNoTemplate(id, taskModel.getType()));

        //audios
        AudioModel audioParam = new AudioModel();
        audioParam.setTaskId(id);
        List<AudioModel> audios = audioService.selectPage(audioParam, new PageRequest(0, Integer.MAX_VALUE));
        taskDetailModel.setAudios(audios);

        //customer
        CustomerModel customerModel = customerService.findByPrimaryKey(taskModel.getCustomerId());
        UserModel userModel = userService.findByPrimaryKey(customerModel.getSyndicId());
        customerModel.setSyndicName(userModel.getAccount());
        taskDetailModel.setCustomer(customerModel);

        //photos
        taskDetailModel.setPhotos(findPhotos(id, taskModel.getType()));

        //sign

        taskDetailModel.setSign(signService.getByTaskId(id));


        //



        return taskDetailModel;
    }

    @Override
    public Map<Long, Integer> taskStatus(List<Long> taskIds) {
        String sql = "select status from task where id = ?";
        Map<Long, Integer> statuses = new HashMap<>();
        for (Long taskId : taskIds) {
            Integer status = queryForObject(sql, Integer.class, taskId);
            if(null==status){
                continue;
            }
            statuses.put(taskId, status);
        }
        return statuses;
    }

    @Override
    public Map<String, ReportConfModel> taskTemplate(Long id) {
        TaskModel taskModel = findByPrimaryKey(id);
        return findReports(id, taskModel.getType());
    }

    @Override
    public void checkCanEdit(Long id) {
        TaskModel taskModel = findByPrimaryKey(id);
        int status = taskModel.getStatus();
        if (TaskStatus.CREATED.getCode() != status && TaskStatus.COMMIT.getCode() != status
                && TaskStatus.STARTED.getCode() != status && TaskStatus.FAILURE.getCode() != status) {
            HRErrorCode.throwBusinessException(HRErrorCode.TASK_STATUS_INCORRECT);
        }
    }

    @Transactional
    @Override
    public void failureTask(TaskModel taskModel) {

        //失败
        taskModel.setStatus(TaskStatus.FAILURE.getCode());
        updateByPrimaryKeySelective(taskModel);

        //删除位置
        String sql = "delete from sign where task_id = ?";
        jdbcTemplate.update(sql, taskModel.getId());

    }

    @Override
    public void discardTask(TaskModel taskModel) {

    }


    private Map<String, PhotoConfModel> findPhotos(Long taskId, String type) {
        String sql = "SELECT photo_type from conf where name = ? and type = 0";
        String photoTypes = jdbcTemplate.queryForObject(sql, String.class, type);
        JSONArray jsonArray = JSON.parseArray(photoTypes);

        Map<String, PhotoConfModel> photos = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Long photoTypeId = jsonArray.getLong(i);
            String name = queryForObject("select name from conf where id = ?", String.class, photoTypeId);
            if(null==name){
                continue;
            }
            String pixel = jdbcTemplate.queryForObject("select pixel from conf where id = ?", String.class, photoTypeId);
            PhotoConfModel photoConfModel = new PhotoConfModel();
            photoConfModel.setPixel(pixel);
            photoConfModel.setPhotoType(photoTypeId);
            PhotoModel photoParam = new PhotoModel();
            photoParam.setTaskId(taskId);
            photoParam.setPhotoType(photoTypeId);
            List<PhotoModel> photoModels = photoService.selectPage(photoParam, new PageRequest(0, Integer.MAX_VALUE));
            photoConfModel.setContents(photoModels);
            photos.put(name, photoConfModel);
        }

        return photos;
    }

    private Map<String, ReportConfModel> findReportsNoTemplate(Long taskId, String type) {
        String sql = "SELECT template from conf where name = ? and type = 0";
        Map<String, ReportConfModel> reports = new HashMap<>();
        String templateIds = queryForObject(sql, String.class, type);
        if(null==templateIds){
            return reports;
        }
        JSONArray jsonArray = JSON.parseArray(templateIds);

        for (int i = 0; i < jsonArray.size(); i++) {
            Long templateId = jsonArray.getLong(i);
            String name = queryForObject("select name from conf where id = ?", String.class, templateId);
            if(null==name)continue;;
            ReportConfModel reportConfModel = new ReportConfModel();
            reportConfModel.setTemplateId(templateId);

            ReportModel reportParam = new ReportModel();
            reportParam.setTaskId(taskId);
            reportParam.setTemplateId(templateId);
            List<ReportModel> reportModels = reportService.selectPage(reportParam, new PageRequest(0, Integer.MAX_VALUE));
            if (!CollectionUtils.isEmpty(reportModels)) {
                reportConfModel.setContent(reportModels.get(0));
            }
            reports.put(name, reportConfModel);
        }

        return reports;
    }

    private Map<String, ReportConfModel> findReports(Long taskId, String type) {
        Map<String, ReportConfModel> reports = new HashMap<>();
        String sql = "SELECT template from conf where name = ? and type = 0";
        String templateIds = queryForObject(sql, String.class, type);
        if(null==templateIds){
            return reports;
        }

        JSONArray jsonArray = JSON.parseArray(templateIds);

        for (int i = 0; i < jsonArray.size(); i++) {
            Long templateId = jsonArray.getLong(i);
            String name = queryForObject("select name from conf where id = ?", String.class, templateId);
            if(null==name){
                continue;
            }
            String template = jdbcTemplate.queryForObject("select content from conf where id = ?", String.class, templateId);
            template = template.replace("templateId", "templateId-" + templateId);
            ReportConfModel reportConfModel = new ReportConfModel();
            reportConfModel.setTemplate(template);
            reportConfModel.setTemplateId(templateId);

            ReportModel reportParam = new ReportModel();
            reportParam.setTaskId(taskId);
            reportParam.setTemplateId(templateId);
            List<ReportModel> reportModels = reportService.selectPage(reportParam, new PageRequest(0, Integer.MAX_VALUE));
            if (!CollectionUtils.isEmpty(reportModels)) {
                reportConfModel.setContent(reportModels.get(0));
            }
            reports.put(name, reportConfModel);
        }

        return reports;
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


    private <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {
        T t = null;
        try {
            t = jdbcTemplate.queryForObject(sql, requiredType, args);
        } catch (DataAccessException e) {

        }
        return t;
    }
}
