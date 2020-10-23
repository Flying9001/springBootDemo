/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/10/9 17:25:22                           */
/*==============================================================*/


DROP TABLE IF EXISTS CLASS;

DROP TABLE IF EXISTS CLASS_TEACHER;

DROP TABLE IF EXISTS TEACHER;

/*==============================================================*/
/* Table: CLASS                                                 */
/*==============================================================*/
CREATE TABLE CLASS
(
   ID                   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
   HEAD_TEACHER_ID      BIGINT UNSIGNED NOT NULL COMMENT '班主任ID',
   NAME                 VARCHAR(30) NOT NULL DEFAULT '' COMMENT '班级名称',
   PRIMARY KEY (ID)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8;

ALTER TABLE CLASS COMMENT '班级';

/*==============================================================*/
/* Table: CLASS_TEACHER                                         */
/*==============================================================*/
CREATE TABLE CLASS_TEACHER
(
   ID                   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
   CLASS_ID             BIGINT UNSIGNED NOT NULL COMMENT '班级 id',
   TEACHER_ID           BIGINT UNSIGNED NOT NULL COMMENT '教师 id',
   PRIMARY KEY (ID)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8;

ALTER TABLE CLASS_TEACHER COMMENT '班级教师';

/*==============================================================*/
/* Table: TEACHER                                               */
/*==============================================================*/
CREATE TABLE TEACHER
(
   ID                   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
   NAME                 VARCHAR(30) NOT NULL DEFAULT '' COMMENT '姓名',
   PRIMARY KEY (ID)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8;

ALTER TABLE TEACHER COMMENT '教师';

