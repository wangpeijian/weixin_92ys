/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : 92ys

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-07-01 15:28:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` varchar(32) NOT NULL,
  `title` varchar(255) DEFAULT '',
  `publishTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `userId` varchar(32) DEFAULT '',
  `content` longtext,
  `status` int(11) DEFAULT NULL COMMENT '文章状态 1：已发布，2未发布',
  `cover` text COMMENT '封面图片',
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `shareDescribe` varchar(200) DEFAULT '' COMMENT '文章分享描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公众号发布的文章';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('533a0a4bcf3f4f44aed827ebb70e2649', '123', '2017-06-25 23:16:26', '56dbb2134b6c495abe1292ed4f7065d3', '<p style=\"margin-left: 40px; text-align: left;\">123123123<img class=\"fr-fin\" data-fr-image-preview=\"false\" alt=\"Image title\" src=\"http://localhost/uploadImages/article_image_20170624180013_66134906gw1eyawok4wcyj20k00f0do1.jpg\" width=\"300\"></p>', '1', 'http://localhost/uploadImages/article_cover_20170624164622_61e7f4aajw1eqteegcuj8j21kw223nm5.jpg', '2017-06-25 23:16:26', '文章分享描述123');
INSERT INTO `article` VALUES ('662bdd02b7a84891a25ddc68ad74368e', '测试文章', '2017-06-25 23:14:32', '56dbb2134b6c495abe1292ed4f7065d3', '<p>786543</p>', '1', 'http://localhost/uploadImages/article_cover_20170624164612_61e7f4aajw1eqteeefyx0j21kw223k2a.jpg', '2017-06-25 23:14:32', null);

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
  `id` varchar(255) NOT NULL,
  `url` text,
  `pId` varchar(255) DEFAULT '',
  `no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存产品的轮播图链接';

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES ('218525f05b1b433db825936d536823a8', 'http://localhost/uploadImages/product_carousel_20170624181006_66134906jw1f47mh21q56j20k00qowvg.jpg', '082dff9ea85446f9ae2b65d37184451a', '1');
INSERT INTO `carousel` VALUES ('337f95cc11b24b438abe5d3960786457', 'http://localhost/uploadImages/product_carousel_20170624180845_66134906jw1eof59v32pkj20dc0hs400.jpg', 'ad6c17131a9b4488b10cc8e11d48e54e', '0');
INSERT INTO `carousel` VALUES ('36ee086e0cb84b9a991cdb5f9995df98', 'http://localhost/uploadImages/product_carousel_20170625221803_66134906gw1f5mwjg50ndj20m810ojwq.jpg', '16c7e14c78ba406ca37245dd83644aca', '0');
INSERT INTO `carousel` VALUES ('404e2c3d94234da49b61aa9275c3c7b9', 'http://localhost/uploadImages/product_carousel_20170624180751_61e7f4aajw1eqteejrmf8j21kw24ftyi.jpg', '27638b069cad4bdf8c0e6c41baf4d181', '2');
INSERT INTO `carousel` VALUES ('6bcc93d11af84521a152f22299816f75', 'http://localhost/uploadImages/product_carousel_20170624180751_66134906gw1ezpfkvjyp9j20k00qo78e.jpg', '27638b069cad4bdf8c0e6c41baf4d181', '1');
INSERT INTO `carousel` VALUES ('8fcdb8f94ba049c7a1e74f6e6bcae894', 'http://localhost/uploadImages/product_carousel_20170624180751_66134906gw1evgfbbqf4bj20k00qoqly.jpg', '27638b069cad4bdf8c0e6c41baf4d181', '0');
INSERT INTO `carousel` VALUES ('a28c8bb555214839a47651995e970d66', 'http://localhost/uploadImages/product_carousel_20170624181006_66134906jw1f47mh2qntcj20k00qo0vw.jpg', '082dff9ea85446f9ae2b65d37184451a', '0');
INSERT INTO `carousel` VALUES ('aa4321ad402e4c59ba52a07ba32f5da9', 'http://localhost/uploadImages/product_carousel_20170624180816_66134906jw1ekvxx4v64nj21w02ionpd.jpg', '343547fcc35d4f0ba9a0a0eff5e24a24', '1');
INSERT INTO `carousel` VALUES ('b54ab45773fe4d3fa343544ad86536ec', 'http://localhost/uploadImages/product_carousel_20170624180945_66134906jw1f2or5ooxzbj20yo0n4x4o.jpg', 'a0bc7171cf9d4cbbb68066a931f43f13', '1');
INSERT INTO `carousel` VALUES ('d13bf0e60d094965a793a7a804a138e0', 'http://localhost/uploadImages/product_carousel_20170624180816_66134906gw1f5mwjg50ndj20m810ojwq.jpg', '343547fcc35d4f0ba9a0a0eff5e24a24', '0');
INSERT INTO `carousel` VALUES ('def0537455f948f0ad0935a7ab241275', 'http://localhost/uploadImages/product_carousel_20170624180945_66134906jw1f2hwj51rhsj20ku0v8kbh.jpg', 'a0bc7171cf9d4cbbb68066a931f43f13', '0');

-- ----------------------------
-- Table structure for jobtype
-- ----------------------------
DROP TABLE IF EXISTS `jobtype`;
CREATE TABLE `jobtype` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `code` varchar(255) DEFAULT '',
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色为员工的还需要有不同岗位的分类（设计师，程序员等）';

-- ----------------------------
-- Records of jobtype
-- ----------------------------
INSERT INTO `jobtype` VALUES ('3b9ac1e9af9641cdac6997ce9eea3b9f', '后端开发', '', '2017-03-25 16:03:54');
INSERT INTO `jobtype` VALUES ('57982e61cc2d4cf5b8f9fb2975ca6c2f', '设计师', '', '2017-03-25 16:03:39');
INSERT INTO `jobtype` VALUES ('8ff3ee096adb469d938213555b9090b6', '前端开发', '', '2017-03-25 16:03:47');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(32) NOT NULL,
  `productId` varchar(32) DEFAULT '',
  `price` decimal(10,2) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `customerId` varchar(32) DEFAULT '',
  `status` int(11) DEFAULT NULL COMMENT '订单状态',
  `authorId` varchar(32) DEFAULT '',
  `discount` float(2,1) DEFAULT NULL,
  `remark` varchar(200) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  `type` varchar(30) DEFAULT '',
  `cover` text,
  `haveAttachment` int(11) DEFAULT '0' COMMENT '产品是否需要给客户附件',
  `attachmentURL` text COMMENT '附件地址',
  `attachmentPassword` varchar(255) DEFAULT '' COMMENT '附件提取密码',
  `director` varchar(10) DEFAULT '' COMMENT '项目负责人',
  `directorPhone` varchar(11) DEFAULT '' COMMENT '项目负责人电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('92YS_PERSONAL_1498893573690', '082dff9ea85446f9ae2b65d37184451a', '23.00', '2017-07-01 15:19:34', '56dbb2134b6c495abe1292ed4f7065d4', '0', '56dbb2134b6c495abe1292ed4f7065d3', '1.0', '', '私人网站项目', 'personal', 'http://localhost/uploadImages/product_cover_20170624181007_66134906jw1f48sty49cwj20q00yo7wh.jpg', '0', '', '', '王配额', '15810577538');
INSERT INTO `order` VALUES ('92YS_RESOURCE_1498299269013', 'a0bc7171cf9d4cbbb68066a931f43f13', '1.50', '2017-06-24 18:17:01', '56dbb2134b6c495abe1292ed4f7065d4', '0', '', '0.5', '这个是有附件内容的', '静态资源', 'resource', 'http://localhost/uploadImages/product_cover_20170624180945_66134906jw1ewq71doxtrj20k00qotv7.jpg', '1', null, null, '', '');
INSERT INTO `order` VALUES ('92YS_RESOURCE_1498307327537', 'a0bc7171cf9d4cbbb68066a931f43f13', '2.50', '2017-06-24 20:30:02', '56dbb2134b6c495abe1292ed4f7065d4', '1', '', '0.5', '123123123', '静态资源', 'resource', 'http://localhost/uploadImages/product_cover_20170624180945_66134906jw1ewq71doxtrj20k00qotv7.jpg', '1', null, null, '', '');
INSERT INTO `order` VALUES ('92YS_RESOURCE_1498400349879', 'a0bc7171cf9d4cbbb68066a931f43f13', '1.00', '2017-06-25 22:19:10', '56dbb2134b6c495abe1292ed4f7065d4', '0', '', '0.5', '有素材的静态资源', '静态资源', 'resource', 'http://localhost/uploadImages/product_cover_20170624180945_66134906jw1ewq71doxtrj20k00qotv7.jpg', '1', null, null, '', '');
INSERT INTO `order` VALUES ('92YS_RESOURCE_1498400818435', 'a0bc7171cf9d4cbbb68066a931f43f13', '1.00', '2017-06-25 22:26:58', '56dbb2134b6c495abe1292ed4f7065d4', '0', '', '0.5', '', '静态资源', 'resource', 'http://localhost/uploadImages/product_cover_20170624180945_66134906jw1ewq71doxtrj20k00qotv7.jpg', '1', null, null, '', '');
INSERT INTO `order` VALUES ('92YS_SERVICE_1498299243025', '343547fcc35d4f0ba9a0a0eff5e24a24', '2.00', '2017-06-24 18:17:03', '56dbb2134b6c495abe1292ed4f7065d4', '0', '', '1.0', '这个是没有附件的', '开发网站', 'service', 'http://localhost/uploadImages/product_cover_20170624180817_66134906gw1f029tp0prjj20k00qoatr.jpg', '0', null, null, '', '');
INSERT INTO `order` VALUES ('92YS_SERVICE_1498400804385', '27638b069cad4bdf8c0e6c41baf4d181', '6.00', '2017-06-25 22:26:44', '56dbb2134b6c495abe1292ed4f7065d4', '0', '', '1.0', '洗的', '平面设计', 'service', 'http://localhost/uploadImages/product_cover_20170624180751_66134906jw1ekthhzmdaxj21w02iokjl.jpg', '0', null, null, '', '');
INSERT INTO `order` VALUES ('92YS_SERVICE_1498889188970', 'ad6c17131a9b4488b10cc8e11d48e54e', '4.20', '2017-07-01 14:06:29', '56dbb2134b6c495abe1292ed4f7065d4', '0', '', '1.0', '678768768', '维护公众号', 'service', 'http://localhost/uploadImages/product_cover_20170624180921_66134906jw1et2v06rbhlj20pw0tm1kx.jpg', '1', '哈哈哈哈哈', '123', '', '');
INSERT INTO `order` VALUES ('92YS_SERVICE_1498889590624', 'ad6c17131a9b4488b10cc8e11d48e54e', '4.20', '2017-07-01 14:13:11', '56dbb2134b6c495abe1292ed4f7065d4', '0', '', '1.0', '4', '维护公众号', 'service', 'http://localhost/uploadImages/product_cover_20170624180921_66134906jw1et2v06rbhlj20pw0tm1kx.jpg', '0', '', '', '王鹏阿胶爱你', '15881055727');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(32) NOT NULL,
  `type` varchar(30) DEFAULT '' COMMENT '企业服务：0，资源分享：1，私人服务：2',
  `title` varchar(255) DEFAULT '',
  `authorId` varchar(32) DEFAULT '',
  `cover` text,
  `content` longtext,
  `resourcesTypeId` varchar(32) DEFAULT '',
  `articleId` varchar(32) DEFAULT '',
  `discount` float(2,1) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `haveAttachment` int(11) DEFAULT '0' COMMENT '产品是否需要给客户附件',
  `attachmentURL` text COMMENT '附件地址',
  `attachmentPassword` varchar(255) DEFAULT '' COMMENT '附件提取密码',
  `director` varchar(10) DEFAULT '' COMMENT '项目负责人',
  `directorPhone` varchar(11) DEFAULT '' COMMENT '项目负责人电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='所有的产品存放在此表中，目前有三种类型，企业服务，静态资源，个人服务（包括静态资源，个人）';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('082dff9ea85446f9ae2b65d37184451a', 'personal', '私人网站项目', '56dbb2134b6c495abe1292ed4f7065d3', 'http://localhost/uploadImages/product_cover_20170624181007_66134906jw1f48sty49cwj20q00yo7wh.jpg', '<p>阿萨德</p>', '66d4e0670a814389beb90c409318dc15', '662bdd02b7a84891a25ddc68ad74368e', '1.0', '2017-06-26 23:57:33', '0', '', '', '王配额', '15810577538');
INSERT INTO `product` VALUES ('16c7e14c78ba406ca37245dd83644aca', 'personal', '私人静态资源', '56dbb2134b6c495abe1292ed4f7065d3', 'http://localhost/uploadImages/product_cover_20170625221803_66134906gw1ezj0uwpiggj20k00qoav2.jpg', '<p>阿达岁的</p>', '', '', '1.0', '2017-06-25 22:18:04', '1', null, '', '', '');
INSERT INTO `product` VALUES ('27638b069cad4bdf8c0e6c41baf4d181', 'service', '平面设计', '', 'http://localhost/uploadImages/product_cover_20170624180751_66134906jw1ekthhzmdaxj21w02iokjl.jpg', '<p>矮的</p>', '', '662bdd02b7a84891a25ddc68ad74368e', '1.0', '2017-06-26 23:51:17', '0', '', '', '123123', '12222222222');
INSERT INTO `product` VALUES ('343547fcc35d4f0ba9a0a0eff5e24a24', 'service', '开发网站', '', 'http://localhost/uploadImages/product_cover_20170624180817_66134906gw1f029tp0prjj20k00qoatr.jpg', '<p>矮的</p>', '', '533a0a4bcf3f4f44aed827ebb70e2649', '1.0', '2017-06-25 22:16:17', '0', null, '', '', '');
INSERT INTO `product` VALUES ('a0bc7171cf9d4cbbb68066a931f43f13', 'resource', '静态资源', '', 'http://localhost/uploadImages/product_cover_20170624180945_66134906jw1ewq71doxtrj20k00qotv7.jpg', '<p>阿萨德</p>', '66d4e0670a814389beb90c409318dc15', '533a0a4bcf3f4f44aed827ebb70e2649', '0.5', '2017-06-25 22:16:38', '1', null, '', '', '');
INSERT INTO `product` VALUES ('ad6c17131a9b4488b10cc8e11d48e54e', 'service', '维护公众号', '', 'http://localhost/uploadImages/product_cover_20170624180921_66134906jw1et2v06rbhlj20pw0tm1kx.jpg', '<p>123123123</p>', '', '662bdd02b7a84891a25ddc68ad74368e', '1.0', '2017-07-01 14:08:16', '0', '', '', '王鹏阿胶爱你', '15881055727');

-- ----------------------------
-- Table structure for productitem
-- ----------------------------
DROP TABLE IF EXISTS `productitem`;
CREATE TABLE `productitem` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `pid` varchar(32) DEFAULT '',
  `price` decimal(10,2) DEFAULT NULL,
  `isItem` int(1) DEFAULT NULL COMMENT '是否是子项目 1：子项目，2：项目组节点',
  `no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品的收费项目';

-- ----------------------------
-- Records of productitem
-- ----------------------------
INSERT INTO `productitem` VALUES ('0c12d130f79f4deaab2d28bd55822f51', '1', 'ad6c17131a9b4488b10cc8e11d48e54e', null, '0', '0');
INSERT INTO `productitem` VALUES ('1cef5f85c194439a829c4e64ddcd876f', '1.0', '0c12d130f79f4deaab2d28bd55822f51', '1.00', '1', '0');
INSERT INTO `productitem` VALUES ('2ed351d6dbd948ec9ed40f7a6376e1f2', '2', 'df624391df504b2597d65acf601e753c', '3.00', '1', '0');
INSERT INTO `productitem` VALUES ('2f5db24885174f9ab7c65e303b3aa6d8', '1', '343547fcc35d4f0ba9a0a0eff5e24a24', null, '0', '0');
INSERT INTO `productitem` VALUES ('4013cbc0e3024e9a9ff9c587e0a222d0', '1', '2f5db24885174f9ab7c65e303b3aa6d8', '2.00', '1', '0');
INSERT INTO `productitem` VALUES ('48859095d4a64f98ab74c792c6e99900', '22', 'a1d6f18557a64a3ca42728485570def2', '22.00', '1', '0');
INSERT INTO `productitem` VALUES ('4fad7670b3ce49febd83d986ce1f3374', '1.1', '082dff9ea85446f9ae2b65d37184451a', null, '0', '1');
INSERT INTO `productitem` VALUES ('52a0e0b88e63458092e620c9d4fff429', '1', 'b5aee12be0cb415889ae1324cb7e2631', '1.00', '1', '0');
INSERT INTO `productitem` VALUES ('657ea798db474c01bad2dba5ad7d2435', '1.2', '0c12d130f79f4deaab2d28bd55822f51', '1.20', '1', '1');
INSERT INTO `productitem` VALUES ('7d74dfe8d6a74ddebad3d2efd08f4417', '2.1', 'a0bc7171cf9d4cbbb68066a931f43f13', null, '0', '0');
INSERT INTO `productitem` VALUES ('97fa24fb90314092b5b2144c6971304c', '3', '27638b069cad4bdf8c0e6c41baf4d181', null, '0', '1');
INSERT INTO `productitem` VALUES ('9c51760646014836aa0168a799bfa68c', '123', 'ce9c84b3030f4b918238eef7e1f2db3e', '123.00', '1', '0');
INSERT INTO `productitem` VALUES ('a1d6f18557a64a3ca42728485570def2', '23', '082dff9ea85446f9ae2b65d37184451a', null, '0', '0');
INSERT INTO `productitem` VALUES ('b5aee12be0cb415889ae1324cb7e2631', '1', '27638b069cad4bdf8c0e6c41baf4d181', null, '0', '0');
INSERT INTO `productitem` VALUES ('bc8aae62f4b74199a043fcffc4879f9e', '4', '97fa24fb90314092b5b2144c6971304c', '5.00', '1', '0');
INSERT INTO `productitem` VALUES ('c31f28161caa477aa8b17ea55c1e95cc', '2.0', 'f965e19abcc64a7babbf4802d5aca13c', '2.00', '1', '0');
INSERT INTO `productitem` VALUES ('c99f68c8177a474abc2de0a30d07fd21', '1.0', '4fad7670b3ce49febd83d986ce1f3374', '1.00', '1', '0');
INSERT INTO `productitem` VALUES ('ce9c84b3030f4b918238eef7e1f2db3e', '123', '16c7e14c78ba406ca37245dd83644aca', null, '0', '0');
INSERT INTO `productitem` VALUES ('df624391df504b2597d65acf601e753c', '2', '343547fcc35d4f0ba9a0a0eff5e24a24', null, '0', '1');
INSERT INTO `productitem` VALUES ('f8c46cd682234817bc139ded9c9d74f2', '2.0', '7d74dfe8d6a74ddebad3d2efd08f4417', '2.00', '1', '0');
INSERT INTO `productitem` VALUES ('f965e19abcc64a7babbf4802d5aca13c', '2', 'ad6c17131a9b4488b10cc8e11d48e54e', null, '0', '1');

-- ----------------------------
-- Table structure for productitem_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `productitem_snapshot`;
CREATE TABLE `productitem_snapshot` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `pid` varchar(32) DEFAULT '',
  `price` decimal(10,2) DEFAULT NULL,
  `isItem` int(1) DEFAULT NULL COMMENT '是否是子项目 1：子项目，2：项目组节点',
  `no` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品的收费项目';

-- ----------------------------
-- Records of productitem_snapshot
-- ----------------------------
INSERT INTO `productitem_snapshot` VALUES ('07981edfeae34a35b33daf5cb65a91fd', '1.0', '71d7efad3aef43789e7b5eea11ae1113', '1.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('16e9a87773e3474ba856b1476b613f9c', '1.2', '71d7efad3aef43789e7b5eea11ae1113', '1.20', '1', '1', '1');
INSERT INTO `productitem_snapshot` VALUES ('250c4d6cef88445f906fc0424745afdb', '23', '92YS_PERSONAL_1498893573690', null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('26aff7f077f54bf598f4f635fa6c091d', '1.0', 'd5da879fb9694377b6d7e6d19815dbeb', '1.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('2bbbe4697df14f819260ece5471419ea', '2.1', '92YS_RESOURCE_1498299269013', null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('3488ca50df3b469392424ab593bf7c14', '2.0', '2bbbe4697df14f819260ece5471419ea', '2.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('36c14e484541451980182135414bfcb4', '1.1', '5cad3e5079fa40cc92b03b25d3747107', null, '0', '1', '0');
INSERT INTO `productitem_snapshot` VALUES ('37323afb23f741769f4dd8ffb3c0e83b', '22', '250c4d6cef88445f906fc0424745afdb', '22.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('3ab485b8738c4453af8f6bf208900e15', '4', 'f92acafdf19145ddafda7caa3ec77881', '5.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('52e90215992d43468e33b48693873e0d', '2.0', 'adc615c40155459f96d596f6a894dca8', '2.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('5bb66f8e34a5474a84361893120036af', '1.0', null, '1.00', '1', '0', '2');
INSERT INTO `productitem_snapshot` VALUES ('64b22beecce64f41b783be196d38038d', '2.1', '5cad3e5079fa40cc92b03b25d3747107', null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('71d7efad3aef43789e7b5eea11ae1113', '1', '92YS_SERVICE_1498889590624', null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('79d5f8ea68c940789d88fb8039262082', '2.0', 'bb910b1095a643f79937249c6d1a49b0', '2.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('7fed48d78a2044b39aa4aae978aa6fb0', '1', '92YS_SERVICE_1498400804385', null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('9a282322258b40bea9272bed16ec6ee7', '1', '7fed48d78a2044b39aa4aae978aa6fb0', '1.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('9b1a221682a7423b92771dcea4eb39aa', '1.2', 'd5da879fb9694377b6d7e6d19815dbeb', '1.20', '1', '1', '1');
INSERT INTO `productitem_snapshot` VALUES ('9ce5c8b964ae4d6f8e370726e49a18b4', '2.0', 'd3589b201db84c1f80382bf93f526fa1', '2.00', '1', '0', '2');
INSERT INTO `productitem_snapshot` VALUES ('9dd7a6680801446cb418e0a9748f8932', '1.1', '92YS_RESOURCE_1498299269013', null, '0', '1', '0');
INSERT INTO `productitem_snapshot` VALUES ('a0d0cf1a5d9b486b9934f775178e0e8c', '2.1', null, null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('a4f87d26861a469682aff26eabbd701b', '2.0', '64b22beecce64f41b783be196d38038d', '2.00', '1', '0', '2');
INSERT INTO `productitem_snapshot` VALUES ('a5a0b2bcb6794e18b7f83ca12aecd4cb', '1.0', 'b3ac96d59cff4067ad155f50fdcd1f73', '1.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('adc615c40155459f96d596f6a894dca8', '2', '92YS_SERVICE_1498889590624', null, '0', '1', '0');
INSERT INTO `productitem_snapshot` VALUES ('b3ac96d59cff4067ad155f50fdcd1f73', '1.1', '92YS_RESOURCE_1498307327537', null, '0', '1', '0');
INSERT INTO `productitem_snapshot` VALUES ('b497bcc2de02478e835b214d8fcf12c0', '1.0', 'cdd9329aed5046128a0b26e5849a7736', '1.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('b7976e90e6e04b748afdb26c6985d648', '1.1', null, null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('bb910b1095a643f79937249c6d1a49b0', '2', '92YS_SERVICE_1498889188970', null, '0', '1', '0');
INSERT INTO `productitem_snapshot` VALUES ('cdd9329aed5046128a0b26e5849a7736', '1.1', '92YS_PERSONAL_1498893573690', null, '0', '1', '0');
INSERT INTO `productitem_snapshot` VALUES ('d3589b201db84c1f80382bf93f526fa1', '2.1', '92YS_RESOURCE_1498307327537', null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('d5da879fb9694377b6d7e6d19815dbeb', '1', '92YS_SERVICE_1498889188970', null, '0', '0', '0');
INSERT INTO `productitem_snapshot` VALUES ('d68c3db75dc74a81a0e986118f819002', '1.0', '9dd7a6680801446cb418e0a9748f8932', '1.00', '1', '0', '1');
INSERT INTO `productitem_snapshot` VALUES ('db5f2062ff6f453c8c2236fd64f668a8', '1.0', '36c14e484541451980182135414bfcb4', '1.00', '1', '0', '3');
INSERT INTO `productitem_snapshot` VALUES ('e6f6317d586d4be5bdc91db8179e3a20', '2.0', null, '2.00', '1', '0', '2');
INSERT INTO `productitem_snapshot` VALUES ('f92acafdf19145ddafda7caa3ec77881', '3', '92YS_SERVICE_1498400804385', null, '0', '1', '0');

-- ----------------------------
-- Table structure for resourcestype
-- ----------------------------
DROP TABLE IF EXISTS `resourcestype`;
CREATE TABLE `resourcestype` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT '' COMMENT '静态资源的分类名称',
  `cover` text,
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='静态资源类型的产品分类标记';

-- ----------------------------
-- Records of resourcestype
-- ----------------------------
INSERT INTO `resourcestype` VALUES ('66d4e0670a814389beb90c409318dc15', 'ui设计', 'http://localhost/uploadImages/resources_type_cover_20170624181145_66134906jw1f64sjag88wj20k00qoql1.jpg', '2017-03-26 06:46:44');
INSERT INTO `resourcestype` VALUES ('95722adecf484f9b9f6b84d86ea4b3bd', '静态图片', 'http://localhost/uploadImages/resources_type_cover_20170624181148_66134906jw1ev8ywywdv3j20yo0q01kd.jpg', '2017-06-11 11:57:07');
INSERT INTO `resourcestype` VALUES ('dd5f989a8944455da8677ea6a4111df7', 'web原型', 'http://localhost/uploadImages/resources_type_cover_20170624181146_66134906jw1f5zmbilaudj23sg5ogu12.jpg', '2017-03-31 12:50:02');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `tag` varchar(255) DEFAULT '' COMMENT '权限名',
  `code` varchar(255) DEFAULT '' COMMENT '权限码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统中的角色表，管理员，员工，普通用户';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('56dbb2134b6c495abe1292ed4f7065d1', '管理员', 'admin');
INSERT INTO `role` VALUES ('56dbb2134b6c495abe1292ed4f7065d2', '工人', 'worker');
INSERT INTO `role` VALUES ('56dbb2134b6c495abe1292ed4f7065d3', '游客', 'user');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `username` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `head` text,
  `lastTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `registerTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `roleId` varchar(255) DEFAULT '',
  `sex` varchar(255) DEFAULT '',
  `phone` varchar(255) DEFAULT '',
  `province` varchar(255) DEFAULT '',
  `city` varchar(255) DEFAULT '',
  `style` varchar(255) DEFAULT '' COMMENT '设计师风格，用分号分隔',
  `jobTypeId` varchar(255) DEFAULT '' COMMENT '员工的岗位信息',
  `remark` varchar(200) DEFAULT '' COMMENT '员工的简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('56dbb2134b6c495abe1292ed4f7065d2', '超管', 'admin', 'admin', 'http://ui.narutom.com/imgd/lufei.jpg', '2017-03-23 13:11:10', '2017-03-23 13:11:10', '56dbb2134b6c495abe1292ed4f7065d1', '男', '15810577538', '北京', '宣武', null, null, '');
INSERT INTO `user` VALUES ('56dbb2134b6c495abe1292ed4f7065d3', '王配额', 'dd', 'dd', 'http://ui.narutom.com/imgd/lufei.jpg', '2017-03-26 17:12:19', '2017-03-26 17:12:19', '56dbb2134b6c495abe1292ed4f7065d2', '女', '15810577538', '北京', '宣武', '123', '57982e61cc2d4cf5b8f9fb2975ca6c2f', '123123');
INSERT INTO `user` VALUES ('56dbb2134b6c495abe1292ed4f7065d4', '游客123', 'tt', 'tt', 'http://ui.narutom.com/imgd/lufei.jpg', '2017-03-23 11:20:04', '2017-03-23 11:20:04', '56dbb2134b6c495abe1292ed4f7065d3', '男', '15810577538', '北京', '宣武', null, null, '');
INSERT INTO `user` VALUES ('97b1a1fae5d74a3a8a8c6d58979e657e', '阿斯达岁的', '123123123', '12312312', 'http://localhost:8081/views/image/upload/user_head/20170331211812_66134906gw1eyawok4wcyj20k00f0do1.jpg', null, '2017-03-31 13:18:26', '56dbb2134b6c495abe1292ed4f7065d2', '男', '12312312321', null, null, '123123123', '57982e61cc2d4cf5b8f9fb2975ca6c2f', '');
