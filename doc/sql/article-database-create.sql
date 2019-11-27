/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/27 14:32:00                          */
/*==============================================================*/


drop table if exists article;

drop table if exists article_tag;

drop table if exists article_to_tag;

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   id                   bigint unsigned not null auto_increment comment '文章 id,主键',
   title                varchar(100) not null default '' comment '文章标题',
   content              varchar(5000) not null default '' comment '文章内容',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = UTF8MB4;

alter table article comment '文章表';

/*==============================================================*/
/* Table: article_tag                                           */
/*==============================================================*/
create table article_tag
(
   id                   bigint unsigned not null auto_increment comment '文章标签 id,主键',
   tag_name             varchar(20) not null default '' comment '标签名称',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = UTF8MB4;

alter table article_tag comment '文章标签表';

/*==============================================================*/
/* Table: article_to_tag                                        */
/*==============================================================*/
create table article_to_tag
(
   id                   bigint unsigned not null auto_increment comment '文章-标签关联表 id,主键',
   article_id           bigint unsigned not null default 0 comment '文章 id',
   tag_id               bigint unsigned not null default 0 comment '标签 id',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = UTF8MB4;

alter table article_to_tag comment '文章-标签关联表';

