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

 Date: 12/06/2022 15:21:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role_conn_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_conn_perm`;
CREATE TABLE `role_conn_perm`  (
  `role_perm_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `perm_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_perm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_conn_perm
-- ----------------------------
INSERT INTO `role_conn_perm` VALUES (1, 1, 1);
INSERT INTO `role_conn_perm` VALUES (2, 1, 2);
INSERT INTO `role_conn_perm` VALUES (3, 1, 3);
INSERT INTO `role_conn_perm` VALUES (4, 1, 4);
INSERT INTO `role_conn_perm` VALUES (5, 2, 6);
INSERT INTO `role_conn_perm` VALUES (6, 2, 7);
INSERT INTO `role_conn_perm` VALUES (7, 2, 8);
INSERT INTO `role_conn_perm` VALUES (8, 3, 9);
INSERT INTO `role_conn_perm` VALUES (9, 3, 10);
INSERT INTO `role_conn_perm` VALUES (10, 3, 11);
INSERT INTO `role_conn_perm` VALUES (11, 3, 12);
INSERT INTO `role_conn_perm` VALUES (27, 4, 1);
INSERT INTO `role_conn_perm` VALUES (28, 4, 2);
INSERT INTO `role_conn_perm` VALUES (29, 4, 3);
INSERT INTO `role_conn_perm` VALUES (30, 4, 4);
INSERT INTO `role_conn_perm` VALUES (31, 4, 5);
INSERT INTO `role_conn_perm` VALUES (32, 4, 6);
INSERT INTO `role_conn_perm` VALUES (33, 4, 7);
INSERT INTO `role_conn_perm` VALUES (34, 4, 8);
INSERT INTO `role_conn_perm` VALUES (35, 4, 9);
INSERT INTO `role_conn_perm` VALUES (36, 4, 10);
INSERT INTO `role_conn_perm` VALUES (37, 4, 11);
INSERT INTO `role_conn_perm` VALUES (38, 4, 12);
INSERT INTO `role_conn_perm` VALUES (39, 4, 13);
INSERT INTO `role_conn_perm` VALUES (40, 4, 14);
INSERT INTO `role_conn_perm` VALUES (41, 4, 15);
INSERT INTO `role_conn_perm` VALUES (42, 4, 16);
INSERT INTO `role_conn_perm` VALUES (43, 4, 17);
INSERT INTO `role_conn_perm` VALUES (44, 4, 18);
INSERT INTO `role_conn_perm` VALUES (45, 4, 19);

SET FOREIGN_KEY_CHECKS = 1;
