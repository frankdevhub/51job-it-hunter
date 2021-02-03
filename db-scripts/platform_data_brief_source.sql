/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80022
Source Host           : 127.0.0.1:3306
Source Database       : 51job_data_center

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-01-31 02:28:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `platform_data_brief_source`
-- ----------------------------
DROP TABLE IF EXISTS `platform_data_brief_source`;
CREATE TABLE `platform_data_brief_source` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '搜索类型',
  `jt` int DEFAULT NULL,
  `tags` varchar(100) DEFAULT NULL,
  `ad_track` varchar(100) DEFAULT NULL,
  `jobid` varchar(20) DEFAULT NULL,
  `coid` varchar(20) DEFAULT NULL,
  `effect` int DEFAULT NULL,
  `is_special_job` varchar(5) DEFAULT NULL,
  `job_href` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位链接',
  `job_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鑱屼綅鍚嶇О',
  `job_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鑱屼綅鏍囬',
  `company_href` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业信息链接',
  `company_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浼佷笟鍚嶇О',
  `providesalary_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `workarea` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宸ヤ綔鍖哄煙缂栫爜',
  `workarea_text` varchar(100) DEFAULT NULL,
  `updatedate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新时间(页面可能显示为发布时间)',
  `is_intern` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT ' 是否是实习岗位',
  `is_communicate` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '薪资是否面议',
  `companytype_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浼佷笟鎬ц川绫诲瀷',
  `degree_from` int DEFAULT NULL COMMENT '学历要求',
  `work_year` int DEFAULT NULL COMMENT '工作年限',
  `issue_date` varchar(50) DEFAULT NULL,
  `is_from_xyz` varchar(5) DEFAULT NULL,
  `jobwelf` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位福利信息',
  `jobwelf_list` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位福利信息',
  `attribute_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位其他属性例如:计划招聘人数,辖区,学历简述等',
  `companysize_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业人数规模',
  `companyind_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业行业运营类型',
  `adid` varchar(10) DEFAULT NULL,
  `context` longtext COMMENT '平台返回的报文源数据',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间(时间戳)',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间(时间戳)',
  PRIMARY KEY (`id`),
  KEY `plat_data_index` (`jt`,`jobid`,`coid`,`effect`,`workarea`,`is_intern`,`is_communicate`,`degree_from`,`work_year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_data_brief_source
-- ----------------------------
