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

 Date: 12/06/2022 15:20:42
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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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

SET FOREIGN_KEY_CHECKS = 1;
