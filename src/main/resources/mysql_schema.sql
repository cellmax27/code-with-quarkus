/* SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO"; */
/* SET AUTOCOMMIT = 0; */
/* START TRANSACTION; */
/* SET time_zone = "+00:00"; */

-- --------------------------------------------------------

--
-- Table structure for table `Book` generated from model 'Book'
--

CREATE TABLE IF NOT EXISTS `Book` (
  `id` INT NOT NULL COMMENT 'ID unique du produit',
  `name` TEXT NOT NULL COMMENT 'Nom du produit',
  `description` TEXT DEFAULT NULL COMMENT 'Description du produit',
  `price` DECIMAL(20, 9) NOT NULL COMMENT 'Prix du produit',
  `author` TEXT DEFAULT NULL,
  `isbn` TEXT DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `Clothing` generated from model 'Clothing'
--

CREATE TABLE IF NOT EXISTS `Clothing` (
  `id` INT NOT NULL COMMENT 'ID unique du produit',
  `name` TEXT NOT NULL COMMENT 'Nom du produit',
  `description` TEXT DEFAULT NULL COMMENT 'Description du produit',
  `price` DECIMAL(20, 9) NOT NULL COMMENT 'Prix du produit',
  `size` TEXT DEFAULT NULL,
  `color` TEXT DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `Product` generated from model 'Product'
--

CREATE TABLE IF NOT EXISTS `Product` (
  `id` INT NOT NULL COMMENT 'ID unique du produit',
  `name` TEXT NOT NULL COMMENT 'Nom du produit',
  `description` TEXT DEFAULT NULL COMMENT 'Description du produit',
  `price` DECIMAL(20, 9) NOT NULL COMMENT 'Prix du produit'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `ProductInput` generated from model 'ProductInput'
--

CREATE TABLE IF NOT EXISTS `ProductInput` (
  `name` TEXT NOT NULL COMMENT 'Nom du produit',
  `description` TEXT DEFAULT NULL COMMENT 'Description du produit',
  `price` DECIMAL(20, 9) NOT NULL COMMENT 'Prix du produit'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


