package com.scarb.service;

import com.scarb.model.User;
import com.scarb.model.UserExample;

/**
 * Created by Scarb on 9/27/2016.
 */
public interface RegisterService {

    public int insert(User record);

    public int countByExample(UserExample example);
}
