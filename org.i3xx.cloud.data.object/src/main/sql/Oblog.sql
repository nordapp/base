/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 04-Jan-2017 13:41:28 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Tables */

DROP TABLE IF EXISTS Oblog CASCADE
;

/* Create Tables */

CREATE TABLE Oblog
(
	uuid varchar(36) NULL,
	history varchar(36) NULL,
	CLASSNAME varchar(50) NULL,
	NAME varchar(255) NULL,
	CREATETIMESTAMP bigint NULL,
	OBTIMESTAMP bigint NULL   DEFAULT 0,
	TRANSID integer NULL   DEFAULT 0,
	OWNER varchar(36) NULL,
	LABEL varchar(36) NULL,
	CREATEUSER varchar(36) NULL,
	OBUSER varchar(36) NULL,
	FLAGS bigint NULL,
	STEREOTYPES varchar(1024) NULL,
	MANDANT varchar(50) NULL,
	guid bigint NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE Oblog ADD CONSTRAINT PK_Oblog
	PRIMARY KEY (guid)
;

CREATE INDEX Oblog_history_idx ON Oblog (history ASC)
;

CREATE INDEX Oblog_uuid_idx ON Oblog (uuid ASC)
;