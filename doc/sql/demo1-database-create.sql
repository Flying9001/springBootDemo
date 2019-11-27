/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/6/16 11:42:47                           */
/*==============================================================*/

drop database if exists `demo1`;
create database `demo1` default character set utf8mb4 collate utf8mb4_general_ci;
use `demo1`;


drop table if exists receive_address;

/*==============================================================*/
/* Table: receive_address                                       */
/*==============================================================*/
create table receive_address
(
   id                   bigint(20) unsigned not null auto_increment comment 'id,主键',
   user_id              bigint(20) unsigned not null default 0 comment '用户 id',
   receiver_name        varchar(30) not null default '' comment '收货人姓名',
   telephone            varchar(20) not null default '' comment '收货人电话,支持手机号和座机',
   province             varchar(15) not null default '' comment '省份',
   city                 varchar(15) not null default '' comment '城市',
   area                 varchar(15) not null default '' comment '城市地区,县',
   address              varchar(100) not null default '' comment '详细地址',
   default_address      tinyint(1) not null default 0 comment '是否为默认地址,true是;false不是(默认值)',
   insert_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   insert_operator_id   bigint(20) not null default 0 comment '添加人用户 id',
   insert_identity      tinyint(4) not null default 1 comment '添加人身份标识,0:前台用户;1:后台用户(默认)',
   update_time          datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   update_operator_id   bigint(20) not null default 0 comment '修改人用户 id',
   update_identity      tinyint(4) not null default 1 comment '修改人身份标识;0:前台用户;1:后台用户(默认)',
   versions             int(11) not null default 1 comment '版本号(默认1);用于更新时对比操作',
   del_sign             tinyint(4) not null default 0 comment '是否逻辑删除;0:不删除(默认);1:逻辑删除;所有查询sql都要带上del=0这个条件',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = UTF8MB4;

alter table receive_address comment '收货地址';

