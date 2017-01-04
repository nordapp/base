/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 04-Jan-2017 13:41:28 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Tables */

DROP TABLE IF EXISTS Objecthandshake CASCADE
;

/* Create Tables */

CREATE TABLE Objecthandshake
(
	guid bigserial NOT NULL,
	uuid varchar(36) NULL,
	history varchar(36) NULL,
	transid varchar(36) NULL,
	SYN integer NULL   DEFAULT 0,
	ACK integer NULL   DEFAULT 0,
	TIMEOUT bigint NULL   DEFAULT 0,
	FIN integer NULL   DEFAULT 0,
	RST integer NULL   DEFAULT 0,
	LOG varchar(72) NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE Objecthandshake ADD CONSTRAINT PK_Objecthandshake
	PRIMARY KEY (guid)
;

CREATE INDEX Objecthandshake_transid_idx ON Objecthandshake (transid ASC)
;

CREATE INDEX Objecthandshake_uuid_idx ON Objecthandshake (uuid ASC)
;

CREATE INDEX Objecthandshake_uuid_idx ON Objecthandshake (uuid ASC)
;