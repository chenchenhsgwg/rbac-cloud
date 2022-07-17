-- MySQL dump 10.13  Distrib 5.6.51, for Win64 (x86_64)
--
-- Host: localhost    Database: admin
-- ------------------------------------------------------
-- Server version	5.6.51

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `base_dept`
--

DROP TABLE IF EXISTS `base_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_dept` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `parent_id` varchar(36) NOT NULL DEFAULT 'root' COMMENT '父级部门id',
  `dept_name` varchar(256) NOT NULL COMMENT '部门名称',
  `dept_code` varchar(128) NOT NULL COMMENT '部门编码',
  `description` varchar(512) DEFAULT NULL COMMENT '部门说明',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(1:删除|0:未删除)',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `create_user_id` varchar(36) NOT NULL COMMENT '创建用户Id',
  `create_user_name` varchar(128) NOT NULL COMMENT '创建用户姓名',
  `update_time` datetime NOT NULL COMMENT '最后更新日期',
  `update_user_id` varchar(36) NOT NULL COMMENT '最后更新用户Id',
  `update_user_name` varchar(128) NOT NULL COMMENT '最后更新用户姓名',
  `tenant_id` varchar(36) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dept_code` (`dept_code`) USING BTREE COMMENT '部门编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础部门信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_dept`
--

LOCK TABLES `base_dept` WRITE;
/*!40000 ALTER TABLE `base_dept` DISABLE KEYS */;
INSERT INTO `base_dept` VALUES ('0aa8c2a89da143fbbcb5ffbec1c0fc2a','root','测试部门','test',NULL,0,'2022-07-13 05:36:37','1','超级管理员','2019-12-23 05:38:58','1','超级管理员','1');
/*!40000 ALTER TABLE `base_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_authorization`
--

DROP TABLE IF EXISTS `sys_role_authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_authorization` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(128) NOT NULL COMMENT '角色编码',
  `resource_id` varchar(64) NOT NULL COMMENT '资源id',
  `menu_code` varchar(128) NOT NULL COMMENT '菜单编码',
  `resource_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '资源类型（0菜单|1按钮）',
  `tenant_id` varchar(36) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色授权关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_authorization`
--

LOCK TABLES `sys_role_authorization` WRITE;
/*!40000 ALTER TABLE `sys_role_authorization` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_user`
--

DROP TABLE IF EXISTS `base_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `username` varchar(64) NOT NULL COMMENT '用户账户',
  `password` varchar(256) NOT NULL COMMENT '用户密码',
  `name` varchar(128) DEFAULT NULL COMMENT '用户姓名',
  `user_sex` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户性别（0男|1女）',
  `portrait` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `birthday` datetime DEFAULT NULL COMMENT '用户生日',
  `address` varchar(256) DEFAULT NULL COMMENT '用户地址',
  `mobile_phone` varchar(16) DEFAULT NULL COMMENT '用户手机号',
  `tel_phone` varchar(16) DEFAULT NULL COMMENT '用户电话号',
  `user_email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `description` varchar(512) DEFAULT NULL COMMENT '用户说明',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(1:删除|0:未删除)',
  `is_disabled` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否禁用（1是|0否）',
  `is_super_admin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否超级管理员（1是|0否）',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `create_user_id` varchar(36) NOT NULL COMMENT '创建用户Id',
  `create_user_name` varchar(128) NOT NULL COMMENT '创建用户姓名',
  `update_time` datetime NOT NULL COMMENT '最后更新日期',
  `update_user_id` varchar(36) NOT NULL COMMENT '最后更新用户Id',
  `update_user_name` varchar(128) NOT NULL COMMENT '最后更新用户姓名',
  `tenant_id` varchar(36) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE COMMENT '用户账户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_user`
--

LOCK TABLES `base_user` WRITE;
/*!40000 ALTER TABLE `base_user` DISABLE KEYS */;
INSERT INTO `base_user` VALUES ('1','admin','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','超级管理员',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,1,'2022-07-11 22:57:07','1','超级管理员','2022-07-12 22:56:59','1','超级管理员','1');
/*!40000 ALTER TABLE `base_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_user_role`
--

DROP TABLE IF EXISTS `base_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(36) NOT NULL COMMENT '用户Id',
  `role_code` varchar(128) NOT NULL COMMENT '角色编码',
  `tenant_id` varchar(36) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_user_role`
--

LOCK TABLES `base_user_role` WRITE;
/*!40000 ALTER TABLE `base_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` varchar(36) NOT NULL DEFAULT 'root' COMMENT '主键',
  `parent_id` varchar(36) NOT NULL DEFAULT 'root' COMMENT '父级菜单id',
  `menu_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '菜单类型(0:目录|1:菜单)',
  `menu_code` varchar(128) NOT NULL COMMENT '菜单编码',
  `menu_title` varchar(64) NOT NULL COMMENT '菜单标题',
  `menu_icon` varchar(128) DEFAULT NULL COMMENT '菜单图标',
  `menu_path` varchar(256) NOT NULL DEFAULT '/' COMMENT '菜单路径（路径别名）',
  `component` varchar(255) DEFAULT NULL COMMENT '菜单组件地址(菜单url地址)',
  `redirect` varchar(256) DEFAULT NULL COMMENT '重定向地址',
  `order_num` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `description` varchar(512) DEFAULT NULL COMMENT '菜单描述',
  `hidden` tinyint(1) unsigned DEFAULT '0' COMMENT '是否隐藏(1:隐藏|0:未隐藏)',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(1:删除|0:未删除)',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `create_user_id` varchar(36) NOT NULL COMMENT '创建用户Id',
  `create_user_name` varchar(128) NOT NULL COMMENT '创建用户姓名',
  `update_time` datetime NOT NULL COMMENT '最后更新日期',
  `update_user_id` varchar(36) NOT NULL COMMENT '最后更新用户Id',
  `update_user_name` varchar(128) NOT NULL COMMENT '最后更新用户姓名',
  `tenant_id` varchar(36) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_code` (`menu_code`) USING BTREE COMMENT '按钮编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统基础菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('01549dbf3c6542b99c7559f5d2883bdc','80e863628d5f453981dcbb853a3ef953',1,'sysMenu','菜单管理',NULL,'sysMenu','/system/sys/menu/index',NULL,3,'菜单管理',0,0,'2022-07-15 13:57:33','1','admin','2022-07-15 05:31:25','1','admin','1'),('1422833c6ab044f5b89261bace41ad45','21bfe17560284601a7c1b88d6b01f607',1,'dashboard','仪表板','','dashboard','/center/dashboard/index',NULL,1,'个人主页',0,0,'2022-07-15 12:04:31','1','admin','2022-07-15 13:37:39','1','admin','1'),('21bfe17560284601a7c1b88d6b01f607','root',0,'center','个人中心','dashboard','/center',NULL,'/center/dashboard',1,'个人中心',0,0,'2022-07-15 01:05:47','1','admin','2022-07-15 09:25:46','1','admin','1'),('224767b51e954e3299a49a92913c3128','80e863628d5f4539814cbb853a3ef953',1,'deptUser','部门用户',NULL,'deptUser','/system/base/deptUser/index',NULL,3,NULL,0,0,'2022-07-15 13:16:20','1','超级管理员','2022-07-15 03:07:08','1','超级管理员','1'),('49992d462a004f6d95d53a6e95fa6071','80e863628d5f453981dcbb853a3ef953',1,'sysRole','角色管理',NULL,'sysRole','/system/sys/role/index',NULL,4,'角色管理',0,0,'2022-07-15 13:58:05','1','admin','2022-07-15 05:31:30','1','admin','1'),('49f5a59dfe5a4f22832052e7ad0b9f48','80e863628d5f453981dcbb853a3ef953',1,'swagger','接口文档',NULL,'swagger','/system/sys/swagger/index',NULL,6,'接口文档',0,0,'2022-07-15 13:58:28','1','admin','2022-07-15 12:32:22','f47e1a6da8bd4976a8faa4fe5124a6b7','晨生','1'),('5ed6df281aa2492cb9f026bc393db58d','80e863628d5f4539814cbb853a3ef953',1,'baseUser','用户管理',NULL,'baseUser','/system/base/user/index',NULL,1,'角色管理',0,0,'2022-07-15 12:13:14','1','admin','2022-07-15 02:48:53','1','admin','1'),('80e863628d5f4539814cbb853a3ef953','root',0,'base','系统管理','example','/base',NULL,'/system/sysDict',2,'系统配置中心',0,0,'2022-07-15 13:56:24','1','admin','2022-07-15 01:49:43','1','admin','1'),('80e863628d5f453981dcbb853a3ef953','root',0,'system','系统配置管理','component','/system',NULL,'/system/sysDict',2,'系统配置中心',0,0,'2022-07-15 13:56:24','1','admin','2022-07-15 01:49:43','1','admin','1'),('a1a70a12612d4330b01268ba4f63e068','21bfe17560284601a7c1b88d6b01f607',1,'centerPersonal','个人信息','','personal','/center/personal/index','',2,'系统配置中心',0,0,'2022-07-15 13:35:38','1','admin','2022-07-15 13:41:21','1','admin','1'),('a789477a626041bd86aecbd2ae995d16','80e863628d5f453981dcbb853a3ef953',1,'sysDict','数据字典',NULL,'sysDict','/system/sys/dict/index',NULL,3,'数据字典',0,0,'2022-07-15 13:57:47','1','admin','2022-07-15 12:12:07','1','admin','1'),('d273f2b2f3cf4bfebb487a0634612904','80e863628d5f4539814cbb853a3ef953',1,'baseDept','部门管理','','baseDept','/system/base/dept/index',NULL,2,NULL,0,0,'2022-07-15 01:48:33','1','admin','2022-07-15 21:06:03','1','admin','1');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `parent_id` varchar(36) NOT NULL DEFAULT 'root' COMMENT '父级角色id',
  `role_code` varchar(128) NOT NULL COMMENT '角色编码',
  `role_name` varchar(256) NOT NULL COMMENT '角色名称',
  `description` varchar(512) DEFAULT NULL COMMENT '角色描述',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(1:删除|0:未删除)',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `create_user_id` varchar(36) NOT NULL COMMENT '创建用户Id',
  `create_user_name` varchar(128) NOT NULL COMMENT '创建用户姓名',
  `update_time` datetime NOT NULL COMMENT '最后更新日期',
  `update_user_id` varchar(36) NOT NULL COMMENT '最后更新用户Id',
  `update_user_name` varchar(128) NOT NULL COMMENT '最后更新用户姓名',
  `tenant_id` varchar(36) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code` (`role_code`) USING BTREE COMMENT '角色编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统基础角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-15  6:39:51
