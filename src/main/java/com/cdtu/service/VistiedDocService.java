package com.cdtu.service;

import com.cdtu.dao.VistiedDocDao;
import com.cdtu.entity.VistiedDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VistiedDocService {
    @Autowired
    private VistiedDocDao vistiedDocDao;

    public VistiedDoc findByUid(Long uid) {
        return vistiedDocDao.findByUid(uid);
    }

    public boolean insertDoc(VistiedDoc vistiedDoc1) {
        return  vistiedDocDao.insertDoc(vistiedDoc1);
    }

    public boolean updateInfo(VistiedDoc vistiedDoc1,Long id) {
        return vistiedDocDao.updateInfo(vistiedDoc1,id);
    }
}
