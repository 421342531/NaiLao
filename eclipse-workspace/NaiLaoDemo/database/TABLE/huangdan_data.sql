
--记录黄疸值
CREATE TABLE `mysql`.`huangdan_data`  (
  `date` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '日期',
  `info` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '数值',
  `time` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '时间',
  PRIMARY KEY (`date`) USING BTREE
)