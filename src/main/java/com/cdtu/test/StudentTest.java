package com.cdtu.test;

import com.cdtu.dao.DocumentDao;
import com.cdtu.dao.StudentDao;
import com.cdtu.entity.*;
import com.cdtu.entity.JudgeTest;
import com.cdtu.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentTest extends BaseTest {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private TestEntityService testEntityService;
    @Autowired
    private ChioceTestService chioceTestService;
    @Autowired
    private JudgeTestService judgeTestService;
    @Autowired
    private VacantService vacantService;
    @Autowired
    private TestInfoService testInfoService;
    @Test
    public void test(){
        List<Material> list=studentDao.findAllDocument(8l);
        System.out.println(list);
    }
    @Test
    public  void test1(){
        List<Material> list=studentDao.findByFilename(8l,"%SQL%");
        System.out.println(list);
    }
    @Test
    public void test2(){
        List<Material> list=documentDao.findByMaAndEndWith(8l,"%.avi");
        System.out.println(list);
    }
    @Test
    public void test3(){
        TestEntity testEntity=testEntityService.findByTid(2l);
        int score=0;
        String chioceIDS="("+testEntity.getCidList()+")";
        String judgeIDS="("+testEntity.getJidList()+")";
        String vacantIDS="("+testEntity.getVidList()+")";
        String cAns="D,A,B,C,";
        String[] cc=cAns.split(",");
        String jAns="0,1,";
        String[] jj=jAns.split(",");
        String vAns="xxxx,yyyy";
        String[] vv=vAns.split(",");
        List<ChioceTest> chioceList=chioceTestService.findBtCids(chioceIDS);
        List<JudgeTest> judgeList=judgeTestService.findByJids(judgeIDS);
        List<VacantTest> vacantList=vacantService.findByVids(vacantIDS);
        for(int i=0;i<cc.length;i++){
            if(cc[i].equals(chioceList.get(i).getAnswer())){
                score+=15;
            }
            String temp="";
            temp=chioceList.get(i).getAnswer();
            chioceList.get(i).setAnswer(cc[i]);
            cc[i]=temp;
        }
        cAns= Arrays.stream(cc).collect(Collectors.joining(","));
        for (int j=0;j<jj.length;j++){
            if(Integer.parseInt(jj[j])==judgeList.get(j).getAnswer()){
                score+=10;
            }
           int temp=0;
            temp=judgeList.get(j).getAnswer();
            judgeList.get(j).setAnswer(Integer.parseInt(jj[j]));
            jj[j]=temp+"";
        }
        jAns=Arrays.stream(jj).collect(Collectors.joining(","));
        for (int k=0;k<vv.length;k++){
            if(vv[k].equals(vacantList.get(k).getAnswer())){
                score+=10;
            }
            String temp="";
            temp=vacantList.get(k).getAnswer();
            vacantList.get(k).setAnswer(vv[k]);
            vv[k]=temp;
        }
        vAns=Arrays.stream(vv).collect(Collectors.joining(","));
        System.out.println(chioceList);
        System.out.println(cAns);
        System.out.println(judgeList);
        System.out.println(jAns);
        System.out.println(vacantList);
        System.out.println(vAns);
        System.out.println(score);
    }
    @Test
    public void test4(){
        String tname=testInfoService.findByUid(12l).getTest().getTname();
        System.out.println(tname);
    }
}
