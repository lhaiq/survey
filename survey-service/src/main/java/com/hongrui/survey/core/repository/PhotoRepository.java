package com.hongrui.survey.core.repository;

import com.hongrui.survey.core.entity.Photo;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("photo") Photo photo);

    int insertSelective(@Param("photo") Photo photo);

    Photo selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("photo") Photo photo);

    int updateByPrimaryKey(@Param("photo") Photo photo);

    int selectCount(@Param("photo") Photo photo);

    List<Photo> selectPage(@Param("photo") Photo photo, @Param("pageable") Pageable pageable);
}