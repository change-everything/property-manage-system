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

 Date: 12/06/2022 15:20:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department_info
-- ----------------------------
DROP TABLE IF EXISTS `department_info`;
CREATE TABLE `department_info`  (
  `dep_id` int(18) NOT NULL AUTO_INCREMENT,
  `dep_num` int(255) NULL DEFAULT NULL,
  `dep_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dep_head` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dep_tel_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dep_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department_info
-- ----------------------------
INSERT INTO `department_info` VALUES (1, 195, '行政部', '李利', '69690505');
INSERT INTO `department_info` VALUES (2, 196, '人事部', '张璋', '68023489');
INSERT INTO `department_info` VALUES (3, 197, '后勤部', '赵钊', '63459087');
INSERT INTO `department_info` VALUES (4, 198, '安保部', '李创', '65482001');
INSERT INTO `department_info` VALUES (5, 199, '销售部', '孙牛', '69354120');
INSERT INTO `department_info` VALUES (11, 900, '待分配', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
