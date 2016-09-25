import mysql.connector
import parse_json
import time

conn = mysql.connector.connect(user='root', password='621374as', database='sc2rank')
cursor = conn.cursor()

def db_addLadder(ld):
    try:
        cursor.execute('replace into ladder (id, name, league, queue, playernum, iscurrent) values(%s, %s, %s, %s, %s, %s)'
                       ,[ld['id'], ld['name'], ld['league'], ld['queue'], ld['playernum'], ld['iscurrent']])
        conn.commit()

    except mysql.connector.Error as e:
        print(e)

def db_selectLadderByQueue(queue):
    try:
        cursor.execute('select * from ladder where queue = %s and iscurrent = 1', (queue,))
        values = cursor.fetchall()

        return values
    except mysql.connector.Error as e:
        print(e)

def db_addPlayer(player):
    cursor.execute('replace into player (id, name, clanName, clanTag, favoriteRace, points, wins, losses, joinTime, league, profilePath, '
                   'ladderid, updateTime, winRate) '
                   'values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)',
                   [player['id'], player['name'], player['clanName'], player['clanTag'], player['favoriteRace'],
                    player['points'], player['wins'], player['losses'], player['joinTime'], player['league'], player['profilePath'],
                    player['ladderid'], player['updateTime'], player['winRate']])
    conn.commit()

def db_addPlayers(players):
    try:
        for p in players:
            db_addPlayer(p)
    except:
        print('Error in db_addPlayers')

def db_selectNewestLadder():
    try:
        cursor.execute('select max(id) from ladder')
        value = cursor.fetchone()
        return value
    except mysql.connector.Error as e:
        print(e)

def db_clearTable(tableName):
    sql = 'TRUNCATE TABLE ' + str(tableName)
    cursor.execute(sql)
    conn.commit()

def db_orderPlayerByRank():
    sql = '''
        UPDATE player r,(
            SELECT a.id ,(@row:=@row + 1) AS rowNum
            FROM player a,(SELECT @row:=0)b
            ORDER BY
                CASE
                  WHEN league = 'GRANDMASTER' THEN 1
                  WHEN league = 'MASTER' THEN 2
                  WHEN league = 'DIAMOND' THEN 3
                  WHEN league = 'PLATINUM' THEN 4
                  WHEN league = 'GOLD' THEN 5
                  WHEN league = 'SILVER' THEN 6
                  WHEN league = 'BRONZE' THEN 7
                END
            , points DESC
        )t
        SET r.rank = t.rowNum
        WHERE r.id = t.id
        '''
    cursor.execute(sql)
    conn.commit()