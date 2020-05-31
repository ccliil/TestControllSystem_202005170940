package com.cdtu.dao;

import com.cdtu.entity.ChioceTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChioceTestDao {
    @Select("select cid,account,Aitem,Bitem,Citem,Ditem,answer,score,comment,uid,u.name 'user.name',m.name 'major.name',c.majorid FROM chiocetest c INNER JOIN user u ON c.uid=u.id INNER JOIN major m ON c.majorid=m.mid ")
    List<ChioceTest> findAll();
    @Update("delete from chiocetest where cid=#{cids}")
    boolean delete(@Param("cids") Long cids);
    @Select("select cid,account,Aitem,Bitem,Citem,Ditem,answer,score,comment,uid,u.name 'user.name',m.name 'major.name',c.majorid FROM chiocetest c INNER JOIN user u ON c.uid=u.id INNER JOIN major m ON c.majorid=m.mid where cid=#{cid}")
    ChioceTest findByCid(@Param("cid") Long cid);
    @Update("update chiocetest set account=#{chioceTest.account},Aitem=#{chioceTest.aitem},Bitem=#{chioceTest.bitem},Citem=#{chioceTest.citem},Ditem=#{chioceTest.ditem},answer=#{chioceTest.answer},score=#{chioceTest.score},uid=#{chioceTest.uid},majorid=#{chioceTest.majorid} where cid=#{chioceTest.cid}")
    boolean updateChioce(@Param("chioceTest") ChioceTest chioceTest);
    @Insert("insert into chiocetest(account,comment,Aitem,Bitem,Citem,Ditem,answer,score,majorid,isRight,uid) values(#{account},#{comment},#{aitem},#{bitem},#{citem},#{ditem},#{answer},#{score},#{majorid},false,#{uid})")
    boolean add(ChioceTest chioceTest);
    @Select("select cid,account,Aitem,Bitem,Citem,Ditem,answer,score,comment,uid,u.name 'user.name',m.name 'major.name',c.majorid FROM chiocetest c INNER JOIN user u ON c.uid=u.id INNER JOIN major m ON c.majorid=m.mid where c.majorid=#{value}")
    List<ChioceTest> findByMajorid(@Param("value") Long value);
    @Select("select cid,account,Aitem,Bitem,Citem,Ditem,answer,score,comment,uid,u.name 'user.name',m.name 'major.name',c.majorid FROM chiocetest c INNER JOIN user u ON c.uid=u.id INNER JOIN major m ON c.majorid=m.mid where c.uid=#{value}")
    List<ChioceTest> findByUid(@Param("value")Long value);
    @Select("select cid,account,Aitem,Bitem,Citem,Ditem,answer,score,comment,uid,u.name 'user.name',m.name 'major.name',c.majorid FROM chiocetest c INNER JOIN user u ON c.uid=u.id INNER JOIN major m ON c.majorid=m.mid where c.uid=#{uid} and c.majorid=#{majorid}")
    List<ChioceTest> findByUidAndMaj(@Param("majorid") Long majorid,@Param("uid") Long uid);
    @Update("update chiocetest set account=#{chioceTest.account},Aitem=#{chioceTest.aitem},Bitem=#{chioceTest.bitem},Citem=#{chioceTest.citem},Ditem=#{chioceTest.ditem},answer=#{chioceTest.answer},score=#{chioceTest.score},uid=#{chioceTest.uid} where cid=#{chioceTest.cid}")
    boolean teacherUpdateChioce(@Param("chioceTest") ChioceTest chioceTest);
    @Select("select cid,account from chiocetest where majorid=#{majorid}")
    List<ChioceTest> findAllByMajorId(@Param("majorid") Long majorid);
    @Select("select account,Aitem,Bitem,Citem,Ditem,answer from chiocetest where cid in ${split}")
    List<ChioceTest> findAllByIds(@Param("split") String split);
}
