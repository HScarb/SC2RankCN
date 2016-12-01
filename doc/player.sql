/*
Navicat MySQL Data Transfer

Source Server         : 11111
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : sc2rank

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-12-01 13:28:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `id` int(11) NOT NULL,
  `season` int(11) NOT NULL,
  `rank` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `bnetname` varchar(255) DEFAULT NULL,
  `clanName` varchar(255) DEFAULT NULL,
  `clanTag` varchar(255) DEFAULT NULL,
  `favoriteRace` varchar(255) DEFAULT NULL,
  `league` varchar(255) DEFAULT NULL,
  `tier` int(11) DEFAULT NULL,
  `mmr` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `wins` int(11) DEFAULT NULL,
  `losses` int(11) DEFAULT NULL,
  `ties` int(11) DEFAULT NULL,
  `ladderid` int(11) DEFAULT NULL,
  `updateTime` int(11) DEFAULT NULL,
  `lastPlayedTime` int(11) DEFAULT NULL,
  `joinTime` int(11) DEFAULT NULL,
  `winRate` int(11) DEFAULT NULL,
  `profilePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
