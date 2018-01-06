DROP SCHEMA IF EXISTS `spacja_erp`;

CREATE SCHEMA `spacja_erp`;

use `spacja_erp`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  `lending_time` int NOT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `position_id` int NOT NULL,
  `user_type` varchar(10) DEFAULT "user",
  `mario_dollars` decimal(14,2) DEFAULT 0.0,
  `student_index` varchar(10) NOT NULL,
  `office_entrance` tinyint(1) DEFAULT 0,
  `active` tinyint(1) DEFAULT 1,
  
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_POSITION`
  FOREIGN KEY(`position_id`) REFERENCES `position`(`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `place` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `organizer` varchar(60) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `comments` varchar(256) DEFAULT NULL,
  `priority` int DEFAULT 5,
  `deadline` datetime DEFAULT NULL,
  `archived` bool DEFAULT false,
  `video_type` varchar(40) NOT NULL,
  `value` decimal(14,2) DEFAULT NULL,
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `participation`;

CREATE TABLE `participation` (
  `event_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `role_id` int NOT NULL,
  
  
  PRIMARY KEY (`event_id`, `employee_id`, `role_id`),
  
  CONSTRAINT `FK_EMPLOYEE_PARTICIPATION` FOREIGN KEY (`employee_id`) 
  REFERENCES `employee` (`id`) 
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
  `employee_id` int NOT NULL,
  
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_EVENT_LENDING`
  FOREIGN KEY(`event_id`) REFERENCES `event`(`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_EMPLOYEE_LENDING`
  FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `state` varchar(45) DEFAULT "Dobry",
  `comments` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

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

create view most_active_employees
AS
(SELECT e.id, e.first_name, e.last_name, e.mario_dollars
FROM employee e
WHERE e.active = 1
ORDER BY e.mario_dollars DESC
LIMIT 5
);

delimiter //
create procedure get_positions()
BEGIN
SELECT *
FROM position
order by position.lending_time;
END//
delimiter ;

delimiter //
create procedure delete_position(id int)
BEGIN
DELETE
FROM position
WHERE position.id=id;
END//
delimiter ;

delimiter //
create procedure add_position(name varchar(48), lending_time int)
BEGIN
insert into position (position.name, position.lending_time)
value(name, lending_time);
END//
delimiter ;

delimiter //
create procedure update_position(id int, name varchar(48), lending_time int)
BEGIN
update position p
set p.name = name, p.lending_time = lending_time
where p.id = id;
END//
delimiter ;

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
CREATE TRIGGER `makeUserInactive` BEFORE DELETE ON `employee`
FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'Nie można usuwać użytkowników! Spróbuj dezaktywacji!';
END$$   
DELIMITER ; 

DELIMITER $$
CREATE TRIGGER `Europe_email_test` BEFORE INSERT ON `employee`
FOR EACH ROW
BEGIN
    IF NEW.email like '%.eu' THEN
        SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'Nie przyjmujemy maili w domenie .eu';
    END IF;
END$$   
DELIMITER ; 

DELIMITER $$
CREATE TRIGGER `set_lending_time` BEFORE INSERT ON `lending`
FOR EACH ROW
BEGIN
	set NEW.since = now();
    set NEW.end = date_add(NEW.since, INTERVAL (
    select p.lending_time
    from position p
    where p.id = 
    (select e.position_id
    from employee e
    where e.id = NEW.employee_id)) DAY);
END$$   
DELIMITER ; 

SET FOREIGN_KEY_CHECKS = 1;

