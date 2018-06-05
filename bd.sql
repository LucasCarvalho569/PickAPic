
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pickapic
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `pickapic` ;

-- -----------------------------------------------------
-- Schema pickapic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pickapic` DEFAULT CHARACTER SET utf8 ;
USE `pickapic` ;

-- -----------------------------------------------------
-- Table `pickapic`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pickapic`.`usuario` ;

CREATE TABLE IF NOT EXISTS `pickapic`.`usuario` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `sobrenome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pickapic`.`votacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pickapic`.`votacao` ;

CREATE TABLE IF NOT EXISTS `pickapic`.`votacao` (
  `id_votacao` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL DEFAULT NULL,
  `usuario_id` INT(11) NOT NULL,
  INDEX `fk_Votacao_Usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_Votacao_Usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `pickapic`.`usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pickapic`.`foto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pickapic`.`foto` ;

CREATE TABLE IF NOT EXISTS `pickapic`.`foto` (
  `id_foto` INT(11) NOT NULL AUTO_INCREMENT,
  `arquivo` MEDIUMBLOB NOT NULL,
  `votos` INT(11) NOT NULL,
  `votacao_id` INT(11) NULL,
  PRIMARY KEY (`id_foto`),
  INDEX `fk_Foto_Votacao` (`votacao_id` ASC),
  CONSTRAINT `fk_Foto_Votacao`
    FOREIGN KEY (`votacao_id`)
    REFERENCES `pickapic`.`votacao` (`id_votacao`)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
