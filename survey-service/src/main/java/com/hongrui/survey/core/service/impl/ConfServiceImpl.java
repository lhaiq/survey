package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Conf;
import com.hongrui.survey.core.repository.ConfRepository;
import com.hongrui.survey.core.model.ConfModel;
import com.hongrui.survey.core.service.ConfService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class ConfServiceImpl implements ConfService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ConfRepository confRepo;

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

	@Transactional(readOnly = true)
	@Override
	public List<ConfModel> selectPage(ConfModel confModel,Pageable pageable) {
		Conf conf = beanMapper.map(confModel, Conf.class);
		return beanMapper.mapAsList(confRepo.selectPage(conf,pageable),ConfModel.class);
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
