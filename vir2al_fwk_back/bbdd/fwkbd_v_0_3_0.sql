USE fwkbd;

# https://www.datosmundial.com/codigos-de-pais.php

CREATE TABLE t_paises (

	id 				INT UNSIGNED 	AUTO_INCREMENT 	PRIMARY KEY,
	nombre		    VARCHAR(64) NOT NULL,
    iso_3166_a2     VARCHAR(2)  NOT NULL,
    iso_3166_a3     VARCHAR(3)  NOT NULL,
    iso_3166_num    VARCHAR(3)  NOT NULL,
    domain          VARCHAR(4)  
	
);

INSERT INTO t_paises VALUES 
(null,'Alemania','DE','DEU','276','.de'),
(null,'Argentina','AR','ARG','032','.ar'),
(null,'Bolivia','BO','BOL','068','.bo'),
(null,'España','ES','ESP','724','.es'),
(null,'Honduras','HN','HND','340','.hn');

