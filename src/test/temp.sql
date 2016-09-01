DROP TABLE `demo`.`oms_commodity`, `demo`.`oms_commodity_column`, `demo`.`oms_order`, `demo`.`oms_order_item`, `demo`.`online_apply`, `demo`.`ordercontext`, `demo`.`orderdetails`, `demo`.`orderdetailstemplate`, `demo`.`orders`, `demo`.`ordertesttemplate`;

# 上锌量配置表
CREATE TABLE rpt_base_zinccoatingthickness_config (
  id varchar(64) NOT NULL COMMENT '主键',
  configname varchar(50) NOT NULL COMMENT '配置项名称',
  zincratetargetfront double DEFAULT '0' COMMENT '正面目标上锌量',
  zincratetargetfrontmaxval double DEFAULT '0' COMMENT '正面目标上锌量最大值',
  zincratetargetfrontminval double DEFAULT '0' COMMENT '正面目标上锌量最小值',
  zincratetargetreverse double DEFAULT '0' COMMENT '反面目标上锌量',
  zincratetargetreversemaxval double DEFAULT '0' COMMENT '反面目标上锌量最大值',
  zincratetargetreverseminval double DEFAULT '0' COMMENT '反面目标上锌量最小值',
  createtime datetime DEFAULT NULL COMMENT '创建时间',
  createuser varchar(50) DEFAULT NULL COMMENT '创建人',
  updatetime datetime DEFAULT NULL COMMENT '修改时间',
  updateuser varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (id),
  KEY config_name_idx (configname)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='锌层测厚配置表';



# 异常数据表
CREATE TABLE rpt_base_zinccoatingthickness_exception (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  logtime datetime DEFAULT NULL COMMENT '记录时间',
  msvalue int(11) DEFAULT '0' COMMENT '时间毫秒值',
  loggroup varchar(10) DEFAULT NULL COMMENT '班组',
  prodcode varchar(50) DEFAULT NULL COMMENT '生产编号',
  gencode varchar(50) DEFAULT NULL COMMENT '生成编号',
  workmode varchar(10) DEFAULT NULL COMMENT '测厚仪工作模式',
  bandwidth double DEFAULT '0' COMMENT '带宽',
  bandthickness double DEFAULT '0' COMMENT '带厚',
  linespeed double DEFAULT '0' COMMENT '线速',
  walklen double DEFAULT '0' COMMENT '钢卷行走长度',
  zincrateunilateral double DEFAULT '0' COMMENT '单侧目标上锌量',
  zincratetargetfront double DEFAULT '0' COMMENT '正面目标上锌量',
  zincratetargetreverse double DEFAULT '0' COMMENT '反面目标上锌量',
  detectionpositionfront double DEFAULT '0' COMMENT '正面检测位置',
  detectionpositionreverse double DEFAULT '0' COMMENT '反面检测位置',
  zincratefront double DEFAULT '0' COMMENT '正面上实时上锌量',
  zincratereverse double DEFAULT '0' COMMENT '反面上实时上锌量',
  flag double DEFAULT '0' COMMENT '接头信号: 0和1.',
  
  zincratetargetfrontmaxval double DEFAULT '0' COMMENT '正面目标上锌量最大值',
  zincratetargetfrontminval double DEFAULT '0' COMMENT '正面目标上锌量最小值',
  zincratetargetfrontoffset double DEFAULT '0' COMMENT '正面上锌量偏差值',
  
  zincratetargetreversemaxval double DEFAULT '0' COMMENT '反面目标上锌量最大值',
  zincratetargetreverseminval double DEFAULT '0' COMMENT '反面目标上锌量最小值',
  zincratetargetreverseoffset double DEFAULT '0' COMMENT '反面上锌量偏差值',
  
  PRIMARY KEY (id),
  KEY exception_logtime_idx (logtime),
  KEY exception_loggroup_idx (loggroup),
  KEY exception_prodcode_idx (prodcode),
  KEY exception_gencode_idx (gencode),
  KEY exception_workmode_idx (workmode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='锌层测厚日志异常记录表';
