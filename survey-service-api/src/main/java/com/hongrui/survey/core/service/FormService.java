
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.FormModel;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FormService{

public int create(FormModel formModel);

public int createSelective(FormModel formModel);

public FormModel findByPrimaryKey(Long id);

public int updateByPrimaryKey(FormModel formModel);

public int updateByPrimaryKeySelective(FormModel formModel);

public int deleteByPrimaryKey(Long id);

public long selectCount(FormModel formModel);

public List<FormModel> selectPage(FormModel formModel, Pageable pageable);

}