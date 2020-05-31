package com.cdtu.test;

import com.cdtu.dao.TestInfoDao;
import com.cdtu.entity.TestInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestInfoTest extends BaseTest {
    @Autowired
    private TestInfoDao testInfoDao;
    @Test
    public void test(){
        List<TestInfo> list=testInfoDao.findAllByMajorId(8l);
        String[] listAnswer=new String[list.size()];
        for (int i=0;i<list.size();i++){
            String[] integerList= list.get(i).getjAnswerList().split(",");
                for (int k=0;k<integerList.length;k++){
                    if("0".equals(integerList[k])){
                        if(k==0){
                            listAnswer[i]="×,";
                        }else if(k==integerList.length-1){
                            listAnswer[i]+="×";
                        }else {
                            listAnswer[i]+="×,";
                        }
                    }else if("1".equals(integerList[k])){
                        if(k==0){
                            listAnswer[i]="√,";
                        }else if(k==integerList.length-1){
                            listAnswer[i]+="√";
                        }else {
                            listAnswer[i]+="√,";
                        }
                    }
                }
            list.get(i).setjAnswerList(listAnswer[i]);
        }
        System.out.println(list);
    }
}
