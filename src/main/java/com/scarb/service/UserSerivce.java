package com.scarb.service;

import com.scarb.model.User;

/**
 * Created by Scarb on 9/29/2016.
 */
public interface UserSerivce {
    User selectUserById(Integer userId);

    User findUserByLoginName(String username);
}
