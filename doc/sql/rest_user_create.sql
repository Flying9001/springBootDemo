/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/9/29 14:59:22                           */
/*==============================================================*/


drop table if exists rest_user;

/*==============================================================*/
/* Table: rest_user                                             */
/*==============================================================*/
create table rest_user
(
   id                   bigint(20) unsigned not null auto_increment comment 'id 主键',
   user_name            varchar(30) default null comment '用户名',
   passcode             varchar(100) default null comment '登陆密码',
   email                varchar(50) default null comment '邮箱',
   user_status          tinyint default 1 comment '用户账号状态,1正常(默认),2禁止登陆',
   insert_time          datetime default current_timestamp comment '用户注册时间',
   insert_operator_id   bigint comment '创建用户 id',
   insert_identity      tinyint comment '创建人身份标识',
   update_time          datetime default current_timestamp on update current_timestamp comment '用户更新时间',
   update_operator_id   bigint comment '更新人用户 id',
   update_identity      tinyint comment '更新人身份标识',
   versions             int unsigned default 1 comment '版本控制字段(默认1)',
   del_sign             tinyint default 0 comment '逻辑删除字段,0正常(默认),1删除',
   primary key (id)
)
engine=innodb default charset=utf8mb4 comment='REST用户';

