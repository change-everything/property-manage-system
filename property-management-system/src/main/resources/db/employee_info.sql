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

 Date: 12/06/2022 15:21:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee_info
-- ----------------------------
DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE `employee_info`  (
  `emp_id` int(18) NOT NULL AUTO_INCREMENT,
  `emp_num` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth_date` date NULL DEFAULT NULL,
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_dep_num` int(255) NULL DEFAULT NULL,
  `emp_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_info
-- ----------------------------
INSERT INTO `employee_info` VALUES (1, '19001', '张艺', '1980-05-05', '女', '天津市津南区', '15968489865', '正式员工', 197, '$2a$10$Y8FxyI00utPJhPXyWjK6KOp79BV4Q5DrOQaBMUK2d8/I2zGY4RdsO');
INSERT INTO `employee_info` VALUES (2, '19501', '吴令仪', '1994-04-11', '女', '北京市海淀区', '16589564586', '正式员工', 198, '$2a$10$/fs2JL/Dypng7pYZP8tZ6ufZZ9ZqtnhCSQAtCian2w5ND.kn/a4fK');
INSERT INTO `employee_info` VALUES (3, '19502', '李尔', '2000-06-14', '男', '河北省张家口市', '18569635687', '组长', 199, '$2a$10$YSaBhUZyXotbndbhzVM/Y.kZlTeLKZDkBI/Afe5oLQ7jHXfjSml3m');
INSERT INTO `employee_info` VALUES (4, '19503', '武善', '1995-06-07', '女', '天津市和平区', '15836597845', '实习生', 197, '$2a$10$2CGX3/lOIlMhShtCWYEI0ueUi2Pos7fh2bzURF2VSkH981pb1gC7e');
INSERT INTO `employee_info` VALUES (5, '19602', '刘玲', '1988-12-02', '女', '天津市河西区', '13865696586', '组长', 196, '$2a$10$hlH2iPTyRN..tej4TD8TO..KCcKwzQZnYPSvN6iGQNx.Z4EDQxpR.');
INSERT INTO `employee_info` VALUES (6, '10001', 'root', '2022-06-10', '男', '北京市朝阳区', '13945857451', '管理员账号', 195, '$2a$10$3NZ3qZLB6TbIqwYC3ojjB.1DQ/XCiz1krLy6S3/ZJaCGKvd17URwq');
INSERT INTO `employee_info` VALUES (10, '19505', '李利', '1972-04-07', '男', '香港特别行政区 香港岛', '18545879654', '经理', 195, '$2a$10$fekkzCuRafLMFQILDuHr0.Cm5BqnJZjFCYXf.lr.w5oJ2vfx0OuIS');
INSERT INTO `employee_info` VALUES (11, '19606', '张璋', '1998-10-21', '女', '福建省 龙岩市', '18154974104', '经理', 196, '$2a$10$CEMzb8DfYOnRWpw.m8tNLOBwNhbVzI3PxeXbGfRcIX1BmsvRJQjGS');
INSERT INTO `employee_info` VALUES (12, '19707', '赵钊', '1989-12-01', '男', '江西省 新余市', '13654174043', '经理', 197, '$2a$10$TeqRNmMMyPWggm8/J7qX9udG/uQT8CHjFfybH6KfDeL0M3UTBUVRS');
INSERT INTO `employee_info` VALUES (13, '19808', '李创', '1991-04-30', '男', '河北省 沧州市', '18645273787', '经理', 198, '$2a$10$JRBeeGcf2i30x9tBGQoc2.93ht2iIjFHHXPGPG/JgkTb3h0LEekly');
INSERT INTO `employee_info` VALUES (14, '19909', '孙牛', '1998-08-14', '男', '辽宁省 沈阳市', '18191895615', '经理', 199, '$2a$10$7Yw3uDACow1plffdzpkRDe31ovzRfR0ZlB6Ccn60DGphy7qS9F3Eu');
INSERT INTO `employee_info` VALUES (18, '71', '阎桂英', '2002-03-24', '男', '新疆维吾尔自治区 哈密地区', '13487272403', '实习生', 195, '$2a$10$pcFkZBqsOr0lE3LKXl.9COFN8KzqSqgUnm97KxfauxTRL4ImkXFNu');
INSERT INTO `employee_info` VALUES (19, '83', '陈洋', '2010-11-11', '女', '上海 上海市', '19840452092', '经理', 200, '$2a$10$Sm2cEDlAdDCLTbXqhFUmo.FXGtG3xxT9OlRuAtBLTI1eo99lARxMi');
INSERT INTO `employee_info` VALUES (20, '99', '薛强', '2003-01-19', '男', '吉林省 松原市', '18154925471', '经理', 200, '$2a$10$3vyPbd4OeWFfQc/0rXwkRe5/R1HjOldxWSSeaaYk/qZ2y58/am55K');
INSERT INTO `employee_info` VALUES (21, '87', '秦秀英', '1998-03-06', '男', '浙江省 舟山市', '19814885166', '实习生', 200, '$2a$10$hd/DFVueddWK2ctZhT.1VuzIYLNiJFJpD7cktoJvrFpoCuu1r6fVm');
INSERT INTO `employee_info` VALUES (22, '70', '田洋', '1985-10-12', '女', '陕西省 汉中市', '18616536329', '经理', 200, '$2a$10$2c4VwOhoZLaGKHGlFdR4C.kWRvM1TRBiwN28UD2ldVrZEe4nKE/aW');
INSERT INTO `employee_info` VALUES (23, '87', '崔强', '2015-08-15', '男', '贵州省 黔东南苗族侗族自治州', '18633638299', '组长', 200, '$2a$10$kDu80bDWSuky3lVlIfMcRu99wcvmk4TgDcPXyOb8AkGaS1QtylE0S');
INSERT INTO `employee_info` VALUES (24, '72', '沈超', '1978-10-25', '女', '黑龙江省 鹤岗市', '18162644732', '经理', 200, '$2a$10$20xHCk4tP9jWhK9fJH1XM.P7S1YI5Rdmi/UVyrA5h3gwkJkjK/skq');
INSERT INTO `employee_info` VALUES (25, '76', '蒋敏', '1996-08-26', '女', '上海 上海市', '18185891994', '正式工', 200, '$2a$10$dzNu/iE3zGPtcUTu4BlElOebXFuqglef.qA4csNaNTaTCBz2b0ZmK');
INSERT INTO `employee_info` VALUES (26, '67', '孟勇', '1984-05-05', '男', '西藏自治区 昌都地区', '18136685503', '经理', 200, '$2a$10$gJkS.Zg5L/s8Z8c1GePor.TwUNZZ67qL3qjBhEi9/lWgibVTIYlNa');

SET FOREIGN_KEY_CHECKS = 1;
