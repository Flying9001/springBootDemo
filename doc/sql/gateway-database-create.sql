/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/8/23 16:29:21                           */
/*==============================================================*/


drop table if exists gateway_route;

drop table if exists gateway_white_list;

/*==============================================================*/
/* Table: gateway_route                                         */
/*==============================================================*/
create table gateway_route
(
   id                   bigint unsigned not null auto_increment comment '数据库id',
   route_id             varchar(64) comment '路由id',
   uri                  varchar(128) comment '请求地址',
   predicates           varchar(512) comment '断言',
   filters              varchar(512) comment '拦截器',
   metadata             varchar(128) comment '附加参数',
   filter_order         int comment '执行顺序,数值越小优先级越高',
   create_date          bigint unsigned comment '创建时间',
   update_date          bigint unsigned comment '更新时间',
   primary key (id)
);

alter table gateway_route comment '网关路由';

/*==============================================================*/
/* Table: gateway_white_list                                    */
/*==============================================================*/
create table gateway_white_list
(
   id                   bigint unsigned not null comment 'id',
   route_type           varchar(32) comment '路由类型',
   path                 varchar(128) comment '请求路径',
   comment              varchar(128) comment '说明',
   create_date          bigint unsigned comment '创建时间',
   update_date          bigint unsigned comment '更新时间',
   primary key (id)
)
engine = innodb default
charset = utf8mb4;

alter table gateway_white_list comment '网关路由白名单';

