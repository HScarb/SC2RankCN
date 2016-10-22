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

def db_addNewLadder(ld):
    try:
        cursor.execute('replace into newladder (LADDER_ID, LADDER_LEAGUE, LADDER_SEASON, LADDER_QUEUE, LADDER_TEAMTYPE, LADDER_TIER, '
                       'LADDER_MEMBER_COUNT, LADDER_MIN_RATING, LADDER_MAX_RATING) values (%s, %s, %s, %s, %s, %s, %s, %s, %s)',
                       [ld['id'], ld['league'], ld['season'], ld['queue'], ld['teamtype'], ld['tier'],
                       ld['member_count'], ld['min_rating'], ld['max_rating']])
        conn.commit()
    except mysql.connector.Error as e:
        print(e)
    except:
        print('Error occur in db_addNewLadder.')

def db_selectLadderByQueue(queue):
    try:
        cursor.execute('select * from ladder where queue = %s and iscurrent = 1', (queue,))
        values = cursor.fetchall()

        return values
    except mysql.connector.Error as e:
        print(e)

def db_selectNewLadderByData(season, league, queue = 201, teamtype = 0):
    try:
        cursor.execute('select * from newladder where LADDER_QUEUE = %s and LADDER_SEASON = %s and LADDER_LEAGUE = %s and LADDER_TEAMTYPE = %s',
                       (queue, season, league, teamtype,))
        values = cursor.fetchall()

        return values
    except mysql.connector.Error as e:
        print(e)
    except:
        print('Error occur in db_selectNewLadderByData.')

def db_addPlayer(player):
    cursor.execute('replace into player (id, name, clanName, clanTag, favoriteRace, points, wins, losses, joinTime, league, profilePath, '
                   'ladderid, updateTime, winRate, tier) '
                   'values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)',
                   [player['id'], player['name'], player['clanName'], player['clanTag'], player['favoriteRace'],
                    player['points'], player['wins'], player['losses'], player['joinTime'], player['league'], player['profilePath'],
                    player['ladderid'], player['updateTime'], player['winRate'], player['tier']])
    conn.commit()

def db_addLadderTier(ladderId, tier):
    try:
        cursor.execute('replace into ladder (id, tier) values(%s, %s)')
        conn.commit()
    except mysql.connector.Error as e:
        print(e)
    except:
        print('db_addLadderTier Error')


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
                league DESC ,
                tier ,
                points DESC
        )t
        SET r.rank = t.rowNum
        WHERE r.id = t.id
        '''
    cursor.execute(sql)
    conn.commit()