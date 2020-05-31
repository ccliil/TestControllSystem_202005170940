package com.cdtu.test;

import com.cdtu.dao.UserDao;
import com.cdtu.entity.SmsProperties;
import com.cdtu.entity.User;
import com.cdtu.util.MD5Util;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class UserTest extends BaseTest {
    @Autowired
    private UserDao userDao;


    @Test
    public void testFindAll(){
        List<User> list=new ArrayList<>();
        list=userDao.findAll();
        System.out.println(list);
    }
@Test
  public void test(){
    System.out.println(userDao.updateUser("18328508652", MD5Util.MD5("admin").substring(0,30),"admin"));
  }

}
