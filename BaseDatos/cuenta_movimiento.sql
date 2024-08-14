-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql-db
-- Generation Time: Aug 14, 2024 at 08:31 PM
-- Server version: 9.0.1
-- PHP Version: 8.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cuenta_movimiento`
--

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--

CREATE TABLE `clientes` (
  `cliente_id` bigint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`cliente_id`, `nombre`) VALUES
(123, 'Jose Lema'),
(127, 'Marianela Montalvo'),
(128, 'Juan Osorio');

-- --------------------------------------------------------

--
-- Table structure for table `cuentas`
--

CREATE TABLE `cuentas` (
  `idcuenta` bigint NOT NULL,
  `estado` enum('F','T') DEFAULT NULL,
  `numerocuenta` varchar(10) NOT NULL,
  `saldoinicial` decimal(38,2) DEFAULT NULL,
  `tipocuenta` enum('Ahorro','Corriente') NOT NULL,
  `cliente_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cuentas`
--

INSERT INTO `cuentas` (`idcuenta`, `estado`, `numerocuenta`, `saldoinicial`, `tipocuenta`, `cliente_id`) VALUES
(1, 'T', '585545', 1000.00, 'Ahorro', 123),
(2, 'T', '478758', 2000.00, 'Corriente', 123),
(4, 'T', '225487', 100.00, 'Corriente', 127),
(5, 'T', '495878', 0.00, 'Ahorro', 128),
(6, 'T', '496825', 540.00, 'Ahorro', 127);

-- --------------------------------------------------------

--
-- Table structure for table `movimientos`
--

CREATE TABLE `movimientos` (
  `idmovimiento` bigint NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `saldo` decimal(38,2) DEFAULT NULL,
  `tipomovimiento` enum('Deposito','Retiro') NOT NULL,
  `valor` decimal(38,2) DEFAULT NULL,
  `idcuenta` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `movimientos`
--

INSERT INTO `movimientos` (`idmovimiento`, `fecha`, `saldo`, `tipomovimiento`, `valor`, `idcuenta`) VALUES
(26, '2024-08-13 07:06:18.898012', 1000.00, 'Deposito', -200.00, 1),
(27, '2024-08-14 07:06:19.934190', 800.00, 'Deposito', -200.00, 1),
(28, '2024-08-14 07:06:20.851367', 600.00, 'Deposito', -200.00, 1),
(29, '2024-08-14 07:06:21.754212', 400.00, 'Deposito', -200.00, 1),
(30, '2024-08-14 07:06:22.683410', 200.00, 'Deposito', -200.00, 1),
(31, '2024-08-14 07:06:23.667609', 0.00, 'Deposito', -200.00, 1),
(32, '2024-08-14 09:54:08.437601', 1800.00, 'Deposito', -200.00, 1),
(33, '2024-08-14 09:54:17.714475', 2000.00, 'Deposito', 200.00, 1),
(34, '2024-08-14 09:54:26.647152', 2600.00, 'Deposito', 600.00, 1),
(35, '2024-08-14 20:16:28.776493', 4000.00, 'Retiro', 2000.00, 2),
(36, '2024-08-14 20:17:51.129575', 1425.00, 'Retiro', -575.00, 2),
(37, '2024-08-12 20:19:24.211580', 700.00, 'Deposito', 600.00, 4),
(38, '2024-08-14 20:22:12.818413', 150.00, 'Deposito', 150.00, 5),
(39, '2024-08-12 20:23:05.107813', 0.00, 'Retiro', -540.00, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cliente_id`);

--
-- Indexes for table `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`idcuenta`),
  ADD UNIQUE KEY `UKncc6ob6q7wqq059ymsi3ieias` (`numerocuenta`),
  ADD KEY `FK65yk2321jpusl3fk96lqehrli` (`cliente_id`);

--
-- Indexes for table `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`idmovimiento`),
  ADD KEY `FKje1s7ij896qsvjk9i7quwf3cx` (`idcuenta`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `idcuenta` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `idmovimiento` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cuentas`
--
ALTER TABLE `cuentas`
  ADD CONSTRAINT `FK65yk2321jpusl3fk96lqehrli` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`);

--
-- Constraints for table `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `FKje1s7ij896qsvjk9i7quwf3cx` FOREIGN KEY (`idcuenta`) REFERENCES `cuentas` (`idcuenta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
