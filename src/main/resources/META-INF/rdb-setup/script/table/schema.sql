CREATE DATABASE IF NOT EXISTS moon;
CREATE TABLE IF NOT EXISTS  moon.`user` (
  `uuid` VARCHAR(38)  NOT NULL,
  `name` VARCHAR(255)  NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `moon`.`user` (`uuid`, `name`) VALUES ('uuid1', 'Trulon') ;