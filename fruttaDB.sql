SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fruttasrldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fruttasrldb` ;

-- -----------------------------------------------------
-- Schema fruttasrldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fruttasrldb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `fruttasrldb` ;

-- -----------------------------------------------------
-- Table `fruttasrldb`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`cliente` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`cliente` (
  `Partita IVA` CHAR(11) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Via Residenza` VARCHAR(45) NOT NULL,
  `Città Residenza` VARCHAR(45) NOT NULL,
  `Civico Residenza` SMALLINT UNSIGNED NOT NULL,
  `Via Fatturazione` VARCHAR(45) NOT NULL,
  `Città Fatturazione` VARCHAR(45) NOT NULL,
  `Civico Fatturazione` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`Partita IVA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`contatto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`contatto` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`contatto` (
  `Recapito` VARCHAR(45) NOT NULL,
  `Cliente` CHAR(11) NOT NULL,
  `Tipo Contatto` ENUM('Cellulare', 'Mail', 'Telefono') NOT NULL,
  PRIMARY KEY (`Recapito`),
  INDEX `Cliente_idx` (`Cliente` ASC) VISIBLE,
  CONSTRAINT `Cliente`
    FOREIGN KEY (`Cliente`)
    REFERENCES `fruttasrldb`.`cliente` (`Partita IVA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`ordine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`ordine` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`ordine` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Stato` ENUM('Aperto', 'Inviato', 'Finalizzato') NOT NULL,
  `Via` VARCHAR(45) NOT NULL,
  `Città` VARCHAR(45) NOT NULL,
  `Civico` SMALLINT UNSIGNED NOT NULL,
  `Contatto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `Contatto_idx` (`Contatto` ASC) VISIBLE,
  CONSTRAINT `Contatto`
    FOREIGN KEY (`Contatto`)
    REFERENCES `fruttasrldb`.`contatto` (`Recapito`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`prodotto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`prodotto` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`prodotto` (
  `Codice` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Quantità` FLOAT UNSIGNED NOT NULL,
  `Tipo` ENUM('Frutta', 'Verdura') NOT NULL,
  `Prezzo/kg` DECIMAL(10,2) UNSIGNED NOT NULL,
  PRIMARY KEY (`Codice`),
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`composto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`composto` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`composto` (
  `Ordine` INT UNSIGNED NOT NULL,
  `Prodotto` SMALLINT UNSIGNED NOT NULL,
  `Quantità` DECIMAL(10,2) UNSIGNED NOT NULL,
  PRIMARY KEY (`Ordine`, `Prodotto`),
  INDEX `Prodotto_idx` (`Prodotto` ASC) VISIBLE,
  CONSTRAINT `Ordine`
    FOREIGN KEY (`Ordine`)
    REFERENCES `fruttasrldb`.`ordine` (`ID`),
  CONSTRAINT `ProdottoOrdinato`
    FOREIGN KEY (`Prodotto`)
    REFERENCES `fruttasrldb`.`prodotto` (`Codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`fornitore`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`fornitore` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`fornitore` (
  `Codice` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Codice Fiscale` CHAR(16) NOT NULL,
  PRIMARY KEY (`Codice`),
  UNIQUE INDEX `Codice Fiscale_UNIQUE` (`Codice Fiscale` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`disponibilità`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`disponibilità` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`disponibilità` (
  `Prodotto` SMALLINT UNSIGNED NOT NULL,
  `Fornitore` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`Prodotto`, `Fornitore`),
  INDEX `fornitoreprodotto_idx` (`Fornitore` ASC) VISIBLE,
  CONSTRAINT `fornitoreprodotto`
    FOREIGN KEY (`Fornitore`)
    REFERENCES `fruttasrldb`.`fornitore` (`Codice`),
  CONSTRAINT `prodottofornito`
    FOREIGN KEY (`Prodotto`)
    REFERENCES `fruttasrldb`.`prodotto` (`Codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`indirizzo sede`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`indirizzo sede` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`indirizzo sede` (
  `Città` VARCHAR(45) NOT NULL,
  `Via` VARCHAR(45) NOT NULL,
  `Civico` SMALLINT UNSIGNED NOT NULL,
  `Fornitore` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`Città`, `Via`, `Civico`),
  INDEX `Fornitore_idx` (`Fornitore` ASC) VISIBLE,
  CONSTRAINT `Fornitore`
    FOREIGN KEY (`Fornitore`)
    REFERENCES `fruttasrldb`.`fornitore` (`Codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`prodotto in magazzino`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`prodotto in magazzino` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`prodotto in magazzino` (
  `Prodotto` SMALLINT UNSIGNED NOT NULL,
  `Scadenza` DATE NOT NULL,
  `Giacenza` DECIMAL(10,2) UNSIGNED NOT NULL,
  PRIMARY KEY (`Prodotto`, `Scadenza`),
  CONSTRAINT `Prodottomagazzino`
    FOREIGN KEY (`Prodotto`)
    REFERENCES `fruttasrldb`.`prodotto` (`Codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`rifornimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`rifornimento` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`rifornimento` (
  `ID` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Prodotto` SMALLINT UNSIGNED NOT NULL,
  `Fornitore` SMALLINT UNSIGNED NOT NULL,
  `Quantità` DECIMAL(10,2) UNSIGNED NOT NULL,
  `Data` DATE NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `ProdottoRifornito_idx` (`Prodotto` ASC) VISIBLE,
  CONSTRAINT `ProdottoRifornito`
    FOREIGN KEY (`Prodotto`)
    REFERENCES `fruttasrldb`.`prodotto` (`Codice`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fruttasrldb`.`utenti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fruttasrldb`.`utenti` ;

CREATE TABLE IF NOT EXISTS `fruttasrldb`.`utenti` (
  `username` VARCHAR(45) NOT NULL,
  `password` CHAR(32) NOT NULL,
  `ruolo` ENUM('operatore', 'manager', 'magazziniere') NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `fruttasrldb` ;

-- -----------------------------------------------------
-- procedure aggiungi_contatto
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`aggiungi_contatto`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_contatto`(in var_recapito varchar(45), in var_cliente char(11), in var_tipoContatto enum('Cellulare','Mail','Telefono'))
BEGIN
		insert into contatto(`Recapito`, `Cliente`, `Tipo Contatto`) values(var_recapito, var_cliente, var_tipoContatto);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure aggiungi_fornitore
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`aggiungi_fornitore`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_fornitore`(in var_nome varchar(45), in var_CF char(16), in var_citta varchar(45), in var_via varchar(45), in var_civico smallint unsigned, out var_codice smallint unsigned)
BEGIN
	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
    
    set transaction isolation level read committed;
    
    start transaction;
		insert into fornitore(`Nome`, `Codice Fiscale`)
        values(var_nome, var_CF);
        select max(Codice) from fornitore into var_codice;
        insert into `indirizzo sede`(Città, Via, Civico, Fornitore) values(var_citta, var_via, var_civico, var_codice);
	commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure aggiungi_indirizzo_fornitore
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`aggiungi_indirizzo_fornitore`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_indirizzo_fornitore`(in var_citta varchar(45), in var_via varchar(45), in var_civico smallint unsigned, in var_codice smallint unsigned)
BEGIN
		insert into `indirizzo sede`(Città, Via, Civico, Fornitore) values(var_citta, var_via, var_civico, var_codice);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure aggiungi_prodotto
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`aggiungi_prodotto`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_prodotto`(in var_nome varchar(45), in var_tipo enum('Frutta','Verdura'), in var_prezzoKg float unsigned)
BEGIN

		insert into prodotto(`Nome`, `Quantità`, `Tipo`, `Prezzo/kg`)
        values(var_nome, 0, var_tipo, var_prezzoKg);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure aggiungi_prodotto_a_ordine
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`aggiungi_prodotto_a_ordine`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_prodotto_a_ordine`(in var_IDOrdine smallint unsigned, in var_prodotto smallint unsigned, in var_quantita float unsigned)
BEGIN
	declare exit handler for sqlexception
		begin	
			rollback;
            resignal;	
		end;
        
        set transaction isolation level repeatable read; /*per il trigger*/
        start transaction;
        insert into composto(Ordine, Prodotto, Quantità) values (var_IDOrdine, var_prodotto, var_quantita);
        commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure aggiungi_prodotto_fornitore
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`aggiungi_prodotto_fornitore`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungi_prodotto_fornitore`(in var_IDFornitore smallint unsigned, in var_IDProdotto smallint unsigned)
BEGIN
	insert into `disponibilità`(Fornitore, Prodotto) values(var_IDFornitore, var_IDProdotto);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure apri_ordine
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`apri_ordine`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `apri_ordine`(in var_viaConsegna varchar(45), in var_cittaConsegna varchar(45), in var_civicoConsegna smallint unsigned,
															in var_contatto varchar(45), in var_prodotto smallint unsigned, in var_quantita float unsigned, out var_ID smallint unsigned)
BEGIN
	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
    
    set transaction isolation level repeatable read; /*per il trigger*/
    start transaction;
		insert into ordine(Stato, Via, Città, Civico, Contatto) values('Aperto', var_viaConsegna, var_cittaConsegna, var_civicoConsegna, var_contatto);
        select max(ID) from ordine into var_ID;
        insert into composto(Ordine, Prodotto, Quantità) values (var_ID, var_prodotto, var_quantita);
	commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure effettua_richiesta_fornitore
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`effettua_richiesta_fornitore`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `effettua_richiesta_fornitore`(in var_Fornitore smallint unsigned, in var_Prodotto smallint unsigned, in var_Quantita float unsigned)
BEGIN
	declare var int;
    declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
    set transaction isolation level read committed;
    start transaction;
		select count(*)
        from disponibilità
        where Prodotto = var_Prodotto and Fornitore = var_Fornitore
        into var;
		
        if var = 0 then
			signal sqlstate "45000" set message_text = "Il fornitore non dispone di quel prodotto";
		else
			insert into rifornimento(Prodotto, Fornitore, Quantità, Data) values(var_Prodotto, var_fornitore, var_Quantita, NOW());
		end if;
		commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure finalizza_ordine
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`finalizza_ordine`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `finalizza_ordine`(in var_IDOrdine smallint unsigned)
BEGIN
		declare var varchar(45);
        select Stato
        from ordine
        where ID = var_IDOrdine
        into var;
        if var <> 'Inviato' then
			signal sqlstate "45000" set message_text = "Non si può finalizzare un ordine non inviato";
        else
        update ordine
        set Stato = 'Finalizzato'
		where ID = var_IDOrdine;
        end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure invia_ordine
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`invia_ordine`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `invia_ordine`(in var_IDOrdine smallint unsigned)
BEGIN
declare var varchar(45);
declare exit handler for sqlexception
		begin	
			rollback;
            resignal;	
		end;
        
        set transaction isolation level serializable; /*per il trigger*/
        start transaction;
		
        select Stato
        from ordine
        where ID = var_IDOrdine
        into var;
        if var <> 'Aperto' then
			signal sqlstate "45000" set message_text = "Non si può inviare un ordine non aperto";
        else
        
        update ordine
        set Stato = 'Inviato'
		where ID = var_IDOrdine;
        end if;
        commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_fornitori_che_hanno_prodotto
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`visualizza_fornitori_che_hanno_prodotto`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_fornitori_che_hanno_prodotto`(in var_IDProdotto smallint unsigned)
BEGIN

        select `fornitore`.`Codice`, `fornitore`.`Nome`
        from fornitore join disponibilità on `fornitore`.`Codice` = `disponibilità`.`Fornitore`
		where `disponibilità`.`Prodotto` = var_IDProdotto;
       
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure login
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`login`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(in var_username varchar(45), in var_pass varchar(45), out var_role INT)
BEGIN
	declare var_user_role ENUM('manager', 'operatore', 'magazziniere');
    
    select ruolo from utenti
		where username = var_username
        and password = md5(var_pass)
	into var_user_role;
    
    if var_user_role = 'manager' then set var_role = 1;
    elseif var_user_role = 'operatore' then set var_role = 2;
    elseif var_user_role = 'magazziniere' then set var_role = 3;
    else set var_role = 4;
    end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure modifica_prezzoKg
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`modifica_prezzoKg`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifica_prezzoKg`(in var_IDProdotto smallint unsigned, in var_nuovoPrezzo float unsigned)
BEGIN   
    update prodotto
    set `Prezzo/kg` = var_nuovoPrezzo
    where `Codice` = var_IDProdotto;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure mostra_prodotti_ordine
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`mostra_prodotti_ordine`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostra_prodotti_ordine`(in var_IDOrdine smallint unsigned)
BEGIN
	Select Codice, Nome, `composto`.`Quantità` as QuantitaOrdinata
    from prodotto join composto on `prodotto`.`Codice` = `composto`.`Prodotto`
    join ordine on `composto`.`Ordine` = `ordine`.`ID`
    where `ordine`.`Stato` = 'Inviato' and `composto`.`Ordine` = var_IDOrdine;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure registra_cliente
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`registra_cliente`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registra_cliente`(in var_partitaIVA char(11), in var_nome varchar(45), in var_viaResidenza varchar(45), 
																in var_cittaResidenza varchar(45), in var_civicoResidenza smallint unsigned, in var_viaFatturazione varchar(45), in var_cittaFatturazione varchar(45), 
                                                                in var_civicoFatturazione smallint unsigned, in var_contatto varchar(45), in var_tipoContatto enum('Cellulare','Mail','Telefono'))
BEGIN
	declare exit handler for sqlexception
		begin	
			rollback;
            resignal;	
		end;
        
        set transaction isolation level read uncommitted;
        start transaction;
    insert into cliente(`Partita IVA`, `Nome`, `Via Residenza`, `Città Residenza`,`Civico Residenza` , `Via Fatturazione`, `Città Fatturazione`, `Civico Fatturazione`)
	values(var_partitaIVA, var_nome, var_viaResidenza, var_cittaResidenza, var_civicoResidenza, var_viaFatturazione, var_cittaFatturazione, var_civicoFatturazione);
    insert into contatto(`Recapito`, `Cliente`, `Tipo Contatto`) values(var_contatto, var_partitaIVA, var_tipoContatto);
    commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure registra_rifornimento
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`registra_rifornimento`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registra_rifornimento`(in var_Prodotto smallint unsigned, in var_Scadenza date, in var_Quantita float)
BEGIN
	declare var int;
    declare var_totale float unsigned;
    declare var_OldGiacenza float unsigned;
    
    declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
    set transaction isolation level repeatable read;
    start transaction;
    
		select count(*)
        from `prodotto in magazzino`
        where Prodotto = var_Prodotto and Scadenza = var_scadenza
        into var;
        
        if var = 1 then 
        select Giacenza
        from `prodotto in magazzino`
        where Prodotto = var_Prodotto and Scadenza = var_scadenza
        into var_OldGiacenza;
        
		update `prodotto in magazzino`
		set `Giacenza` = var_Quantita + var_OldGiacenza
		where Prodotto = var_Prodotto and Scadenza = var_scadenza;
        
        else
			insert into `prodotto in magazzino`(Prodotto, Scadenza, Giacenza) values (var_Prodotto, var_Scadenza, var_Quantita);
		end if;
        
        select `Quantità`
        from `prodotto`
        where Codice = var_Prodotto
        into var_totale;
        
        update `prodotto`
        set Quantità = var_totale + var_Quantita
        where Codice = var_Prodotto;
        commit;
	
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure report_ordine
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`report_ordine`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `report_ordine`(in var_id smallint unsigned)
BEGIN
	declare exit handler for sqlexception
		begin	
			rollback;
            resignal;	
		end;
        set transaction isolation level read committed;
        start transaction;
        
        select `Codice`, `Nome`, `Prezzo/kg`, `composto`.`Quantità`, (`Prezzo/kg` * `composto`.`Quantità`) as `Costo`
        from prodotto join composto on `composto`.`Prodotto` = `prodotto`.`Codice`
        where `composto`.`Ordine` = var_id;
        
        select sum(`Prezzo/kg` * `composto`.`Quantità`) as `Costo Totale`
        from prodotto join composto on `composto`.`Prodotto` = `prodotto`.`Codice`
        where `composto`.`Ordine` = var_id;
        commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure report_prodotti_in_scadenza
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`report_prodotti_in_scadenza`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `report_prodotti_in_scadenza`()
BEGIN
		declare exit handler for sqlexception
		begin	
			rollback;
            resignal;	
		end;
        set transaction isolation level read committed;
        start transaction;
        
        select  Prodotto, Giacenza, Scadenza, Nome
        from `prodotto in magazzino` join `prodotto` on Prodotto = Codice
        where Scadenza > now() and Scadenza <= date_add(now(), interval 7 day) 
        order by Scadenza;
        commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure mostra_richieste_ai_fornitori_settimana
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`mostra_richieste_ai_fornitori_settimana`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostra_richieste_ai_fornitori_settimana`()
BEGIN
		declare exit handler for sqlexception
		begin	
			rollback;
            resignal;	
		end;
        set transaction isolation level read committed;
        start transaction;
        
        select  `rifornimento`.`ID`, Prodotto, Fornitore, `rifornimento`.`Quantità`, Data, `prodotto`.`Nome` as NomeProdotto, `fornitore`.`Nome` as NomeFornitore
        from `rifornimento` join `prodotto` on Prodotto = `prodotto`.`Codice` join `fornitore` on Fornitore = `fornitore`.`Codice`
        where Data > date_sub(now(), interval 7 day) 
        order by Data;
        commit;
END$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure rimuovi_prodotto_a_ordine
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`rimuovi_prodotto_a_ordine`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `rimuovi_prodotto_a_ordine`(in var_IDOrdine smallint unsigned, in var_prodotto smallint unsigned)
BEGIN
	declare exit handler for sqlexception
		begin	
			rollback;
            resignal;	
		end;
        
        set transaction isolation level repeatable read;
        start transaction;
        delete
        from composto
        where Ordine = var_IDOrdine and Prodotto = var_prodotto;
        commit;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_ordini_da_spedire
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`visualizza_ordini_da_spedire`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_ordini_da_spedire`()
BEGIN

	select * 
    from ordine
    where Stato = 'Inviato';
	
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_dettagli_cliente
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`visualizza_dettagli_cliente`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_dettagli_cliente`(in var_partitaIVA char(11))
BEGIN
	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
    
    set transaction isolation level read committed;
    start transaction;
	select * 
    from cliente
    where `Partita IVA` = var_partitaIVA;
    
    select `Recapito`, `Tipo Contatto`
    from contatto
    where Cliente = var_partitaIVA;
    
    commit;
	
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_prodotti_disponibili
-- -----------------------------------------------------

USE `fruttasrldb`;
DROP procedure IF EXISTS `fruttasrldb`.`visualizza_prodotti_disponibili`;

DELIMITER $$
USE `fruttasrldb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_prodotti_disponibili`()
BEGIN
		declare exit handler for sqlexception
	begin
		rollback;
		resignal;
    end;
    
set transaction isolation level read committed;
set transaction read only;
start transaction;

	select * 
    from prodotto
    where Quantità > 0;
	commit;
END$$

DELIMITER ;
USE `fruttasrldb`;

DELIMITER $$

USE `fruttasrldb`$$
DROP TRIGGER IF EXISTS `fruttasrldb`.`composto_AFTER_DELETE` $$
USE `fruttasrldb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `fruttasrldb`.`composto_AFTER_DELETE`
AFTER DELETE ON `fruttasrldb`.`composto`
FOR EACH ROW
BEGIN
	UPDATE prodotto
    SET prodotto.Quantità = prodotto.Quantità + old.Quantità
    WHERE Codice = old.Prodotto;
END$$


USE `fruttasrldb`$$
DROP TRIGGER IF EXISTS `fruttasrldb`.`composto_AFTER_INSERT` $$
USE `fruttasrldb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `fruttasrldb`.`composto_AFTER_INSERT`
AFTER INSERT ON `fruttasrldb`.`composto`
FOR EACH ROW
BEGIN
	UPDATE prodotto
    SET prodotto.Quantità = prodotto.Quantità - New.Quantità
    WHERE Codice = New.Prodotto;
END$$


USE `fruttasrldb`$$
DROP TRIGGER IF EXISTS `fruttasrldb`.`composto_BEFORE_INSERT` $$
USE `fruttasrldb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `fruttasrldb`.`composto_BEFORE_INSERT`
BEFORE INSERT ON `fruttasrldb`.`composto`
FOR EACH ROW
BEGIN
	declare var_Giacenza decimal(10,2);
    declare var_Stato varchar(45);
    select Stato from ordine where ID = New.Ordine into var_Stato;
    if var_Stato <> 'Aperto' then
    SIGNAL sqlstate '45000' set message_text = "Impossibile aggiungere prodotto perchè ordine già inviato o finalizzato";
    end if;
    
    select Quantità from Prodotto where Codice = New.prodotto into var_Giacenza;
    if var_Giacenza < New.Quantità then 
    SIGNAL sqlstate '45000' set message_text = "La richiesta è superiore alla giacenza in magazzino";
    end if;
END$$

USE `fruttasrldb`$$
DROP TRIGGER IF EXISTS `fruttasrldb`.`composto_BEFORE_DELETE`$$
USE `fruttasrldb`$$
CREATE
DEFINER = `root`@`localhost`
TRIGGER `fruttasrldb`.`composto_BEFORE_DELETE`
BEFORE DELETE ON `composto` FOR EACH ROW
BEGIN
	declare var_stato varchar(45);
    select Stato from ordine
    where old.Ordine = `ordine`.`ID`
    into var_stato;
    if var_stato <> 'Aperto' then
    SIGNAL sqlstate '45000' set message_text = "Impossibile modificare l'ordine perchè inviato o finalizzato";
    end if;
END$$




USE `fruttasrldb`$$
DROP TRIGGER IF EXISTS `fruttasrldb`.`scaduto_AFTER_DELETE` $$
USE `fruttasrldb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `fruttasrldb`.`scaduto_AFTER_DELETE`
AFTER DELETE ON `fruttasrldb`.`prodotto in magazzino`
FOR EACH ROW
BEGIN
	UPDATE prodotto
    SET prodotto.Quantità = prodotto.Quantità - old.`Giacenza`
    WHERE Codice = old.`Prodotto`;
END$$




USE `fruttasrldb`$$
DROP TRIGGER IF EXISTS `fruttasrldb`.`ordine_AFTER_UPDATE` $$
USE `fruttasrldb`$$
CREATE
DEFINER=`root`@`localhost` 
TRIGGER `ordine_AFTER_UPDATE` 
AFTER UPDATE ON `ordine` 
FOR EACH ROW
BEGIN
	
	declare done int default false;
    declare var_richiesta decimal (10,2) unsigned;
    declare var_prodotto smallint unsigned;
    
    declare var_prodotto_magazzino smallint unsigned;
    declare var_giacenza decimal(10,2) unsigned;
    declare var_data date;

    declare cur cursor for
		select `composto`.`Prodotto`, `composto`.`Quantità`
        from ordine join composto on `ordine`.`ID` = `composto`.`Ordine`
        where old.Stato = 'Aperto' and new.Stato = 'Inviato' ;
        
	declare curr cursor for
		select `prodotto in magazzino`.`Prodotto`, `prodotto in magazzino`.`Scadenza`, `prodotto in magazzino`.`Giacenza`
        from `prodotto in magazzino`
        order by `prodotto in magazzino`.`Scadenza`;
        
    declare continue handler for not found set done = true;   
    
    open cur;
    read_loop: loop
		fetch cur into var_prodotto, var_richiesta;
        if done then
			leave read_loop;
		end if;
        
	open curr;
    read__loop: loop
		fetch curr into var_prodotto_magazzino, var_data, var_giacenza;
        if var_richiesta = 0 then
			leave read__loop;
		end if;
			
		if var_prodotto = var_prodotto_magazzino then

             
               if var_giacenza > var_richiesta then   
				update `prodotto in magazzino`
                set `prodotto in magazzino`.Giacenza = var_giacenza - var_richiesta
                where Prodotto = var_prodotto and Scadenza = var_data;
                
                set var_richiesta = 0;
                end if;
                
                			if var_richiesta >= var_giacenza then
				update `prodotto in magazzino`
                set `prodotto in magazzino`.Giacenza = 0
                where Prodotto = var_prodotto and Scadenza = var_data;
                
                delete from `prodotto in magazzino`
                where Prodotto = var_prodotto and Scadenza = var_data;
                
                set var_richiesta = var_richiesta - var_giacenza;
                end if;
        end if;
        
        
        end loop;
        close curr;
        
        end loop;
        close cur;
		
END$$




DELIMITER ;
/*-------------------------------------------------------------------------*/
SET SQL_MODE = '';
DROP USER IF EXISTS login;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'login' IDENTIFIED BY 'login';
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`login` TO login;


SET SQL_MODE = '';
DROP USER IF EXISTS operatore;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'operatore' IDENTIFIED BY 'operatore';
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`visualizza_prodotti_disponibili` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`apri_ordine` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`aggiungi_prodotto_a_ordine` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`rimuovi_prodotto_a_ordine` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`invia_ordine` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`report_ordine` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`registra_cliente` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`aggiungi_contatto` to operatore;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`visualizza_dettagli_cliente` to operatore;


SET SQL_MODE = '';
DROP USER IF EXISTS manager;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'manager' IDENTIFIED BY 'manager';
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`modifica_prezzoKg` to manager;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`aggiungi_prodotto` to manager;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`aggiungi_fornitore` to manager;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`aggiungi_indirizzo_fornitore` to manager;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`aggiungi_prodotto_fornitore` to manager;


SET SQL_MODE = '';
DROP USER IF EXISTS magazziniere;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'magazziniere' IDENTIFIED BY 'magazziniere';
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`visualizza_ordini_da_spedire` to magazziniere;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`mostra_prodotti_ordine` to magazziniere;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`finalizza_ordine` to magazziniere;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`effettua_richiesta_fornitore` to magazziniere;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`report_prodotti_in_scadenza` to magazziniere;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`registra_rifornimento` to magazziniere;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`visualizza_fornitori_che_hanno_prodotto` to magazziniere;
GRANT EXECUTE ON PROCEDURE `fruttasrldb`.`mostra_richieste_ai_fornitori_settimana` to magazziniere;



/*eventi*/

DELIMITER $$
CREATE EVENT IF NOT EXISTS elimina_scaduti_o_indisponibili
	On schedule EVERY 1 DAY
    Do BEGIN
    DELETE from `prodotto in magazzino` where Scadenza = curdate();
    END$$
    
DELIMITER ;

DELIMITER $$
CREATE EVENT IF NOT EXISTS elimina_vecchi_rifornimenti
	On schedule EVERY 1 DAY
    Do BEGIN
    DELETE from `rifornimento` where Data < (curdate()- INTERVAL 1 MONTH);
    END$$
    
DELIMITER ;

/*-------------------------------------------------------------------------------------------------*/


INSERT INTO cliente values (78451589658, 'Marco Rosi', 'Appia', 'Roma', 22, 'Appia', 'Roma', 22),
							(78965412302, 'Beppe Arancia', 'Casilina', 'Roma', 8, 'Casilina', 'Roma', 8),
                            (11111111111, 'Luigi Mario', 'Collatina', 'Roma', 7, 'Collatina', 'Roma', 7),
                            (01478523698, 'Gianni Aria', 'Ripetta', 'Roma', 74, 'Ripetta', 'Roma', 74),
                            (85744758854, 'Genna Ferri', 'Cartagine', 'Ciampino', 22, 'Cartagine', 'Ciampino', 22);
                            
INSERT INTO contatto values ('+398965475122', 78451589658, 'Cellulare'), ('068757485', 78451589658, 'Telefono'), ('marco.rosi@gmail.com', 78451589658, 'Mail'),
							('+398765475122', 78965412302, 'Cellulare'), ('068857485', 78965412302, 'Telefono'), ('beppe.arancia@gmail.com', 78965412302, 'Mail'),
                            ('+393921426002', 11111111111, 'Cellulare'), ('068957495', 11111111111, 'Telefono'), ('luigi.mario@gmail.com', 11111111111, 'Mail'),
                            ('+398965475922', 01478523698, 'Cellulare'), ('068957465', 01478523698, 'Telefono'), ('gianni.aria@gmail.com', 01478523698, 'Mail'),
                            ('+398965475102', 85744758854, 'Cellulare'), ('068951485', 85744758854, 'Telefono'), ('genna.ferri@gmail.com', 85744758854, 'Mail');
                            
INSERT INTO prodotto values (1, 'Mela', 0, 'Frutta', 0.80), (2, 'Pera', 0, 'Frutta', 0.85), (3, 'Banana', 0, 'Frutta', 0.84),
							(4, 'Albicocca', 0, 'Frutta', 0.83), (5, 'Pesca', 0, 'Frutta', 0.80), (6, 'Carciofo', 0, 'Verdura', 0.84),
                            (7, 'Broccolo', 0, 'Verdura', 0.99), (8, 'Finocchio', 0, 'Verdura', 0.41), (9, 'Lattuga', 0, 'Verdura', 0.80);
                            
INSERT INTO fornitore values (1, 'TropicoSRL', 'VLKKDJ00E45F938U'), (2, 'NordSRL', 'SDKSFJ04E45F938U'), (3, 'GiustoSRL', 'VLOKHJ00E45T938U'),
							(4, 'EstSRL', 'VLLOPJ98E45F938U'), (5, 'SudSRL', 'VOPLDJ20E45F823U');
                            
INSERT INTO `indirizzo sede` values ('Roma', 'Filmona', 3, 1), ('Ciampino', 'Carpi', 73, 1), ('Milano', 'Presti', 993, 2),
									('Napoli', 'Partenope', 7, 3), ('Roma', 'Colleggiale', 34, 4), ('Firenze', 'Capradossi', 77, 5);
                                    
INSERT INTO disponibilità values (1,1), (2,1), (3,1), (4,1), (5,1), (8,1),
								(1,2), (4,2), (5,2), (7,2), (8,2), (9,2), 
                                (1,3), (2,3), (3,3), (5,3), (6,3), (8,3), 
                                (4,4), (5,4), (6,4), (7,4), (8,4), (9,4), 
                                (1,5), (2,5), (3,5), (4,5), (5,5), (8,5);
                                
INSERT INTO utenti values ('a', md5('a'), 'magazziniere'), 
							('b', md5('b'), 'operatore'), 
                            ('c', md5('c'), 'manager');
                            

                       

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
