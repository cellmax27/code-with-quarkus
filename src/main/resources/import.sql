-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into myentity (id, field) values(1, 'field-1');
insert into myentity (id, field) values(2, 'field-2');
insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


-- sakila.actor definition

CREATE TABLE `actor` (
                         `actor_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(45) NOT NULL,
                         `last_name` varchar(45) NOT NULL,
                         `character` varchar(45) NOT NULL,
                         `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`actor_id`),
                         KEY `idx_actor_last_name` (`last_name`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.category definition

CREATE TABLE `category` (
                            `category_id` tinyint unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(25) NOT NULL,
                            `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.country definition

CREATE TABLE `country` (
                           `country_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                           `country` varchar(50) NOT NULL,
                           `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.film_text definition

CREATE TABLE `film_text` (
                             `film_id` smallint NOT NULL,
                             `title` varchar(255) NOT NULL,
                             `description` text,
                             PRIMARY KEY (`film_id`),
                             FULLTEXT KEY `idx_title_description` (`title`,`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.`language` definition

CREATE TABLE `language` (
                            `language_id` tinyint unsigned NOT NULL AUTO_INCREMENT,
                            `name` char(20) NOT NULL,
                            `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.city definition

CREATE TABLE `city` (
                        `city_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                        `city` varchar(50) NOT NULL,
                        `country_id` smallint unsigned NOT NULL,
                        `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`city_id`),
                        KEY `idx_fk_country_id` (`country_id`),
                        CONSTRAINT `fk_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.film definition

CREATE TABLE `film` (
                        `film_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                        `title` varchar(128) NOT NULL,
                        `description` text,
                        `release_year` year DEFAULT NULL,
                        `language_id` tinyint unsigned NOT NULL,
                        `original_language_id` tinyint unsigned DEFAULT NULL,
                        `rental_duration` tinyint unsigned NOT NULL DEFAULT '3',
                        `rental_rate` decimal(4,2) NOT NULL DEFAULT '4.99',
                        `length` smallint unsigned DEFAULT NULL,
                        `replacement_cost` decimal(5,2) NOT NULL DEFAULT '19.99',
                        `rating` enum('G','PG','PG-13','R','NC-17') DEFAULT 'G',
                        `special_features` set('Trailers','Commentaries','Deleted Scenes','Behind the Scenes') DEFAULT NULL,
                        `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`film_id`),
                        KEY `idx_title` (`title`),
                        KEY `idx_fk_language_id` (`language_id`),
                        KEY `idx_fk_original_language_id` (`original_language_id`),
                        CONSTRAINT `fk_film_language` FOREIGN KEY (`language_id`) REFERENCES `language` (`language_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                        CONSTRAINT `fk_film_language_original` FOREIGN KEY (`original_language_id`) REFERENCES `language` (`language_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.film_actor definition

CREATE TABLE `film_actor` (
                              `actor_id` smallint unsigned NOT NULL,
                              `film_id` smallint unsigned NOT NULL,
                              `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`actor_id`,`film_id`),
                              KEY `idx_fk_film_id` (`film_id`),
                              CONSTRAINT `fk_film_actor_actor` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`actor_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                              CONSTRAINT `fk_film_actor_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.film_category definition

CREATE TABLE `film_category` (
                                 `film_id` smallint unsigned NOT NULL,
                                 `category_id` tinyint unsigned NOT NULL,
                                 `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`film_id`,`category_id`),
                                 KEY `fk_film_category_category` (`category_id`),
                                 CONSTRAINT `fk_film_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                 CONSTRAINT `fk_film_category_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.address definition

CREATE TABLE `address` (
                           `address_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                           `address` varchar(50) NOT NULL,
                           `address2` varchar(50) DEFAULT NULL,
                           `district` varchar(20) NOT NULL,
                           `city_id` smallint unsigned NOT NULL,
                           `postal_code` varchar(10) DEFAULT NULL,
                           `phone` varchar(20) NOT NULL,
                           `location` geometry NOT NULL /*!80003 SRID 0 */,
                           `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`address_id`),
                           KEY `idx_fk_city_id` (`city_id`),
                           SPATIAL KEY `idx_location` (`location`),
                           CONSTRAINT `fk_address_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=606 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.customer definition

CREATE TABLE `customer` (
                            `customer_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                            `store_id` tinyint unsigned NOT NULL,
                            `first_name` varchar(45) NOT NULL,
                            `last_name` varchar(45) NOT NULL,
                            `email` varchar(50) DEFAULT NULL,
                            `address_id` smallint unsigned NOT NULL,
                            `active` tinyint(1) NOT NULL DEFAULT '1',
                            `create_date` datetime NOT NULL,
                            `last_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`customer_id`),
                            KEY `idx_fk_store_id` (`store_id`),
                            KEY `idx_fk_address_id` (`address_id`),
                            KEY `idx_last_name` (`last_name`),
                            CONSTRAINT `fk_customer_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                            CONSTRAINT `fk_customer_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.inventory definition

CREATE TABLE `inventory` (
                             `inventory_id` mediumint unsigned NOT NULL AUTO_INCREMENT,
                             `film_id` smallint unsigned NOT NULL,
                             `store_id` tinyint unsigned NOT NULL,
                             `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`inventory_id`),
                             KEY `idx_fk_film_id` (`film_id`),
                             KEY `idx_store_id_film_id` (`store_id`,`film_id`),
                             CONSTRAINT `fk_inventory_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                             CONSTRAINT `fk_inventory_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4582 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.payment definition

CREATE TABLE `payment` (
                           `payment_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                           `customer_id` smallint unsigned NOT NULL,
                           `staff_id` tinyint unsigned NOT NULL,
                           `rental_id` int DEFAULT NULL,
                           `amount` decimal(5,2) NOT NULL,
                           `payment_date` datetime NOT NULL,
                           `last_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`payment_id`),
                           KEY `idx_fk_staff_id` (`staff_id`),
                           KEY `idx_fk_customer_id` (`customer_id`),
                           KEY `fk_payment_rental` (`rental_id`),
                           CONSTRAINT `fk_payment_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                           CONSTRAINT `fk_payment_rental` FOREIGN KEY (`rental_id`) REFERENCES `rental` (`rental_id`) ON DELETE SET NULL ON UPDATE CASCADE,
                           CONSTRAINT `fk_payment_staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16050 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.rental definition

CREATE TABLE `rental` (
                          `rental_id` int NOT NULL AUTO_INCREMENT,
                          `rental_date` datetime NOT NULL,
                          `inventory_id` mediumint unsigned NOT NULL,
                          `customer_id` smallint unsigned NOT NULL,
                          `return_date` datetime DEFAULT NULL,
                          `staff_id` tinyint unsigned NOT NULL,
                          `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`rental_id`),
                          UNIQUE KEY `rental_date` (`rental_date`,`inventory_id`,`customer_id`),
                          KEY `idx_fk_inventory_id` (`inventory_id`),
                          KEY `idx_fk_customer_id` (`customer_id`),
                          KEY `idx_fk_staff_id` (`staff_id`),
                          CONSTRAINT `fk_rental_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                          CONSTRAINT `fk_rental_inventory` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`inventory_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                          CONSTRAINT `fk_rental_staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16050 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.staff definition

CREATE TABLE `staff` (
                         `staff_id` tinyint unsigned NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(45) NOT NULL,
                         `last_name` varchar(45) NOT NULL,
                         `address_id` smallint unsigned NOT NULL,
                         `picture` blob,
                         `email` varchar(50) DEFAULT NULL,
                         `store_id` tinyint unsigned NOT NULL,
                         `active` tinyint(1) NOT NULL DEFAULT '1',
                         `username` varchar(16) NOT NULL,
                         `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                         `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`staff_id`),
                         KEY `idx_fk_store_id` (`store_id`),
                         KEY `idx_fk_address_id` (`address_id`),
                         CONSTRAINT `fk_staff_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                         CONSTRAINT `fk_staff_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.store definition

CREATE TABLE `store` (
                         `store_id` tinyint unsigned NOT NULL AUTO_INCREMENT,
                         `manager_staff_id` tinyint unsigned NOT NULL,
                         `address_id` smallint unsigned NOT NULL,
                         `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`store_id`),
                         UNIQUE KEY `idx_unique_manager` (`manager_staff_id`),
                         KEY `idx_fk_address_id` (`address_id`),
                         CONSTRAINT `fk_store_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                         CONSTRAINT `fk_store_staff` FOREIGN KEY (`manager_staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- Création des tables pour les utilisateurs et les rôles
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- Création de la table pour les objets Item
CREATE TABLE items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Insertion des rôles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Insertion des utilisateurs avec des mots de passe hachés (par exemple, avec BCrypt)
-- Les mots de passe ci-dessous doivent être hachés avant insertion
INSERT INTO users (username, password, enabled) VALUES ('user1', '$2a$10$Dc0k2spQ7ZkS0D/Q.3dJnO0cRpWht65nL5q5kX9GZj5ZuXFTM0P6K', true); -- password: user1pass
INSERT INTO users (username, password, enabled) VALUES ('admin1', '$2a$10$2bTgC/5szDgVgd0oU0uZueyk5Azz9.Q.GZ9H.y4uZ7pp.skiwgyRi', true); -- password: admin1pass

-- Association des rôles aux utilisateurs
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- user1 -> ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- admin1 -> ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1); -- admin1 -> ROLE_USER

-- Insertion des objets Item
INSERT INTO items (name, description) VALUES ('Item 1', 'Description de l\'Item 1');
INSERT INTO items (name, description) VALUES ('Item 2', 'Description de l\'Item 2');
INSERT INTO items (name, description) VALUES ('Item 3', 'Description de l\'Item 3');





CREATE SEQUENCE CUSTOMER_SEQ START WITH 1 INCREMENT BY 1;



INSERT INTO Greeting(id, name)
VALUES (nextval('Greeting_SEQ'), 'Alice');
INSERT INTO Greeting(id, name)
VALUES (nextval('Greeting_SEQ'), 'Bob');


CREATE TABLE sakila2.city_1 (
	ID int auto_increment NOT NULL,
	Name char(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	CountryCode char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	District char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	Population int DEFAULT 0 NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (ID)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='';
CREATE INDEX CountryCode USING BTREE ON sakila2.city_1 (CountryCode);


CREATE TABLE sakila2.country_1 (
	Code char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	Name char(52) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	Continent enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Asia' NOT NULL,
	Region char(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	SurfaceArea decimal(10,2) DEFAULT 0.00 NOT NULL,
	IndepYear smallint NULL,
	Population int DEFAULT 0 NOT NULL,
	LifeExpectancy decimal(3,1) NULL,
	GNP decimal(10,2) NULL,
	GNPOld decimal(10,2) NULL,
	LocalName char(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	GovernmentForm char(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	HeadOfState char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	Capital int NULL,
	Code2 char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (Code)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='';


CREATE TABLE sakila2.countrylanguage (
	CountryCode char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	`Language` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NOT NULL,
	IsOfficial enum('T','F') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'F' NOT NULL,
	Percentage decimal(4,1) DEFAULT 0.0 NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (CountryCode,`Language`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='';
CREATE INDEX CountryCode USING BTREE ON sakila2.countrylanguage (CountryCode);

