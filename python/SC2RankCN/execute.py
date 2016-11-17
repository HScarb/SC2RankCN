import parse_json
import sql_service
import mysql.connector

def executeLadders(minId, maxId):
    # 处理从minId到maxId的天梯，添加进ladder表
    notOkNum = 0
    for i in range(minId, maxId):
        print('appending ladder #', i)
        ladder = parse_json.parseLadderInfo(i)
        if ladder != None:
            notOkNum = 0
            if len(ladder) > 0:
                sql_service.db_addLadder(ladder)
        else:
            notOkNum = notOkNum + 1
            if notOkNum > 5:
                print('execute ladder done.')
                break

def parsePlayersFromLadders(minId):
    # 从天梯列表中获取玩家数据，存储进玩家表
    ladders = sql_service.db_selectLadderByQueue('LOTV_SOLO')
    for ladder in ladders:
        if ladder[0] >= minId:
            print('Adding player in ladder # ', ladder[0])
            playersInfo = parse_json.parsePlayersInfo(ladder)
            if playersInfo != None:
                print('PlayersInfo got. Now adding into data base.')
                sql_service.db_addPlayers(playersInfo)
            else:
                print('playersInfo is None')
                continue

def parsePlayersFromAllLadders():
    # 从天梯列表中获取玩家数据，存储进玩家表
    ladders = sql_service.db_selectLadderByQueue('LOTV_SOLO')
    for ladder in ladders:
        print('Adding player in ladder # ', ladder[0], ' league: ', ladder[1])
        playersInfo = parse_json.parsePlayersInfo(ladder)
        if playersInfo != None:
            print('PlayersInfo got. Now adding into data base.')
            sql_service.db_addPlayers(playersInfo)
        else:
            print('playersInfo is None')
            continue

def parsePlayersFromNewLaddersByData(season, league, queue = 201, teamtype = 0):
    # 从天梯列表中获取玩家数据，存储进玩家表
    ladders = sql_service.db_selectNewLadderByData(season, league, queue, teamtype)
    for ladder in ladders:
        print('Adding player in ladder # ', ladder[0])
        sql_service.db_deletePlayersInLadder(ladder[0])
        playersInfo = parse_json.parsePlayersInfo(ladder)
        if playersInfo != None:
            print('PlayersInfo got. Now adding into data base.')
            sql_service.db_addPlayers(playersInfo)
        else:
            print('playersInfo is None')
            continue

def addLaddersByData(leagueID = 6, seasonID = 29, queueID = 201, teamType = 0):
    # ladder["id"]: ladderID
    # ladder["member_count"]: 玩家数量
    # ladder["tire"]: 组别中的小组别
    print('parsing ladders leagueID: ' + str(leagueID))
    ladderList = parse_json.parseLaddersByData(leagueID, seasonID, queueID, teamType)
    if ladderList == None or ladderList == []:
        return
    for ladder in ladderList:
        print('Add ladder # ' + str(ladder["id"]) + ' in db.')
        sql_service.db_addNewLadder(ladder)


def checkNewLadder(maxLadderId):
    oldLadderId = sql_service.db_selectNewestLadder()[0]
    executeLadders(oldLadderId, maxLadderId)

if __name__ == '__main__':
    # 得到当前赛季号
    season = parse_json.parseCurrentSeason()

    # 将玩家数据表现清空
    #print('clearing player table...')
    #sql_service.db_clearTable('player')

    for i in range(6, -1, -1):
        addLaddersByData(i, season)

    for i in range(6, -1, -1):
        print('parsing players in leage # ', i)
        parsePlayersFromNewLaddersByData(season, i)

    # 将玩家表按组别和分数添加排名序号
    print('ordering players rank....')
    sql_service.db_orderPlayerByRank()

    sql_service.cursor.close()
    sql_service.conn.close()