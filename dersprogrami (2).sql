-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 15 Oca 2024, 12:12:11
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `dersprogrami`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `academic_program`
--

CREATE TABLE `academic_program` (
  `Id` int(11) NOT NULL,
  `Episode` int(11) NOT NULL,
  `Lessons` int(11) NOT NULL,
  `Lesson_Code` varchar(255) NOT NULL,
  `Teacher` int(11) NOT NULL,
  `Classroom` int(11) NOT NULL,
  `Classroom_Number` int(11) NOT NULL,
  `Day` varchar(255) NOT NULL,
  `LessonTime` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `academic_program`
--

INSERT INTO `academic_program` (`Id`, `Episode`, `Lessons`, `Lesson_Code`, `Teacher`, `Classroom`, `Classroom_Number`, `Day`, `LessonTime`) VALUES
(514, 4, 16, 'blg-2', 18, 4, 1, 'Çarşamba', '1'),
(515, 4, 16, 'blg-2', 18, 4, 1, 'Çarşamba', '2'),
(516, 4, 16, 'blg-2', 18, 4, 1, 'Çarşamba', '3'),
(517, 4, 16, 'blg-2', 18, 4, 1, 'Çarşamba', '4'),
(518, 4, 16, 'blg-2', 18, 4, 1, 'Çarşamba', '5'),
(519, 4, 16, 'blg-2', 18, 4, 1, 'Çarşamba', '6'),
(520, 3, 1, 'blg-01', 20, 4, 2, 'Çarşamba', '1'),
(521, 3, 1, 'blg-01', 20, 4, 2, 'Çarşamba', '2'),
(522, 3, 1, 'blg-01', 20, 4, 2, 'Çarşamba', '3'),
(523, 4, 34, 'blg-8/1', 12, 3, 1, 'Cuma', '1'),
(524, 4, 34, 'blg-8/1', 12, 3, 1, 'Cuma', '2'),
(525, 4, 34, 'blg-8/1', 12, 3, 1, 'Cuma', '3'),
(526, 4, 34, 'blg-8/2', 12, 3, 1, 'Cuma', '4'),
(527, 4, 34, 'blg-8/2', 12, 3, 1, 'Cuma', '5'),
(528, 4, 34, 'blg-8/2', 12, 3, 1, 'Cuma', '6'),
(529, 4, 33, 'blg-7/1', 40, 3, 2, 'Cuma', '4'),
(530, 4, 33, 'blg-7/1', 40, 3, 2, 'Cuma', '5'),
(531, 4, 33, 'blg-7/1', 40, 3, 2, 'Cuma', '6'),
(532, 4, 33, 'blg-7/2', 12, 3, 1, 'Pazartesi', '1'),
(533, 4, 33, 'blg-7/2', 12, 3, 1, 'Pazartesi', '2'),
(534, 4, 33, 'blg-7/2', 12, 3, 1, 'Pazartesi', '3'),
(535, 4, 31, 'blg-6/1', 3, 3, 3, 'Cuma', '4'),
(536, 4, 31, 'blg-6/1', 3, 3, 3, 'Cuma', '5'),
(537, 4, 31, 'blg-6/1', 3, 3, 3, 'Cuma', '6'),
(538, 4, 31, 'blg-6/2', 3, 3, 2, 'Cuma', '1'),
(539, 4, 31, 'blg-6/2', 3, 3, 2, 'Cuma', '2'),
(540, 4, 31, 'blg-6/2', 3, 3, 2, 'Cuma', '3'),
(541, 4, 29, 'blg-5', 42, 3, 1, 'Pazartesi', '4'),
(542, 4, 29, 'blg-5', 42, 3, 1, 'Pazartesi', '5'),
(543, 4, 29, 'blg-5', 42, 3, 1, 'Pazartesi', '6'),
(544, 4, 28, 'blg-4/1', 1, 3, 3, 'Cuma', '1'),
(545, 4, 28, 'blg-4/1', 1, 3, 3, 'Cuma', '2'),
(546, 4, 28, 'blg-4/1', 1, 3, 3, 'Cuma', '3'),
(547, 4, 28, 'blg-4/2', 1, 3, 4, 'Cuma', '4'),
(548, 4, 28, 'blg-4/2', 1, 3, 4, 'Cuma', '5'),
(549, 4, 28, 'blg-4/2', 1, 3, 4, 'Cuma', '6'),
(550, 3, 26, 'blg-08/1', 12, 3, 1, 'Çarşamba', '4'),
(551, 3, 26, 'blg-08/1', 12, 3, 1, 'Çarşamba', '5'),
(552, 3, 26, 'blg-08/1', 12, 3, 1, 'Çarşamba', '6'),
(553, 3, 26, 'blg-08/2', 12, 3, 2, 'Pazartesi', '1'),
(554, 3, 26, 'blg-08/2', 12, 3, 2, 'Pazartesi', '2'),
(555, 3, 26, 'blg-08/2', 12, 3, 2, 'Pazartesi', '3'),
(556, 3, 25, 'blg-06/1', 1, 3, 3, 'Pazartesi', '1'),
(557, 3, 25, 'blg-06/1', 1, 3, 3, 'Pazartesi', '2'),
(558, 3, 25, 'blg-06/1', 1, 3, 3, 'Pazartesi', '3'),
(559, 3, 25, 'blg-06/2', 1, 3, 2, 'Çarşamba', '4'),
(560, 3, 25, 'blg-06/2', 1, 3, 2, 'Çarşamba', '5'),
(561, 3, 25, 'blg-06/2', 1, 3, 2, 'Çarşamba', '6'),
(562, 3, 24, 'blg-02/1', 40, 3, 3, 'Çarşamba', '4'),
(563, 3, 24, 'blg-02/1', 40, 3, 3, 'Çarşamba', '5'),
(564, 3, 24, 'blg-02/1', 40, 3, 3, 'Çarşamba', '6'),
(565, 3, 24, 'blg-02/2', 40, 3, 4, 'Cuma', '1'),
(566, 3, 24, 'blg-02/2', 40, 3, 4, 'Cuma', '2'),
(567, 3, 24, 'blg-02/2', 40, 3, 4, 'Cuma', '3'),
(568, 3, 23, 'blg-07/1', 12, 3, 2, 'Pazartesi', '4'),
(569, 3, 23, 'blg-07/1', 12, 3, 2, 'Pazartesi', '5'),
(570, 3, 23, 'blg-07/1', 12, 3, 2, 'Pazartesi', '6'),
(571, 3, 23, 'blg-07/2', 12, 3, 1, 'Perşembe', '1'),
(572, 3, 23, 'blg-07/2', 12, 3, 1, 'Perşembe', '2'),
(573, 3, 23, 'blg-07/2', 12, 3, 1, 'Perşembe', '3'),
(574, 4, 17, 'blg-3/1', 1, 3, 2, 'Perşembe', '1'),
(575, 4, 17, 'blg-3/1', 1, 3, 2, 'Perşembe', '2'),
(576, 4, 17, 'blg-3/1', 1, 3, 2, 'Perşembe', '3'),
(577, 4, 17, 'blg-3/2', 1, 3, 1, 'Perşembe', '4'),
(578, 4, 17, 'blg-3/2', 1, 3, 1, 'Perşembe', '5'),
(579, 4, 17, 'blg-3/2', 1, 3, 1, 'Perşembe', '6'),
(580, 4, 15, 'blg-1', 3, 3, 1, 'Salı', '1'),
(581, 4, 15, 'blg-1', 3, 3, 1, 'Salı', '2'),
(582, 4, 15, 'blg-1', 3, 3, 1, 'Salı', '3'),
(583, 3, 11, 'blg-09', 1, 3, 1, 'Çarşamba', '7'),
(584, 3, 7, 'blg-05/1', 3, 3, 4, 'Çarşamba', '4'),
(585, 3, 7, 'blg-05/1', 3, 3, 4, 'Çarşamba', '5'),
(586, 3, 7, 'blg-05/2', 3, 3, 1, 'Cuma', '7'),
(587, 3, 7, 'blg-05/2', 3, 3, 1, 'Cuma', '8'),
(588, 3, 4, 'blg-04/1', 12, 3, 2, 'Cuma', '7'),
(589, 3, 4, 'blg-04/1', 12, 3, 2, 'Cuma', '8'),
(590, 3, 4, 'blg-04/2', 12, 3, 1, 'Pazartesi', '7'),
(591, 3, 4, 'blg-04/2', 12, 3, 1, 'Pazartesi', '8'),
(592, 3, 3, 'blg-03/1', 3, 3, 4, 'Pazartesi', '1'),
(593, 3, 3, 'blg-03/1', 3, 3, 4, 'Pazartesi', '2'),
(594, 3, 3, 'blg-03/1', 3, 3, 4, 'Pazartesi', '3'),
(595, 3, 3, 'blg-03/2', 3, 3, 3, 'Pazartesi', '4'),
(596, 3, 3, 'blg-03/2', 3, 3, 3, 'Pazartesi', '5'),
(597, 3, 3, 'blg-03/2', 3, 3, 3, 'Pazartesi', '6'),
(598, 3, 1, 'ugurg-2', 18, 2, 2, 'Salı', '6'),
(600, 3, 1, '7', 26, 4, 5, 'Salı', '1');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `availabilityteacher`
--

CREATE TABLE `availabilityteacher` (
  `id` int(11) NOT NULL,
  `Teacher` int(11) NOT NULL,
  `Day` varchar(255) DEFAULT NULL,
  `Reason` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `classroom`
--

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL,
  `Type` varchar(255) NOT NULL,
  `Capacity` int(11) NOT NULL,
  `Custom` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `classroom`
--

INSERT INTO `classroom` (`id`, `Type`, `Capacity`, `Custom`) VALUES
(1, 'Uzaktan Eğitim', 500, 10),
(2, 'Amfi', 250, 2),
(3, 'Laboratuvar', 100, 4),
(4, 'Derslik', 150, 7);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `days`
--

CREATE TABLE `days` (
  `id` int(11) NOT NULL,
  `Day` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `days`
--

INSERT INTO `days` (`id`, `Day`) VALUES
(3, 'Çarşamba'),
(5, 'Cuma'),
(1, 'Pazartesi'),
(4, 'Perşembe'),
(2, 'Salı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `episode`
--

CREATE TABLE `episode` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `episode`
--

INSERT INTO `episode` (`id`, `Name`) VALUES
(3, 'Bilgisayar 1'),
(4, 'Bilgisayar 2');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `episodecourses`
--

CREATE TABLE `episodecourses` (
  `id` int(11) NOT NULL,
  `Episode` int(11) NOT NULL,
  `Code` varchar(15) NOT NULL,
  `Lesson` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `episodecourses`
--

INSERT INTO `episodecourses` (`id`, `Episode`, `Code`, `Lesson`) VALUES
(1, 3, 'blg-01', 1),
(2, 3, 'blg-02', 24),
(3, 3, 'blg-03', 3),
(4, 3, 'blg-04', 4),
(5, 3, 'blg-05', 7),
(6, 3, 'blg-06', 25),
(7, 3, 'blg-07', 23),
(8, 3, 'blg-08', 26),
(9, 3, 'blg-09', 11),
(10, 4, 'blg-1', 15),
(11, 4, 'blg-2', 16),
(12, 4, 'blg-3', 17),
(13, 4, 'blg-4', 28),
(14, 4, 'blg-5', 29),
(15, 4, 'blg-6', 31),
(16, 4, 'blg-7', 33),
(17, 4, 'blg-8', 34);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `lesson`
--

CREATE TABLE `lesson` (
  `id` int(11) NOT NULL,
  `Classroom` int(11) NOT NULL,
  `Code` varchar(15) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Time` varchar(255) NOT NULL,
  `Student` int(11) NOT NULL,
  `Branch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `lesson`
--

INSERT INTO `lesson` (`id`, `Classroom`, `Code`, `Name`, `Time`, `Student`, `Branch`) VALUES
(1, 4, 'blg-01', 'Matematik', '3', 64, 1),
(3, 3, 'blg-03', 'Programlama Temelleri', '3', 70, 2),
(4, 3, 'blg-04', 'Bilgisayar Donanımı', '2', 63, 2),
(7, 3, 'blg-05', 'İş Sağlığı ve Güvenliği ', '2', 60, 3),
(11, 3, 'blg-09', 'Üniversite Yaşam Kültürü ', '1', 60, 1),
(15, 3, 'blg-1', 'Sistem Analizi ve Tasarım', '3', 70, 1),
(16, 4, 'blg-2', 'Girişimcilik ', '3', 66, 1),
(17, 3, 'blg-3', 'Sunucu İşletim Sistemi', '3', 66, 2),
(23, 3, 'blg-07', 'Grafik ve Animasyon', '3', 66, 2),
(24, 3, 'blg-02', 'Veri Tabanı', '3', 72, 2),
(25, 3, 'blg-06', 'Web Tasarımının Temelleri', '3', 64, 2),
(26, 3, 'blg-08', 'Ofis Yazılımları', '3', 64, 2),
(28, 3, 'blg-4', 'İnternet Programcılığı II', '3', 62, 2),
(29, 3, 'blg-5', 'Araştırma Yöntem ve Teknikleri ', '3', 58, 1),
(31, 3, 'blg-6', 'İşletmelerde Bilgisayar Ağları', '3', 58, 2),
(33, 3, 'blg-7', 'Görsel Programlama II', '3', 64, 2),
(34, 3, 'blg-8', 'Veri Yapıları', '3', 60, 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `Title` int(11) NOT NULL,
  `Op` tinyint(1) DEFAULT NULL,
  `TC` varchar(15) NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `teacher`
--

INSERT INTO `teacher` (`id`, `Title`, `Op`, `TC`, `PhoneNumber`, `Name`, `Surname`, `Username`, `Password`) VALUES
(1, 4, 1, '12345678901', '12345678901', 'Engin ', 'Uçar', 'enginucar', 'enginucar'),
(3, 4, 0, '34567890123', '34567890123', 'Murat ', 'Albayrak', 'muratalbayrak', 'muratalbayrak'),
(12, 4, 0, '45678901234', '45678901234', 'Mesude', 'Uçar', 'mesudeucar', 'mesudeucar'),
(14, 3, 0, '67890123456', '67890123456', 'Sezai', 'Bahar', 'sezaibahar', 'sezaibahar'),
(17, 4, 0, '90123456789', '90123456789', 'Mustafa ', 'Çelik', 'mustafacelik', 'mustafacelik'),
(18, 4, 0, '01234567890', '01234567890', 'Uğur', 'Bilgen', 'ugurbilgen', 'ugurbilgen'),
(19, 4, 0, '12345098765', '12345098765', 'İsmail Altuğ', 'Baysak', 'ismailaltugbaysak', 'ismailaltugbaysak'),
(20, 3, 0, '23450987654', '23450987654', 'Muhammed Mustafa', 'Vahşi', 'muhammedmustafabahsi', 'muhammedmustafabahsi'),
(21, 4, 0, '34509876543', '34509876543', 'Hilal', 'Cura', 'hilalcura', 'hilalcura'),
(22, 3, 0, '45678901232', '45678901232', 'Ergün', 'Metin', 'ergunmetin', 'ergunmetin'),
(23, 7, 0, '56789012321', '56789012321', 'Derya', 'Kayma', 'deryakayma', 'deryakayma'),
(24, 3, 0, '67890123210', '67890123210', 'Elif', 'Baş', 'elifbas', 'elifbas'),
(25, 4, 0, '78901232109', '78901232109', 'Gözde', 'Yılmaz', 'gozdeyilmaz', 'gozdeyilmaz'),
(26, 4, 0, '89012321098', '89012321098', 'Aslıhan', 'Salın', 'aslıhansalın', '21312312'),
(27, 1, 0, '90123210987', '90123210987', 'Cihangir ', 'Güven', 'cihanginguven', '123123123'),
(28, 4, 0, '01232109876', '01232109876', 'Ayşe', 'Kik', 'Ayşekik', 'awe312'),
(39, 3, 0, '89012345678', '89012345678', 'İdris', 'Yağmur', 'idrisyagmur', 'idrisyagmur'),
(40, 4, 0, '23456789012', '23456789012', 'Levent ', 'Karakuz', 'leventkarakuz', 'leventkarakuz'),
(41, 3, 0, '56789012345', '56789012345', 'Didem', 'Çavuşoğlu', 'didemcavusoglu', 'didemcavusoglu'),
(42, 3, 0, '78901234567', '78901234567', 'Kübra Göksu Köstepen', 'Özbek', 'kubragoksukostepenozbek', 'kubragoksukostepenozbek');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `teachercourses`
--

CREATE TABLE `teachercourses` (
  `id` int(11) NOT NULL,
  `Teacher` int(11) NOT NULL,
  `Code` varchar(15) NOT NULL,
  `Lesson` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `teachercourses`
--

INSERT INTO `teachercourses` (`id`, `Teacher`, `Code`, `Lesson`) VALUES
(1, 20, 'blg-01', 1),
(2, 40, 'blg-02', 24),
(3, 40, 'blg-7', 33),
(4, 3, 'blg-03', 3),
(5, 3, 'blg-1', 15),
(6, 12, 'blg-04', 4),
(7, 12, 'blg-07', 23),
(8, 12, 'blg-08', 26),
(9, 12, 'blg-8', 34),
(10, 12, 'blg-7', 33),
(11, 3, 'blg-05', 7),
(12, 1, 'blg-06', 25),
(13, 1, 'blg-09', 11),
(14, 1, 'blg-3', 17),
(15, 1, 'blg-4', 28),
(16, 42, 'blg-5', 29),
(18, 18, 'blg-2', 16),
(19, 3, 'blg-6', 31);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `times`
--

CREATE TABLE `times` (
  `id` int(11) NOT NULL,
  `Number` varchar(255) NOT NULL,
  `Type` varchar(255) NOT NULL,
  `Start` varchar(255) NOT NULL,
  `Finish` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `times`
--

INSERT INTO `times` (`id`, `Number`, `Type`, `Start`, `Finish`) VALUES
(1, '1', 'Ders', '540', '585'),
(2, '1', 'Tenefüs', '585', '595'),
(3, '2', 'Ders', '595', '640'),
(4, '2', 'Tenefüs', '640', '650'),
(5, '3', 'Ders', '650', '695'),
(6, '3', 'Tenefüs', '695', '705'),
(7, '4', 'Ders', '705', '750'),
(8, '0', 'Öğlen', '750', '810'),
(9, '5', 'Ders', '810', '855'),
(10, '5', 'Tenefüs', '855', '865'),
(11, '6', 'Ders', '865', '910'),
(12, '6', 'Tenefüs', '910', '920'),
(13, '7', 'Ders', '920', '965'),
(14, '7', 'Tenefüs', '965', '975'),
(15, '8', 'Ders', '975', '1020');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `title`
--

CREATE TABLE `title` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `title`
--

INSERT INTO `title` (`id`, `Name`) VALUES
(6, 'Araştırma Görevlisi'),
(5, 'Araştırma Görevlisi Dr.'),
(7, 'Doç Dr.'),
(2, 'Dr. Öğretim Üyesi'),
(3, 'Öğretim Görevlisi Dr.'),
(4, 'Öğretim  Görevlisi'),
(1, 'Profesör');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `academic_program`
--
ALTER TABLE `academic_program`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Lesson` (`Lessons`),
  ADD KEY `Episode` (`Episode`),
  ADD KEY `Teacher` (`Teacher`),
  ADD KEY `Classroom` (`Classroom`);

--
-- Tablo için indeksler `availabilityteacher`
--
ALTER TABLE `availabilityteacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Teacher` (`Teacher`,`Day`);

--
-- Tablo için indeksler `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Type` (`Type`);

--
-- Tablo için indeksler `days`
--
ALTER TABLE `days`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Day` (`Day`);

--
-- Tablo için indeksler `episode`
--
ALTER TABLE `episode`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Tablo için indeksler `episodecourses`
--
ALTER TABLE `episodecourses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Episode` (`Episode`,`Lesson`),
  ADD KEY `Lesson` (`Lesson`);

--
-- Tablo için indeksler `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Code` (`Code`),
  ADD UNIQUE KEY `Code_2` (`Code`,`Name`),
  ADD KEY `Classroom` (`Classroom`);

--
-- Tablo için indeksler `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `TC` (`TC`),
  ADD UNIQUE KEY `PhoneNumber` (`PhoneNumber`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD KEY `Title` (`Title`);

--
-- Tablo için indeksler `teachercourses`
--
ALTER TABLE `teachercourses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Teacher` (`Teacher`,`Lesson`),
  ADD KEY `Lesson` (`Lesson`);

--
-- Tablo için indeksler `times`
--
ALTER TABLE `times`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `academic_program`
--
ALTER TABLE `academic_program`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=602;

--
-- Tablo için AUTO_INCREMENT değeri `availabilityteacher`
--
ALTER TABLE `availabilityteacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `days`
--
ALTER TABLE `days`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `episode`
--
ALTER TABLE `episode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `episodecourses`
--
ALTER TABLE `episodecourses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Tablo için AUTO_INCREMENT değeri `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Tablo için AUTO_INCREMENT değeri `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Tablo için AUTO_INCREMENT değeri `teachercourses`
--
ALTER TABLE `teachercourses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Tablo için AUTO_INCREMENT değeri `times`
--
ALTER TABLE `times`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `title`
--
ALTER TABLE `title`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `academic_program`
--
ALTER TABLE `academic_program`
  ADD CONSTRAINT `academic_program_ibfk_1` FOREIGN KEY (`Lessons`) REFERENCES `lesson` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `academic_program_ibfk_2` FOREIGN KEY (`Episode`) REFERENCES `episode` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `academic_program_ibfk_3` FOREIGN KEY (`Teacher`) REFERENCES `teacher` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `academic_program_ibfk_4` FOREIGN KEY (`Classroom`) REFERENCES `classroom` (`id`) ON DELETE CASCADE;

--
-- Tablo kısıtlamaları `availabilityteacher`
--
ALTER TABLE `availabilityteacher`
  ADD CONSTRAINT `availabilityteacher_ibfk_1` FOREIGN KEY (`Teacher`) REFERENCES `teacher` (`id`) ON DELETE CASCADE;

--
-- Tablo kısıtlamaları `episodecourses`
--
ALTER TABLE `episodecourses`
  ADD CONSTRAINT `episodecourses_ibfk_1` FOREIGN KEY (`Episode`) REFERENCES `episode` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `episodecourses_ibfk_2` FOREIGN KEY (`Lesson`) REFERENCES `lesson` (`id`) ON DELETE CASCADE;

--
-- Tablo kısıtlamaları `lesson`
--
ALTER TABLE `lesson`
  ADD CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`Classroom`) REFERENCES `classroom` (`id`) ON DELETE CASCADE;

--
-- Tablo kısıtlamaları `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`Title`) REFERENCES `title` (`id`) ON DELETE CASCADE;

--
-- Tablo kısıtlamaları `teachercourses`
--
ALTER TABLE `teachercourses`
  ADD CONSTRAINT `teachercourses_ibfk_1` FOREIGN KEY (`Teacher`) REFERENCES `teacher` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `teachercourses_ibfk_2` FOREIGN KEY (`Lesson`) REFERENCES `lesson` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
