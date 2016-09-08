package com.scarb.service;

import com.scarb.model.Ladder;
import org.springframework.stereotype.Service;

/**
 * Created by Scarb on 9/2/2016.
 */
public interface LadderService {

    int add(Ladder record);

    Ladder findOne(int id);

    int update(Ladder redord);

}
