package com.cdtu.service;

import com.cdtu.dao.JudgeTestDao;
import com.cdtu.entity.ChioceTest;
import com.cdtu.entity.JudgeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeTestService {
    @Autowired
    private JudgeTestDao judgeTestDao;

    public List<JudgeTest> findAll() {
        return judgeTestDao.findAll();
    }

    public List<JudgeTest> findByMajorid(Long value) {
        return judgeTestDao.findByMajorid(value);
    }

    public List<JudgeTest> findByUid(Long value) {
        return judgeTestDao.findByUid(value);
    }

    public boolean delete(Long jid) {
        return judgeTestDao.delete(jid);
    }

    public JudgeTest findByJid(Long jid) {
        return judgeTestDao.findByJid(jid);
    }

    public boolean update(JudgeTest judgeTest) {
        return judgeTestDao.update(judgeTest);
    }

    public boolean addJudge(JudgeTest judgeTest) {
        return judgeTestDao.addJudge(judgeTest);
    }

    public List<JudgeTest> findByMajAndUid(Long uid, Long majorid) {
        return judgeTestDao.findByMajAndUid(uid,majorid);
    }

    public boolean teacherUpdate(JudgeTest judgeTest) {
        return judgeTestDao.teacherUpdate(judgeTest);
    }

    public List<JudgeTest> findAllByMajorId(Long majorid) {
        return judgeTestDao.findAllByMajorId(majorid);
    }

    public List<JudgeTest> findByJids(String str1) {
        return judgeTestDao.findByJids(str1);
    }
}
