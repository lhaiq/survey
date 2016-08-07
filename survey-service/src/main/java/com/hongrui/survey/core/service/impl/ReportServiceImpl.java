package com.hongrui.survey.core.service.impl;

import com.hongrui.survey.core.service.TaskService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Report;
import com.hongrui.survey.core.repository.ReportRepository;
import com.hongrui.survey.core.model.ReportModel;
import com.hongrui.survey.core.service.ReportService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ReportRepository reportRepo;

	@Autowired
	private TaskService taskService;

	@Transactional
	@Override
	public int create(ReportModel reportModel) {
		return reportRepo.insert(beanMapper.map(reportModel, Report.class));
	}

	@Transactional
	@Override
	public int createSelective(ReportModel reportModel) {
		return reportRepo.insertSelective(beanMapper.map(reportModel, Report.class));
	}

	@Transactional
	@Override
	public void submitReports(Long taskId, List<ReportModel> reportModels) {
		taskService.checkCanEdit(taskId);
		for (ReportModel reportModel : reportModels) {
			reportModel.setTaskId(taskId);
			reportModel.setCreateTime(new Date());

			// 看是否存在
			ReportModel param = new ReportModel();
			param.setTaskId(taskId);
			param.setTemplateId(reportModel.getTemplateId());
			List<ReportModel> results = selectPage(param, new PageRequest(0, Integer.MAX_VALUE));
			if (!CollectionUtils.isEmpty(results)) {
				ReportModel existedReport = results.get(0);
				param.setId(existedReport.getId());
				param.setContent(reportModel.getContent());
				updateByPrimaryKeySelective(param);
			} else {
				createSelective(reportModel);
			}

		}
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return reportRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public ReportModel findByPrimaryKey(Long id) {
		Report report = reportRepo.selectByPrimaryKey(id);
		return beanMapper.map(report, ReportModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(ReportModel reportModel) {
		return reportRepo.selectCount(beanMapper.map(reportModel, Report.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<ReportModel> selectPage(ReportModel reportModel, Pageable pageable) {
		Report report = beanMapper.map(reportModel, Report.class);
		return beanMapper.mapAsList(reportRepo.selectPage(report, pageable), ReportModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(ReportModel reportModel) {
		return reportRepo.updateByPrimaryKey(beanMapper.map(reportModel, Report.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ReportModel reportModel) {
		return reportRepo.updateByPrimaryKeySelective(beanMapper.map(reportModel, Report.class));
	}

	@Override
	public List<ReportModel> findByTaskId(Long id) {

		Pageable pageable = new PageRequest(0, 1000);
		Report report = new Report();
		report.setTaskId(id);
		List<Report> reps = reportRepo.selectPage(report, pageable);

		return beanMapper.mapAsList(reps, ReportModel.class);
	}

}
