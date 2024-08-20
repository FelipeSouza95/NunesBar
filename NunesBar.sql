-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nunesbar
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nunesbar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nunesbar` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `nunesbar` ;

-- -----------------------------------------------------
-- Table `nunesbar`.`produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nunesbar`.`produtos` (
  `idproduto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `valor` DOUBLE(10,2) NOT NULL,
  PRIMARY KEY (`idproduto`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

ALTER TABLE produtos
ADD COLUMN quantidade INT NOT NULL DEFAULT 0 AFTER valor;

-- -----------------------------------------------------
-- Table `nunesbar`.`caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nunesbar`.`caixa` (
  `idproduto` INT NOT NULL,
  `quantidade` INT NOT NULL,
  PRIMARY KEY (`idproduto`),
  INDEX `idproduto` (`idproduto` ASC) VISIBLE,
  CONSTRAINT `caixa_ibfk_1`
    FOREIGN KEY (`idproduto`)
    REFERENCES `nunesbar`.`produtos` (`idproduto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- -----------------------------------------------------
-- Table `nunesbar`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nunesbar`.`usuarios` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(100) NOT NULL,
  `senha` TEXT,
  `tipo` VARCHAR(100) NOT NULL,
  `ativado` BOOLEAN NOT NULL DEFAULT TRUE, -- Adicionando a coluna 'ativado' como boolean
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT 
 CHARSET=utf8mb4
 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `nunesbar`.`relatorios` (
  `idrelatorio` INT NOT NULL AUTO_INCREMENT,
  `nota` VARCHAR(100) NOT NULL,
  `valor` DOUBLE(10,2) NOT NULL,
  `data`  DATE NOT NULL,
  PRIMARY KEY (`idrelatorio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



insert into usuarios (usuario, senha, tipo, ativado) values ('Administrador', 'admin', 'Administrador', true);
