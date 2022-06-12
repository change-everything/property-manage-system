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

 Date: 12/06/2022 15:21:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `perm_id` int(18) NOT NULL AUTO_INCREMENT,
  `perm_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `perm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`perm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES (1, 'add_emp', '添加员工', 'POST /emp');
INSERT INTO `permissions` VALUES (2, 'query_emp', '查询员工', 'GET /emp');
INSERT INTO `permissions` VALUES (3, 'delete_emp', '删除员工', 'DELETE /emp');
INSERT INTO `permissions` VALUES (4, 'modify_emp', '修改员工', 'PUT /emp');
INSERT INTO `permissions` VALUES (5, 'query_charge', '查询收费单', 'GET /charge');
INSERT INTO `permissions` VALUES (6, 'delete_charge', '删除收费单', 'DELETE /charge');
INSERT INTO `permissions` VALUES (7, 'modify_charge', '修改收费单', 'PUT /charge');
INSERT INTO `permissions` VALUES (8, 'add_owner', '添加业主', 'POST /owner');
INSERT INTO `permissions` VALUES (9, 'query_owner', '查询业主', 'GET /owner');
INSERT INTO `permissions` VALUES (10, 'delete_owner', '删除业主', 'DELETE /owner');
INSERT INTO `permissions` VALUES (11, 'modify_owner', '修改业主', 'PUT /owner');
INSERT INTO `permissions` VALUES (12, 'query_dept', '查询部门', 'GET /dept');
INSERT INTO `permissions` VALUES (13, 'add_dept', '新增部门', 'POST /dept');
INSERT INTO `permissions` VALUES (14, 'delete_dept', '删除部门', 'DELETE /dept');
INSERT INTO `permissions` VALUES (15, 'modify_dept', '修改部门', 'PUT /dept');
INSERT INTO `permissions` VALUES (16, 'query_role', '查询角色', 'GET /role');
INSERT INTO `permissions` VALUES (17, 'delete_role', '删除角色', 'DELETE /role');
INSERT INTO `permissions` VALUES (18, 'grant_Authority', '分配权限', 'POST /role');
INSERT INTO `permissions` VALUES (19, 'add_charge', '新增收费单', 'GET /charge');

SET FOREIGN_KEY_CHECKS = 1;
