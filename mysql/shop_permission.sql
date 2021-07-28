drop database if exists `shop_permission`;
create database `shop_permission` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

use `shop_permission`;
-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `source_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名字',
                               `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限访问地址，支持通配符',
                               `url_match` int(1) NOT NULL COMMENT '匹配方式：0 完全匹配   1 通配符匹配',
                               `service_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务名字',
                               `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'GET/POST/PUT/OPTIONS/DELETE/*',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '添加购物车', '/cart/{string}/{integer}', 1, 'mall-cart', 'GET');
INSERT INTO `permission` VALUES (2, '购物车集合', '/cart/list', 0, 'mall-cart', 'GET');

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES (1, '会员', '网站会员');
INSERT INTO `role_info` VALUES (2, '网站管理员', '网站管理员');
INSERT INTO `role_info` VALUES (3, '游客', '游客');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
                                    `pid` int(11) NOT NULL,
                                    `rid` int(11) NOT NULL,
                                    PRIMARY KEY (`pid`, `rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (2, 1);

SET FOREIGN_KEY_CHECKS = 1;
