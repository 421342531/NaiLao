--拉屎记录表
CREATE TABLE `mysql`.`shit_count`  (
  `date` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '日期',
  `count` varchar(100) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '拉屎次数',
  PRIMARY KEY (`date`) USING BTREE
);