USE fwkbd;

CREATE TABLE t_contacto (

	id 				INT UNSIGNED 	AUTO_INCREMENT 	PRIMARY KEY,
	nombre		    VARCHAR(64) NOT NULL,
    apellidos       VARCHAR(128)
	
);

INSERT INTO t_contacto VALUES (null,'David','Alonso Sánchez');
