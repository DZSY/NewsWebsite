package com.dzsy.service;

import com.dzsy.dao.UserDAO;
import com.dzsy.entity.User;

import java.util.Random;

/**
 * Created by positif on 06/05/2017.
 */

public class UserService {
    private UserDAO userDao;

    public boolean isUserExist(String username) {
        return userDao.isUserExist(username);
    }
    public boolean isEmailExist(String email) { return userDao.isEmailExist(email); }

    public  boolean isPasswordMatching(String username, String password) {
        return userDao.isPasswordMatching(username, password);
    }

    public String getActivationCode(String username) {
        return userDao.getActivationCode(username);
    }

    public String Register(String username, String password, String email) {


        Random random = new Random();
        StringBuffer activation = new StringBuffer();
        for (int i = 0; i < 6; i++)
            activation.append(random.nextInt(10));

        ///邮件的内容
        StringBuffer sb=new StringBuffer("亲爱的 " + username + " 您好，您的激活码是 " + activation + " ，请尽快验证");


        //发送邮件
        User user = new User(username, password, email, activation.toString());
        Long as=5480l;

        userDao.addUser(user);//保存注册信息
        SendEmail.send(email, sb.toString());
    }


    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

}