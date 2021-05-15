CREATE DATABASE tradingPlatform;

USE tradingPlatform;

CREATE TABLE users (
    user_ID         INT(8)             PRIMARY KEY   NOT NULL   ,
    first_name      VARCHAR(144)                     NOT NULL   ,
    last_name       VARCHAR(144)                     NOT NULL   ,
    position        VARCHAR(64)                                 ,
    admin_flag      INT(1)                                      ,
    unit_ID         INT(8)                                      ,
    pass_word       VARCHAR(512)                     NOT NULL             
);

CREATE TABLE units (
    unitID          INT(8)             PRIMARY KEY  NOT NULL    ,
    unit_name       VARCHAR(144)                    NOT NULL    ,
    credit_balance  FLOAT                                       ,
    credit_limit    FLOAT                                       
);

CREATE TABLE assets (
    asset_ID        INT(8)             PRIMARY KEY  NOT NULL    ,
    asset_name      VARCHAR(64)                     NOT NULL    ,
    current_price   FLOAT                                       ,
    asset_type      VARCHAR(64)
);

CREATE TABLE orders (
    order_ID        INT(8)             PRIMARY KEY  NOT NULL    ,
    user_ID         INT(8)                          NOT NULL    ,
    unit_ID         INT(8)                          NOT NULL    ,
    date_time       DATETIME                                    ,
    asset_ID        INT(8)                          NOT NULL    ,
    order_status    VARCHAR(32)                                 ,
    order_type      VARCHAR(32)                                 ,
    order_price     FLOAT                           NOT NULL    ,
    order_quantity  INT(16)                         NOT NULL    ,
    quant_filled    INT(16)                                     ,
    quant_remain    INT(16)                                     
);

CREATE TABLE trades (
    trade_ID        INT(8)             PRIMARY KEY  NOT NULL    ,
    user_ID         INT(8)                          NOT NULL    ,
    unit_ID         INT(8)                          NOT NULL    ,
    date_time       DATETIME                                    ,
    asset_ID        INT(8)                          NOT NULL    ,
    order_price     FLOAT                           NOT NULL    ,
    order_quantity  INT(16)                         NOT NULL    
);

CREATE TABLE notifications (
    notif_ID        INT(8)             PRIMARY KEY  NOT NULL    ,
    user_ID         INT(8)                          NOT NULL    ,
    user_message    VARCHAR(244)
);