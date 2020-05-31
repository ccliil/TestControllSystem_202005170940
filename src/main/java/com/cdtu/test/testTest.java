package com.cdtu.test;

import com.cdtu.dao.ChioceTestDao;
import com.cdtu.dao.JudgeTestDao;
import com.cdtu.dao.TestEntityDao;
import com.cdtu.dao.VacantDao;
import com.cdtu.entity.ChioceTest;
import com.cdtu.entity.TestEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class testTest extends BaseTest {
    @Autowired
    private TestEntityDao testEntityDao;
    @Autowired
    private ChioceTestDao chioceTestDao;
    @Autowired
    private JudgeTestDao judgeTestDao;
    @Autowired
    private VacantDao vacantDao;
    @Test
    public void test1(){
        TestEntity testEntity=new TestEntity("java试题一",2l,3l,"2019-11-25","2,3,4,6","1,2","3,4",100,0);
        boolean flag=testEntityDao.insertTest(testEntity);
        System.out.println(flag);
    }
    @Test
    public void test2(){
        List<TestEntity> list=testEntityDao.findAllByMajorId(2l);
        System.out.println(list);
    }
    @Test
    public void test3(){
        TestEntity testEntity=testEntityDao.findByTid(1L);
        String str="("+testEntity.getCidList()+")";
        List<ChioceTest> listChioce=chioceTestDao.findAllByIds(str);
        System.out.println(listChioce);
    }
    @Test
    public void test4(){
        List<TestEntity> list=testEntityDao.findByMajAndTea(8l);
        System.out.println(list);
    }
    @Test
    public void test5(){
        TestEntity testEntity=testEntityDao.findAllByTid(2l);
        System.out.println(testEntity);
    }
}
