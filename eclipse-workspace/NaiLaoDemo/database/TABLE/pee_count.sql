--记录小便信息
CREATE TABLE `mysql`.`pee_count`  (
  `date` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '日期',
  `count` varchar(100) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '小便次数',
  PRIMARY KEY (`date`) USING BTREE
 );