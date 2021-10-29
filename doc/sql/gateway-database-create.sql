/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/10/21 15:41:38                          */
/*==============================================================*/


drop table if exists gateway_route;

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

