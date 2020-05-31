package com.cdtu.dao;

import com.cdtu.entity.Material;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
    @Select("select m.id,title,remark,dataName,u.name 'user.name',mj.name 'major.name',createTime,m.majorId from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.majorId=#{majorid}")
    List<Material> findAllDocument(@Param("majorid") Long majorid);
    @Select("select m.id,title,remark,dataName,u.name 'user.name',mj.name 'major.name',createTime,m.majorId from material m inner join user u on m.uId=u.id inner join major mj on m.majorId=mj.mid where m.majorId=#{majorid} and dataName like #{filename} ")
    List<Material> findByFilename(@Param("majorid") Long majorId, @Param("filename") String filename);
}
