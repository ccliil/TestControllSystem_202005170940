package com.cdtu.dao;

import com.cdtu.entity.Material;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface DocumentDao {
    @Select("select m.id,title,remark,dataName,uId,m.majorId,createTime,mj.name 'major.name',u.name 'user.name',location,isLoad from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid")
    List<Material> findAll();
    @Select("select m.id,title,remark,dataName,uId,m.majorId,createTime,mj.name 'major.name',u.name 'user.name',isLoad from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where uId=#{uid}")
    List<Material> findByUid(@Param("uid") long value);
    @Select("select m.id,title,remark,dataName,uId,m.majorId,createTime,mj.name 'major.name',u.name 'user.name',isLoad from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.majorId=#{majorid}")
    List<Material> findByMajorId(@Param("majorid") long value);
    @Select("select m.id,title,remark,dataName,uId,m.majorId,createTime,mj.name 'major.name',u.name 'user.name',isLoad from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where dataName like #{filename}")
    List<Material> findByFilename(@Param("filename") String value);
    @Update("delete from material where id=#{id}")
    boolean delete(@Param("id") Long id);
    @Select("select m.id,title,remark,dataName,uId,m.majorId,u.name 'user.name',mj.name 'major.name',location,isLoad from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.id=#{id}")
    Material findById(@Param("id") Long id);
    @Update("update material set title=#{material.title},remark=#{material.remark},dataName=#{material.dataName},uId=#{material.uId},majorId=#{material.majorId},isLoad=#{material.isLoad} where id=#{material.id}")
    boolean update(@Param("material") Material material);
    @Insert("insert into material(title,remark,dataName,uId,majorId,createTime,location,isLoad) values(#{title},#{remark},#{dataName},#{uId},#{majorId},#{createTime},#{location},#{isLoad})")
    boolean insert(Material material);
    @Select("select m.id,location,dataName from material m inner join major mj on m.majorId=mj.mid where m.majorId=#{majorid} and dataName like #{filename}")
    List<Material> findByMaAndEndWith(@Param("majorid") Long majorid,@Param("filename") String s);
    @Select("select m.id,title,remark,dataName,uId,m.majorId,createTime,mj.name 'major.name',u.name 'user.name',location,isLoad from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.majorId=#{majorId} and m.uId=#{uid}")
    List<Material> findByMajAndUid(@Param("majorId") Long majorId,@Param("uid") long uid);
    @Select("select m.id,title,remark,dataName,uId,m.majorId,createTime,mj.name 'major.name',u.name 'user.name',location,isLoad from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.majorId=#{majorId} and dataName like #{filename}")
    List<Material> findByMajorAndFil(@Param("majorId") Long majorId, @Param("filename") String filename);
    @Update("update material set title=#{material.title},remark=#{material.remark},dataName=#{material.dataName},uId=#{material.uId},isLoad=#{material.isLoad} where id=#{material.id}")
    boolean teacherUpdate(@Param("material") Material material);
    @Select("select m.id,title,remark,dataName,createTime,mj.name 'major.name',u.name 'user.name',isLoad,location from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.majorId=#{majorid} and (m.dataName like '%.doc%' or m.dataName like '%.ppt%' or m.dataName like '%.docx%' or m.dataName like '%.pptx%' or m.dataName like '%.xls%') ")
    List<Material> findAllDocumentEndwithDoc(@Param("majorid") Long majorid);
    @Select("select m.id,title,remark,dataName,createTime,mj.name 'major.name',u.name 'user.name',isLoad,location from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.majorId=#{majorId} and (m.dataName like '%.doc%' or m.dataName like '%.ppt%' or m.dataName like '%.docx%' or m.dataName like '%.pptx%' or m.dataName like '%.xls%')")
    List<Material> findByfilenameStu(@Param("majorId") Long majorId);
}
