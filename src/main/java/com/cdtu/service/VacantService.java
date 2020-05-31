package com.cdtu.service;

import com.cdtu.dao.VacantDao;
import com.cdtu.entity.VacantTest;
import com.cdtu.test.Vacant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacantService {
    @Autowired
    private VacantDao vacantDao;

    public List<VacantTest> findAll() {
        return vacantDao.finAll();
    }

    public List<VacantTest> findByMajorId(Long value) {
        return vacantDao.findByMajorId(value);
    }

    public List<VacantTest> findByUid(Long value) {
        return vacantDao.findByUid(value);
    }

    public boolean delete(Long vid) {
        return vacantDao.deleteVacant(vid);
    }

    public VacantTest findByVid(Long vid) {
        return vacantDao.findByVid(vid);
    }

    public boolean updateVacant(VacantTest vacantTest) {
        return vacantDao.updateVacant(vacantTest);
    }

    public boolean add(VacantTest vacantTest) {
        return vacantDao.addVacant(vacantTest);
    }

    public List<VacantTest> findByMajAndUid(Long majorid, Long uid) {
        return vacantDao.findByMajAndUid(majorid,uid);
    }

    public boolean teacherUpdate(VacantTest vacantTest) {
        return vacantDao.teacherUpdate(vacantTest);
    }

    public List<VacantTest> findAllByMajorId(Long majorid) {
        return vacantDao.findAllByMajorId(majorid);
    }

    public List<VacantTest> findByVids(String str2) {
        return vacantDao.findByVids(str2);
    }
}
