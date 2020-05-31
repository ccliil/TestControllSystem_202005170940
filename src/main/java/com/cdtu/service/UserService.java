package com.cdtu.service;

import com.cdtu.dao.UserDao;
import com.cdtu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public  User login(String username, String password) {
        return userDao.findUser(username,password);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public boolean updateUser(String tel, String password,String passwordText) {
        return userDao.updateUser(tel,password,passwordText);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }
    public boolean dropUser(Long id){
        return userDao.dropUser(id);
    }

    public boolean userUpdate(User user) {
        return userDao.userUpdate(user);
    }

    public List<User> findByUsername(String value) {
        return userDao.findByUsername(value);
    }
    public  int insertUser(User user){
        return userDao.insertUser(user);
    }

    public List<User> findByName(String value) {
        return userDao.findByName(value);
    }
    public Long count(){
        return userDao.count();
    }

    public User findByNamePerson(String userName,String tel) {
        return userDao.findByNamePerson(userName,tel);
    }

    public boolean updatePersonInfo(User user) {
        return userDao.updatePersonInfo(user);
    }

    public User findInfoById(Long id) {
        return userDao.findInfoById(id);
    }
}
