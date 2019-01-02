DROP SCHEMA IF EXISTS `spacja_erp`;

CREATE SCHEMA `spacja_erp`;

use `spacja_erp`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(128) NOT NULL UNIQUE,
  `password` VARCHAR(256) NOT NULL,
  `enabled` TINYINT(1) DEFAULT 1,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

insert into user(id, email, password, enabled, last_update) value
(1, 'admin@admin.pl', '$2a$11$oqL/hV4TyOoI37.D1nBj9.Ud1sJbWhar0OwXsHk.kn.xz4KAIg7cG', 1, now());

DROP TABLE IF EXISTS `user_details`;

CREATE TABLE `user_details` (
  `user_id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(15),
  `student_index` VARCHAR(10),
  `car` TINYINT(1) NOT NULL DEFAULT 0,
  `office_entrance` TINYINT(1) DEFAULT 0,
  `active` TINYINT(1) DEFAULT 0,
  `last_update` TIMESTAMP DEFAULT now(),

  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
    REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

insert into user_details value
  (1, 'admin', 'admin', NULL, NULL, 0, 1, 1, now());

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `user_id` INT NOT NULL,
  `type` INT NOT NULL,
  `hash` VARCHAR(64) UNIQUE NOT NULL,
  `expiration_date` DATETIME NOT NULL,

  PRIMARY KEY (`user_id`, `type`),

  CONSTRAINT FK_TOKEN_USER_ID FOREIGN KEY (user_id)
    REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS adm_role;

CREATE TABLE adm_role (
  `id` INT NOT NULL,
  `adm_role` VARCHAR(50),
  `label` VARCHAR(50),

  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO adm_role VALUES
  (1, 'ROLE_ADMIN', 'Administrator'),
  (2, 'ROLE_MODERATOR', 'Moderator'),
  (3, 'ROLE_TRUSTED', 'Zaufany użytkownik'),
  (4, 'ROLE_USER', 'Użytkownik'),
  (5, 'ROLE_OUTER_USER', 'Użytkownik zewnętrzny');

DROP TABLE IF EXISTS user_adm_role;

CREATE TABLE user_adm_role (
  `user_id` INT NOT NULL,
  adm_role_id INT NOT NULL,

  PRIMARY KEY (`user_id`, adm_role_id),

  CONSTRAINT `FK_ADM_ROLE_USER_ID` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_U_ADM_ROLE_ID FOREIGN KEY (adm_role_id)
  REFERENCES adm_role (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

INSERT INTO user_adm_role VALUE
  (1, 1);

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `place` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `organizer` VARCHAR(60) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `comments` VARCHAR(256) DEFAULT NULL,
  `priority` INT DEFAULT 5,
  `deadline` DATE DEFAULT NULL,
  `confirmed` bool DEFAULT false,
  `archived` bool DEFAULT false,
  `video_type` VARCHAR(40) NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(48) NOT NULL,
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
  `event_id` INT NOT NULL,
  user_id INT NOT NULL,
  `role_id` INT NOT NULL,
  
  
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

DROP TABLE IF EXISTS reservation;

CREATE TABLE reservation (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_since` DATE NOT NULL,
  `time_since` TIME NOT NULL,
  `date_to` DATE NOT NULL,
  `time_to` TIME NOT NULL,
  `comments` VARCHAR(256) DEFAULT NULL,
  `event_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_EVENT_RESERVATION`
  FOREIGN KEY(`event_id`) REFERENCES `event`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_EMPLOYEE_RESERVATION`
  FOREIGN KEY(user_id) REFERENCES `user`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) DEFAULT 'Dobry',
  `comments` VARCHAR(256) DEFAULT NULL,
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

INSERT INTO equipment_category(`id`, `name`) VALUES
  (1, 'Video'),
  (2, 'Audio'),
  (3, 'Live'),
  (4, 'Akcesoria');

DROP TABLE IF EXISTS eq_reservation;

CREATE TABLE eq_reservation (
  `equipment_id` INT NOT NULL,
  `reservation_id` INT NOT NULL,
  
  PRIMARY KEY (`equipment_id`, `reservation_id`),
  
  CONSTRAINT `FK_EQUIPMENT` FOREIGN KEY (`equipment_id`)
  REFERENCES `equipment` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_RESERVATION_EQ` FOREIGN KEY (`reservation_id`)
  REFERENCES `reservation` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

delimiter //
create procedure select_equipment_from_given_reservation(id INT)
BEGIN
select *
from equipment e
where e.id in
(select el.equipment_id
from eq_reservation el
where el.`reservation_id`=id)
order by e.name;
END//
delimiter ;

delimiter //
create procedure select_reservations(dSince DATE, tSince TIME, dTo DATE, tTo TIME)
BEGIN
DECLARE tsSince, tsTo TIMESTAMP;
SET tsSince = TIMESTAMP(dSince, tSince);
SET tsTo = TIMESTAMP(dTo, tTo);

SELECT *
FROM reservation r
WHERE TIMESTAMP(r.date_since, r.time_since) BETWEEN tsSince AND tsTo
OR TIMESTAMP (r.date_to, r.time_to) BETWEEN tsSince AND tsTo
OR tsSince BETWEEN TIMESTAMP(r.date_since, r.time_since) AND TIMESTAMP(r.date_to, r.time_to);
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
CREATE TRIGGER optimistic_locking_user_details BEFORE UPDATE ON `user_details`
FOR EACH ROW
  BEGIN
    IF NEW.last_update != (
      select e.last_update
      from user_details e
      where e.user_id = NEW.user_id) THEN
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
CREATE TRIGGER optimistic_locking_reservation BEFORE UPDATE ON reservation
FOR EACH ROW
BEGIN
	IF NEW.last_update != (
    select l.last_update
    from reservation l
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

