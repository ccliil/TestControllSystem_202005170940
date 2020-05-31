package com.cdtu.dao;

import com.cdtu.entity.Major;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MajorDao {
    @Select("select mid,name from major")
    List<Major> findAll();
    @Select("select * from major where mid=#{majorid}")
    Major findByid(@Param("majorid") Long majorid);
}
