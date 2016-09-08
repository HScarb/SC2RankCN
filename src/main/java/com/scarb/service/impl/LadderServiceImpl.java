package com.scarb.service.impl;

import com.scarb.dao.LadderDao;
import com.scarb.mapper.LadderMapper;
import com.scarb.model.Ladder;
import com.scarb.service.LadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Scarb on 9/5/2016.
 */
@Service
public class LadderServiceImpl implements LadderService{

    @Resource
    LadderMapper ladderMapper;

    @Override
    public int add(Ladder record) {
        int id = ladderMapper.insertSelective(record);
        return id;
    }

    @Override
    public Ladder findOne(int id) {
        return null;
    }

    @Override
    public int update(Ladder redord) {
        return 0;
    }
}
