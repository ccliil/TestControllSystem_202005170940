package com.cdtu.test;

import com.cdtu.dao.VacantDao;
import com.cdtu.entity.VacantTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class Vacant extends BaseTest {
    @Autowired
    private VacantDao vacantDao;
    @Test
    public void testVacant(){
        List<VacantTest> list=vacantDao.finAll();
        System.out.println(list);
    }
    @Test
    public void test1(){
        List<VacantTest> list=vacantDao.findByMajorId(7l);
        System.out.println(list);
    }
    @Test
    public void test2(){
        List<VacantTest> list=vacantDao.findByUid(10l);
        System.out.println(list);
    }
}
