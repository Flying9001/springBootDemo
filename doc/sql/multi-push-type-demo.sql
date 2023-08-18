/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/8/14 15:06:12                           */
/*==============================================================*/


drop table if exists message_push_result;

drop table if exists user_message;

drop table if exists user_push_type;

/*==============================================================*/
/* Table: message_push_result                                   */
/*==============================================================*/
create table message_push_result
(
   id                   bigint unsigned not null comment 'id',
   message_id           bigint unsigned comment '消息id',
   push_type            tinyint comment '推送方式,1-短信;2-邮件;3-app;4-wechat',
   push_result          tinyint comment '推送结果,0-失败,1-成功,2-未推送',
   push_record          varchar(32) comment '推送记录值,部分推送方式可根据记录值查询实际推送结果',
   retry_time           tinyint default 0 comment '消息发送失败重试次数',
   create_time          datetime default current_timestamp comment '创建时间',
   update_time          datetime default current_timestamp on update current_timestamp comment '更新时间',
   primary key (id)
)
engine = innodb default
charset = utf8mb4;

alter table message_push_result comment '消息推送结果';

/*==============================================================*/
/* Table: user_message                                          */
/*==============================================================*/
create table user_message
(
   id                   bigint not null comment 'id',
   user_id              bigint comment '用户信息',
   push_count           tinyint not null default 0 comment '实际消息推送次数',
   push_total           tinyint not null default 0 comment '总共消息所需推送次数',
   message_type         tinyint comment '消息类型;1-登录通知;2-费用通知;3-服务器报警',
   title                varchar(64) comment '消息标题',
   content              varchar(256) comment '消息内容',
   create_time          datetime default current_timestamp comment '创建时间',
   update_time          datetime default current_timestamp on update current_timestamp comment '更新时间',
   primary key (id)
)
engine = innodb default
charset = utf8;

alter table user_message comment '用户消息';

/*==============================================================*/
/* Table: user_push_type                                        */
/*==============================================================*/
create table user_push_type
(
   id                   bigint not null comment 'id',
   user_id              bigint comment '用户id',
   push_type            tinyint comment '推送方式,1-短信;2-邮件;3-app;4-wechat',
   receive_address      varchar(128) comment '通知推送接收地址',
   enable               tinyint comment '是否启用,0-未启用,1-启用',
   create_time          datetime default current_timestamp comment '创建时间',
   update_time          datetime default current_timestamp on update current_timestamp comment '更新时间',
   primary key (id)
)
engine = innodb default
charset = utf8mb4;

alter table user_push_type comment '用户消息推送方式';

