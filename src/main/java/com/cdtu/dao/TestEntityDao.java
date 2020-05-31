package com.cdtu.dao;

import com.cdtu.entity.TestEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestEntityDao {
    @Insert("insert into test(tname,majorid,uid,createTime,cidList,jidList,vidList,totalScore,isUsed) values(#{tname},#{majorid},#{uid},#{createTime},#{cidList},#{jidList},#{vidList},#{totalScore},#{isUsed})")
    boolean insertTest(TestEntity testEntity);
    @Select("select tid,tname,m.name 'major.name',u.name 'user.name',createTime,isUsed,totalScore from test t inner join major m on t.majorid=m.mid inner join user u on t.uid=u.id where t.majorid=#{majorid} ")
    List<TestEntity> findAllByMajorId(@Param("majorid") Long majorid);
    @Delete("delete from test where tid=#{tid}")
    boolean deleteTest(@Param("tid") Long tid);
    @Select("select tid,tname,majorid,uid,totalScore,isUsed,cidList,jidList,vidList from test where tid=#{tid}")
    TestEntity findByTid(@Param("tid") Long tid);
    @Update("update test set tname=#{testEntity.tname},majorid=#{testEntity.majorid},uid=#{testEntity.uid},totalScore=#{testEntity.totalScore},isUsed=#{testEntity.isUsed} where tid=#{tid} ")
    boolean updateTest(@Param("testEntity") TestEntity testEntity);
    @Update("update test set isUsed=0 where majorid=#{majorid}")
    void setOtherFalse(@Param("majorid") Long majorid);
    @Select("select tid,tname,cidList,jidList,vidList from test where majorid=#{majorid} and isUsed=#{isUsed}")
    List<TestEntity> findByMajorAndStart(@Param("majorid") Long majorid,@Param("isUsed") int i);
    @Select("select * from test where majorid=#{majorid}")
    @Results({
            @Result(id=true,column="tid",property="tid"),
            @Result(column="tname",property="tname"),
            @Result(column="tid",property="listInfo",
                    many=@Many(
                            select="com.cdtu.dao.TestInfoDao.findByTid"
                    )
            )
    })
    List<TestEntity> findByMajAndTea(@Param("majorid") long majorid);
    @Select("select * from test where tid=#{tid}")
    @Results({
            @Result(id=true,column="tid",property="tid"),
            @Result(column="tname",property="tname"),
            @Result(column="tid",property="listInfo",
                    many=@Many(
                            select="com.cdtu.dao.TestInfoDao.findByTid"
                    )
            )
    })
    TestEntity findAllByTid(@Param("tid") Long tid);
}
