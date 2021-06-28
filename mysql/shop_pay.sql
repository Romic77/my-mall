drop database if exists `shop_pay`;
create database `shop_pay` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log` (
                                    `id` String(50) NOT NULL,
                                    `status` int(1) DEFAULT NULL COMMENT '支付状态',
                                    `content` varchar(20) DEFAULT NULL COMMENT '支付内容',
                                    `pay_id` varchar(20) DEFAULT NULL COMMENT '支付id',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;