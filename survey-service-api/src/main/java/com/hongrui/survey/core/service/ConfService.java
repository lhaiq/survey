
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.model.TaskTypeModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConfService {

    public int create(ConfModel confModel);

    public int createSelective(ConfModel confModel);

    public ConfModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(ConfModel confModel);

    public int updateByPrimaryKeySelective(ConfModel confModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(ConfModel confModel);

    public List<ConfModel> selectPage(ConfModel confModel, Pageable pageable);

    public void createTaskType(TaskTypeModel taskTypeModel);

}