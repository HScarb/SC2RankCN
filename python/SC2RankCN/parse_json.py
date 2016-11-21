import requests
import json

import time


class Player:
    def __init__(self,
                 id,
                 name,
                 clanTag,
                 clanName,
                 profilePath,
                 points,
                 wins,
                 losses,
                 race):
        self.id = id
        self.name = name
        self.clanTag = clanTag
        self.clanName = clanName
        self.profilePath = profilePath
        self.points = points
        self.wins = wins
        self.losses = losses
        self.race = race

class Ladder:
    def __init__(self,
                 id,
                 name,
                 league,
                 queue,
                 playernum,
                 iscurrent):
        self.id = id
        self.name = name
        self.league = league
        self.queue = queue
        self.playernum = playernum
        self.iscurrent = iscurrent


def _parse_ladder_member(ladderData):
    data = json.loads(ladderData)
    data = data['ladderMembers']        # get ladder members


def parseLadderInfo(ladderID):
    # 根据ladderID解析出第一个玩家的信息，储存到一个dict里
    playerData = {}
    memberNum = 0
    try:
        url = 'https://api.battlenet.com.cn/sc2/ladder/' + str(ladderID) + '?locale=zh_CN&apikey=smrfmd762jv8z9uqem7uufeadu8z8493'
        r = requests.get(url)
        data = json.loads(r.text)
        if 'ladderMembers' not in data:
            print('Ladder # ' + str(ladderID) + ' is not ok.')
            return None
        else:
            playerData = data['ladderMembers'][0]['character']       # 玩家character信息
    except requests.HTTPError as e:
        print(e)
    except:
        print('Ladder parse Error cought when parsing ladder #', ladderID)


    if playerData == None:
        return None
    else:
        memberNum = len(data['ladderMembers'])
        # 根据解析出来的玩家信息，拼接出一个玩家信息的url
        url = 'https://api.battlenet.com.cn/sc2' + playerData['profilePath'] + 'ladders?locale=zh_CN&apikey=smrfmd762jv8z9uqem7uufeadu8z8493'
        try:
            findLadder = False
            ladder = {}
            r = requests.get(url)
            data = json.loads(r.text)
            # print(data)
            if 'currentSeason' in data:
                for item in data['currentSeason']:
                    if 'ladder' in item:
                        if len(item['ladder']) > 0:
                            for ldInfo in item['ladder']:
                                print('ldInfo[ladderId] = ', ldInfo['ladderId'])
                                if int(ldInfo['ladderId']) == int(ladderID):
                                    findLadder = True
                                    print('ladder #', ladderID, ' find.')
                                    ladder['id'] = ladderID
                                    ladder['name'] = ldInfo['ladderName']
                                    ladder['league'] = ldInfo['league']
                                    ladder['queue'] = ldInfo['matchMakingQueue']
                                    ladder['playernum'] = memberNum
                                    ladder['iscurrent'] = 1
                                    break
                    if findLadder == True:
                        break
            if findLadder == False:
                if 'previousSeason' in data:
                    for item in data['previousSeason']:
                        if 'ladder' in item:
                            if len(item['ladder']) > 0:
                                for ldInfo in item['ladder']:
                                    print('ldInfo[ladderId] = ', ldInfo['ladderId'])
                                    if int(ldInfo['ladderId']) == int(ladderID):
                                        findLadder = True
                                        print('ladder #', ladderID, ' find. Is previous.')
                                        ladder['id'] = ladderID
                                        ladder['name'] = ldInfo['ladderName']
                                        ladder['league'] = ldInfo['league']
                                        ladder['queue'] = ldInfo['matchMakingQueue']
                                        ladder['playernum'] = memberNum
                                        ladder['iscurrent'] = 0
                        if findLadder == True:
                            break
        except requests.HTTPError as e:
            print(e)
        except:
            print('Player ladder info parse error cought when parsing player #', playerData['profilePath'])
    # print(ladder)
    return ladder

def parsePlayersInfo(ladderTuple):
    """
    从某个天梯解析出其中所有的玩家
    :param ladderTuple: 从数据库中提取出来的ladderTuple (33000, '阿塔尼斯 ·埃塔', 'DIAMOND', 'LOTV_SOLO', 99, 0)
    :param ladderTuple: new: (33998, 0, 29, 201, 0, 0, 96, 2332, 2520) id, league, season, queue, teamtype, tier, count, minR, maxR
    """
    playersInfo = []
    url = 'https://api.battlenet.com.cn/sc2/ladder/' + str(ladderTuple[0]) + '?locale=zh_CN&apikey=smrfmd762jv8z9uqem7uufeadu8z8493'
    try:
        r = requests.get(url, timeout = 20)
        print('Request ', url , ' COMPLETE.')
        data = json.loads(r.text)
    except:
        print('Error when request url=', url)
        return None

    try:
        for p in data['ladderMembers']:
            player = {}
            player['id'] = p['character']['id']
            player['name'] = p['character']['displayName']
            player['clanName'] = p['character']['clanName']
            player['clanTag'] = p['character']['clanTag']
            player['profilePath'] = p['character']['profilePath']
            if 'favoriteRaceP1' in p:
                player['favoriteRace'] = p['favoriteRaceP1']
            else:
                player['favoriteRace'] = ''
            player['points'] = p['points']
            player['wins'] = p['wins']
            player['losses'] = p['losses']
            player['joinTime'] = p['joinTimestamp']
            player['league'] = ladderTuple[1]
            player['ladderid'] = ladderTuple[0]
            player['updateTime'] = time.time()
            player['winRate'] = p['wins'] / (p['wins'] + p['losses']) * 100
            player['tier'] = ladderTuple[5]
            playersInfo.append(player)
    except:
        print('Error when appending player data.')
        return None

    return playersInfo

def parsePlayersInfoNew(ladderTuple):
    """
    从某个天梯解析出其中所有的玩家
    :param ladderTuple: 从数据库中提取出来的ladderTuple (33000, '阿塔尼斯 ·埃塔', 'DIAMOND', 'LOTV_SOLO', 99, 0)
    :param ladderTuple: new: (33998, 0, 29, 201, 0, 0, 96, 2332, 2520) id, league, season, queue, teamtype, tier, count, minR, maxR
    """
    playersInfo = []
    url = 'https://api.battlenet.com.cn/data/sc2/ladder/' + str(ladderTuple[0]) + '?access_token=u266w4zrqha2hg4quebgd3mq'
    try:
        r = requests.get(url, timeout = 20)
        print('Request ', url , ' COMPLETE.')
        data = json.loads(r.text)
    except:
        print('Error when request url=', url)
        return None


    for p in data['team']:
        try:
            player = {}
            player['id'] = p['member'][0]['character_link']['id']
            player['name'] = p['member'][0]['legacy_link']['name']
            player['clanName'] = p['member'][0]['clan_link']['clan_name']
            player['clanTag'] = p['member'][0]['clan_link']['clan_tag']
            player['profilePath'] = p['member'][0]['legacy_link']['path']
            player['favoriteRace'] = p['member'][0]['played_race_count'][0]['race']['en_US']
            player['points'] = p['points']
            player['wins'] = p['wins']
            player['losses'] = p['losses']
            player['joinTime'] = p['join_time_stamp']
            player['league'] = ladderTuple[1]
            player['ladderid'] = ladderTuple[0]
            player['updateTime'] = time.time()
            player['winRate'] = p['wins'] / (p['wins'] + p['losses']) * 100
            player['tier'] = ladderTuple[5]
            player['mmr'] = p['rating']
        except:
            if player != {} and player['name']:
                print('Error when appending player: %s \'s data.' % player['name'])
            else:
                print('Error when appending player ? \'s data')
        finally:
            playersInfo.append(player)


    return playersInfo


def parseCurrentSeason():
    url = 'https://api.battlenet.com.cn/data/sc2/season/current?access_token=u266w4zrqha2hg4quebgd3mq'
    try:
        r = requests.get(url)
        jsonData = json.loads(r.text)
    except:
        print('Error when request url=', url)
        return None
    return jsonData["id"]

def parseLaddersByData(leagueID = 6, seasonID = 29, queueID = 201, teamType = 0):
    """
    根据参数解析出一个符合参数的ladder list
    :param seasonID: 赛季号
    :param queueID:  天梯联赛种类
        Queue ID

        1 - Wings of Liberty 1v1
        2 - Wings of Liberty 2v2
        3 - Wings of Liberty 3v3
        4 - Wings of Liberty 4v4
        101 - Heart of the Swarm 1v1
        102 - Heart of the Swarm 2v2
        103 - Heart of the Swarm 3v3
        104 - Heart of the Swarm 4v4
        201 - Legacy of the Void 1v1
        202 - Legacy of the Void 2v2
        203 - Legacy of the Void 3v3
        204 - Legacy of the Void 4v4
        206 - Legacy of the Void Archon
    :param teamType: 组队类型
        Team Type

        0 - Arranged
        1 - Random
    :param leagueID: 组别
        League ID

        0 - Bronze
        1 - Silver
        2 - Gold
        3 - Platinum
        4 - Diamond
        5 - Master
        6 - Grandmaster
    """
    ladderList = []
    url = 'https://api.battlenet.com.cn/data/sc2/league/' + str(seasonID) + '/' + str(queueID) + '/' + str(teamType) + '/' + str(leagueID) + '?access_token=u266w4zrqha2hg4quebgd3mq'
    try:
        r = requests.get(url)
        jsonData = json.loads(r.text)
    except:
        print('Error when request url=', url)
        return None
    tier = jsonData["tier"]
    if not "division" in tier[0]:
        print('Ladder in league #', leagueID, ' is not ready.')
        return None
    for item in tier:
        for ld in item["division"]:
            ladder = {}
            ladder["id"] = ld["ladder_id"]
            ladder["league"] = leagueID
            ladder["season"] = seasonID
            ladder["queue"] = queueID
            ladder["teamtype"] = teamType
            ladder["tier"] = item["id"]     # 组别等级
            ladder["member_count"] = ld["member_count"]
            ladder["min_rating"] = item["min_rating"]
            ladder["max_rating"] = item["max_rating"]
            ladderList.append(ladder)
    print(ladderList)
    return ladderList


if __name__ == '__main__':
    season = parseCurrentSeason()
    print(season)
    parseLaddersByData(5, season,201, 0)