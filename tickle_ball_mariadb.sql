/*
SQLyog Community v13.1.8 (64 bit)
MySQL - 10.6.5-MariaDB : Database - tickle_ball
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tickle_ball` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tickle_ball`;

/*Table structure for table `game_info` */

CREATE TABLE `game_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_id` int(11) NOT NULL,
  `tickle_btn` varchar(64) NOT NULL,
  `idle` varchar(512) DEFAULT NULL,
  `success` varbinary(512) DEFAULT NULL,
  `fail` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usr_id` (`usr_id`),
  CONSTRAINT `game_info_ibfk_1` FOREIGN KEY (`usr_id`) REFERENCES `usr_stats` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

/*Data for the table `game_info` */

insert  into `game_info`(`id`,`usr_id`,`tickle_btn`,`idle`,`success`,`fail`) values 
(1,1,'head','kerry_idle.mp4','kerry_success.mp4','kerry_fail.mp4'),
(2,2,'armpit','matt_idle.mov','matt_success.mov','matt_fail.mov'),
(4,5,'foot','batman_idle.mp4','batman_success.mp4','batman_fail.mp4');

/*Table structure for table `usr_stats` */

CREATE TABLE `usr_stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_name` varchar(256) NOT NULL,
  `coin` int(11) NOT NULL,
  `streak` int(11) DEFAULT 0,
  `streak_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

/*Data for the table `usr_stats` */

insert  into `usr_stats`(`id`,`usr_name`,`coin`,`streak`,`streak_date`) values 
(1,'klyon',5,10,'2023-02-09'),
(2,'Akira',3,7,'2023-02-14'),
(3,'Tetsuo',7,15,'2023-02-28'),
(4,'Ironman',16,16,'2023-02-01'),
(5,'Retro',9,5,'2023-01-02'),
(6,'Arcade',3,1,'2022-12-12'),
(7,'LaunchBox',29,42,'2022-10-25'),
(8,'ScummVM',64,33,'2022-12-26'),
(9,'MS-DOS',8,3,'2023-01-13'),
(10,'Ti99 4A',10,8,'2022-11-19');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
