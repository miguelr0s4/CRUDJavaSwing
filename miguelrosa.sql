-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 05-Jul-2022 às 07:24
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `album`
--
CREATE DATABASE IF NOT EXISTS `album` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `album`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `album`
--

CREATE TABLE `album` (
  `id` int(11) NOT NULL,
  `nomeArtista` varchar(45) NOT NULL,
  `nomeAlbum` varchar(45) NOT NULL,
  `anoLancamento` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `album`
--

INSERT INTO `album` (`id`, `nomeArtista`, `nomeAlbum`, `anoLancamento`) VALUES
(4, 'Greta Van Fleet', 'From the Fires', 2017),
(11, 'Paramore', 'Brand New Eyes', 2009),
(12, 'Pitty', 'Matriz', 2019),
(13, 'Pearl Jam', 'Ten', 1991),
(14, 'Nirvana', 'MTV Unplugged in New York', 1994),
(15, 'The Ting Tings', 'The Black Light', 2018),
(16, 'Madonna', 'Like A Prayer', 1989),
(17, 'Black Veil Brides', 'Set The World On Fire', 2011),
(18, 'Lady Gaga', 'Born This Way', 2011),
(19, 'Foster The People', 'Torches', 2011),
(20, 'Ghost', 'Prequelle', 2018);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `album`
--
ALTER TABLE `album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
