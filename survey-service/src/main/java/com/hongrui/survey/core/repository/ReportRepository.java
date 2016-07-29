package com.hongrui.survey.core.repository;

import com.hongrui.survey.core.entity.Report;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("report") Report report);

    int insertSelective(@Param("report") Report report);

    Report selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("report") Report report);

    int updateByPrimaryKeyWithBLOBs(@Param("report") Report report);

    int updateByPrimaryKey(@Param("report") Report report);

    int selectCount(@Param("report") Report report);

    List<Report> selectPage(@Param("report") Report report, @Param("pageable") Pageable pageable);
}