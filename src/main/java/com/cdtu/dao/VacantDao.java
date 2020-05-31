package com.cdtu.dao;

import com.cdtu.entity.VacantTest;
import com.cdtu.test.Vacant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacantDao {
    @Select("SELECT vid,account,answer,score,v.majorId,v.uid,m.name 'major.name',u.name 'user.name' FROM vacanttest v INNER JOIN user u ON v.uid=u.id INNER JOIN major m ON v.majorId=m.mid")
    List<VacantTest> finAll();
    @Select("SELECT vid,account,answer,score,v.majorId,v.uid,m.name 'major.name',u.name 'user.name' FROM vacanttest v INNER JOIN user u ON v.uid=u.id INNER JOIN major m ON v.majorId=m.mid where v.uid=#{uid}")
    List<VacantTest> findByUid(@Param("uid") Long value);
    @Select("SELECT vid,account,answer,score,v.majorId,v.uid,m.name 'major.name',u.name 'user.name' FROM vacanttest v INNER JOIN user u ON v.uid=u.id INNER JOIN major m ON v.majorId=m.mid where v.majorId=#{majorId}")
    List<VacantTest> findByMajorId(@Param("majorId") Long value);
    @Update("delete from vacanttest where vid=#{vid}")
    boolean deleteVacant(@Param("vid") Long vid);
    @Select("SELECT vid,account,answer,score,v.majorId,v.uid,m.name 'major.name',u.name 'user.name',comment FROM vacanttest v INNER JOIN user u ON v.uid=u.id INNER JOIN major m ON v.majorId=m.mid where v.vid=#{vid}")
    VacantTest findByVid(@Param("vid") Long vid);
    @Update("update vacanttest set account=#{vacant.account},answer=#{vacant.answer},comment=#{vacant.comment},score=#{vacant.score},uid=#{vacant.uid},majorId=#{vacant.majorId} where vid=#{vacant.vid}")
    boolean updateVacant(@Param("vacant") VacantTest vacantTest);
    @Insert("insert into vacanttest(account,comment,answer,score,uid,majorId) values(#{account},#{comment},#{answer},#{score},#{uid},#{majorId})")
    boolean addVacant(VacantTest vacantTest);
    @Select("SELECT vid,account,answer,score,v.majorId,v.uid,m.name 'major.name',u.name 'user.name' FROM vacanttest v INNER JOIN user u ON v.uid=u.id INNER JOIN major m ON v.majorId=m.mid where v.majorId=#{majorId} and v.uid=#{uid}")
    List<VacantTest> findByMajAndUid(@Param("majorId") Long majorid, @Param("uid") Long uid);
    @Update("update vacanttest set account=#{vacant.account},answer=#{vacant.answer},comment=#{vacant.comment},score=#{vacant.score},uid=#{vacant.uid} where vid=#{vacant.vid}")
    boolean teacherUpdate(@Param("vacant") VacantTest vacantTest);
    @Select("select vid,account from vacanttest where majorId=#{majorId}")
    List<VacantTest> findAllByMajorId(@Param("majorId") Long majorid);
    @Select("select account,answer from vacanttest where vid in ${str2}")
    List<VacantTest> findByVids(@Param("str2") String str2);
}
