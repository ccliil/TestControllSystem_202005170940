package com.cdtu.test;

import com.cdtu.dao.VistiedDocDao;
import com.cdtu.entity.VistiedDoc;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VistiedDocTest extends BaseTest {
    @Autowired
    private VistiedDocDao vistiedDocDao;
    @Test
    public  void test(){
        VistiedDoc vistiedDoc=vistiedDocDao.findByUid(100l);
        System.out.println(vistiedDoc);
    }
    @Test
    public void test1(){
        VistiedDoc vistiedDoc=new VistiedDoc(100l,"nihao");
        boolean flag=vistiedDocDao.updateInfo(vistiedDoc,1l);
        VistiedDoc vistiedDoc1=vistiedDocDao.findByUid(100l);
        System.out.println(vistiedDoc1);
    }
}
