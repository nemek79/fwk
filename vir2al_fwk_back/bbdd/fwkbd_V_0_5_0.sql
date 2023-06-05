use fwkbd;

ALTER TABLE t_contactos 
ADD COLUMN fecha_nacimiento DATE;

CREATE TABLE t_tests (

    id 				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    str_key         VARCHAR(64) NOT NULL,
    int_key         INT NOT NULL       

);

