package com.scarb.service;

import com.scarb.model.Player;
import com.scarb.util.PagedResult;

import java.util.List;

/**
 * Created by Scarb on 9/14/2016.
 */
public interface PlayerService {

    public Player selectPlayerById(Integer playerId);

    public List<Player> selectPlayerByName(String playerName);

    public List<Player> selectPlayerByClanTag(String clanTag);

    public List<Player> selectPlayerByLeague(Integer league);

    PagedResult<Player> queryNameByPage(String playerName, Integer pageNo, Integer pageSize);
    PagedResult<Player> queryClanTagByPage(String clanTag, Integer pageNo, Integer pageSize);
    PagedResult<Player> queryLeagueByPage(Integer league, Integer pageNo, Integer pageSize);
}
