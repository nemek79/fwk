CREATE DATABASE  IF NOT EXISTS fwkbd CHARACTER SET utf8mb4;


CREATE USER 'fwkadmin'@'localhost' IDENTIFIED WITH mysql_native_password BY 'fwkadmin';
GRANT ALL PRIVILEGES ON fwkbd. * TO 'fwkadmin'@'localhost';

CREATE USER 'fwkuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'fwkuser';
GRANT SELECT,INSERT,UPDATE,DELETE,EXECUTE  ON fwkbd. * TO 'fwkuser'@'localhost';

FLUSH PRIVILEGES;
