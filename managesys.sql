/*
 Navicat MySQL Data Transfer

 Source Server         : hellowweb
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : managesys

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 01/01/2018 15:38:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `dId` int(11) NOT NULL AUTO_INCREMENT,
  `dName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dDes` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (2, '人事部', '招聘人才');
INSERT INTO `t_dept` VALUES (3, '财务部', '财务结算');
INSERT INTO `t_dept` VALUES (4, '技术部', '技术研发');
INSERT INTO `t_dept` VALUES (5, '营销部', '经营销售');
INSERT INTO `t_dept` VALUES (6, '后勤部', '后勤保障');

-- ----------------------------
-- Table structure for t_download
-- ----------------------------
DROP TABLE IF EXISTS `t_download`;
CREATE TABLE `t_download`  (
  `doId` int(11) NOT NULL AUTO_INCREMENT,
  `doDes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doTitle` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doCreateTime` date NULL DEFAULT NULL,
  `uId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`doId`) USING BTREE,
  INDEX `t_download_user`(`uId`) USING BTREE,
  CONSTRAINT `t_download_user` FOREIGN KEY (`uId`) REFERENCES `t_user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_download
-- ----------------------------
INSERT INTO `t_download` VALUES (1, '你好世界', '世界', NULL, 8);
INSERT INTO `t_download` VALUES (2, '上传测试', '图片上传测试', '2018-01-06', 8);
INSERT INTO `t_download` VALUES (3, '测试文档', '测试文档', '2018-01-06', 8);
INSERT INTO `t_download` VALUES (6, '测试文档1', '测试文档1', '2018-01-07', 8);
INSERT INTO `t_download` VALUES (7, '测试文档', '测试文档.txt', '2018-01-07', 8);

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp`  (
  `eId` int(11) NOT NULL AUTO_INCREMENT,
  `eName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eGender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eTelNum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eEmail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jId` int(11) NULL DEFAULT NULL,
  `eStu` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dId` int(11) NULL DEFAULT NULL,
  `eIdCard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eCreateTime` date NULL DEFAULT NULL,
  `eAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`eId`) USING BTREE,
  INDEX `t_emp_job`(`jId`) USING BTREE,
  INDEX `t_emp_dept`(`dId`) USING BTREE,
  CONSTRAINT `t_emp_dept` FOREIGN KEY (`dId`) REFERENCES `t_dept` (`dId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_emp_job` FOREIGN KEY (`jId`) REFERENCES `t_job` (`jId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES (1, '秀杰', '1', '18581516685', '30223254@qq.com', 1, '大学本科', 4, '41032819940620053X', '2018-01-06', '海南海口');
INSERT INTO `t_emp` VALUES (3, '倪妮', '2', '18511111111', '22@qq.com', 1, '博士', 2, '410328199906200500', '2018-01-06', '中国台湾');
INSERT INTO `t_emp` VALUES (4, '吴彦祖', '1', '18581516688', '98767896@qq.com', 5, '本科', 4, '999999999999999999', '2018-01-06', '中国香港');
INSERT INTO `t_emp` VALUES (6, '老王', '1', '18888899898', '6666@qq.com', 6, '小学', 6, '410328199906200501', '2018-01-06', '隔壁');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
  `jId` int(11) NOT NULL AUTO_INCREMENT,
  `jName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jDes` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`jId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES (1, '程序员', '伟大的编码工作');
INSERT INTO `t_job` VALUES (4, '前端', 'HTML5');
INSERT INTO `t_job` VALUES (5, '财务', '算钱哒');
INSERT INTO `t_job` VALUES (6, '保洁员', '打扫卫生哒');
INSERT INTO `t_job` VALUES (7, '外援', '技术攻关');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `nId` int(11) NOT NULL AUTO_INCREMENT,
  `nName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nContent` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nCreateTime` date NULL DEFAULT NULL,
  `uId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`nId`) USING BTREE,
  INDEX `t_notice_user`(`uId`) USING BTREE,
  CONSTRAINT `t_notice_user` FOREIGN KEY (`uId`) REFERENCES `t_user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (2, '重要的通知', '好重要的通知', '2018-01-06', 8);
INSERT INTO `t_notice` VALUES (3, '寒假通知', '2月8日放假啊', '2018-01-06', 8);
INSERT INTO `t_notice` VALUES (5, '关于财务的公告', '我们公司没钱啦', '2018-01-06', 7);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uPwd` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uLoginName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uCreateTime` date NULL DEFAULT NULL,
  `uState` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`uId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'xiu', '123456', '123456', '2018-01-07', 2);
INSERT INTO `t_user` VALUES (7, '妮妮', '6', '6', '2018-01-03', 1);
INSERT INTO `t_user` VALUES (8, '秀杰', '11', '11', '2018-01-03', 1);
INSERT INTO `t_user` VALUES (9, '吴彦祖', '22', '22', '2018-01-07', 1);
INSERT INTO `t_user` VALUES (10, '雯雯', 'q', 'q', '2018-01-07', 1);
INSERT INTO `t_user` VALUES (12, '天天', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
