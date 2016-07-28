
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.RecordModel;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RecordService{

public int create(RecordModel recordModel);

public int createSelective(RecordModel recordModel);

public RecordModel findByPrimaryKey(Long id);

public int updateByPrimaryKey(RecordModel recordModel);

public int updateByPrimaryKeySelective(RecordModel recordModel);

public int deleteByPrimaryKey(Long id);

public long selectCount(RecordModel recordModel);

public List<RecordModel> selectPage(RecordModel recordModel, Pageable pageable);

}