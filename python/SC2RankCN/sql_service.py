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
    for p in players:
        db_addPlayer(p)

def db_selectNewestLadder():
    try:
        cursor.execute('select max(id) from ladder')
        value = cursor.fetchone()
        return value
    except mysql.connector.Error as e:
        print(e)
