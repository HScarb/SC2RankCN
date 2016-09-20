package com.scarb.dao;

import com.scarb.model.Ladder;

public interface LadderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Ladder record);

    int insertSelective(Ladder record);

    Ladder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ladder record);

    int updateByPrimaryKey(Ladder record);
}