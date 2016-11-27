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
        print('Error occur in db_addLadder.')
        print(e)
    except:
        print('Error occur in db_addLadder.')

def db_addNewLadder(ld):
    try:
        cursor.execute('replace into newladder (LADDER_ID, LADDER_LEAGUE, LADDER_SEASON, LADDER_QUEUE, LADDER_TEAMTYPE, LADDER_TIER, '
                       'LADDER_MEMBER_COUNT, LADDER_MIN_RATING, LADDER_MAX_RATING) values (%s, %s, %s, %s, %s, %s, %s, %s, %s)',
                       [ld['id'], ld['league'], ld['season'], ld['queue'], ld['teamtype'], ld['tier'],
                       ld['member_count'], ld['min_rating'], ld['max_rating']])
        conn.commit()
    except mysql.connector.Error as e:
        print('Error occur in db_addNewLadder.')
        print(e)
    except:
        print('Error occur in db_addNewLadder.')

def db_selectLadderByQueue(queue):
    try:
        cursor.execute('select * from ladder where queue = %s and iscurrent = 1', (queue,))
        values = cursor.fetchall()

        return values
    except mysql.connector.Error as e:
        print('Error occur in db_selectLadderByQueue')
        print(e)
    except:
        print('Error occur in db_selectLadderByQueue')

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
    try:
        cursor.execute('replace into player '
                       '(id, season, name, bnetname, clanName, clanTag, favoriteRace, league, tier, '
                       'mmr, points, wins, losses, ties, ladderid, updateTime, lastPlayedTime, joinTime, '
                       'winRate, profilePath) '
                       'values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)',
                       [player['id'], player['season'], player['name'], player['bnetname'],
                        player['clanName'], player['clanTag'], player['favoriteRace'], player['league'], player['tier'],
                        player['mmr'], player['points'], player['wins'], player['losses'], player['ties'],
                        player['ladderid'], player['updateTime'], player['lastPlayedTime'], player['joinTime'],
                        player['winRate'], player['profilePath']])
        conn.commit()
    except Exception as e:
        print('Error in db_addPlayer: ', player['name'])
        print(e)


def db_addLadderTier(ladderId, tier):
    try:
        cursor.execute('replace into ladder (id, tier) values(%s, %s)')
        conn.commit()
    except mysql.connector.Error as e:
        print(e)
    except:
        print('db_addLadderTier Error')

def db_deletePlayersInLadder(ladderId):
    try:
        print('Clearing the playersInfo in ladder #', ladderId)
        cursor.execute('delete from player where ladderid = %s', [ladderId])
        conn.commit()
    except mysql.connector.Error as e:
        print(e)
    except:
        print('db_deletePlayersInLadder Error')

def db_addPlayers(players):
    for p in players:
        db_addPlayer(p)

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
                mmr DESC
        )t
        SET r.rank = t.rowNum
        WHERE r.id = t.id
        '''
    cursor.execute(sql)
    conn.commit()