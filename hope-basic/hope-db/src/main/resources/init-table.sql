CREATE TABLE `DEMO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '����������',
  `NAME` varchar(20) DEFAULT '' COMMENT '����',
  `USER_NAME` varchar(32) DEFAULT '' COMMENT '��¼��',
  `PHONE` varchar(12) DEFAULT '' COMMENT '�绰����',
  `EMAIL` varchar(48) DEFAULT '' COMMENT '����',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;




INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (1, '�ܿ�', 'zhoukai', '13666666666', '123@qq.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (2, '���Ŀ�', 'liuwenkai', '13666666667', '137@qq.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (3, '��ɭ', 'caishen', '13666666668', '138@qq.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (4, '������', 'zhangqitao', '13666666669', '139@163.com');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (5, '����', 'hujing', '13666666670', '189@wo.cn');
INSERT INTO `DEMO` (`ID`, `NAME`, `USER_NAME`, `PHONE`, `EMAIL`) VALUES (9, '', '', '13666667', '');










CREATE TABLE `system_basic_data` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '����',
  `MODULE` varchar(20) DEFAULT NULL COMMENT 'ģ��(business,iplimit)',
  `CONF_KEY` varchar(80) DEFAULT NULL COMMENT '���ü�',
  `CONF_VALUE` varchar(400) DEFAULT NULL COMMENT '����ֵ',
  `DESCRIPTION` varchar(512) DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2002424 DEFAULT CHARSET=utf8 COMMENT='ϵͳ�������ݱ�';



INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100000, 'business', '100000', '�ɹ�', '�ɹ�');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100001, 'business', '100001', 'ϵͳ�쳣', 'ϵͳ�쳣');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100010, 'business', '100010', 'Shrio��֤ʧ��', 'Shrio��֤ʧ��');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100011, 'business', '100011', 'session��ʱ,�����µ�½', 'session��ʱ,�����µ�½');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100012, 'business', '100012', 'ǩ����ƥ��', 'ǩ����ƥ��');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100013, 'business', '100013', '��������쳣 ', '��������쳣 ');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (100014, 'business', '100014', ' ǩ������Ϊ��', ' ǩ������Ϊ��');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200005, 'business', '200005', '�����HTTP METHOD��֧�֣�����ΪGET', '�����HTTP METHOD��֧�֣�����ΪGET');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200006, 'business', '200006', '�����HTTP METHOD��֧�֣�����ΪPOST', '�����HTTP METHOD��֧�֣�����ΪPOST');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200007, 'business', '200007', '�������ݲ���Ϊ��', '�������ݲ���Ϊ��');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200008, 'business', '200008', '�������ݳ��ȳ�������', '�������ݳ��ȳ�������');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200009, 'business', '200009', '�������ݳ��Ȳ�ƥ��', '�������ݳ��Ȳ�ƥ��');
INSERT INTO `system_basic_data` (`ID`, `MODULE`, `CONF_KEY`, `CONF_VALUE`, `DESCRIPTION`) VALUES (200011, 'business', '200011', '����У��ʧ��', '����У��ʧ��');





CREATE TABLE `user_operate_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `USER_NO` varchar(32) NOT NULL COMMENT '�û����',
  `ACTION_CODE` varchar(100) NOT NULL COMMENT '����ӿ�',
  `CONTENT` varchar(4000) DEFAULT NULL COMMENT '�������ݣ����ģ�',
  `STATUS` int(1) DEFAULT NULL COMMENT '״̬��0���ɹ���1��ʧ�ܣ�',
  `CODE` varchar(10) DEFAULT NULL COMMENT '״̬����',
  `REQUEST_TIME` datetime DEFAULT NULL COMMENT '����ʱ��',
  `COMPLETE_TIME` datetime DEFAULT NULL COMMENT '���ʱ��',
  `REQUEST_DURATION` int(10) DEFAULT NULL COMMENT '�����ʱ',
  `SERVER_TIME` datetime DEFAULT NULL COMMENT '������ʱ��',
  `DB_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '���ݿ�ʱ��',
  `REQUESET_IP` varchar(80) DEFAULT NULL COMMENT '����IP��ַ',
  `REQUEST_URL` varchar(100) DEFAULT NULL COMMENT '�����ַ',
  `REMARK` longtext COMMENT '��ע',
  `SERVICE_TYPE` varchar(10) DEFAULT NULL COMMENT '��������',
  `ACCESSID` varchar(32) DEFAULT NULL COMMENT '����ID',
  PRIMARY KEY (`ID`),
  KEY `INDEX_USER_NO` (`USER_NO`),
  KEY `INDEX_ACTION_CODE` (`ACTION_CODE`),
  KEY `INDEX_USERNO_ACTION` (`USER_NO`,`ACTION_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=341015 DEFAULT CHARSET=utf8 COMMENT='�û������ӿ���־';