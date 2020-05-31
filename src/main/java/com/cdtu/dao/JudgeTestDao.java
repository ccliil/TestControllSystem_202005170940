package com.cdtu.dao;

import com.cdtu.entity.JudgeTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JudgeTestDao {
    @Select("select jid,account,answer,uid,j.majorid,u.name 'user.name',m.name 'major.name',score from judgetest j INNER JOIN user u ON j.uid=u.id INNER JOIN major m ON j.majorid=m.mid")
    List<JudgeTest> findAll();
    @Select("select jid,account,answer,uid,j.majorid,u.name 'user.name',m.name 'major.name',score from judgetest j INNER JOIN user u ON j.uid=u.id INNER JOIN major m ON j.majorid=m.mid where j.majorid=#{majorId}")
    List<JudgeTest> findByMajorid(@Param("majorId") Long value);
    @Select("select jid,account,answer,uid,j.majorid,u.name 'user.name',m.name 'major.name',score from judgetest j INNER JOIN user u ON j.uid=u.id INNER JOIN major m ON j.majorid=m.mid where j.uid=#{uid}")
    List<JudgeTest> findByUid(@Param("uid") Long value);
    @Update("delete from judgetest where jid=#{jid}")
    boolean delete(@Param("jid") Long jid);
    @Select("select jid,account,answer,uid,j.majorid,u.name 'user.name',m.name 'major.name',score,comment from judgetest j INNER JOIN user u ON j.uid=u.id INNER JOIN major m ON j.majorid=m.mid where jid=#{jid}")
    JudgeTest findByJid(@Param("jid") Long jid);
    @Update("update judgetest set account=#{judgeTest.account},answer=#{judgeTest.answer},uid=#{judgeTest.uid},majorid=#{judgeTest.majorId},comment=#{judgeTest.comment},score=#{judgeTest.score} where jid=#{judgeTest.jid}")
    boolean update(@Param("judgeTest") JudgeTest judgeTest);
    @Insert("insert into judgetest(account,answer,uid,majorid,comment,score) values(#{account},#{answer},#{uid},#{majorId},#{comment},#{score})")
    boolean addJudge(JudgeTest judgeTest);
    @Select("select jid,account,answer,uid,j.majorid,u.name 'user.name',m.name 'major.name',score from judgetest j INNER JOIN user u ON j.uid=u.id INNER JOIN major m ON j.majorid=m.mid where j.uid=#{uid} and j.majorid=#{majorId}")
    List<JudgeTest> findByMajAndUid(@Param("uid") Long uid, @Param("majorId") Long majorid);
    @Update("update judgetest set account=#{judgeTest.account},answer=#{judgeTest.answer},uid=#{judgeTest.uid},comment=#{judgeTest.comment},score=#{judgeTest.score} where jid=#{judgeTest.jid}")
    boolean teacherUpdate(@Param("judgeTest") JudgeTest judgeTest);
    @Select("select jid,account from judgetest where majorid=#{majorid}")
    List<JudgeTest> findAllByMajorId(@Param("majorid") Long majorid);
    @Select("select account,answer from judgetest where jid in ${str1}")
    List<JudgeTest> findByJids(@Param("str1") String str1);
}
