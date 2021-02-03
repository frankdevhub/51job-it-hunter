/*
Navicat MySQL Data Transfer

Source Server         : 39.98.246.50
Source Server Version : 80020
Source Host           : 39.98.246.50:3306
Source Database       : 51job_data_center

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2021-02-03 23:02:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `platform_company_info`
-- ----------------------------
DROP TABLE IF EXISTS `platform_company_info`;
CREATE TABLE `platform_company_info` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `mark_id` int DEFAULT NULL COMMENT '唯一标识',
  `plat_company_logo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业商标',
  `plat_company_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业类型(民营企业)',
  `plat_company_industry` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业行业类型(计算机软件,汽车)',
  `plat_company_info` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '企业职位信息',
  `plat_company_link` varchar(100) DEFAULT NULL COMMENT '招聘平台公司信息介绍链接',
  `context` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '平台上的企业信息html源码',
  `tag_list` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业标签(环境,人文氛围,福利,个人社保公积金)',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间(时间戳)',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_company_info
-- ----------------------------
