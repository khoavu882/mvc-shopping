create database tech;

use tech;

CREATE TABLE `USER` (
 `user_id` int(11) NOT NULL AUTO_INCREMENT ,
 `email` varchar(64) NOT NULL unique ,
 `password` varchar(126) NOT NULL ,
 `first_name` varchar(32) NOT NULL ,
 `last_name` varchar(32) NOT NULL ,
 `address` varchar(255) NOT NULL ,
 `phone_number` varchar(10) not null, 
 PRIMARY KEY (`user_id`)
);

CREATE TABLE `ROLE` (
 `role_id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL,
 PRIMARY KEY (`role_id`)
);

CREATE TABLE `user_role` (
 `user_id` int(11) NOT NULL,
 `role_id` int(11) NOT NULL,
 PRIMARY KEY (`user_id`,`role_id`),
 foreign key (user_id) REFERENCES USER (user_id),
 foreign key (role_id) references ROLE (role_id)
 );

 CREATE table `PRODUCT` (
   product_id int(11) NOT NULL auto_increment,
   category_id int(11),
   product_image varchar(128) not null,
   product_name varchar(256) not null,
   product_brand varchar(32) not null,
   product_description varchar(2000),
   product_quantity int(9999) not null,
   product_price float not null,
   product_promotion float,
   PRIMARY KEY (`product_id`),
   foreign key (`category_id`) references `CATEGORY` (`category_id`)
 );

CREATE table `CATEGORY` (
  category_id int(11) NOT NULL auto_increment,
  category_name varchar(32) not null,
  category_metaWord varchar(128),
  primary key (`category_id`)
);


 
