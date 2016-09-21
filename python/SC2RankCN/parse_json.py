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
    print(ladder)
    return ladder

def parsePlayersInfo(ladderTuple):
    """
    从某个天梯解析出其中所有的玩家
    :param ladderTuple: 从数据库中提取出来的ladderTuple (33000, '阿塔尼斯 ·埃塔', 'DIAMOND', 'LOTV_SOLO', 99, 0)
    """
    playersInfo = []
    url = 'https://api.battlenet.com.cn/sc2/ladder/' + str(ladderTuple[0]) + '?locale=zh_CN&apikey=smrfmd762jv8z9uqem7uufeadu8z8493'
    r = requests.get(url)
    data = json.loads(r.text)
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
        player['league'] = ladderTuple[2]
        player['ladderid'] = ladderTuple[0]
        player['updateTime'] = time.time()
        player['winRate'] = p['wins'] / (p['wins'] + p['losses']) * 100
        playersInfo.append(player)
    return playersInfo

