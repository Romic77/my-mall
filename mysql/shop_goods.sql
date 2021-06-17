/*
Navicat MySQL Data Transfer

Source Server         : 130
Source Server Version : 50731
Source Host           : 192.168.100.130:3306
Source Database       : shop_goods

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-11-09 13:39:06
*/
drop database if exists `shop_goods`;
create database `shop_goods` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_items
-- ----------------------------
DROP TABLE IF EXISTS `ad_items`;
CREATE TABLE `ad_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` int(3) DEFAULT NULL COMMENT '分类，1首页推广,2列表页推广',
  `sku_id` varchar(60) DEFAULT NULL COMMENT '展示的产品',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ad_items
-- ----------------------------
INSERT INTO `ad_items` VALUES ('4', 'ww', '1', '1319051488282112002', '5');
INSERT INTO `ad_items` VALUES ('5', '33', '1', '1319051488298889217', '6');
INSERT INTO `ad_items` VALUES ('6', '33', '1', '1318596430398562305', '7');
INSERT INTO `ad_items` VALUES ('7', '11', '1', '1318596430360813570', '7');
INSERT INTO `ad_items` VALUES ('11', '2324', '1', '1318594982227025922', '8');
INSERT INTO `ad_items` VALUES ('122', '123', '1', '1318594982227025922', '11');

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` varchar(100) NOT NULL COMMENT '品牌名称',
  `image` varchar(1000) DEFAULT '' COMMENT '品牌图片地址',
  `initial` varchar(1) DEFAULT '' COMMENT '品牌的首字母',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('11', '华为', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/eed72cc4-a9c1-4010-949a-03cef5b933d6.jpg', '', null);
INSERT INTO `brand` VALUES ('12', '中兴', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/4fedb361-5ab3-4ad0-a667-580c1f37dff0.jpg', '', null);
INSERT INTO `brand` VALUES ('13', '大疆', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/e8382c48-0487-4a9b-8fd0-a3716c3eea19.jpg', '', null);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级ID',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11182 DEFAULT CHARSET=utf8 COMMENT='商品类目';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1081', '速干衣裤', '1', '1079');
INSERT INTO `category` VALUES ('1082', '滑雪服', '1', '1079');
INSERT INTO `category` VALUES ('1083', '羽绒服/棉服', '1', '1079');
INSERT INTO `category` VALUES ('1084', '休闲衣裤', '1', '1079');
INSERT INTO `category` VALUES ('1085', '抓绒衣裤', '1', '1079');
INSERT INTO `category` VALUES ('1086', '软壳衣裤', '1', '1079');
INSERT INTO `category` VALUES ('1087', 'T恤', '1', '1079');
INSERT INTO `category` VALUES ('1088', '户外风衣', '1', '1079');
INSERT INTO `category` VALUES ('1089', '功能内衣', '1', '1079');
INSERT INTO `category` VALUES ('1090', '军迷服饰', '1', '1079');
INSERT INTO `category` VALUES ('1091', '登山鞋', '1', '1079');
INSERT INTO `category` VALUES ('1092', '雪地靴', '1', '1079');
INSERT INTO `category` VALUES ('1093', '徒步鞋', '1', '1079');
INSERT INTO `category` VALUES ('1094', '越野跑鞋', '1', '1079');
INSERT INTO `category` VALUES ('1095', '休闲鞋', '1', '1079');
INSERT INTO `category` VALUES ('1096', '工装鞋', '1', '1079');
INSERT INTO `category` VALUES ('1097', '溯溪鞋', '1', '1079');
INSERT INTO `category` VALUES ('1098', '沙滩/凉拖', '1', '1079');
INSERT INTO `category` VALUES ('1099', '户外袜', '1', '1079');
INSERT INTO `category` VALUES ('1100', '户外装备', '1', '1031');
INSERT INTO `category` VALUES ('1101', '帐篷/垫子', '1', '1100');
INSERT INTO `category` VALUES ('1102', '睡袋/吊床', '1', '1100');
INSERT INTO `category` VALUES ('1103', '登山攀岩', '1', '1100');
INSERT INTO `category` VALUES ('1104', '背包', '1', '1100');
INSERT INTO `category` VALUES ('1105', '户外配饰', '1', '1100');
INSERT INTO `category` VALUES ('1106', '户外照明', '1', '1100');
INSERT INTO `category` VALUES ('1107', '户外仪表', '1', '1100');
INSERT INTO `category` VALUES ('1108', '户外工具', '1', '1100');
INSERT INTO `category` VALUES ('1109', '望远镜', '1', '1100');
INSERT INTO `category` VALUES ('1110', '旅游用品', '1', '1100');
INSERT INTO `category` VALUES ('1111', '便携桌椅床', '1', '1100');
INSERT INTO `category` VALUES ('1112', '野餐烧烤', '1', '1100');
INSERT INTO `category` VALUES ('1113', '军迷用品', '1', '1100');
INSERT INTO `category` VALUES ('1114', '救援装备', '1', '1100');
INSERT INTO `category` VALUES ('1115', '滑雪装备', '1', '1100');
INSERT INTO `category` VALUES ('1116', '极限户外', '1', '1100');
INSERT INTO `category` VALUES ('1117', '冲浪潜水', '1', '1100');
INSERT INTO `category` VALUES ('1118', '健身训练', '1', '1031');
INSERT INTO `category` VALUES ('1119', '综合训练器', '1', '1118');
INSERT INTO `category` VALUES ('1120', '其他大型器械', '1', '1118');
INSERT INTO `category` VALUES ('1121', '哑铃', '1', '1118');
INSERT INTO `category` VALUES ('1122', '仰卧板/收腹机', '1', '1118');
INSERT INTO `category` VALUES ('1123', '其他中小型器材', '1', '1118');
INSERT INTO `category` VALUES ('1124', '瑜伽舞蹈', '1', '1118');
INSERT INTO `category` VALUES ('1125', '武术搏击', '1', '1118');
INSERT INTO `category` VALUES ('1126', '健身车/动感单车', '1', '1118');
INSERT INTO `category` VALUES ('1127', '跑步机', '1', '1118');
INSERT INTO `category` VALUES ('1128', '运动护具', '1', '1118');
INSERT INTO `category` VALUES ('1129', '纤体瑜伽', '1', '1031');
INSERT INTO `category` VALUES ('1130', '瑜伽垫', '1', '1129');
INSERT INTO `category` VALUES ('1131', '瑜伽服', '1', '1129');
INSERT INTO `category` VALUES ('1132', '瑜伽配件', '1', '1129');
INSERT INTO `category` VALUES ('1133', '瑜伽套装', '1', '1129');
INSERT INTO `category` VALUES ('1134', '舞蹈鞋服', '1', '1129');
INSERT INTO `category` VALUES ('1135', '体育用品', '1', '1031');
INSERT INTO `category` VALUES ('1136', '羽毛球', '1', '1135');
INSERT INTO `category` VALUES ('1137', '乒乓球', '1', '1135');
INSERT INTO `category` VALUES ('1138', '篮球', '1', '1135');
INSERT INTO `category` VALUES ('1139', '足球', '1', '1135');
INSERT INTO `category` VALUES ('1140', '网球', '1', '1135');
INSERT INTO `category` VALUES ('1141', '排球', '1', '1135');
INSERT INTO `category` VALUES ('1142', '高尔夫', '1', '1135');
INSERT INTO `category` VALUES ('1143', '台球', '1', '1135');
INSERT INTO `category` VALUES ('1144', '棋牌麻将', '1', '1135');
INSERT INTO `category` VALUES ('1145', '轮滑滑板', '1', '1135');
INSERT INTO `category` VALUES ('1146', '其他', '1', '1135');
INSERT INTO `category` VALUES ('1148', '彩票', '1', '1147');
INSERT INTO `category` VALUES ('1149', '双色球', '1', '1148');
INSERT INTO `category` VALUES ('1150', '大乐透', '1', '1148');
INSERT INTO `category` VALUES ('1151', '福彩3D', '1', '1148');
INSERT INTO `category` VALUES ('1152', '排列三', '1', '1148');
INSERT INTO `category` VALUES ('1153', '排列五', '1', '1148');
INSERT INTO `category` VALUES ('1154', '七星彩', '1', '1148');
INSERT INTO `category` VALUES ('1155', '七乐彩', '1', '1148');
INSERT INTO `category` VALUES ('1156', '竞彩足球', '1', '1148');
INSERT INTO `category` VALUES ('1157', '竞彩篮球', '1', '1148');
INSERT INTO `category` VALUES ('1158', '新时时彩', '1', '1148');
INSERT INTO `category` VALUES ('1159', '机票', '1', '1147');
INSERT INTO `category` VALUES ('1160', '国内机票', '1', '1159');
INSERT INTO `category` VALUES ('1161', '酒店', '1', '1147');
INSERT INTO `category` VALUES ('1162', '国内酒店', '1', '1161');
INSERT INTO `category` VALUES ('1163', '酒店团购', '1', '1161');
INSERT INTO `category` VALUES ('1164', '旅行', '1', '1147');
INSERT INTO `category` VALUES ('1165', '度假', '1', '1164');
INSERT INTO `category` VALUES ('1166', '景点', '1', '1164');
INSERT INTO `category` VALUES ('1167', '租车', '1', '1164');
INSERT INTO `category` VALUES ('1168', '火车票', '1', '1164');
INSERT INTO `category` VALUES ('1169', '旅游团购', '1', '1164');
INSERT INTO `category` VALUES ('1170', '充值', '1', '1147');
INSERT INTO `category` VALUES ('1171', '手机充值', '1', '1170');
INSERT INTO `category` VALUES ('1172', '游戏', '1', '1147');
INSERT INTO `category` VALUES ('1173', '游戏点卡', '1', '1172');
INSERT INTO `category` VALUES ('1174', 'QQ充值', '1', '1172');
INSERT INTO `category` VALUES ('1175', '票务', '1', '1147');
INSERT INTO `category` VALUES ('1176', '电影票', '1', '1175');
INSERT INTO `category` VALUES ('1177', '演唱会', '1', '1175');
INSERT INTO `category` VALUES ('1178', '话剧歌剧', '1', '1175');
INSERT INTO `category` VALUES ('1179', '音乐会', '1', '1175');
INSERT INTO `category` VALUES ('1180', '体育赛事', '1', '1175');
INSERT INTO `category` VALUES ('1181', '舞蹈芭蕾', '1', '1175');
INSERT INTO `category` VALUES ('1182', '戏曲综艺', '1', '1175');
INSERT INTO `category` VALUES ('1193', '英文小说', '1', '11');
INSERT INTO `category` VALUES ('1194', '火锅涮锅', '1', '718');
INSERT INTO `category` VALUES ('1195', '手电筒', '1', '1192');
INSERT INTO `category` VALUES ('1196', '照妖镜', '1', '1192');
INSERT INTO `category` VALUES ('1198', '蓝宝石', '1', '987');
INSERT INTO `category` VALUES ('1199', '电子小说', '1', '2');
INSERT INTO `category` VALUES ('1200', '上网本', '1', '162');
INSERT INTO `category` VALUES ('1201', '婴儿礼服', '1', '341');
INSERT INTO `category` VALUES ('1202', '电子漫画书', '1', '2');
INSERT INTO `category` VALUES ('1203', '不移动软盘', '1', '186');
INSERT INTO `category` VALUES ('1204', '购机送流量', '1', '562');
INSERT INTO `category` VALUES ('1207', '电子教科书', '1', '2');
INSERT INTO `category` VALUES ('1208', '儿童电脑', '1', '625');
INSERT INTO `category` VALUES ('1213', '测试餐具', '1', '733');
INSERT INTO `category` VALUES ('1215', '电子文学', '1', '2');
INSERT INTO `category` VALUES ('1216', '电子画报', '101', '2');
INSERT INTO `category` VALUES ('11156', '手机配件', '1', '1000');
INSERT INTO `category` VALUES ('11157', '教育、培训', '1', '0');
INSERT INTO `category` VALUES ('11158', '计算机培训', '1', '11157');
INSERT INTO `category` VALUES ('11159', '软件研发', '1', '11158');
INSERT INTO `category` VALUES ('11160', '小吃培训', '1', '11157');
INSERT INTO `category` VALUES ('11161', '面食/小吃', '1', '11160');
INSERT INTO `category` VALUES ('11162', '重庆特色小吃', '1', '11161');
INSERT INTO `category` VALUES ('11163', '董秘培训', '1', '11157');
INSERT INTO `category` VALUES ('11164', '金融董秘培训', '1', '11163');
INSERT INTO `category` VALUES ('11165', '新三板方向', '1', '11163');
INSERT INTO `category` VALUES ('11166', '公司融资方向', '1', '11163');
INSERT INTO `category` VALUES ('11167', '人工智能', '1', '11158');
INSERT INTO `category` VALUES ('11168', '物联网', '1', '11158');
INSERT INTO `category` VALUES ('11169', 'Java就业班', '1', '11158');
INSERT INTO `category` VALUES ('11170', '大数据', '1', '11158');
INSERT INTO `category` VALUES ('11171', 'PHP', '1', '11158');
INSERT INTO `category` VALUES ('11172', '嵌入式开发', '1', '11158');
INSERT INTO `category` VALUES ('11173', '新媒体', '1', '11158');
INSERT INTO `category` VALUES ('11174', '产品经理', '1', '11158');
INSERT INTO `category` VALUES ('11175', 'DBA', '1', '11158');
INSERT INTO `category` VALUES ('11176', '手机', '1', '559');
INSERT INTO `category` VALUES ('11177', '家电', null, '0');
INSERT INTO `category` VALUES ('11178', 'ces', null, null);
INSERT INTO `category` VALUES ('11179', 'ces', null, '0');
INSERT INTO `category` VALUES ('11180', 'rr', null, null);
INSERT INTO `category` VALUES ('11181', 'fdf', null, null);

-- ----------------------------
-- Table structure for category_attr
-- ----------------------------
DROP TABLE IF EXISTS `category_attr`;
CREATE TABLE `category_attr` (
  `category_id` int(11) NOT NULL,
  `attr_id` int(11) NOT NULL COMMENT '属性分类表',
  PRIMARY KEY (`category_id`,`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_attr
-- ----------------------------
INSERT INTO `category_attr` VALUES ('11159', '2');
INSERT INTO `category_attr` VALUES ('11159', '3');
INSERT INTO `category_attr` VALUES ('11159', '4');
INSERT INTO `category_attr` VALUES ('11159', '5');
INSERT INTO `category_attr` VALUES ('11159', '7');
INSERT INTO `category_attr` VALUES ('11159', '10');
INSERT INTO `category_attr` VALUES ('11159', '11');
INSERT INTO `category_attr` VALUES ('11159', '12');
INSERT INTO `category_attr` VALUES ('11161', '6');
INSERT INTO `category_attr` VALUES ('11164', '6');
INSERT INTO `category_attr` VALUES ('11164', '7');
INSERT INTO `category_attr` VALUES ('11164', '8');
INSERT INTO `category_attr` VALUES ('11164', '9');
INSERT INTO `category_attr` VALUES ('11165', '7');
INSERT INTO `category_attr` VALUES ('11165', '8');
INSERT INTO `category_attr` VALUES ('11165', '9');
INSERT INTO `category_attr` VALUES ('11166', '7');
INSERT INTO `category_attr` VALUES ('11166', '8');
INSERT INTO `category_attr` VALUES ('11166', '9');
INSERT INTO `category_attr` VALUES ('11167', '2');
INSERT INTO `category_attr` VALUES ('11167', '3');
INSERT INTO `category_attr` VALUES ('11167', '4');
INSERT INTO `category_attr` VALUES ('11167', '10');
INSERT INTO `category_attr` VALUES ('11167', '11');
INSERT INTO `category_attr` VALUES ('11167', '12');
INSERT INTO `category_attr` VALUES ('11168', '10');
INSERT INTO `category_attr` VALUES ('11168', '11');
INSERT INTO `category_attr` VALUES ('11168', '12');
INSERT INTO `category_attr` VALUES ('11169', '10');
INSERT INTO `category_attr` VALUES ('11169', '12');
INSERT INTO `category_attr` VALUES ('11170', '10');
INSERT INTO `category_attr` VALUES ('11171', '10');
INSERT INTO `category_attr` VALUES ('11172', '10');
INSERT INTO `category_attr` VALUES ('11172', '12');
INSERT INTO `category_attr` VALUES ('11173', '10');
INSERT INTO `category_attr` VALUES ('11173', '12');
INSERT INTO `category_attr` VALUES ('11174', '10');
INSERT INTO `category_attr` VALUES ('11174', '12');
INSERT INTO `category_attr` VALUES ('11175', '10');
INSERT INTO `category_attr` VALUES ('11175', '12');

-- ----------------------------
-- Table structure for category_brand
-- ----------------------------
DROP TABLE IF EXISTS `category_brand`;
CREATE TABLE `category_brand` (
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `brand_id` int(11) NOT NULL COMMENT '品牌ID',
  PRIMARY KEY (`brand_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_brand
-- ----------------------------
INSERT INTO `category_brand` VALUES ('11159', '11');
INSERT INTO `category_brand` VALUES ('11167', '11');
INSERT INTO `category_brand` VALUES ('11159', '12');
INSERT INTO `category_brand` VALUES ('11167', '12');
INSERT INTO `category_brand` VALUES ('11159', '13');
INSERT INTO `category_brand` VALUES ('11167', '13');
INSERT INTO `category_brand` VALUES ('11168', '13');

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` varchar(60) NOT NULL COMMENT '商品id',
  `name` varchar(200) NOT NULL COMMENT 'SKU名称',
  `price` int(20) NOT NULL DEFAULT '1' COMMENT '价格（分）',
  `num` int(10) DEFAULT '100' COMMENT '库存数量',
  `image` varchar(200) DEFAULT NULL COMMENT '商品图片',
  `images` varchar(2000) DEFAULT NULL COMMENT '商品图片列表',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `spu_id` varchar(60) DEFAULT NULL COMMENT 'SPUID',
  `category_id` int(10) DEFAULT NULL COMMENT '类目ID',
  `category_name` varchar(200) DEFAULT NULL COMMENT '类目名称',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(100) DEFAULT NULL COMMENT '品牌名称',
  `sku_attribute` varchar(200) DEFAULT NULL COMMENT '规格',
  `status` int(1) DEFAULT '1' COMMENT '商品状态 1-正常，2-下架，3-删除',
  PRIMARY KEY (`id`),
  KEY `cid` (`category_id`),
  KEY `status` (`status`),
  KEY `updated` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES ('1318594982227025922', '华为Mate40 Pro 32G', '114', '1228', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/af1faf56-b10a-4700-9896-3143a2d1c40f.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', '2020-10-20 16:48:37', '2020-10-20 16:48:37', '1318594982147334146', '11159', '软件研发', '11', '华为', '{\"就业薪资\":\"10K起\",\"学习费用\":\"2万\"}', '1');
INSERT INTO `sku` VALUES ('1318596430360813570', '华为Mate40 Pro 32G 1800万像素', '112', '1227', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/9247d041-e940-426c-8e50-06084b631063.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/5f5b7435-6cf2-4797-8f65-d4abff181390.jpg', '2020-10-20 16:54:22', '2020-10-20 16:54:22', '1318596430293704706', '11159', '软件研发', '11', '华为', '{\"就业薪资\":\"10K起\",\"学习费用\":\"2万\"}', '1');
INSERT INTO `sku` VALUES ('1318596430398562305', '华为Mate40 Pro 128G', '111', '1226', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/900a3618-9884-4778-bad9-c6c31eaf3eab.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/5f5b7435-6cf2-4797-8f65-d4abff181390.jpg', '2020-10-20 16:54:22', '2020-10-20 16:54:22', '1318596430293704706', '11159', '软件研发', '11', '华为', '{\"就业薪资\":\"10K起\",\"学习费用\":\"2万\"}', '1');
INSERT INTO `sku` VALUES ('1318599511605563394', '格力手机 5G手机', '100', '1225', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/2b233c6a-5acc-449e-ba3a-70a506100948.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/ffc66a17-edfc-43bb-8f66-431b1e9bf606.jpg', '2020-10-20 17:06:37', '2020-10-20 17:06:37', '1318599511492317185', '11159', '软件研发', '11', '华为', '{\"就业薪资\":\"10K起\",\"学习费用\":\"2万\"}', '1');
INSERT INTO `sku` VALUES ('1318599511647506433', '格力手机 5G手机 红色', '789', '1224', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/1c1fbfea-af9f-49e7-b89b-35e751874399.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/ffc66a17-edfc-43bb-8f66-431b1e9bf606.jpg', '2020-10-20 17:06:37', '2020-10-20 17:06:37', '1318599511492317185', '11159', '软件研发', '11', '华为', '{\"就业薪资\":\"10K起\",\"学习费用\":\"2万\"}', '1');
INSERT INTO `sku` VALUES ('1319051488240168961', '小米10促销活培训课  1万  6K起', '155', '1223', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/65da862e-6c75-4786-9eff-ab92e79661f6.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', '2020-10-21 23:02:36', '2020-10-21 23:02:36', '1319051488177254401', '11159', '软件研发', '12', '中兴', '{\"就业薪资\":\"6K起\",\"学习费用\":\"1万\"}', '1');
INSERT INTO `sku` VALUES ('1319051488265334786', '小米10促销活培训课  2万  6K起', '1666', '1222', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/9c7a73df-dc02-42ac-b2f8-11a6ef63d18b.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', '2020-10-21 23:02:36', '2020-10-21 23:02:36', '1319051488177254401', '11159', '软件研发', '12', '中兴', '{\"就业薪资\":\"6K起\",\"学习费用\":\"2万\"}', '1');
INSERT INTO `sku` VALUES ('1319051488282112002', '小米10促销活培训课  1万  10K起', '123', '12221', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/1cb38c3f-93b6-4fae-bb1f-5129d01c6eb3.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', '2020-10-21 23:02:36', '2020-10-21 23:02:36', '1319051488177254401', '11159', '软件研发', '12', '中兴', '{\"就业薪资\":\"10K起\",\"学习费用\":\"1万\"}', '1');
INSERT INTO `sku` VALUES ('1319051488298889217', '小米10促销活培训课  2万  10K起', '22222', '12231', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/38421f8f-c20d-40ec-9616-1ad90fff5d74.jpg', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', '2020-10-21 23:02:36', '2020-10-21 23:02:36', '1319051488177254401', '11159', '软件研发', '12', '中兴', '{\"就业薪资\":\"10K起\",\"学习费用\":\"2万\"}', '1');

-- ----------------------------
-- Table structure for sku_attribute
-- ----------------------------
DROP TABLE IF EXISTS `sku_attribute`;
CREATE TABLE `sku_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '属性名称',
  `options` varchar(2000) DEFAULT NULL COMMENT '属性选项',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sku_attribute
-- ----------------------------
INSERT INTO `sku_attribute` VALUES ('1', '电器', '立体声,环绕,小影院', '1');
INSERT INTO `sku_attribute` VALUES ('6', '适合人群', '少年,大学生,职场人士,金融专家', null);
INSERT INTO `sku_attribute` VALUES ('7', '类型', '金融,培训,市场管理', null);
INSERT INTO `sku_attribute` VALUES ('8', '时长', '10小时视频,26小时视频,22小时视频', null);
INSERT INTO `sku_attribute` VALUES ('9', '学习周期', '1个月,2个月,6个月,1年', null);
INSERT INTO `sku_attribute` VALUES ('10', '就业薪资', '6K起,10K起,30K以上', null);
INSERT INTO `sku_attribute` VALUES ('11', '学习费用', '1万,2万,9988', null);
INSERT INTO `sku_attribute` VALUES ('12', '应用场景', '机器制造,软件研发,UI设计', null);

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
  `id` varchar(60) NOT NULL COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT 'SPU名',
  `intro` varchar(200) DEFAULT NULL COMMENT '简介',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID',
  `category_one_id` int(20) DEFAULT NULL COMMENT '一级分类',
  `category_two_id` int(10) DEFAULT NULL COMMENT '二级分类',
  `category_three_id` int(10) DEFAULT NULL COMMENT '三级分类',
  `images` varchar(1000) DEFAULT NULL COMMENT '图片列表',
  `after_sales_service` varchar(50) DEFAULT NULL COMMENT '售后服务',
  `content` longtext COMMENT '介绍',
  `attribute_list` varchar(3000) DEFAULT NULL COMMENT '规格列表',
  `is_marketable` int(1) DEFAULT '0' COMMENT '是否上架,0已下架，1已上架',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除,0:未删除，1：已删除',
  `status` int(1) DEFAULT '0' COMMENT '审核状态，0：未审核，1：已审核，2：审核不通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES ('1318594982147334146', 'ces', 's', '11', '11157', '11158', '11159', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', null, '', '{\"ds\":[\"d\"],\"fd\":[\"ff\"],\"大小\":[\"中\"]}', '1', '0', '1');
INSERT INTO `spu` VALUES ('1318596430293704706', 'ee', 'e', '11', '11157', '11158', '11159', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', null, '<p>ces</p>', '{\"fd\":[\"ff\"],\"大小\":[\"大\",\"中\"]}', '1', '0', '1');
INSERT INTO `spu` VALUES ('1318599511492317185', 'ces2', 'ddes', '11', '11157', '11158', '11159', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', null, '<p>gdfg</p>', '{\"ds\":[\"d\"],\"fd\":[\"ff\"],\"大小\":[\"大\",\"中\"]}', '1', '0', '1');
INSERT INTO `spu` VALUES ('1319051488177254401', '小米10促销活培训课', '双十一电销小米10大促销！最后三天！', '12', '11157', '11158', '11159', 'https://sklll.oss-cn-beijing.aliyuncs.com/secby/a65bfbe4-21b7-42b2-b5cf-47a9730e0a16.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/fa52ef66-7724-4d6e-bece-15eba0f8f903.jpg,https://sklll.oss-cn-beijing.aliyuncs.com/secby/734f0f17-ac73-45d3-a6bf-83e1569ce887.jpg', null, '<p>促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！促销课程！</p>', '{\"就业薪资\":[\"6K起\",\"10K起\"],\"学习费用\":[\"1万\",\"2万\"]}', '1', '0', '1');
