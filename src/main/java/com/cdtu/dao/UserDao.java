package com.cdtu.dao;

import com.cdtu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
public interface UserDao {
   public boolean updateUser(@Param("tel") String tel,@Param("password") String password,@Param("textpass")String textpass) ;
    public List<User> findAll();
    public User findById(@Param("id") Long id);
    @Select("select * from user where username=#{username} and password=#{password}")
    public User findUser(@Param("username") String username, @Param("password") String password);
    @Update("delete from user where id=#{id}")
    boolean dropUser(@Param("id") Long id);
    @Update("update user set username=#{user.username},password=#{user.password},name=#{user.name},tel=#{user.tel},majorid=#{user.majorid},identify=#{user.identify},textpass=#{user.textpass} where id=#{user.id} ")
    boolean userUpdate(@Param("user") User user);
    List<User> findByUsername(@Param("username") String value);
  @Insert("insert into user(username,password,name,tel,majorid,identify,textpass) values(#{username},#{password},#{name},#{tel},#{majorid},#{identify},#{textpass})")
  int insertUser(User user);
    List<User> findByName(@Param("name") String value);
   @Select("select count(*) from user")
   Long count();
    @Select("select * from user where username=#{userName} and tel=#{tel}")
    User findByNamePerson(@Param("userName") String userName,@Param("tel") String tel);
    @Update("update user set username=#{user.username},password=#{user.password},name=#{user.name},tel=#{user.tel},textpass=#{user.textpass} where id=#{user.id} ")
    boolean updatePersonInfo(@Param("user") User user);
    @Select("select * from user where id=#{id}")
    User findInfoById(@Param("id") Long id);
}
