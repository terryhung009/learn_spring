/*
 Navicat Premium Data Transfer

 Source Server         : danny_mySQL
 Source Server Type    : MySQL
 Source Server Version : 100703 (10.7.3-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : springboot-vue

 Target Server Type    : MySQL
 Target Server Version : 100703 (10.7.3-MariaDB)
 File Encoding         : 65001

 Date: 04/12/2022 19:23:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '帳號',
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '密碼',
  `nick_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '暱稱',
  `age` int NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用戶信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, '3', '3', '3', 3, '男', '3');
INSERT INTO `user` VALUES (4, '0', '123456', '0', NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, '0', '123456', '0', NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, '0', '123456', '0', NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, '0', '123456', '0', NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, '0', '123456', '0', NULL, NULL, NULL);
INSERT INTO `user` VALUES (9, '1', '1', '1', 1, '男', '1');

SET FOREIGN_KEY_CHECKS = 1;
