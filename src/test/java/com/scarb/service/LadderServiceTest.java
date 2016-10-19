package com.scarb.service;

import com.scarb.baseTest.LadderTestCase;
import com.scarb.model.Comment;
import com.scarb.model.Ladder;
import com.scarb.model.Player;
import com.scarb.util.PagedResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Scarb on 9/14/2016.
 */
public class LadderServiceTest extends LadderTestCase{
    @Autowired
    private LadderService ladderService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CommentService commentService;


    Logger logger = Logger.getLogger(LadderService.class);

    @Test
    public void selectLadderByIdTest(){
        Ladder ladder = ladderService.selectLadderById(33000);

        logger.debug("Ladder find ============= " + ladder.getName());
    }

    @Test
    public void queryByPage(){
        PagedResult<Player> pagedResult = playerService.queryNameByPage(null, 1, 10);
        logger.debug("查找结果" + pagedResult);
    }

    /*@Test
    public void selectAllComments(){
        PagedResult<Comment> pagedResult = commentService.showComments(1,10);
        logger.debug("留言板查找结果" + pagedResult);
    }*/
}
