/* ---------------------------------------------------- */
/*  Written by Stefan Hauptmann, I.D.S. GmbH 		*/
/*  Created On : 04-Jan-2017 13:41:28 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Tables */

DROP TABLE IF EXISTS Objecttoob4 CASCADE
;

/* Create Tables */

CREATE TABLE Objecttoob4
(
	guid bigserial NOT NULL,
	uuid varchar(36) NULL,
	history varchar(36) NULL,
	ID bigint NULL   DEFAULT 0,
	TRANSID integer NULL   DEFAULT 0,
	FIRMA varchar(50) NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE Objecttoob4 ADD CONSTRAINT PK_Objecttoob4
	PRIMARY KEY (guid)
;

CREATE INDEX Objecttoob4_uuid_idx ON Objecttoob4 (uuid ASC)
;

CREATE INDEX Objecttoob4_history_idx ON Objecttoob4 (history ASC)
;

CREATE INDEX Objecttoob4_uuidindex_idx ON Objecttoob4 (uuid ASC, history ASC)
;

CREATE INDEX Objecttoob4_idindex_idx ON Objecttoob4 (ID ASC, TRANSID ASC, FIRMA ASC)
;
