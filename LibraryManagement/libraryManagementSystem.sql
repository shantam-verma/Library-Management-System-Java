-- MySQL dump 10.11
--
-- Host: localhost    Database: librarymanagement
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `author` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'	Jared Diamond'),(2,'Andrew Hunt'),(3,'Brian Greene'),(4,'Charles Darwin'),(5,'Donald Knuth'),(6,'Douglas Hofstadter'),(7,'Edward Gibbon'),(8,'Erich Gamma'),(9,'G. H. Hardy'),(10,'George Polya'),(11,'Harm J. de Blij'),(12,'James Watson'),(13,'Jerry P. King'),(14,'Martin Fowler'),(15,'Peter Frankopan'),(16,'Philip J. Davis'),(17,'Rand McNally'),(18,'Richard Dawkins'),(19,'Robert Cecil Martin'),(20,'Stephen Hawking'),(21,'Steve McConnell'),(22,'Thomas S. Kuhn'),(23,'Timothy Gowers '),(24,'Yuval Noah Harari');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `book` (
  `bookid` varchar(100) NOT NULL,
  `title` varchar(100) default NULL,
  `author` varchar(100) default NULL,
  `subject` varchar(100) default NULL,
  `publisher` varchar(100) default NULL,
  `category` varchar(100) default NULL,
  `isbn` int(50) default NULL,
  `edition` int(10) default NULL,
  `shelfNo` int(10) default NULL,
  PRIMARY KEY  (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('11727238','Realms, Regions, and Concepts','Harm J. de Blij','Geography','Wiley','Physical Geography',329840,2019,4),('12116356','Guns, Germs, and Steel	','	Jared Diamond','Geography','W. W. Norton & Company','Geography, History',261397,1999,4),('12268461','The Elegant Universe','Brian Greene','Science','W. W. Norton & Company','Theoretical Physics',971365,2000,2),('15516529','The Double Helix','James Watson','Science','W. W. Norton & Company','Molecular Biology',321568,2011,2),('24197225','The Silk Roads: A New History of the World','Peter Frankopan','History','Vintage','World History',108529,2016,5),('27584951','The Structure of Scientific Revolutions','Thomas S. Kuhn','Science','University of Chicago Press','Philosophy of Science',794613,2012,2),('28462781','Refactoring: Improving the Design of Existing Code','Martin Fowler','Coding','Software Development','Addison-Wesley Professional',971355,2018,1),('29395256','The Atlas of World Geography','Rand McNally','Geography','Rand McNally','	World Geography',793462,2021,4),('31322912','The Princeton Companion to Mathematics','Timothy Gowers ','Mathematics','Princeton University Press','Mathematics',324589,2008,3),('31436261','How to Solve It','George Polya','Mathematics','Princeton University Press','Problem-Solving',876512,2004,3),('34735458','Clean Code','Robert Cecil Martin','Coding','Prentice Hall','Software Design',321456,2008,1),('35749510','The Art of Computer Programming','Donald Knuth','Coding','Addison-Wesley Professional','Computer Science',913725,2011,1),('37739767','The Decline and Fall of the Roman Empire','Edward Gibbon','History','Penguin Classics','Ancient History',257913,2003,5),('38155864','The Origin of Species','Charles Darwin','Science','Penguin Classics','Evolutionary Biology',463152,2009,2),('44894347','The Selfish Gene','Richard Dawkins','Science','Oxford University Press','Evolutionary Biology',413987,2016,2),('45745747','The Art of Mathematics','Jerry P. King','Mathematics','No Starch Press','Mathematics',984532,2020,3),('49245283','Code Complete','Steve McConnell','Coding','Microsoft Press','Software Development',136845,2004,1),('49534361','Sapiens: A Brief History of Humankind','Yuval Noah Harari','History','Harper','World History',963852,2018,5),('49895386','A Course in Pure Mathematics','G. H. Hardy','Mathematics','Cambridge University Press','Mathematics',654532,2019,3),('52442195','The Mathematical Experience','Philip J. Davis','Mathematics','Birkhäuser','Mathematics',134697,2012,3),('55951954','Gödel, Escher, Bach: An Eternal Golden Braid','Douglas Hofstadter','Mathematics','Basic Books','Mathematics',127832,1999,3),('56445497','A Brief History of Time','Stephen Hawking','Science','Bantam Books','Astrophysics',758945,1998,2),('59419613','The Pragmatic Programmer','Andrew Hunt','Coding','Addison-Wesley Professional','Software Development',179452,2019,1),('63982413','Design Patterns: Elements of Reusable Object-Oriented Software','Erich Gamma','Coding','Addison-Wesley Professional','Software Design',418637,1994,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `category` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Physical Geography'),(2,'Geography, History'),(3,'Theoretical Physics'),(4,'Molecular Biology'),(5,'World History'),(6,'Philosophy of Science'),(7,'Addison-Wesley Professional'),(8,'	World Geography'),(9,'Mathematics'),(10,'Problem-Solving'),(11,'Software Design'),(12,'Computer Science'),(13,'Ancient History'),(14,'Evolutionary Biology'),(15,'Software Development'),(16,'Astrophysics');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuebooks`
--

DROP TABLE IF EXISTS `issuebooks`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `issuebooks` (
  `username` varchar(50) default NULL,
  `bookId` int(100) NOT NULL,
  `title` varchar(100) default NULL,
  `author` varchar(100) default NULL,
  `issueDate` varchar(20) default NULL,
  `dueDate` varchar(20) default NULL,
  `returnStatus` varchar(10) default NULL,
  PRIMARY KEY  (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `issuebooks`
--

LOCK TABLES `issuebooks` WRITE;
/*!40000 ALTER TABLE `issuebooks` DISABLE KEYS */;
INSERT INTO `issuebooks` VALUES ('ramesh',12116356,'Guns, Germs, and Steel	','	Jared Diamond','06-04-2023','14-04-2023','Pending'),('ankit',15516529,'The Double Helix','James Watson','01-04-2023','07-04-2023','Pending'),('geeta',25938372,'Coduu','Robert Cecil Martin','31-03-2023','10-04-2023','Submitted'),('yash',27584951,'The Structure of Scientific Revolutions','Thomas S. Kuhn','31-03-2023','10-04-2023','Pending'),('ramesh',29395256,'The Atlas of World Geography','Rand McNally','06-04-2023','07-04-2023','Submitted'),('shiv',31436261,'How to Solve It','George Polya','04-04-2023','14-04-2023','Submitted'),('shanu',34735458,'Clean Code','Robert Cecil Martin','31-03-2023','10-04-2023','Submitted'),('neeraj',63982413,'Design Patterns: Elements of Reusable Object-Oriented Software','Erich Gamma','02-04-2023','07-04-2023','Pending');
/*!40000 ALTER TABLE `issuebooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `notice` (
  `id` int(10) NOT NULL auto_increment,
  `notice` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'We\'re thrilled to welcome you to our Library Management System! With our easy-to-use platform and comprehensive search capabilities, you\'ll be able to find exactly what you need in no time.');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `publisher` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Addison-Wesley Professional'),(2,'Bantam Books'),(3,'Basic Books'),(4,'Birkhäuser'),(5,'Cambridge University Press'),(6,'Harper'),(7,'Microsoft Press'),(8,'No Starch Press'),(9,'Oxford University Press'),(10,'Penguin Classics'),(11,'Prentice Hall'),(12,'Princeton University Press'),(13,'Rand McNally'),(14,'Software Development'),(15,'University of Chicago Press'),(16,'Vintage'),(17,'W. W. Norton & Company');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `registration` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) default NULL,
  `mobile` varchar(15) default NULL,
  `email` varchar(200) default NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) default NULL,
  `usertype` varchar(10) default NULL,
  PRIMARY KEY  (`id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('122631','Gaytri Verma','3214658790','geeta@gmail.com','geeta','123','Faculty'),('123448','Shantam','9694313584','shanu@gmail.com','shanu','963','Admin'),('126587','Ankit','9638527410','ankit@gmail.com','ankit','741','Student'),('139746','Neeraj','3235484610','neeraj@gmail.com','neeraj','789','Student'),('324598','Shiv','6549873120','shiv@gmail.com','shiv','465','Student'),('462879','Yash','7410852963','yash@gmail.com','yash','951','Student'),('714993','Ramesh Choudhary','9875643120','rameshchoudhary@gmail.com','ramesh','456','Student'),('987240','abhishek jain','9351463508','abhishek.roadaheadtech@gmail.com','ak123','ak123','Admin');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `subject` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Coding'),(2,'Geography'),(3,'History'),(4,'Mathematics'),(5,'Science');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-08 12:29:14
