package com.scarb.service.impl;

import com.scarb.dao.UserMapper;
import com.scarb.model.User;
import com.scarb.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Scarb on 9/29/2016.
 */
@Service
public class UserServiceImpl implements UserSerivce{
    @Autowired
    private UserMapper userDao;

    @Override
    public User selectUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public User findUserByLoginName(String username) {
        System.out.print("findUserByLoginName call!");
        return userDao.findUserByLoginName(username);
    }
}
