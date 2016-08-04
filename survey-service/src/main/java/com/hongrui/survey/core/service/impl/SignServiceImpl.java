package com.hongrui.survey.core.service.impl;

import com.hongrui.survey.core.HRErrorCode;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hongrui.survey.core.entity.Sign;
import com.hongrui.survey.core.repository.SignRepository;
import com.hongrui.survey.core.model.SignModel;
import com.hongrui.survey.core.service.SignService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SignRepository signRepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public int create(SignModel signModel) {
		return signRepo.insert(beanMapper.map(signModel, Sign.class));
	}

	@Transactional
	@Override
	public int createSelective(SignModel signModel) {
		return signRepo.insertSelective(beanMapper.map(signModel, Sign.class));
	}

	@Transactional
	@Override
	public void createSign(SignModel signModel) {

		SignModel param = new SignModel();
		param.setTaskId(signModel.getTaskId());
		List<SignModel> signModels = selectPage(param, new PageRequest(0, Integer.MAX_VALUE));
		if (!CollectionUtils.isEmpty(signModels)) {
			HRErrorCode.throwBusinessException(HRErrorCode.HAVE_SIGNED);
		}

		signModel.setCreateTime(new Date());
		createSelective(signModel);
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return signRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SignModel findByPrimaryKey(Long id) {
		Sign sign = signRepo.selectByPrimaryKey(id);
		return beanMapper.map(sign, SignModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(SignModel signModel) {
		return signRepo.selectCount(beanMapper.map(signModel, Sign.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<SignModel> selectPage(SignModel signModel, Pageable pageable) {
		Sign sign = beanMapper.map(signModel, Sign.class);
		return beanMapper.mapAsList(signRepo.selectPage(sign, pageable), SignModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SignModel signModel) {
		return signRepo.updateByPrimaryKey(beanMapper.map(signModel, Sign.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SignModel signModel) {
		return signRepo.updateByPrimaryKeySelective(beanMapper.map(signModel, Sign.class));
	}

	@Override
	public SignModel findByTaskId(Long id) {

		Sign sign = new Sign();
		sign.setTaskId(id);

		Pageable pageable = new PageRequest(0, 10);

		Sign sign2 = signRepo.selectPage(sign, pageable).get(0);

		SignModel sm = beanMapper.map(sign2, SignModel.class);

		return sm;
	}

}
