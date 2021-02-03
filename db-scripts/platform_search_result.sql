/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80022
Source Host           : 127.0.0.1:3306
Source Database       : 51job_data_center

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-01-30 23:57:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `platform_search_result`
-- ----------------------------
DROP TABLE IF EXISTS `platform_search_result`;
CREATE TABLE `platform_search_result` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '涓婚敭id',
  `job_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位名称',
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司名称',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位地点',
  `salary_range_chars` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '薪资描述原文字符串',
  `salary_range_min` double(10,2) DEFAULT NULL COMMENT '薪资最小值',
  `salary_range_max` double(10,2) DEFAULT NULL COMMENT '薪资最大值',
  `salary_time_unit` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '薪资时间计量单位',
  `salary_numeric_unit` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '薪资计量单位',
  `head_count` int DEFAULT NULL COMMENT '计划招聘人数',
  `is_define_by_w` tinyint DEFAULT NULL COMMENT '是否以万计量',
  `is_define_by_k` tinyint DEFAULT NULL COMMENT '是否以千计量',
  `is_define_by_day` tinyint DEFAULT NULL COMMENT '是否按日计量',
  `is_define_by_month` tinyint DEFAULT NULL COMMENT '是否月薪计量',
  `is_define_by_year` tinyint DEFAULT NULL COMMENT '是否年薪计量',
  `is_internship_pos` tinyint DEFAULT NULL COMMENT '是否内推职位',
  `is_campus_only` tinyint DEFAULT NULL COMMENT '是否校招职位',
  `is_salary_negotiable` tinyint DEFAULT NULL COMMENT '薪资是否可商议',
  `publish_date_char` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发布日期',
  `publish_date_month_numeric` int DEFAULT NULL COMMENT '发布月份（月）',
  `publish_date_day_numeric` int DEFAULT NULL COMMENT '发布日期（天）',
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位链接地址',
  `mark_id` int DEFAULT NULL,
  `create_time` bigint DEFAULT NULL COMMENT '创建时间(时间戳)',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间(时间戳)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

