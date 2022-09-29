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

 Date: 16/06/2022 09:02:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for charge_info
-- ----------------------------
DROP TABLE IF EXISTS `charge_info`;
CREATE TABLE `charge_info`  (
  `cha_id` int(18) NOT NULL AUTO_INCREMENT,
  `room_num` int(255) NULL DEFAULT NULL,
  `owner_num` int(255) NULL DEFAULT NULL,
  `cha_date` date NULL DEFAULT NULL,
  `cha_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cha_quantity` int(50) NULL DEFAULT NULL,
  `cha_sum` double(255, 0) NULL DEFAULT NULL,
  `emp_num` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cha_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charge_info
-- ----------------------------
INSERT INTO `charge_info` VALUES (2, 102, 1002, '2022-05-01', '电费', 2, 300, 19502);
INSERT INTO `charge_info` VALUES (5, 101, 1001, '2022-06-10', '电费', 100, 1152, 19501);
INSERT INTO `charge_info` VALUES (6, 101, 1001, '2022-06-10', '卫生费', 1, 122, 19501);
INSERT INTO `charge_info` VALUES (7, 101, 1001, '2022-06-10', '物业费', 1, 1550, 19501);
INSERT INTO `charge_info` VALUES (8, 102, 1002, '2022-06-10', '水费', 10, 120, 19502);
INSERT INTO `charge_info` VALUES (9, 102, 1002, '2022-06-10', '卫生费', 1, 120, 19502);
INSERT INTO `charge_info` VALUES (10, 102, 1002, '2022-06-10', '物业费', 1, 1550, 19502);
INSERT INTO `charge_info` VALUES (11, 101, 1001, '2022-06-12', '水费', 22, 125, 19501);
INSERT INTO `charge_info` VALUES (12, 108, 1005, '2022-06-15', '水费', 10, 120, 10001);
INSERT INTO `charge_info` VALUES (13, 108, 1005, '2022-06-15', '电费', 20, 200, 10001);
INSERT INTO `charge_info` VALUES (14, 108, 1005, '2022-06-15', '物业费', 1, 1500, 10001);
INSERT INTO `charge_info` VALUES (15, 108, 1005, '2022-06-15', '卫生费', 1, 200, 10001);
INSERT INTO `charge_info` VALUES (20, 303, 1004, '2022-06-15', '水费', 22, 220, 10001);
INSERT INTO `charge_info` VALUES (21, 303, 1004, '2022-06-15', '电费', 30, 600, 10001);
INSERT INTO `charge_info` VALUES (22, 303, 1004, '2022-06-15', '卫生费', 1, 120, 10001);
INSERT INTO `charge_info` VALUES (23, 303, 1004, '2022-06-15', '物业费', 1, 1500, 10001);
INSERT INTO `charge_info` VALUES (24, 109, 1003, '2022-06-15', '水费', 12, 120, 10001);
INSERT INTO `charge_info` VALUES (25, 109, 1003, '2022-06-15', '电费', 20, 300, 10001);
INSERT INTO `charge_info` VALUES (26, 109, 1003, '2022-06-15', '物业费', 1, 1500, 10001);
INSERT INTO `charge_info` VALUES (27, 109, 1003, '2022-06-15', '卫生费', 1, 120, 10001);

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department_info
-- ----------------------------
INSERT INTO `department_info` VALUES (1, 195, '行政部', '李利', '69690505');
INSERT INTO `department_info` VALUES (3, 197, '后勤部', '赵钊', '63459087');
INSERT INTO `department_info` VALUES (4, 198, '安保部', '李创', '65482001');
INSERT INTO `department_info` VALUES (5, 199, '销售部', '孙牛', '69354120');
INSERT INTO `department_info` VALUES (11, 900, '待分配', NULL, NULL);
INSERT INTO `department_info` VALUES (14, 196, '营业部', '薛强', '68785542');

-- ----------------------------
-- Table structure for emp_conn_role
-- ----------------------------
DROP TABLE IF EXISTS `emp_conn_role`;
CREATE TABLE `emp_conn_role`  (
  `emp_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_conn_role
-- ----------------------------
INSERT INTO `emp_conn_role` VALUES (1, 6, 4);
INSERT INTO `emp_conn_role` VALUES (2, 10, 1);
INSERT INTO `emp_conn_role` VALUES (3, 12, 3);
INSERT INTO `emp_conn_role` VALUES (4, 20, 2);
INSERT INTO `emp_conn_role` VALUES (5, 22, 6);
INSERT INTO `emp_conn_role` VALUES (6, 2, 10);
INSERT INTO `emp_conn_role` VALUES (7, 11, 14);
INSERT INTO `emp_conn_role` VALUES (8, 19, 15);
INSERT INTO `emp_conn_role` VALUES (9, 13, 12);
INSERT INTO `emp_conn_role` VALUES (10, 21, 11);
INSERT INTO `emp_conn_role` VALUES (11, 23, 13);
INSERT INTO `emp_conn_role` VALUES (12, 24, 13);
INSERT INTO `emp_conn_role` VALUES (13, 25, 11);
INSERT INTO `emp_conn_role` VALUES (14, 3, 15);
INSERT INTO `emp_conn_role` VALUES (15, 4, 6);
INSERT INTO `emp_conn_role` VALUES (16, 5, 10);
INSERT INTO `emp_conn_role` VALUES (17, 14, 14);
INSERT INTO `emp_conn_role` VALUES (18, 19, 15);
INSERT INTO `emp_conn_role` VALUES (19, 30, 6);
INSERT INTO `emp_conn_role` VALUES (20, 35, 1);

-- ----------------------------
-- Table structure for employee_info
-- ----------------------------
DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE `employee_info`  (
  `emp_id` int(18) NOT NULL AUTO_INCREMENT,
  `emp_num` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_dep_num` int(255) NULL DEFAULT NULL,
  `birth_date` date NULL DEFAULT NULL,
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_info
-- ----------------------------
INSERT INTO `employee_info` VALUES (2, '19501', '吴令仪', 195, '1994-04-11', '女', '北京市海淀区', '16589564586', '$2a$10$/fs2JL/Dypng7pYZP8tZ6ufZZ9ZqtnhCSQAtCian2w5ND.kn/a4fK');
INSERT INTO `employee_info` VALUES (3, '19502', '李尔', 199, '2000-06-14', '男', '河北省张家口市', '18569635687', '$2a$10$YSaBhUZyXotbndbhzVM/Y.kZlTeLKZDkBI/Afe5oLQ7jHXfjSml3m');
INSERT INTO `employee_info` VALUES (4, '19503', '武善', 197, '1995-06-07', '女', '天津市和平区', '15836597845', '$2a$10$2CGX3/lOIlMhShtCWYEI0ueUi2Pos7fh2bzURF2VSkH981pb1gC7e');
INSERT INTO `employee_info` VALUES (5, '19602', '刘玲', 195, '1988-12-02', '女', '天津市河西区', '13865696586', '$2a$10$7.aiPssj.GA/9ZMdUWTRHO/Rs/sw1om8jdmoBHTNRqBn5LYUildpC');
INSERT INTO `employee_info` VALUES (6, '10001', 'root', 195, '2022-06-10', '男', '北京市朝阳区', '13945857451', '$2a$10$3NZ3qZLB6TbIqwYC3ojjB.1DQ/XCiz1krLy6S3/ZJaCGKvd17URwq');
INSERT INTO `employee_info` VALUES (10, '19505', '李利', 195, '1972-04-07', '男', '香港特别行政区 香港岛', '18545879654', '$2a$10$fekkzCuRafLMFQILDuHr0.Cm5BqnJZjFCYXf.lr.w5oJ2vfx0OuIS');
INSERT INTO `employee_info` VALUES (11, '19606', '张璋', 199, '1998-10-21', '女', '福建省 龙岩市', '18154974104', '$2a$10$CEMzb8DfYOnRWpw.m8tNLOBwNhbVzI3PxeXbGfRcIX1BmsvRJQjGS');
INSERT INTO `employee_info` VALUES (12, '19707', '赵钊', 197, '1989-12-01', '男', '江西省 新余市', '13654174043', '$2a$10$TeqRNmMMyPWggm8/J7qX9udG/uQT8CHjFfybH6KfDeL0M3UTBUVRS');
INSERT INTO `employee_info` VALUES (13, '19808', '李创', 198, '1991-04-30', '男', '河北省 沧州市', '18645273787', '$2a$10$JRBeeGcf2i30x9tBGQoc2.93ht2iIjFHHXPGPG/JgkTb3h0LEekly');
INSERT INTO `employee_info` VALUES (14, '19909', '孙牛', 199, '1998-08-14', '男', '辽宁省 沈阳市', '18191895615', '$2a$10$7Yw3uDACow1plffdzpkRDe31ovzRfR0ZlB6Ccn60DGphy7qS9F3Eu');
INSERT INTO `employee_info` VALUES (19, '83', '陈洋', 199, '2010-11-11', '女', '上海 上海市', '19840452092', '$2a$10$Sm2cEDlAdDCLTbXqhFUmo.FXGtG3xxT9OlRuAtBLTI1eo99lARxMi');
INSERT INTO `employee_info` VALUES (20, '99', '薛强', 196, '2003-01-19', '男', '吉林省 松原市', '18154925471', '$2a$10$3vyPbd4OeWFfQc/0rXwkRe5/R1HjOldxWSSeaaYk/qZ2y58/am55K');
INSERT INTO `employee_info` VALUES (21, '87', '秦秀英', 196, '1998-03-06', '男', '浙江省 舟山市', '19814885166', '$2a$10$hd/DFVueddWK2ctZhT.1VuzIYLNiJFJpD7cktoJvrFpoCuu1r6fVm');
INSERT INTO `employee_info` VALUES (22, '70', '田洋', 197, '1985-10-12', '女', '陕西省 汉中市', '18616536329', '$2a$10$2c4VwOhoZLaGKHGlFdR4C.kWRvM1TRBiwN28UD2ldVrZEe4nKE/aW');
INSERT INTO `employee_info` VALUES (23, '87', '崔强', 198, '2015-08-15', '男', '贵州省 黔东南苗族侗族自治州', '18633638299', '$2a$10$kDu80bDWSuky3lVlIfMcRu99wcvmk4TgDcPXyOb8AkGaS1QtylE0S');
INSERT INTO `employee_info` VALUES (24, '72', '沈超', 198, '1978-10-25', '女', '黑龙江省 鹤岗市', '18162644732', '$2a$10$20xHCk4tP9jWhK9fJH1XM.P7S1YI5Rdmi/UVyrA5h3gwkJkjK/skq');
INSERT INTO `employee_info` VALUES (25, '76', '蒋敏', 200, '1996-08-26', '女', '上海 上海市', '18185891994', '$2a$10$dzNu/iE3zGPtcUTu4BlElOebXFuqglef.qA4csNaNTaTCBz2b0ZmK');
INSERT INTO `employee_info` VALUES (30, '266', '张三', 197, '2022-06-05', '男', '中国', '13315318165', '$2a$10$VwPYBvkFkfSTS9q8oCdiDOKnr5wAIj7Y2f09Tx8d7rRTs81jCfkYS');
INSERT INTO `employee_info` VALUES (35, '3328', '3232', 195, '2022-06-05', '男', '32323', '13315318165', '$2a$10$wFYlV4MckaggfHe90nisBO2vHSaiM7LGhP9hs4RGhxek9w9KRaCHK');

-- ----------------------------
-- Table structure for no_pay_charge_info
-- ----------------------------
DROP TABLE IF EXISTS `no_pay_charge_info`;
CREATE TABLE `no_pay_charge_info`  (
  `cha_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_num` int(11) NULL DEFAULT NULL,
  `owner_num` int(11) NULL DEFAULT NULL,
  `cha_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cha_quantity` int(11) NULL DEFAULT NULL,
  `cha_sum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cha_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of no_pay_charge_info
-- ----------------------------
INSERT INTO `no_pay_charge_info` VALUES (17, 402, 1006, '水费', 12, 120);
INSERT INTO `no_pay_charge_info` VALUES (18, 402, 1006, '电费', 20, 222);
INSERT INTO `no_pay_charge_info` VALUES (19, 402, 1006, '物业费', 1, 1500);
INSERT INTO `no_pay_charge_info` VALUES (20, 402, 1006, '卫生费', 1, 200);
INSERT INTO `no_pay_charge_info` VALUES (21, 403, 1007, '水费', 13, 130);
INSERT INTO `no_pay_charge_info` VALUES (22, 403, 1007, '电费', 20, 222);
INSERT INTO `no_pay_charge_info` VALUES (23, 403, 1007, '物业费', 1, 1500);
INSERT INTO `no_pay_charge_info` VALUES (24, 403, 1007, '卫生费', 1, 200);
INSERT INTO `no_pay_charge_info` VALUES (25, 404, 1008, '水费', 20, 600);
INSERT INTO `no_pay_charge_info` VALUES (26, 404, 1008, '电费', 20, 600);
INSERT INTO `no_pay_charge_info` VALUES (27, 404, 1008, '物业费', 1, 1500);
INSERT INTO `no_pay_charge_info` VALUES (28, 404, 1008, '卫生费', 1, 200);
INSERT INTO `no_pay_charge_info` VALUES (29, 405, 1009, '水费', 40, 400);
INSERT INTO `no_pay_charge_info` VALUES (30, 405, 1009, '电费', 20, 500);
INSERT INTO `no_pay_charge_info` VALUES (31, 405, 1009, '物业费', 1, 1500);
INSERT INTO `no_pay_charge_info` VALUES (32, 405, 1009, '卫生费', 1, 200);

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner_info
-- ----------------------------
INSERT INTO `owner_info` VALUES (1, 1001, '裴永鹏', 101, '1991', 'XH公司', '18890908543');
INSERT INTO `owner_info` VALUES (2, 1002, '张凡', 102, '120', 'JH公司', '18293483903');
INSERT INTO `owner_info` VALUES (3, 1003, '孙明君', 201, '140', 'JN公司', '15989302380');
INSERT INTO `owner_info` VALUES (4, 1004, '何周同', 202, '130', 'WQ公司', '17023974231');
INSERT INTO `owner_info` VALUES (6, 1005, '孙越', 108, '180', 'XM公司', '15578885555');
INSERT INTO `owner_info` VALUES (8, 1699126987, '测试', 1006, '323', 'XX公司', '13315318165');
INSERT INTO `owner_info` VALUES (9, 1006, '岳云鹏', 402, '150', 'FD公司', '15489865555');
INSERT INTO `owner_info` VALUES (12, 1007, '小熊维尼', 403, '130', 'LO公司', '15845654125');
INSERT INTO `owner_info` VALUES (13, 1008, '夏露露', 404, '120', 'OL公司', '15487965225');
INSERT INTO `owner_info` VALUES (14, 1009, '将大大', 405, '110', 'PL公司', '13545658456');

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
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES (1, 'add_emp', '添加员工', 'POST /emp');
INSERT INTO `permissions` VALUES (2, 'query_emp', '查询员工', 'GET /emp');
INSERT INTO `permissions` VALUES (3, 'delete_emp', '删除员工', 'DELETE /emp');
INSERT INTO `permissions` VALUES (4, 'modify_emp', '修改员工', 'PUT /emp');
INSERT INTO `permissions` VALUES (5, 'query_noPayCharge', '查询未缴费收费单', 'GET /noPayCharge');
INSERT INTO `permissions` VALUES (6, 'delete_charge', '删除已缴费收费单', 'DELETE /charge');
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
INSERT INTO `permissions` VALUES (19, 'add_charge', '新增已缴费收费单', 'POST /charge');
INSERT INTO `permissions` VALUES (20, 'query_noPayCharge', '查询未缴费收费单', 'POST /noPayCharge');
INSERT INTO `permissions` VALUES (21, 'query_charge', '查询已缴费收费单', 'GET /charge');
INSERT INTO `permissions` VALUES (22, 'query_perm', '查询权限', 'GET /perm');

-- ----------------------------
-- Table structure for role_conn_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_conn_perm`;
CREATE TABLE `role_conn_perm`  (
  `role_perm_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `perm_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_perm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_conn_perm
-- ----------------------------
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
INSERT INTO `role_conn_perm` VALUES (46, 4, 20);
INSERT INTO `role_conn_perm` VALUES (47, 4, 21);
INSERT INTO `role_conn_perm` VALUES (48, 4, 22);
INSERT INTO `role_conn_perm` VALUES (49, 1, 1);
INSERT INTO `role_conn_perm` VALUES (50, 1, 2);
INSERT INTO `role_conn_perm` VALUES (51, 1, 3);
INSERT INTO `role_conn_perm` VALUES (52, 1, 4);
INSERT INTO `role_conn_perm` VALUES (53, 1, 1);
INSERT INTO `role_conn_perm` VALUES (54, 1, 2);
INSERT INTO `role_conn_perm` VALUES (55, 1, 3);
INSERT INTO `role_conn_perm` VALUES (56, 1, 4);
INSERT INTO `role_conn_perm` VALUES (57, 1, 12);
INSERT INTO `role_conn_perm` VALUES (58, 1, 13);

-- ----------------------------
-- Table structure for role_group
-- ----------------------------
DROP TABLE IF EXISTS `role_group`;
CREATE TABLE `role_group`  (
  `role_id` int(18) NOT NULL AUTO_INCREMENT,
  `dep_num` int(11) NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_dest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_group
-- ----------------------------
INSERT INTO `role_group` VALUES (1, 195, 'GENERAL_MANAGER', '总经理');
INSERT INTO `role_group` VALUES (2, 196, 'TOLL_MANAGER', '收费经理');
INSERT INTO `role_group` VALUES (3, 197, 'LOGISTICSL_MANAGER', '后勤经理');
INSERT INTO `role_group` VALUES (4, NULL, 'SUPER_MANAGER', '超级管理员');
INSERT INTO `role_group` VALUES (6, 197, 'LOGISTICSL_PERSONAL', '保洁人员');
INSERT INTO `role_group` VALUES (10, 195, 'SECRETART', '行政秘书');
INSERT INTO `role_group` VALUES (11, 196, 'TOLL', '收费员');
INSERT INTO `role_group` VALUES (12, 198, 'SECURITY_MANAGER', '安保经理');
INSERT INTO `role_group` VALUES (13, 198, 'SECURITY_PERSONAL', '保安');
INSERT INTO `role_group` VALUES (14, 199, 'SALES_MANAGER', '销售经理');
INSERT INTO `role_group` VALUES (15, 199, 'SALES_PERSONAL', '销售人员');

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_test
-- ----------------------------
INSERT INTO `user_test` VALUES (1, '张三', '111');
INSERT INTO `user_test` VALUES (2, '李四', '222');
INSERT INTO `user_test` VALUES (3, '王五', '333');

SET FOREIGN_KEY_CHECKS = 1;
