/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/9/29 16:10:13                           */
/*==============================================================*/


drop database if exists `demo`;
create database `demo` default character set utf8mb4 collate utf8mb4_general_ci;
use `demo`;

drop table if exists user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint unsigned not null auto_increment comment 'id 主键',
   user_name            varchar(30) comment '用户名',
   user_passcode        varchar(100) comment '登陆密码',
   user_email           varchar(50) comment '邮箱',
   user_insert_time     varchar(30) comment '用户注册时间',
   user_update_time     varchar(30) comment '用户更新时间',
   user_status          tinyint default 1 comment '用户账号状态,1正常(默认),2禁止登陆',
   user_version         int unsigned default 1 comment '版本控制字段(默认1)',
   user_del             tinyint default 0 comment '逻辑删除字段,0正常(默认),1删除',
   primary key (id)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8MB4;

alter table user comment '用户表';

