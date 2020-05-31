package com.cdtu.test;

import com.cdtu.dao.TestEntityDao;
import com.cdtu.dao.TimeDao;
import com.cdtu.entity.TestEntity;
import com.cdtu.util.StrDetalUtils;
import org.apache.ibatis.annotations.Insert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {
    @Autowired
    private TimeDao timeDao;
    @Autowired
    private TestEntityDao testEntityDao;
    @Test
    public void test(){

            String date="2019-11-28";
            String[] dates=date.split("-");
            String startDate="2019-11-12";
            String[] startTime=startDate.split("-");
            String endDate="2019-11-20";
            String[] endTime=endDate.split("-");
            Long now=0l;
            Long start=0l;
            Long end=0l;
            date="";
            for(int i=0;i<dates.length;i++){
                date+=dates[i];
            }
            now=Long.parseLong(date);
            startDate="";
            for(int i=0;i<startTime.length;i++){
                startDate+=startTime[i];
            }
            start=Long.parseLong(startDate);
            endDate="";
            for (int i=0;i<endTime.length;i++){
                endDate+=endTime[i];
            }
            end=Long.parseLong(endDate);
            if(now<start){
                System.out.println("考试还未开始哦！！");
            }else if(now>end){
                System.out.println("考试已经过了哟！！");
            }else {
                System.out.println("可以进行考试了！！");
            }

    }
    @Test
    public void test1(){
        int a= StrDetalUtils.dateComp("2019-11-10","2019-11-12","2019-11-30");
        if(a==-1){
            System.out.println("考试时间还没到哦！！");
        }else if(a==0){
            System.out.println("考试正在进行咯！！！");
        }else if(a==1){
            System.out.println("考试时间已经过了哦！！！");
        }
    }
    @Test
    public void test2(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(new Date());
        System.out.println(str);
    }
    @Test
    public void test3(){
        Integer i=Integer.parseInt("02");
        System.out.println(i);
    }
    @Test
    public  void test4(){
        List<TestEntity> list=testEntityDao.findByMajorAndStart(8l,1);
        Random random=new Random(list.size());
        int num=random.nextInt(list.size());
        TestEntity testEntity=list.get(num);
        System.out.println(testEntity);
    }
}
