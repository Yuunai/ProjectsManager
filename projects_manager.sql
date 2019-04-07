DROP SCHEMA IF EXISTS `projects_manager`;

CREATE SCHEMA `projects_manager`;

use `projects_manager`;

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
  `last_update` TIMESTAMP DEFAULT now(),

  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
    REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

insert into user_details value
  (1, 'admin', 'admin', '', now());

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
  (2, 'ROLE_USER', 'Użytkownik');

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

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(60) NOT NULL,
  `comment` VARCHAR(1024),
  `last_update` TIMESTAMP DEFAULT now(),
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `project_access`;

CREATE TABLE `project_user` (
  `project_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  CONSTRAINT `FK_PROJECT_USER` FOREIGN KEY (`project_id`)
  REFERENCES `project` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_USER_PROJECT` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  PRIMARY KEY(`project_id`, `user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `project_id` INT NOT NULL,
  `title` VARCHAR(60) NOT NULL,
  `comment` VARCHAR(1024),
  `priority` INT DEFAULT 5,
  `deadline` DATE DEFAULT NULL,
  `status` INT DEFAULT 0,
  `task_type` VARCHAR(40) NOT NULL,
  `last_update` TIMESTAMP DEFAULT now(),
  
  CONSTRAINT `FK_TASK_PROJECT` FOREIGN KEY (`project_id`)
  REFERENCES `project` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comment` (
`id` INT NOT NULL AUTO_INCREMENT,
`task_id` INT NOT NULL,
`comment` VARCHAR(1024),

CONSTRAINT `FK_COMMENT_TASK` FOREIGN KEY (`task_id`)
REFERENCES `task` (`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION,

PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `participation`;

CREATE TABLE `participation` (
  `task_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  
  
  PRIMARY KEY (`task_id`, `user_id`),
  
  CONSTRAINT `FK_EMPLOYEE_PARTICIPATION` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_EVENT_PARTICIPATION` FOREIGN KEY (`task_id`) 
  REFERENCES `task` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1;

SET FOREIGN_KEY_CHECKS = 1;

