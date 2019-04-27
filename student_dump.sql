SET NAMES utf8;

CREATE DATABASE IF NOT EXISTS student_info;
GRANT ALL PRIVILEGES on student_info.*
TO 'root'@'%' IDENTIFIED BY 'admin'
WITH GRANT OPTION;

DROP TABLE IF EXISTS `studentDetails`;

CREATE TABLE `studentDetails`(
	`studentId` varchar(11) NOT NULL,
	`studentName` varchar(40) NOT NULL,
	`email` varchar(60) NOT NULL,
	PRIMARY KEY(`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


DROP TABLE IF EXISTS `messRegister`;

CREATE TABLE `messRegister`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`student_id` varchar(11) NOT NULL,
	`name` varchar(40) NOT NULL,
	`currDate` Date, 
	`currTime` TIME,
	`breakfast` int(11),
	`lunch` int(11),
	`dinner` int(11),
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


DROP TABLE IF EXISTS `messCharges`;

CREATE TABLE `messCharges`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`breakfastcharge` int(11) NOT NULL,
	`lunchcharge` int(11) NOT NULL,
	`dinnercharge` int(11) NOT NULL,
	`semestercharge` int(11) NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

Insert into studentDetails(studentId, studentName, email) VALUES
('IMT2015048', 'Harsha', 'H.V@iiitb.org'),
('IMT2015013', 'Ketchup', 'R.K@iiitb.org'),
('IMT2015510', 'Sankeerth', 'V.S@iiitb.org'),
('IMT2015012', 'Raman', 'S.R@iiitb.org'),
('IMT2015023', 'Vas', 'V.C@iiitb.org'),
('IMT2015047', 'Ritvik', 'V.R@iiitb.org');

Insert into messCharges(breakfastcharge,lunchcharge,dinnercharge,semestercharge) VALUES 
(40,70,70,33000);
