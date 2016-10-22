package com.scarb.dao;

import com.scarb.model.Player;
import com.scarb.model.page.PlayerPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);

    public List<Player> listAllPlayers();

    public List<Player> selectPlayerByName(String name);

    public List<Player> selectPlayerByClanTag(String clanTag);

    public List<Player> selectPlayerByLeague(Integer league);
}