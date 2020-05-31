package com.cdtu.test;

import com.cdtu.dao.ChioceTestDao;
import com.cdtu.entity.ChioceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Chioce extends BaseTest {
    @Autowired
    private ChioceTestDao chioceTestDao;
    @Test
    public void testList(){
        List<ChioceTest> list=chioceTestDao.findAll();
        System.out.println(list);
    }
    @Test
    public void testChioce(){
        ChioceTest chioceTest=chioceTestDao.findByCid(2l);
        System.out.println(chioceTest);
    }
}
