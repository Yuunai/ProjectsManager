DROP SCHEMA IF EXISTS `spacja_erp`;

CREATE SCHEMA `spacja_erp`;

use `spacja_erp`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(128) NOT NULL UNIQUE,
  `password` VARCHAR(256) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `student_index` varchar(10) NOT NULL,
  `car` tinyint(1) NOT NULL DEFAULT 0,
  `office_entrance` tinyint(1) DEFAULT 0,
  `enabled` tinyint(1) DEFAULT 1,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

insert into user value
(1, 'admin', 'admin', 'admin@admin.pl', '$2a$11$oqL/hV4TyOoI37.D1nBj9.Ud1sJbWhar0OwXsHk.kn.xz4KAIg7cG', 000000000, 000000, 1, 1, 1, now());

DROP TABLE IF EXISTS privilege;

CREATE TABLE privilege (
  `id` int NOT NULL,
  `privilege` varchar(50),
  `label` VARCHAR(50),

   PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS adm_role;

CREATE TABLE adm_role (
  `id` int NOT NULL,
  `adm_role` varchar(50),
  `label` VARCHAR(50),

  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE adm_role_privilege (
  `adm_role_id` int NOT NULL,
  privilege_id int NOT NULL,

  PRIMARY KEY (privilege_id, `adm_role_id`),

  CONSTRAINT FK_PRIVILEGE_ID FOREIGN KEY (privilege_id)
  REFERENCES privilege (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_P_ADM_ROLE_ID FOREIGN KEY (`adm_role_id`)
  REFERENCES adm_role (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS user_adm_role;

CREATE TABLE user_adm_role (
  `user_id` int NOT NULL,
  adm_role_id int NOT NULL,

  PRIMARY KEY (`user_id`, adm_role_id),

  CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_U_ADM_ROLE_ID FOREIGN KEY (adm_role_id)
  REFERENCES adm_role (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `place` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `organizer` varchar(60) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `email` varchar(128) NOT NULL,
  `comments` varchar(256) DEFAULT NULL,
  `priority` int DEFAULT 5,
  `deadline` datetime DEFAULT NULL,
  `confirmed` bool DEFAULT false,
  `archived` bool DEFAULT false,
  `video_type` varchar(40) NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

INSERT INTO role(`name`) values
('Operator'),
('Montazysta'),
('Reporter'),
('Socialmedia'),
('Tech'),
('Realizator');

DROP TABLE IF EXISTS `participation`;

CREATE TABLE `participation` (
  `event_id` int NOT NULL,
  user_id int NOT NULL,
  `role_id` int NOT NULL,
  
  
  PRIMARY KEY (`event_id`, user_id, `role_id`),
  
  CONSTRAINT `FK_EMPLOYEE_PARTICIPATION` FOREIGN KEY (user_id)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_EVENT_PARTICIPATION` FOREIGN KEY (`event_id`) 
  REFERENCES `event` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE_PARTICIPATION` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `lending`;

CREATE TABLE `lending` (
  `id` int NOT NULL AUTO_INCREMENT,
  `since` datetime DEFAULT current_timestamp,
  `end` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL,
  `comments` varchar(256) DEFAULT NULL,
  `event_id` int NOT NULL,
  user_id int NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_EVENT_LENDING`
  FOREIGN KEY(`event_id`) REFERENCES `event`(`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_EMPLOYEE_LENDING`
  FOREIGN KEY(user_id) REFERENCES `user`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `state` varchar(45) DEFAULT "Dobry",
  `comments` varchar(256) DEFAULT NULL,
  `category` INT NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY (`id`),

  CONSTRAINT `FK_EQ_CAT` FOREIGN KEY (`category`)
  REFERENCES `equipment_category` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `equipment_category`;

CREATE TABLE `equipment_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

INSERT INTO equipment_category(`name`) VALUES
  ('Video'),
  ('Audio'),
  ('Live'),
  ('Akcesoria');

DROP TABLE IF EXISTS `eq_lending`;

CREATE TABLE `eq_lending` (
  `equipment_id` int NOT NULL,
  `lending_id` int NOT NULL,
  
  PRIMARY KEY (`equipment_id`,`lending_id`),
  
  CONSTRAINT `FK_EQUIPMENT` FOREIGN KEY (`equipment_id`)
  REFERENCES `equipment` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_LENDING_EQ` FOREIGN KEY (`lending_id`) 
  REFERENCES `lending` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

delimiter //
create procedure select_equipment_from_given_lending(id int)
BEGIN
select *
from equipment e
where e.id in
(select el.equipment_id
from eq_lending el
where el.lending_id=id)
order by e.name;
END//
delimiter ;

delimiter //
create procedure select_free_equipment()
BEGIN
select *
from equipment e
where e.id not in
(select el.equipment_id
from eq_lending el
where el.lending_id in
(select l.id
from lending l
where l.return_time is null))
order by e.name;
END//
delimiter ;

DELIMITER $$
CREATE TRIGGER `makeUserInactive` BEFORE DELETE ON `user`
FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'Nie można usuwać użytkowników! Spróbuj dezaktywacji!';
END$$   
DELIMITER ; 

DELIMITER $$
CREATE TRIGGER `Europe_email_test` BEFORE INSERT ON `user`
FOR EACH ROW
BEGIN
    IF NEW.email like '%.eu' THEN
        SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'Nie przyjmujemy maili w domenie .eu';
    END IF;
END$$   
DELIMITER ;

DELIMITER $$
CREATE TRIGGER optimistic_locking_user BEFORE UPDATE ON `user`
FOR EACH ROW
BEGIN
	IF NEW.last_update != (
    select e.last_update
    from user e
    where e.id = NEW.id) THEN
        SIGNAL SQLSTATE '12346'
            SET MESSAGE_TEXT = 'Edycja nieudana, ponów próbę!';
	ELSE set new.last_update = now();
    END IF;
END$$   
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `optimistic_locking_equipment` BEFORE UPDATE ON `equipment`
FOR EACH ROW
BEGIN
	IF NEW.last_update != (
    select e.last_update
    from equipment e
    where e.id = NEW.id) THEN
        SIGNAL SQLSTATE '12346'
            SET MESSAGE_TEXT = 'Edycja nieudana, ponów próbę!';
	ELSE set new.last_update = now();
    END IF;
END$$   
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `optimistic_locking_event` BEFORE UPDATE ON `event`
FOR EACH ROW
BEGIN
	IF NEW.last_update != (
    select e.last_update
    from event e
    where e.id = NEW.id) THEN
        SIGNAL SQLSTATE '12346'
            SET MESSAGE_TEXT = 'Edycja nieudana, ponów próbę!';
	ELSE set new.last_update = now();
    END IF;
END$$   
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `optimistic_locking_lending` BEFORE UPDATE ON `lending`
FOR EACH ROW
BEGIN
	IF NEW.last_update != (
    select l.last_update
    from lending l
    where l.id = NEW.id) THEN
        SIGNAL SQLSTATE '12346'
            SET MESSAGE_TEXT = 'Edycja nieudana, ponów próbę!';
	ELSE set new.last_update = now();
    END IF;
END$$   
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `optimistic_locking_role` BEFORE UPDATE ON `role`
FOR EACH ROW
BEGIN
	IF NEW.last_update != (
    select r.last_update
    from role r
    where r.id = NEW.id) THEN
        SIGNAL SQLSTATE '12346'
            SET MESSAGE_TEXT = 'Edycja nieudana, ponów próbę!';
	ELSE set new.last_update = now();
    END IF;
END$$   
DELIMITER ;

SET FOREIGN_KEY_CHECKS = 1;

