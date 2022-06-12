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

 Date: 12/06/2022 15:21:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for owner_info
-- ----------------------------
DROP TABLE IF EXISTS `owner_info`;
CREATE TABLE `owner_info`  (
  `owner_id` int(18) NOT NULL AUTO_INCREMENT,
  `owner_num` int(255) NULL DEFAULT NULL,
  `owner_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room_num` int(255) NULL DEFAULT NULL,
  `floor_area` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`owner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner_info
-- ----------------------------
INSERT INTO `owner_info` VALUES (1, 1001, '裴永鹏', 101, '150', 'XH公司', '18890908543');
INSERT INTO `owner_info` VALUES (2, 1002, '张凡', 102, '120', 'JH公司', '18293483903');
INSERT INTO `owner_info` VALUES (3, 1003, '孙明君', 201, '140', 'JN公司', '15989302380');
INSERT INTO `owner_info` VALUES (4, 1004, '何周同', 202, '130', 'WQ公司', '17023974231');

SET FOREIGN_KEY_CHECKS = 1;
