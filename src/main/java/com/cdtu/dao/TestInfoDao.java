package com.cdtu.dao;

import com.cdtu.entity.TestInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestInfoDao {
    @Select("select fid,t.tname 'test.tname',u.name 'user.name',ti.createTime,cAnswerList,jAnswerList,vAnswerList,score from testinfo ti inner join test t on ti.tid=t.tid inner join user u on ti.uid=u.id where ti.delid=0 and t.majorid=#{majorid} ")
    List<TestInfo> findAllByMajorId(@Param("majorid") Long majorid);
    @Select("SELECT fid,t.tname 'test.tname',u.name 'user.name',cAnswerList,vAnswerList,jAnswerList,score ,ti.createTime FROM testinfo ti INNER JOIN test t ON ti.tid=t.tid INNER JOIN user u ON ti.uid=u.id WHERE t.majorid=#{majorid} and t.tname LIKE #{findValue} ")
    List<TestInfo> findByTnameAndMajor(@Param("findValue") String findValue,@Param("majorid") Long majorid);
    @Select("SELECT fid,t.tname 'test.tname',u.name 'user.name',cAnswerList,vAnswerList,jAnswerList,score,ti.createTime FROM testinfo ti INNER JOIN user u ON ti.uid=u.id INNER JOIN test t ON ti.tid=t.tid WHERE t.majorid=#{majorid} and u.name LIKE #{findValue}")
    List<TestInfo> findByUnameAndMajor(@Param("findValue") String findValue,@Param("majorid") Long majorid);
    @Update(" update testinfo set delid=1 where fid=#{fid}")
    boolean deleteByFid(@Param("fid") Long fid);
    @Select("SELECT t.tname 'test.tname',u.name 'user.name',score,ti.createTime,cAnswerList,jAnswerList,vAnswerList,t.cidList 'test.cidList',t.jidList 'test.jidList',t.vidList 'test.vidList' FROM testinfo ti INNER JOIN test t ON ti.tid=t.tid INNER JOIN user u ON t.uid=u.id WHERE ti.fid=#{fid}")
    TestInfo findByFid(@Param("fid") Long fid);
    @Select("select *,t.tname 'test.tname',u.name 'user.name' from testinfo ti inner join test t on ti.tid=t.tid inner join user u on ti.uid=u.id where ti.uid=#{uid} and delid=0")
    TestInfo findByUid(@Param("uid") Long uid);
    @Insert("insert into testinfo(uid,cAnswerList,jAnswerList,vAnswerList,score,createTime,majorid,tid,delid) values(#{uid},#{cAnswerList},#{jAnswerList},#{vAnswerList},#{score},#{createTime},#{majorid},#{tid},#{delId})")
    boolean insertEntity(TestInfo testInfo);
    @Select("select * from testinfo where tid=#{tid}")
    List<TestInfo> findByTid(@Param("tid") Long tid);
}
