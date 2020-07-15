/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/13 16:53:12                           */
/*==============================================================*/


drop table if exists leave_info;

/*==============================================================*/
/* Table: leave_info                                            */
/*==============================================================*/
create table leave_info
(
   id                   varchar(40) not null comment '编号',
   student_name         varchar(30) comment '学生姓名',
   student_id           varchar(40) comment '学生编号',
   leave_reason         varchar(100) comment '请假原因',
   leave_duration       int comment '请假时长(单位:天)',
   primary key (id)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8;

alter table leave_info comment '请假信息';

