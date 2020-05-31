package com.cdtu.service;

import com.cdtu.dao.TestEntityDao;
import com.cdtu.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestEntityService {
    @Autowired
    private TestEntityDao testEntityDao;

    public boolean insertTest(TestEntity testEntity) {
        return testEntityDao.insertTest(testEntity);
    }

    public List<TestEntity> findAllByMajorId(Long majorid) {
        return testEntityDao.findAllByMajorId(majorid);
    }

    public boolean deleteTest(Long tid) {
        return testEntityDao.deleteTest(tid);
    }

    public TestEntity findByTid(Long tid) {
        return testEntityDao.findByTid(tid);
    }

    public boolean updateTest(TestEntity testEntity) {
        return testEntityDao.updateTest(testEntity);
    }
//
//    public void setOtherFalse(Long majorid) {
//        testEntityDao.setOtherFalse(majorid);
//    }

    public List<TestEntity> findByMajorAndStart(Long majorid, int i) {
        return testEntityDao.findByMajorAndStart(majorid,i);
    }

    public List<TestEntity> findByMajAndTea(Long majorid) {
        return testEntityDao.findByMajAndTea(majorid);
    }

    public TestEntity findAllByTid(Long tid) {
        return testEntityDao.findAllByTid(tid);
    }
}
