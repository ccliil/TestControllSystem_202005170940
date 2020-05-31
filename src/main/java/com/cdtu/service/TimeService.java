package com.cdtu.service;

import com.cdtu.dao.TimeDao;
import com.cdtu.entity.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {
    @Autowired
    private TimeDao timeDao;

    public boolean addTime(Time time) {
        return timeDao.addTime(time);
    }

    public Time findTime() {
        return timeDao.findTime();
    }
}
