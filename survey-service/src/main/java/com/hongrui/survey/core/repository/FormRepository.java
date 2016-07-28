package com.hongrui.survey.core.repository;

import com.hongrui.survey.core.entity.Form;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("form") Form form);

    int insertSelective(@Param("form") Form form);

    Form selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("form") Form form);

    int updateByPrimaryKeyWithBLOBs(@Param("form") Form form);

    int updateByPrimaryKey(@Param("form") Form form);

    int selectCount(@Param("form") Form form);

    List<Form> selectPage(@Param("form") Form form, @Param("pageable") Pageable pageable);
}