package com.scarb.service.impl;

import com.github.pagehelper.PageHelper;
import com.scarb.dao.PlayerMapper;
import com.scarb.model.Player;
import com.scarb.service.PlayerService;
import com.scarb.util.BeanUtil;
import com.scarb.util.PagedResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Scarb on 9/16/2016.
 */
@Service
public class PlayerServiceImpl implements PlayerService{

    @Resource
    private PlayerMapper playerDao;


    @Override
    public Player selectPlayerById(Integer playerId) {
        return playerDao.selectByPrimaryKey(playerId);
    }

    @Override
    public List<Player> selectPlayerByName(String playerName) {
        return playerDao.selectPlayerByName(playerName);
    }

    @Override
    public List<Player> selectPlayerByClanTag(String clanTag) {
        return playerDao.selectPlayerByClanTag(clanTag);
    }

    @Override
    public List<Player> selectPlayerByLeague(String league) {
        return playerDao.selectPlayerByLeague(league);
    }

    @Override
    public PagedResult<Player> queryNameByPage(String playerName, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize); // startPage告诉拦截器开始分页，分页参数是这两个
        return BeanUtil.toPagedResult(playerDao.selectPlayerByName(playerName));
    }

    @Override
    public PagedResult<Player> queryClanTagByPage(String clanTag, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize); // startPage告诉拦截器开始分页，分页参数是这两个
        return BeanUtil.toPagedResult(playerDao.selectPlayerByClanTag(clanTag));
    }

    @Override
    public PagedResult<Player> queryLeagueByPage(String league, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize); // startPage告诉拦截器开始分页，分页参数是这两个
        return BeanUtil.toPagedResult(playerDao.selectPlayerByLeague(league));
    }
}
