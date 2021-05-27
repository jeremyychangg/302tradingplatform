CREATE DATABASE tradingPlatform;

USE tradingPlatform;

CREATE TABLE users (
    userID          CHAR(10)            PRIMARY KEY     NOT NULL    ,
    firstName       VARCHAR(144)                        NOT NULL    ,
    lastName        VARCHAR(144)                        NOT NULL    ,
    unitID          CHAR(10)                                        ,
    accountType     VARCHAR(64)                                     ,
    password        VARCHAR(244)                        NOT NULL             
);

CREATE TABLE units (
    unitID          CHAR(10)            PRIMARY KEY     NOT NULL    ,
    unitName        VARCHAR(144)                        NOT NULL    ,
    creditBalance   FLOAT                                           ,
    creditLimit     FLOAT                                       
);

CREATE TABLE assets (
    assetID         CHAR(10)            PRIMARY KEY     NOT NULL    ,
    assetName       VARCHAR(64)                         NOT NULL    ,
    currentPrice    FLOAT                                           ,
    assetType       VARCHAR(64)
);

CREATE TABLE inventory (
	unitID          CHAR(10)            PRIMARY KEY     NOT NULL    ,
    assetID         CHAR(10)                            NOT NULL    ,
    quantity        INT(8)                                          ,
	orderID         CHAR(10)
);

CREATE TABLE orders (
    orderID         CHAR(10)            PRIMARY KEY     NOT NULL    ,
    userID          CHAR(10)                            NOT NULL    ,
    unitID          CHAR(10)                            NOT NULL    ,
    orderTime       DATETIME                                        ,
    assetID         CHAR(10)                            NOT NULL    ,
    orderStatus     VARCHAR(32)                                     ,
    orderType       VARCHAR(32)                                     ,
    orderPrice      FLOAT                               NOT NULL    ,
    orderQuantity   INT(16)                             NOT NULL    ,
    quantFilled     INT(16)                                         ,
    quantRemain     INT(16)                                     
);

CREATE TABLE requests (
    requestID       CHAR(10)            PRIMARY KEY     NOT NULL    ,
    userID          CHAR(10)                            NOT NULL    ,
    requestType     VARCHAR(144)                                    ,
    requestMessage  VARCHAR(512)
);

CREATE TABLE notifications (
    notifID         CHAR(10)            PRIMARY KEY     NOT NULL    ,
    userID          CHAR(10)                            NOT NULL    ,
    userMessage     VARCHAR(244)
);

CREATE TABLE watchlists (
    unitID          CHAR(10)                            NOT NULL    ,
    assetID         CHAR(10)                            NOT NULL    ,
    CONSTRAINT      pk_watchlists       PRIMARY KEY (unitID, assetID)
);
