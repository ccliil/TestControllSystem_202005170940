package com.cdtu.service;

import com.cdtu.dao.TestInfoDao;
import com.cdtu.entity.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestInfoService {
    @Autowired
    private TestInfoDao testInfoDao;

    public List<TestInfo> findAllByMajorId(Long majorid) {
        return testInfoDao.findAllByMajorId(majorid);
    }

    public List<TestInfo> findByTnameAndMajor(String findValue,Long majorid) {
        findValue="%"+findValue+"%";
        return testInfoDao.findByTnameAndMajor(findValue,majorid);
    }

    public List<TestInfo> findByUnameAndMajor(String findValue,Long majorid) {
        findValue="%"+findValue+"%";
        return testInfoDao.findByUnameAndMajor(findValue,majorid);
    }

    public boolean deleteByFid(Long fid) {
        return testInfoDao.deleteByFid(fid);
    }

    public TestInfo findByFid(Long fid) {
        TestInfo testInfo=testInfoDao.findByFid(fid);
        String[] answers=testInfo.getjAnswerList().split(",");
        String temp="";
        for (int i=0;i<answers.length;i++){
            if("0".equals(answers[i])){
                if(i==0){
                    temp="×,";
                }else if(i==answers.length-1){
                    temp+="×";
                }else {
                    temp+="×,";
                }
            }else if("1".equals(answers[i])){
                if(i==0){
                    temp="√,";
                }else if(i==answers.length-1){
                    temp+="√";
                }else {
                    temp+="√,";
                }
            }
        }
        testInfo.setjAnswerList(temp);
        return testInfo;
    }

    public TestInfo findByUid(Long uid) {
        return testInfoDao.findByUid(uid);
    }

    public boolean insertEntity(TestInfo testInfo) {
        return testInfoDao.insertEntity(testInfo);
    }
}
