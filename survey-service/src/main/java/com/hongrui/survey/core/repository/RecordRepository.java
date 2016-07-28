package com.hongrui.survey.core.repository;

import com.hongrui.survey.core.entity.Record;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("record") Record record);

    int insertSelective(@Param("record") Record record);

    Record selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("record") Record record);

    int updateByPrimaryKey(@Param("record") Record record);

    int selectCount(@Param("record") Record record);

    List<Record> selectPage(@Param("record") Record record, @Param("pageable") Pageable pageable);
}