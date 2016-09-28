package com.scarb.service.impl;

import com.scarb.dao.UserMapper;
import com.scarb.model.User;
import com.scarb.model.UserExample;
import com.scarb.service.RegisterService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Scarb on 9/27/2016.
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService{

    private static Logger logger = Logger.getLogger(RegisterService.class);
    @Resource
    private UserMapper userDao;

    @Override
    public int insert(User record) {
        try {
            return userDao.insert(record);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countByExample(UserExample example) {
        try {
            return (int) userDao.countByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
