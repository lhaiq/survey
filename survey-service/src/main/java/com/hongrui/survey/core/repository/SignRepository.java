package com.hongrui.survey.core.repository;

import com.hongrui.survey.core.entity.Sign;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SignRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("sign") Sign sign);

    int insertSelective(@Param("sign") Sign sign);

    Sign selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("sign") Sign sign);

    int updateByPrimaryKey(@Param("sign") Sign sign);

    int selectCount(@Param("sign") Sign sign);

    java.util.List<com.hongrui.survey.core.entity.Sign> selectPage(@Param("sign") Sign sign, @Param("pageable") Pageable pageable);
}