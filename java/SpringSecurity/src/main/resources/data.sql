CREATE TABLE `tb_user` (
	`id` BIGINT ( 20 ) NOT NULL auto_increment,
	`username` VARCHAR ( 50 ) NOT NULL COMMENT '用户名',
	`password` VARCHAR ( 64 ) NOT NULL COMMENT '密码，加密存储',
	`phone` VARCHAR ( 20 ) DEFAULT NULL COMMENT '注册手机号',
	`email` VARCHAR ( 50 ) DEFAULT NULL COMMENT '注册邮箱',
	`created` datetime NOT NULL,
	`updated` datetime NOT NULL,
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `username` ( `username` ) USING BTREE,
	UNIQUE KEY `phone` ( `phone` ) USING BTREE,
	UNIQUE KEY `email` ( `email` ) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 38 DEFAULT CHARSET = utf8 COMMENT = '用户表';

INSERT INTO `tb_user` ( `id`, `username`, `password`, `phone`, `email`, `created`, `updated` )
VALUES
	( 37, 'fox', '$2a$10$9ZhDOBp.sRKat4l14ygu/.LscxrMUcDAfeVOEPiYwbcRkoB09gCmi', '158xxxxxxx', 'xxxxxxx@gmail.com', '2019-04-04 23:21:27', '2019-04-04 23:21:29' );

CREATE TABLE `tb_role` (
	`id` BIGINT ( 20 ) NOT NULL auto_increment,
	`parent_id` BIGINT ( 20 ) DEFAULT NULL COMMENT '父角色',
	`name` VARCHAR ( 64 ) NOT NULL COMMENT '角色名称',
	`enname` VARCHAR ( 64 ) NOT NULL COMMENT '角色英文名称',
	`description` VARCHAR ( 200 ) DEFAULT NULL COMMENT '备注',
	`created` datetime NOT NULL,
	`updated` datetime NOT NULL,
PRIMARY KEY ( `id` )
) ENGINE = INNODB auto_increment = 38 DEFAULT charset = utf8 COMMENT = '角色表';

INSERT INTO `tb_role` ( `id`, `parent_id`, `name`, `enname`, `description`, `created`, `updated` )
VALUES
	( 37, 0, '超级管理员', 'fox', NULL, '2019-04-04 23:22:03', '2019-04-04 23:22:05' );

CREATE TABLE `tb_user_role` (
	`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT ( 20 ) NOT NULL COMMENT '用户ID',
	`role_id` BIGINT ( 20 ) NOT NULL COMMENT '角色ID',
	PRIMARY KEY ( `id` )
) ENGINE = INNODB AUTO_INCREMENT = 38 DEFAULT CHARSET = utf8 COMMENT = '用户角色表';


INSERT INTO `tb_user_role` ( `id`, `user_id`, `role_id` )
VALUES
	( 37, 37, 37 );

CREATE TABLE `tb_permission` (
	`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT,
	`parent_id` BIGINT ( 20 ) DEFAULT NULL COMMENT '父权限',
	`name` VARCHAR ( 64 ) NOT NULL COMMENT '权限名称',
	`enname` VARCHAR ( 64 ) NOT NULL COMMENT '权限英文名称',
	`url` VARCHAR ( 255 ) NOT NULL COMMENT '授权路径',
	`description` VARCHAR ( 200 ) DEFAULT NULL COMMENT '备注',
	`created` datetime NOT NULL,
	`updated` datetime NOT NULL,
	PRIMARY KEY ( `id` )
) ENGINE = INNODB AUTO_INCREMENT = 44 DEFAULT CHARSET = utf8 COMMENT = '权限表';


INSERT INTO `tb_permission` ( `id`, `parent_id`, `name`, `enname`, `url`, `description`, `created`, `updated` )
VALUES
	( 37, 0, '系统管理', 'System', '/', NULL, '2019-04-04 23:22:54', '2019-04-04 23:22:56' ),
	( 38, 37, '用户管理', 'SystemUser', '/users/', NULL, '2019-04-04 23:25:31', '2019-04-04 23:25:33' ),
	( 39, 38, '查看用户', 'SystemUserView', '', NULL, '2019-04-04 15:30:30', '2019-04-04 15:30:43' ),
	( 40, 38, '新增用户', 'SystemUserInsert', '', NULL, '2019-04-04 15:30:31', '2019-04-04 15:30:44' ),
	( 41, 38, '编辑用户', 'SystemUserUpdate', '', NULL, '2019-04-04 15:30:32', '2019-04-04 15:30:45' ),
	( 42, 38, '删除用户', 'SystemUserDelete', '', NULL, '2019-04-04 15:30:48', '2019-04-04 15:30:45' ),
	( 44, 37, '内容管理', 'SystemContent', '/contents/', NULL, '2019-04-06 18:23:58', '2019-04-06 18:24:00' ),
	( 45, 44, '查看内容', 'SystemContentView', '/contents/view/**', NULL, '2019-04-06 23:49:39', '2019-04-06 23:49:41' ),
	( 46, 44, '新增内容', 'SystemContentInsert', '/contents/insert/**', NULL, '2019-04-06 23:51:00', '2019-04-06 23:51:02' ),
	( 47, 44, '编辑内容', 'SystemContentUpdate', '/contents/update/**', NULL, '2019-04-06 23:51:04', '2019-04-06 23:51:06' ),
	( 48, 44, '删除内容', 'SystemContentDelete', '/contents/delete/**', NULL, '2019-04-06 23:51:08', '2019-04-06 23:51:10' );

CREATE TABLE `tb_role_permission` (
	`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT,
	`role_id` BIGINT ( 20 ) NOT NULL COMMENT '角色ID',
	`permission_id` BIGINT ( 20 ) NOT NULL COMMENT '权限ID',
	PRIMARY KEY ( `id` )
) ENGINE = INNODB AUTO_INCREMENT = 43 DEFAULT CHARSET = utf8 COMMENT = '角色权限表';


INSERT INTO `tb_role_permission` ( `id`, `role_id`, `permission_id` )
VALUES
	( 37, 37, 37 ),
	( 38, 37, 38 ),
	( 39, 37, 39 ),
	( 40, 37, 40 ),
	( 41, 37, 41 ),
	( 42, 37, 42 ),
	( 43, 37, 44 ),
	( 44, 37, 45 ),
	( 45, 37, 46 ),
	( 46, 37, 47 ),
	( 47, 37, 48 );