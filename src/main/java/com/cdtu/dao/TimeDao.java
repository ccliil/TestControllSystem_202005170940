package com.cdtu.dao;

import com.cdtu.entity.Time;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TimeDao {
    @Insert("insert into time(startTime,endTime,createTime,uid) values(#{startTime},#{endTime},#{createTime},#{uid})")
    boolean addTime(Time time);
    @Select("SELECT * FROM TIME WHERE sid=(SELECT MAX(sid) FROM TIME)")
    Time findTime();
}
