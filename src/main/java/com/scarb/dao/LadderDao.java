package com.scarb.dao;

import com.scarb.model.Ladder;

/**
 * Created by Scarb on 9/2/2016.
 */
public interface LadderDao {

    public Ladder selectLadderById(Integer ladderId);
}
