package com.scarb.app;

import com.scarb.dao.PlayerMapper;
import com.scarb.model.Player;
import com.scarb.model.page.PlayerPage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Scarb on 9/14/2016.
 */
public class App {
    public static void main(String[] args){
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream;

        try {

            // 1. 获取sqlSessionFactory
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 2. 获取SqlSession
            SqlSession session = sqlSessionFactory.openSession();
            try{
                // method1
                Player player = (Player) session.selectOne("com.scarb.dao.PlayerMapper.selectByPrimaryKey", 963462);
                System.out.println(player.getName());

                List<Player> playerList = session.selectList("com.scarb.dao.PlayerMapper.selectPlayerByClanTag", "Hist", new RowBounds(1, 100));

                // method2
//                PlayerMapper mapper = session.getMapper(PlayerMapper.class);
//                Player player = mapper.selectByPrimaryKey(4248451);

//                System.out.println(player.getName());
//                System.out.println(player.getFavoriterace());

//                PlayerPage playerPage = new PlayerPage();
//                List<Player> pls = mapper.listAllPlayers();


                for (Player p: playerList)
                {
                    System.out.println(p.getName());
                }
            } finally {
                session.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
