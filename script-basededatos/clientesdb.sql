-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysqldb
-- Generation Time: Aug 11, 2024 at 02:36 AM
-- Server version: 9.0.1
-- PHP Version: 8.2.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clientesdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `Clientes`
--

CREATE TABLE `Clientes` (
  `idcliente` bigint NOT NULL,
  `edad` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `estado` enum('TRUE','FALSE') DEFAULT NULL,
  `genero` enum('MASCULINO','FEMENINO','OTRO') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Clientes`
--

INSERT INTO `Clientes` (`idcliente`, `edad`, `nombre`, `identificacion`, `direccion`, `telefono`, `password`, `estado`, `genero`) VALUES
(1, 25, 'José Lema', 'DUI', 'Otavalo sn y principal', '098254785', '1234', 'TRUE', 'MASCULINO');

-- --------------------------------------------------------

--
-- Table structure for table `Cuenta`
--

CREATE TABLE `Cuenta` (
  `idcuenta` bigint NOT NULL,
  `numerocuenta` varchar(10) NOT NULL,
  `tipocuenta` enum('Corriente','Ahorros') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `saldoinicial` decimal(38,2) NOT NULL,
  `estado` enum('True','False') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idcliente` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Cuenta`
--

INSERT INTO `Cuenta` (`idcuenta`, `numerocuenta`, `tipocuenta`, `saldoinicial`, `estado`, `idcliente`) VALUES
(1, '478758', 'Ahorros', 2000.00, 'True', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Movimientos`
--

CREATE TABLE `Movimientos` (
  `idmovimiento` bigint NOT NULL,
  `fecha` datetime(6) NOT NULL,
  `tipomovimiento` enum('retiro','deposito') NOT NULL,
  `valor` decimal(38,2) NOT NULL,
  `saldo` decimal(38,2) NOT NULL,
  `idcuenta` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Movimientos`
--

INSERT INTO `Movimientos` (`idmovimiento`, `fecha`, `tipomovimiento`, `valor`, `saldo`, `idcuenta`) VALUES
(1, '2024-08-10 20:20:50.000000', 'retiro', 575.00, 1425.00, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Clientes`
--
ALTER TABLE `Clientes`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indexes for table `Cuenta`
--
ALTER TABLE `Cuenta`
  ADD PRIMARY KEY (`idcuenta`),
  ADD UNIQUE KEY `numerocuenta` (`numerocuenta`),
  ADD KEY `idcliente` (`idcliente`);

--
-- Indexes for table `Movimientos`
--
ALTER TABLE `Movimientos`
  ADD PRIMARY KEY (`idmovimiento`),
  ADD UNIQUE KEY `idcuenta` (`idcuenta`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Clientes`
--
ALTER TABLE `Clientes`
  MODIFY `idcliente` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Cuenta`
--
ALTER TABLE `Cuenta`
  MODIFY `idcuenta` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Movimientos`
--
ALTER TABLE `Movimientos`
  MODIFY `idmovimiento` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Cuenta`
--
ALTER TABLE `Cuenta`
  ADD CONSTRAINT `idcliente` FOREIGN KEY (`idcliente`) REFERENCES `Clientes` (`idcliente`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `Movimientos`
--
ALTER TABLE `Movimientos`
  ADD CONSTRAINT `idcuenta` FOREIGN KEY (`idcuenta`) REFERENCES `Cuenta` (`idcuenta`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
