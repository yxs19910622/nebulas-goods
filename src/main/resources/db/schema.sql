CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL,
  `channel` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '通道名称',
  `isRefund` tinyint(1) NOT NULL DEFAULT '1' COMMENT '该通道失败是否退费 1:退费',
  `isSingle` tinyint(1) NOT NULL DEFAULT '0' COMMENT '该通道是否包含单通道 1:包含',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '通道状态 0:正常',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
);