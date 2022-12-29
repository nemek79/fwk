USE fwkbd;

CREATE TABLE t_contactos (

	id 				INT UNSIGNED 	AUTO_INCREMENT 	PRIMARY KEY,
	nombre		    VARCHAR(64) NOT NULL,
    apellidos       VARCHAR(128)
	
);

INSERT INTO t_contactos VALUES (null,'David','Alonso Sánchez');
