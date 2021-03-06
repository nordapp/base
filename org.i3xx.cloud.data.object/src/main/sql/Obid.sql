/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 05-Jan-2017 00:39:50 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Sequences for Autonumber Columns */

 

/* Drop Tables */

DROP TABLE IF EXISTS Obid CASCADE
;

/* Create Tables */

CREATE TABLE Obid
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
	guid bigserial NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE Obid ADD CONSTRAINT PK_Obid
	PRIMARY KEY (guid)
;

CREATE INDEX Obid_history_idx ON Obid (history ASC)
;

CREATE INDEX Obid_uuid_idx ON Obid (uuid ASC)
;

/* Create Table Comments, Sequences for Autonumber Columns */

 