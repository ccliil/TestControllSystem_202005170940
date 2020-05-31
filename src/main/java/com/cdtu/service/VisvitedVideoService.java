package com.cdtu.service;

import com.cdtu.dao.VisvitedVideoDao;
import com.cdtu.entity.VistiedVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisvitedVideoService {
    @Autowired
    private VisvitedVideoDao dao;
    public VistiedVideo findByUid(Long uid){
        return dao.findByUid(uid);
    }
    public boolean insert(VistiedVideo vistiedVideo){
        return dao.insert(vistiedVideo);
    }

    public boolean updateInfo(VistiedVideo vv) {
        return dao.updateInfo(vv);
    }
}
