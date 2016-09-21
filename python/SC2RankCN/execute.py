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


def checkNewLadder(maxLadderId):
    oldLadderId = sql_service.db_selectNewestLadder()[0]
    executeLadders(oldLadderId, maxLadderId)

if __name__ == '__main__':
    checkNewLadder(34000)

    ladders = sql_service.db_selectLadderByQueue('LOTV_SOLO')
    for ladder in ladders:
        print('Adding player in ladder # ', ladder[0])
        playersInfo = parse_json.parsePlayersInfo(ladder)
        sql_service.db_addPlayers(playersInfo)

    sql_service.cursor.close()
    sql_service.conn.close()