
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.ReportModel;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportService {

    public int create(ReportModel reportModel);

    public int createSelective(ReportModel reportModel);

    public void submitReports(Long taskId, List<ReportModel> reportModels);

    public ReportModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(ReportModel reportModel);

    public int updateByPrimaryKeySelective(ReportModel reportModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(ReportModel reportModel);

    public List<ReportModel> selectPage(ReportModel reportModel, Pageable pageable);
    
    
    public List<ReportModel> findByTaskId(Long id);

    

}