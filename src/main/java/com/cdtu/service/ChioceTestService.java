package com.cdtu.service;

import com.cdtu.dao.ChioceTestDao;
import com.cdtu.entity.ChioceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChioceTestService {
    @Autowired
    private ChioceTestDao chioceTestDao;

    public  boolean addChioce(ChioceTest chioceTest) {
        return chioceTestDao.add(chioceTest);
    }

    public ChioceTest findByCid(Long cid) {
        return chioceTestDao.findByCid(cid);
    }

    public boolean delete(Long cids) {
        return chioceTestDao.delete(cids);
    }

    public List<ChioceTest> findAll(){
        return chioceTestDao.findAll();
    }

    public boolean updateChioce(ChioceTest chioceTest) {
        return chioceTestDao.updateChioce(chioceTest);
    }

    public List<ChioceTest> findByMajorid(Long value) {
        return chioceTestDao.findByMajorid(value);
    }

    public List<ChioceTest> findByUid(Long value) {
        return chioceTestDao.findByUid(value);
    }

    public List<ChioceTest> findByUidAndMaj(Long majorid, Long uid) {
        return chioceTestDao.findByUidAndMaj(majorid,uid);
    }

    public boolean teacherUpdateChioce(ChioceTest chioceTest) {
        return chioceTestDao.teacherUpdateChioce(chioceTest);
    }

    public List<ChioceTest> findAllByMajorId(Long majorid) {
        return chioceTestDao.findAllByMajorId(majorid);
    }

    public List<ChioceTest> findBtCids(String str) {
        return chioceTestDao.findAllByIds(str);
    }
}
