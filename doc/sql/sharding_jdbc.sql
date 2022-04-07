/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/4/7 10:35:20                            */
/*==============================================================*/


DROP TABLE IF EXISTS DEVICE_INFO;

DROP TABLE IF EXISTS ROUTER_CONFIG_0;

DROP TABLE IF EXISTS ROUTER_CONFIG_1;

DROP TABLE IF EXISTS ROUTER_CONFIG_2;

/*==============================================================*/
/* Table: DEVICE_INFO                                           */
/*==============================================================*/
CREATE TABLE DEVICE_INFO
(
   ID                   BIGINT UNSIGNED NOT NULL COMMENT 'id',
   TYPE                 TINYINT COMMENT '设备类型,1-路由器,2-音响,3-摄像头',
   BRAND                VARCHAR(32) COMMENT '设备品牌',
   MODEL                VARCHAR(32) COMMENT '设备型号',
   NAME                 VARCHAR(32) COMMENT '设备名称',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   UPDATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8MB4;

ALTER TABLE DEVICE_INFO COMMENT '设备信息';

/*==============================================================*/
/* Table: ROUTER_CONFIG_0                                       */
/*==============================================================*/
CREATE TABLE ROUTER_CONFIG_0
(
   ID                   BIGINT UNSIGNED NOT NULL COMMENT '主键',
   DEVICE_ID            BIGINT COMMENT '设备id',
   WIFI_NAME            VARCHAR(32) COMMENT 'wifi名称',
   WIFI_PASSWORD        VARCHAR(64) COMMENT 'wifi密码',
   ENCRYPT_TYPE         TINYINT DEFAULT 0 COMMENT '加密类型,0-不加密,1-WPA-PSK,2-WPA2-PSK,3-WPA/WPA2-PSK',
   ADMIN_PASSWORD       VARCHAR(64) COMMENT '管理员密码',
   WIFI_SWITCH          TINYINT DEFAULT 1 COMMENT 'wifi开关,0-关闭,1-开启',
   HIDE_SWITCH          TINYINT DEFAULT 0 COMMENT '是否隐藏 wifi,0-不隐藏,1-隐藏',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   UPDATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8MB4;

ALTER TABLE ROUTER_CONFIG_0 COMMENT '路由器配置信息';

/*==============================================================*/
/* Table: ROUTER_CONFIG_1                                       */
/*==============================================================*/
CREATE TABLE ROUTER_CONFIG_1
(
   ID                   BIGINT UNSIGNED NOT NULL COMMENT '主键',
   DEVICE_ID            BIGINT COMMENT '设备id',
   WIFI_NAME            VARCHAR(32) COMMENT 'wifi名称',
   WIFI_PASSWORD        VARCHAR(64) COMMENT 'wifi密码',
   ENCRYPT_TYPE         TINYINT DEFAULT 0 COMMENT '加密类型,0-不加密,1-WPA-PSK,2-WPA2-PSK,3-WPA/WPA2-PSK',
   ADMIN_PASSWORD       VARCHAR(64) COMMENT '管理员密码',
   WIFI_SWITCH          TINYINT DEFAULT 1 COMMENT 'wifi开关,0-关闭,1-开启',
   HIDE_SWITCH          TINYINT DEFAULT 0 COMMENT '是否隐藏 wifi,0-不隐藏,1-隐藏',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   UPDATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8MB4;

ALTER TABLE ROUTER_CONFIG_1 COMMENT '路由器配置信息';

/*==============================================================*/
/* Table: ROUTER_CONFIG_2                                       */
/*==============================================================*/
CREATE TABLE ROUTER_CONFIG_2
(
   ID                   BIGINT UNSIGNED NOT NULL COMMENT '主键',
   DEVICE_ID            BIGINT COMMENT '设备id',
   WIFI_NAME            VARCHAR(32) COMMENT 'wifi名称',
   WIFI_PASSWORD        VARCHAR(64) COMMENT 'wifi密码',
   ENCRYPT_TYPE         TINYINT DEFAULT 0 COMMENT '加密类型,0-不加密,1-WPA-PSK,2-WPA2-PSK,3-WPA/WPA2-PSK',
   ADMIN_PASSWORD       VARCHAR(64) COMMENT '管理员密码',
   WIFI_SWITCH          TINYINT DEFAULT 1 COMMENT 'wifi开关,0-关闭,1-开启',
   HIDE_SWITCH          TINYINT DEFAULT 0 COMMENT '是否隐藏 wifi,0-不隐藏,1-隐藏',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   UPDATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8MB4;

ALTER TABLE ROUTER_CONFIG_2 COMMENT '路由器配置信息';

