-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 03:05 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paymentdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PId` varchar(20) NOT NULL,
  `Pname` varchar(50) NOT NULL,
  `Paddress` varchar(60) NOT NULL,
  `Ttime` varchar(40) NOT NULL,
  `Pdate` varchar(10) NOT NULL,
  `Pamount` varchar(30) NOT NULL,
  `Hname` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PId`, `Pname`, `Paddress`, `Ttime`, `Pdate`, `Pamount`, `Hname`) VALUES
('1', 'reth', 'tehr', '3.30Am', '2020-05-30', '5555', 'wgs45g4'),
('2', 'wegv', 'wvae', '12.45Pm', '2020-05-21', '4444', 'wefARE'),
('3', 'WEFAWE', 'FAWEFfrew', '01:00', '2020-05-23', '3333', '34grg'),
('6', 'savini', 'kalutara', '10:01', '2020-05-10', '2000', 'asiri');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
