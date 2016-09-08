package com.scarb.plugins.timetask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.scarb.model.Ladder;
import com.scarb.service.LadderService;
import com.scarb.service.impl.LadderServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Scarb on 8/31/2016.
 */
@Component("taskJob")
public class TestTimeTask {
    @Scheduled(cron = "2/10 * * * * ?")
    public void testTask()
    {

        System.out.println(System.currentTimeMillis());
        System.out.println(new java.util.Date(System.currentTimeMillis()));

        @Resource
        LadderService ladderService;

        for(int i = 33000; i < 34000; i++)
        {
            Ladder ladder = new Ladder();
            ladder = parseLadderByID(i);

        }

    }

    public Ladder parseLadderByID(int ID)
    {
        Ladder ladder = new Ladder();

        Crawler c = new Crawler();

        int ladderID = ID;
        String url = "https://api.battlenet.com.cn/sc2/ladder/" + ladderID + "?locale=zh_CN&apikey=smrfmd762jv8z9uqem7uufeadu8z8493";
        String json = c.craw(url);

        JSONObject jsonObject = JSONObject.fromObject(json);
        Collection values = jsonObject.values();
        Object[] playersObject = values.toArray();
        JSONArray playersArray = (JSONArray)playersObject[0];
        int playerNum = playersArray.size();  // 天梯中玩家人数
        int playerID;
        String playerName;
        JSONObject player = playersArray.getJSONObject(0);
        JSONObject character = player.getJSONObject("character");
        playerID = character.getInt("id");
        playerName = character.getString("displayName");

        // 访问玩家天梯api 获取天梯的类别
        String league;
        String queue;
        boolean findLadderID = false;
        url = "https://api.battlenet.com.cn/sc2/profile/" + playerID + "/1/" + playerName + "/ladders?locale=zh_CN&apikey=smrfmd762jv8z9uqem7uufeadu8z8493";
        json = c.craw(url);
        jsonObject = JSONObject.fromObject(json);
        JSONArray playerLadderArray = jsonObject.getJSONArray("currentSeason");

        for(int i = 0; i < playerLadderArray.size(); i++)
        {
            JSONObject ladderInfo = playerLadderArray.getJSONObject(i);
            ladderInfo.size();
            Collection vs = ladderInfo.values();
            Object[] os = vs.toArray();

            JSONArray ladderAry = (JSONArray)os[0];
            for(int j = 0; j < ladderAry.size(); j++)
            {
                Object ladderDetail = ladderAry.get(j);
                JSONObject ob = (JSONObject) ladderDetail;
                Collection details = ob.values();
                Object[] ds = details.toArray();
                if((int)ds[1] == ladderID)
                {
                    findLadderID = true;
                    ladder.setId(ladderID);
                    ladder.setIscurrent((byte)1);
                    ladder.setName((String)ds[0]);
                    ladder.setLeague((String)ds[4]);
                    ladder.setQueue((String)ds[5]);
                    ladder.setPlayernum(playerNum);
                    break;
                }
            }
            if(findLadderID)
                break;
        }

        if(findLadderID)
            return ladder;
        else
            return null;
    }
}
