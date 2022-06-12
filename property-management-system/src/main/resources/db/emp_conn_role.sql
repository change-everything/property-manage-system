/*
 Navicat Premium Data Transfer

 Source Server         : myPC
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : 127.0.0.1:3306
 Source Schema         : manager_system

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 12/06/2022 15:20:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emp_conn_role
-- ----------------------------
DROP TABLE IF EXISTS `emp_conn_role`;
CREATE TABLE `emp_conn_role`  (
  `emp_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_conn_role
-- ----------------------------
INSERT INTO `emp_conn_role` VALUES (1, 6, 4);

SET FOREIGN_KEY_CHECKS = 1;
