
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.SignModel;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SignService{

public int create(SignModel signModel);

public int createSelective(SignModel signModel);

public SignModel findByPrimaryKey(Long id);

public int updateByPrimaryKey(SignModel signModel);

public int updateByPrimaryKeySelective(SignModel signModel);

public int deleteByPrimaryKey(Long id);

public long selectCount(SignModel signModel);

public List<SignModel> selectPage(SignModel signModel, Pageable pageable);

}