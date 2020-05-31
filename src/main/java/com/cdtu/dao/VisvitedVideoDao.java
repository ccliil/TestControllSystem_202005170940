package com.cdtu.dao;

import com.cdtu.entity.VistiedVideo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
@Repository
public interface VisvitedVideoDao {
    @Select("select * from vistiedvideo where uid=#{uid}")
    VistiedVideo findByUid(@Param("uid") Long uid);
    @Insert("insert into vistiedvideo(uid,video) values(#{uid},#{video})")
    boolean insert(VistiedVideo vistiedVideo);
    @Update("update vistiedvideo set video=#{VistiedVideo.video} where id=#{VistiedVideo.id}")
    boolean updateInfo(@Param("VistiedVideo") VistiedVideo vv);
}
