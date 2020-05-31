package com.cdtu.test;

import com.cdtu.dao.JudgeTestDao;
import com.cdtu.entity.ChioceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JudgeTest extends BaseTest {
    @Autowired
    private JudgeTestDao judgeTestDao;
    @Test
    public void testList(){
        List<com.cdtu.entity.JudgeTest> list=judgeTestDao.findAll();
        System.out.println(list);
    }
    @Test
    public  void testMajor(){
        List<com.cdtu.entity.JudgeTest> list= judgeTestDao.findByMajorid(7l);
        System.out.println(list);
    }
    @Test
    public void testUid(){
       List<com.cdtu.entity.JudgeTest> list = judgeTestDao.findByUid(3l);
        System.out.println(list);
    }
    @Test
    public void testJid(){
        com.cdtu.entity.JudgeTest test=judgeTestDao.findByJid(2l);
        System.out.println(test);
    }
}
