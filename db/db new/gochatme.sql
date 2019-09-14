-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Wrz 2019, 22:58
-- Wersja serwera: 10.1.37-MariaDB
-- Wersja PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `gochatme`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `channel`
--

CREATE TABLE `channel` (
  `channelid` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `adults_only` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `channel`
--

INSERT INTO `channel` (`channelid`, `active`, `adults_only`, `description`, `name`) VALUES
(1, b'1', b'0', 'Kana? testowy o nazwie podstawówka', 'Podstawowka'),
(2, b'1', b'0', 'Kana? testowy o nazwie Liceum/Technikum', 'Liceum/Technikum');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(44),
(44),
(44);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `message_output`
--

CREATE TABLE `message_output` (
  `id` int(11) NOT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `channelid` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `message_output`
--

INSERT INTO `message_output` (`id`, `sender`, `text`, `time`, `channelid`) VALUES
(1, 'User1', 'hi', '23:47', 1),
(2, 'User2', 'hello', '23:48', 1),
(3, 'User1', 'bye', '23:49', 1),
(4, 'User1', 'hey', '23:59', 2),
(22, 'Admin1', 'aaa', '15:13', NULL),
(23, 'nick', 'Hello World!', '15:16', NULL),
(24, 'Marian', 'Hello Darkness my old friend...', '15:19', NULL),
(25, '', 'I\'ve come to talk with you again', '15:21', NULL),
(26, 'nick', 'Private Hello Darkness my old friend', '15:22', NULL),
(43, 'aaaaaaaac', 'bababab', '14-09-2019 22:42', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `private_channel`
--

CREATE TABLE `private_channel` (
  `channelid` bigint(20) NOT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `private_channel`
--

INSERT INTO `private_channel` (`channelid`, `token`) VALUES
(20, 'token16'),
(21, 'token123'),
(18, 'token\"1\"-1'),
(19, 'token1-1');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `private_message_output`
--

CREATE TABLE `private_message_output` (
  `id` bigint(20) NOT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `channelid` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `private_message_output`
--

INSERT INTO `private_message_output` (`id`, `sender`, `text`, `time`, `channelid`) VALUES
(27, 'Dario1', 'Hello Private World', '15:37', NULL),
(28, 'Dario1', 'Private message', '18:52', NULL),
(29, 'Dario1', 'Hello Private World', '18:58', 20),
(30, 'Dario1', 'aaaaaaaaa', '19:11', 20),
(31, 'Dario1', 'aaaaaaaa', '19:13', 20),
(32, 'Dario1', 'Hello Private World!!!', '19:16', 20),
(33, 'nick', 'Hello There', '19:17', 20),
(34, 'nick', 'Hello', '19:19', 20),
(35, 'Dario1', 'Hello', '19:23', 20),
(36, 'Dario1', 'aaaaaaaaa', '19:24', 20);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `email`, `login`, `name`, `nickname`, `password`, `sex`, `surname`) VALUES
(1, 'email@johnny.com', 'login', 'imie', 'nick', '$2a$10$hx5YeD31soTUtYtQLzQwuOCLYcltHex40HG4jfsqa6Q26CIIoSRGe', 0, 'nazwisko'),
(23, 'admin@admin.com', 'admin', 'admin', 'Admin1', '$2a$10$xFCb3N1551dfovj2gpsvm.nIu1QOQEPHsFGcMmjGAzOab0ixdQ3tK', 0, 'dzik'),
(6, 'email@email.com', 'SpecjalnyTestowy', 'Darek ', 'Dario1', '$2a$10$q86un.AsTLBwSJxWiUY0nO4qZW/n4jZw9m3J19NKPoe.15ZoeJ3A6', 0, 'Biedny');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `channel`
--
ALTER TABLE `channel`
  ADD PRIMARY KEY (`channelid`);

--
-- Indeksy dla tabeli `message_output`
--
ALTER TABLE `message_output`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqdifdarkta82d7kxl24w4wtas` (`channelid`);

--
-- Indeksy dla tabeli `private_channel`
--
ALTER TABLE `private_channel`
  ADD PRIMARY KEY (`channelid`);

--
-- Indeksy dla tabeli `private_message_output`
--
ALTER TABLE `private_message_output`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1bubrcwhghh0ofud7so03gpvl` (`channelid`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
