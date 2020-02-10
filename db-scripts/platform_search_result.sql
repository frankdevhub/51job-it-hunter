/*
Navicat MySQL Data Transfer

Source Server         : 39.98.246.50
Source Server Version : 50645
Source Host           : 39.98.246.50:3306
Source Database       : frankdevhub_company_db

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2020-02-11 03:12:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for platform_search_result
-- ----------------------------
DROP TABLE IF EXISTS `platform_search_result`;
CREATE TABLE `platform_search_result` (
  `id` bigint(20) NOT NULL,
  `search_key_id` bigint(20) DEFAULT NULL,
  `search_keyword_text` varchar(50) DEFAULT NULL,
  `job_title` varchar(50) DEFAULT NULL,
  `company_name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `salary_range_chars` varchar(50) DEFAULT NULL,
  `salary_range_min` double(10,2) DEFAULT NULL,
  `salary_range_max` double(10,2) DEFAULT NULL,
  `salary_time_unit` varchar(5) DEFAULT NULL,
  `salary_numeric_unit` varchar(5) DEFAULT NULL,
  `is_define_by_w` tinyint(4) DEFAULT NULL,
  `is_define_by_k` tinyint(4) DEFAULT NULL,
  `is_define_by_day` tinyint(4) DEFAULT NULL,
  `is_define_by_month` tinyint(4) DEFAULT NULL,
  `is_define_by_year` tinyint(4) DEFAULT NULL,
  `is_internship_pos` tinyint(4) DEFAULT NULL,
  `is_campus_only` tinyint(4) DEFAULT NULL,
  `is_salary_negotiable` tinyint(4) DEFAULT NULL,
  `publish_date_char` varchar(50) DEFAULT NULL,
  `publish_date_month_numeric` int(5) DEFAULT NULL,
  `publish_date_day_numeric` int(5) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `mark_id` int(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
