/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.9.175
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 192.168.9.175:3306
 Source Schema         : spider-manage

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 22/10/2020 20:53:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (14, '数据长图', '2020-10-22 14:51:38', '2020-10-22 14:51:38');
INSERT INTO `category` VALUES (15, '快讯', '2020-10-22 14:51:59', '2020-10-22 14:51:59');
INSERT INTO `category` VALUES (16, '数据中心', '2020-10-22 14:52:19', '2020-10-22 14:52:19');

-- ----------------------------
-- Table structure for img_url
-- ----------------------------
DROP TABLE IF EXISTS `img_url`;
CREATE TABLE `img_url`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label_id` int(11) NOT NULL COMMENT '标签id',
  `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片URL',
  `path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片绝对路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img_url
-- ----------------------------
INSERT INTO `img_url` VALUES (80, 41, 'http://192.168.9.175:8095/images/b4fb3a70242437cb4e914b7bd0a152e9.gif', '/home/software/web/spider-manage/data/images/b4fb3a70242437cb4e914b7bd0a152e9.gif');

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` int(11) NOT NULL COMMENT '所属类别ID',
  `label_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '标签创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `time_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抓取时间描述',
  `demand_desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '需求描述',
  `example` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '示例图片',
  `spider_id` int(11) NULL DEFAULT NULL COMMENT '爬虫ID',
  `param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `auto_distribution` tinyint(1) NULL DEFAULT 0 COMMENT '自动分配（默认为0手动分配）',
  `open` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启调度',
  `exectuor_id` int(11) NULL DEFAULT 0 COMMENT '执行器id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (41, 14, '测试模板001', '2020-10-22 16:49:30', '2020-10-22 20:32:06', '大约在冬季', '', NULL, 123, 'msg=comex&times=2', '', 1, 1, 0);
INSERT INTO `label` VALUES (44, 15, '爬虫调度', '2020-10-22 18:29:28', '2020-10-22 20:32:07', '每隔5秒', '每个5秒调度爬取一次数据', NULL, 10, 'spider=jiachunwang.latest&msg=山东', '*/5 * * * * ?', 0, 0, 0);
INSERT INTO `label` VALUES (47, 15, '百度爬虫调度', '2020-10-22 18:53:06', '2020-10-22 20:32:08', '每隔10秒', '每隔10秒调度爬取一次数据', NULL, 10, '', '*/10 * * * * ?', 0, 0, 0);
INSERT INTO `label` VALUES (48, 15, '百度爬虫调度1', '2020-10-22 18:57:53', '2020-10-22 20:32:08', '每隔10秒', '每隔10秒调度爬取一次数据', NULL, 10, '', '*/10 * * * * ?', 0, 0, 0);
INSERT INTO `label` VALUES (49, 15, '百度爬虫调度2', '2020-10-22 19:04:37', '2020-10-22 20:32:09', '每隔10秒', '每隔10秒调度爬取一次数据', NULL, 10, '', '*/10 * * * * ?', 0, 0, 0);
INSERT INTO `label` VALUES (50, 15, '爬虫百度调度', '2020-10-22 20:09:58', '2020-10-22 20:32:10', '每隔5秒', '每个5秒调度爬取一次数据', NULL, 10, 'spider=jiachunwang.latest&msg=山东', '*/5 * * * * ?', 0, 0, 0);
INSERT INTO `label` VALUES (51, 15, 'github调度', '2020-10-22 20:10:53', '2020-10-22 20:32:25', '每隔10秒', '每个10秒调度github', NULL, 10, 'spider=jiachunwang.latest&msg=山东', '*/10 * * * * ?', 0, 0, 24);

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
  `label_id` int(11) NULL DEFAULT NULL COMMENT '对应标签ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `label_id`(`label_id`) USING BTREE,
  CONSTRAINT `label_id` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (142, 'https://quote.eastmoney.com/us/nio.html', 41);
INSERT INTO `link` VALUES (145, 'http://192.168.13.175:18888/test', 44);
INSERT INTO `link` VALUES (148, 'www.baidu.com', 47);
INSERT INTO `link` VALUES (149, 'www.baidu.com', 48);
INSERT INTO `link` VALUES (150, 'http://baidu.com/', 49);
INSERT INTO `link` VALUES (151, 'https://www.baidu.com/', 50);
INSERT INTO `link` VALUES (153, 'https://github.com/', 51);

SET FOREIGN_KEY_CHECKS = 1;
