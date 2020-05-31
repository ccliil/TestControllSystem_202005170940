package com.cdtu.service;

import com.cdtu.dao.MajorDao;
import com.cdtu.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MajorService {
    @Autowired
    private MajorDao majorDao;
    public List<Major> findAll(){
        return majorDao.findAll();
    }

    public Major findById(Long majorid) {
        return majorDao.findByid(majorid);
    }
}
