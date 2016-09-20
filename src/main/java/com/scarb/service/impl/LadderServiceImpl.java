package com.scarb.service.impl;

import com.scarb.dao.LadderMapper;
import com.scarb.model.Ladder;
import com.scarb.service.LadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Scarb on 9/14/2016.
 */
@Service
public class LadderServiceImpl implements LadderService{
    @Autowired
    private LadderMapper ladderMapper;

    public Ladder selectLadderById(Integer id) {
        return ladderMapper.selectByPrimaryKey(id);
    }
}
