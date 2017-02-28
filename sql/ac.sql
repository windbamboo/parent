/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ac

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-28 23:13:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ac_app
-- ----------------------------
DROP TABLE IF EXISTS `ac_app`;
CREATE TABLE `ac_app` (
  `app_id` int(10) unsigned NOT NULL COMMENT '应用id',
  `app_name` varchar(30) NOT NULL COMMENT '应用名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `uk_app_name` (`app_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_app
-- ----------------------------

-- ----------------------------
-- Table structure for ac_group
-- ----------------------------
DROP TABLE IF EXISTS `ac_group`;
CREATE TABLE `ac_group` (
  `group_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `status` tinyint(3) unsigned NOT NULL COMMENT '组状态',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `uk_group_name` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_group
-- ----------------------------

-- ----------------------------
-- Table structure for ac_group_role
-- ----------------------------
DROP TABLE IF EXISTS `ac_group_role`;
CREATE TABLE `ac_group_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `group_id` int(11) unsigned NOT NULL COMMENT '组id',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_ac_group_role_role_id` (`role_id`),
  KEY `fk_ac_group_role_group_id` (`group_id`),
  CONSTRAINT `fk_ac_group_role_group_id` FOREIGN KEY (`group_id`) REFERENCES `ac_group` (`group_id`),
  CONSTRAINT `fk_ac_group_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `ac_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_group_role
-- ----------------------------

-- ----------------------------
-- Table structure for ac_log
-- ----------------------------
DROP TABLE IF EXISTS `ac_log`;
CREATE TABLE `ac_log` (
  `log_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `module_id` varchar(128) NOT NULL COMMENT '模块id',
  `action_type` tinyint(4) NOT NULL COMMENT '动作类型',
  `action_time` datetime NOT NULL COMMENT '动作触发时间',
  `action_detail` varchar(1024) NOT NULL DEFAULT '' COMMENT '动作详情',
  `user_ip` char(15) NOT NULL DEFAULT '' COMMENT '用户ip',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`log_id`),
  KEY `fk_ac_log_user_id` (`user_id`),
  KEY `fk_ac_log_module_id` (`module_id`),
  CONSTRAINT `fk_ac_log_module_id` FOREIGN KEY (`module_id`) REFERENCES `ac_module` (`module_id`),
  CONSTRAINT `fk_ac_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `ac_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_log
-- ----------------------------

-- ----------------------------
-- Table structure for ac_module
-- ----------------------------
DROP TABLE IF EXISTS `ac_module`;
CREATE TABLE `ac_module` (
  `module_id` varchar(128) NOT NULL COMMENT '模块id',
  `module_name` varchar(64) NOT NULL COMMENT '模块名称',
  `module_father` varchar(128) NOT NULL COMMENT '模块父id',
  `status` tinyint(4) unsigned NOT NULL COMMENT '状态',
  `uri` varchar(128) DEFAULT '' COMMENT 'uri',
  `module_type` tinyint(4) unsigned NOT NULL COMMENT '模块类型',
  `app_id` int(11) unsigned NOT NULL COMMENT '应用id',
  `icon` varchar(64) NOT NULL DEFAULT '' COMMENT '菜单栏图标',
  `display_menu` tinyint(3) unsigned NOT NULL COMMENT '是否显示',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`module_id`),
  KEY `fk_ac_module_module_id` (`module_father`),
  KEY `fk_ac_module_app_id` (`app_id`),
  CONSTRAINT `fk_ac_module_app_id` FOREIGN KEY (`app_id`) REFERENCES `ac_app` (`app_id`),
  CONSTRAINT `fk_ac_module_module_id` FOREIGN KEY (`module_father`) REFERENCES `ac_module` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_module
-- ----------------------------

-- ----------------------------
-- Table structure for ac_power
-- ----------------------------
DROP TABLE IF EXISTS `ac_power`;
CREATE TABLE `ac_power` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色id',
  `module_id` varchar(128) NOT NULL COMMENT '模块id',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_ac_power_role_id` (`role_id`),
  KEY `fk_ac_power_module_id` (`module_id`),
  CONSTRAINT `fk_ac_power_module_id` FOREIGN KEY (`module_id`) REFERENCES `ac_module` (`module_id`),
  CONSTRAINT `fk_ac_power_role_id` FOREIGN KEY (`role_id`) REFERENCES `ac_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_power
-- ----------------------------

-- ----------------------------
-- Table structure for ac_role
-- ----------------------------
DROP TABLE IF EXISTS `ac_role`;
CREATE TABLE `ac_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `status` tinyint(3) unsigned NOT NULL COMMENT '角色状态',
  `app_id` int(11) unsigned NOT NULL COMMENT '应用id',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`),
  KEY `fk_ac_role_app_id` (`app_id`),
  KEY `uk_role_name_app_id` (`role_name`,`app_id`),
  CONSTRAINT `fk_ac_role_app_id` FOREIGN KEY (`app_id`) REFERENCES `ac_app` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_role
-- ----------------------------

-- ----------------------------
-- Table structure for ac_user
-- ----------------------------
DROP TABLE IF EXISTS `ac_user`;
CREATE TABLE `ac_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL COMMENT '昵称',
  `tel` varchar(11) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态',
  `email_addr` varchar(256) DEFAULT NULL COMMENT '邮箱地址',
  `user_pwd` varchar(64) NOT NULL COMMENT '密码',
  `login_time` datetime NOT NULL COMMENT '登陆时间',
  `login_ip` varchar(15) NOT NULL COMMENT '登陆ip',
  `app_id` int(11) unsigned NOT NULL COMMENT '应用id',
  `group_id` int(11) unsigned NOT NULL COMMENT '组id',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_user_name_app_id` (`user_name`,`app_id`),
  UNIQUE KEY `uk_nick_name_app_id` (`nick_name`,`app_id`),
  KEY `fk_ac_user_app_id` (`app_id`),
  KEY `fk_ac_user_group_id` (`group_id`),
  CONSTRAINT `fk_ac_user_app_id` FOREIGN KEY (`app_id`) REFERENCES `ac_app` (`app_id`),
  CONSTRAINT `fk_ac_user_group_id` FOREIGN KEY (`group_id`) REFERENCES `ac_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_user
-- ----------------------------

-- ----------------------------
-- Table structure for ac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ac_user_role`;
CREATE TABLE `ac_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_ac_user_role_user_id` (`user_id`),
  KEY `fk_ac_user_role_role_id` (`role_id`),
  CONSTRAINT `fk_ac_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `ac_role` (`role_id`),
  CONSTRAINT `fk_ac_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `ac_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_user_role
-- ----------------------------
