package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Record;
import com.hongrui.survey.core.repository.RecordRepository;
import com.hongrui.survey.core.model.RecordModel;
import com.hongrui.survey.core.service.RecordService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private RecordRepository recordRepo;

	@Transactional
	@Override
	public int create(RecordModel recordModel) {
		return recordRepo.insert(beanMapper.map(recordModel, Record.class));
	}

	@Transactional
	@Override
	public int createSelective(RecordModel recordModel) {
		return recordRepo.insertSelective(beanMapper.map(recordModel, Record.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return recordRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public RecordModel findByPrimaryKey(Long id) {
		Record record = recordRepo.selectByPrimaryKey(id);
		return beanMapper.map(record, RecordModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(RecordModel recordModel) {
		return recordRepo.selectCount(beanMapper.map(recordModel, Record.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<RecordModel> selectPage(RecordModel recordModel,Pageable pageable) {
		Record record = beanMapper.map(recordModel, Record.class);
		return beanMapper.mapAsList(recordRepo.selectPage(record,pageable),RecordModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(RecordModel recordModel) {
		return recordRepo.updateByPrimaryKey(beanMapper.map(recordModel, Record.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(RecordModel recordModel) {
		return recordRepo.updateByPrimaryKeySelective(beanMapper.map(recordModel, Record.class));
	}

}
