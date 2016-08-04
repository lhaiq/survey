package com.hongrui.survey.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hongrui.survey.core.ConfType;
import com.hongrui.survey.core.model.TaskTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Conf;
import com.hongrui.survey.core.repository.ConfRepository;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.service.ConfService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ConfServiceImpl implements ConfService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ConfRepository confRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int create(ConfModel confModel) {
        return confRepo.insert(beanMapper.map(confModel, Conf.class));
    }

    @Transactional
    @Override
    public int createSelective(ConfModel confModel) {
        return confRepo.insertSelective(beanMapper.map(confModel, Conf.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return confRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ConfModel findByPrimaryKey(Long id) {
        Conf conf = confRepo.selectByPrimaryKey(id);
        return beanMapper.map(conf, ConfModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(ConfModel confModel) {
        return confRepo.selectCount(beanMapper.map(confModel, Conf.class));
    }

    @Override
    public long selectConfCount() {
        return 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConfModel> selectPage(ConfModel confModel, Pageable pageable) {
        Conf conf = beanMapper.map(confModel, Conf.class);
        return beanMapper.mapAsList(confRepo.selectPage(conf, pageable), ConfModel.class);
    }


    public List<Map<String, Object>> selectConf(Pageable pageable) {
        String sql = "select * from conf where type = 0 order by id limit ?,?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, pageable.getOffset(), pageable.getPageSize());
        for (Map<String, Object> map : maps) {
            List<Long> photoTypeIds = JSON.parseArray(map.get("photo_type").toString(), Long.class);
            List<Long> templateIds = JSON.parseArray(map.get("template").toString(), Long.class);
            String photoTypeSql = "select * from conf where id in (" + StringUtils.collectionToCommaDelimitedString(photoTypeIds) + ")";
            String templateSql = "select * from conf where id in (" + StringUtils.collectionToCommaDelimitedString(photoTypeIds) + ")";
            map.put("photos", jdbcTemplate.queryForList(photoTypeSql));
            map.put("templates", jdbcTemplate.queryForList(templateSql));
        }

        return maps;

    }

    @Override
    public Map<String, Object> findConfById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void createTaskType(TaskTypeModel taskTypeModel) {

        //photo
        List<String> photoTypes = taskTypeModel.getPhotoTypes();
        List<String> pixels = taskTypeModel.getPixels();
        List<Long> photoTypeIds = new ArrayList<>();

        for (int i = 0; i < photoTypes.size(); i++) {
            ConfModel confModel = new ConfModel();
            confModel.setName(photoTypes.get(i));
            confModel.setPixel(pixels.get(i));
            confModel.setType(ConfType.PHOTO.getType());
            createSelective(confModel);
            photoTypeIds.add(confModel.getId());
        }

        //template
        List<String> templateNames = taskTypeModel.getTemplateNames();
        List<String> templateContents = taskTypeModel.getTemplateContents();
        List<Long> templateIds = new ArrayList<>();

        for (int i = 0; i < templateNames.size(); i++) {
            ConfModel confModel = new ConfModel();
            confModel.setName(templateNames.get(i));
            confModel.setContent(templateContents.get(i));
            confModel.setType(ConfType.TEMPLATE.getType());
            createSelective(confModel);
            templateIds.add(confModel.getId());
        }


        ConfModel confModel = new ConfModel();
        confModel.setName(taskTypeModel.getName().get(0));
        confModel.setPhotoType(JSON.toJSONString(photoTypeIds));
        confModel.setTemplate(JSON.toJSONString(templateIds));
        confModel.setType(ConfType.SURVEY.getType());
        createSelective(confModel);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(ConfModel confModel) {
        return confRepo.updateByPrimaryKey(beanMapper.map(confModel, Conf.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ConfModel confModel) {
        return confRepo.updateByPrimaryKeySelective(beanMapper.map(confModel, Conf.class));
    }

}
