USE fwkbd;

# https://www.datosmundial.com/codigos-de-pais.php

CREATE TABLE t_paises (

	id 				INT UNSIGNED PRIMARY KEY,
	nombre		    VARCHAR(64) NOT NULL,
    iso_3166_a2     VARCHAR(2)  NOT NULL,
    iso_3166_a3     VARCHAR(3)  NOT NULL,
    iso_3166_num    VARCHAR(3)  NOT NULL,
    domain          VARCHAR(4)  
	
);

INSERT INTO t_paises VALUES 
(0,'Desconocido','XX','XXX','000',''),
(1,'Alemania','DE','DEU','276','.de'),
(2,'Argentina','AR','ARG','032','.ar'),
(3,'Bolivia','BO','BOL','068','.bo'),
(4,'España','ES','ESP','724','.es'),
(5,'Honduras','HN','HND','340','.hn');


ALTER TABLE t_contactos
ADD COLUMN pais_nacimiento_id INT UNSIGNED NOT NULL;

ALTER TABLE t_contactos
ADD CONSTRAINT fk_contactos_pais_nacimiento FOREIGN KEY (pais_nacimiento_id) 
REFERENCES t_paises(id);

ALTER TABLE t_contactos
ADD COLUMN pais_residencia_id INT UNSIGNED NOT NULL;

ALTER TABLE t_contactos
ADD CONSTRAINT fk_contactos_pais_residencia FOREIGN KEY (pais_residencia_id) 
REFERENCES t_paises(id);