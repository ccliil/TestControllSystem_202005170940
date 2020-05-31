package com.cdtu.dao;

import com.cdtu.entity.VistiedDoc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface VistiedDocDao {
    @Select("select * from vistieddoc where uid=#{uid}")
    VistiedDoc findByUid(@Param("uid") Long uid);
    @Insert("insert into vistieddoc(uid,doc) values(#{uid},#{doc})")
    boolean insertDoc(VistiedDoc vistiedDoc1);
    @Update("update vistieddoc set doc=#{vistiedDoc1.doc} where did=#{id}")
    boolean updateInfo(@Param("vistiedDoc1") VistiedDoc vistiedDoc1,@Param("id") Long id);
}
