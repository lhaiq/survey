package com.hongrui.survey.core.repository;

import com.hongrui.survey.core.entity.Task;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("task") Task task);

    int insertSelective(@Param("task") Task task);

    Task selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("task") Task task);

    int updateByPrimaryKey(@Param("task") Task task);

    int selectCount(@Param("task") Task task);

    java.util.List<com.hongrui.survey.core.entity.Task> selectPage(@Param("task") Task task, @Param("pageable") Pageable pageable);
}