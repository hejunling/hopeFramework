CREATE TABLE `DEMO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `NAME` varchar(20) DEFAULT '' COMMENT '姓名',
  `USER_NAME` varchar(32) DEFAULT '' COMMENT '登录名',
  `PHONE` varchar(12) DEFAULT '' COMMENT '电话号码',
  `EMAIL` varchar(48) DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;




INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (1, '周凯', 'zhoukai', '13666666666', '123@qq.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (2, '刘文凯', 'liuwenkai', '13666666667', '137@qq.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (3, '柴森', 'caishen', '13666666668', '138@qq.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (4, '张起涛', 'zhangqitao', '13666666669', '139@163.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (5, '胡静', 'hujing', '13666666670', '189@wo.cn');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (9, '', '', '13666667', '');










CREATE TABLE `system_basic_data` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MODULE` varchar(20) DEFAULT NULL COMMENT '模块(business,iplimit)',
  `CONF_KEY` varchar(80) DEFAULT NULL COMMENT '配置键',
  `CONF_VALUE` varchar(400) DEFAULT NULL COMMENT '配置值',
  `DESCRIPTION` varchar(512) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2002424 DEFAULT CHARSET=utf8 COMMENT='系统基础数据表';



INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100000, 'business', '100000', '成功', '成功');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100001, 'business', '100001', '系统异常', '系统异常');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100010, 'business', '100010', 'Shrio验证失败', 'Shrio验证失败');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100011, 'business', '100011', 'session超时,请重新登陆', 'session超时,请重新登陆');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100012, 'business', '100012', '签名不匹配', '签名不匹配');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100013, 'business', '100013', '请求参数异常 ', '请求参数异常 ');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100014, 'business', '100014', ' 签名不能为空', ' 签名不能为空');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200005, 'business', '200005', '请求的HTTP METHOD不支持，必须为GET', '请求的HTTP METHOD不支持，必须为GET');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200006, 'business', '200006', '请求的HTTP METHOD不支持，必须为POST', '请求的HTTP METHOD不支持，必须为POST');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200007, 'business', '200007', '请求内容不能为空', '请求内容不能为空');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200008, 'business', '200008', '请求内容长度超过限制', '请求内容长度超过限制');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200009, 'business', '200009', '请求内容长度不匹配', '请求内容长度不匹配');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200011, 'business', '200011', '参数校验失败', '参数校验失败');





CREATE TABLE `user_operate_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `USER_NO` varchar(32) NOT NULL COMMENT '用户编号',
  `ACTION_CODE` varchar(100) NOT NULL COMMENT '请求接口',
  `CONTENT` varchar(4000) DEFAULT NULL COMMENT '请求内容（明文）',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态（0：成功，1：失败）',
  `CODE` varchar(10) DEFAULT NULL COMMENT '状态代码',
  `REQUEST_TIME` datetime DEFAULT NULL COMMENT '请求时间',
  `COMPLETE_TIME` datetime DEFAULT NULL COMMENT '完成时间',
  `REQUEST_DURATION` int(10) DEFAULT NULL COMMENT '请求耗时',
  `SERVER_TIME` datetime DEFAULT NULL COMMENT '服务器时间',
  `DB_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '数据库时间',
  `REQUESET_IP` varchar(80) DEFAULT NULL COMMENT '请求IP地址',
  `REQUEST_URL` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `REMARK` longtext COMMENT '备注',
  `SERVICE_TYPE` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `ACCESSID` varchar(32) DEFAULT NULL COMMENT '访问ID',
  PRIMARY KEY (`ID`),
  KEY `INDEX_USER_NO` (`USER_NO`),
  KEY `INDEX_ACTION_CODE` (`ACTION_CODE`),
  KEY `INDEX_USERNO_ACTION` (`USER_NO`,`ACTION_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=341015 DEFAULT CHARSET=utf8 COMMENT='用户操作接口日志';