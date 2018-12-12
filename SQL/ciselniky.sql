/*Èíselník Kategorie pøedmìtu*/
insert into kat_predmetu (zkr_kat,nazev) values ('A','Povinný');
insert into kat_predmetu (zkr_kat,nazev) values ('B','Povinnì volitelný');
insert into kat_predmetu (zkr_kat,nazev) values ('C','Volitelný');
alter table kat_predmetu read only;

/*Èíselník Vyuèovaný semestr*/
insert into semestr (zkr_sem,nazev) values ('ZS','Zimní semestr');
insert into semestr (zkr_sem,nazev) values ('LS','Letní semestr');
alter table semestr read only;

/*Èíselník Zpùsob zakonèení*/
insert into zpusob_zakonceni (zkr_zak,nazev) values ('Zp','Zápoèet');
insert into zpusob_zakonceni (zkr_zak,nazev) values ('Zk','Zkouška');
alter table zpusob_zakonceni read only;

/*Sekvence a trigger Zpùsobu výuky*/
CREATE SEQUENCE ZPUSOB_VYUKY_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_ZPUSOB_VYUKY
BEFORE INSERT ON ZPUSOB_VYUKY
FOR EACH ROW
 WHEN (new.ID_NAZVU IS NULL) 
BEGIN
  SELECT ZPUSOB_VYUKY_SEQ.NEXTVAL
  INTO :new.ID_NAZVU
  FROM dual;
END;
/
/*Èíselník Zpùsobu výuky*/
insert into zpusob_vyuky (nazev) values ('Pøednáška');
insert into zpusob_vyuky (nazev) values ('Cvièení');
insert into zpusob_vyuky (nazev) values ('Semináø');
insert into zpusob_vyuky (nazev) values ('Exkurze');
alter table zpusob_vyuky read only;

/*Sekvence a trigger Forma výuky*/
CREATE SEQUENCE forma_vyuky_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_forma_vyuky
BEFORE INSERT ON forma_vyuky
FOR EACH ROW
 WHEN (new.id_formy IS NULL) 
BEGIN
  SELECT forma_vyuky_SEQ.NEXTVAL
  INTO :new.id_formy
  FROM dual;
END;
/
/*Èíselník Forma výuky*/
insert into forma_vyuky (nazev_formy) values ('Prezenèní');
insert into forma_vyuky (nazev_formy) values ('Kombinovaná');
insert into forma_vyuky (nazev_formy) values ('Distanèní');
alter table forma_vyuky read only;

/*Sekvence a trigger Týdne*/
CREATE SEQUENCE TYDEN_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_TYDEN
BEFORE INSERT ON TYDEN
FOR EACH ROW
 WHEN (new.ID_TYDNE IS NULL) 
BEGIN
  SELECT TYDEN_SEQ.NEXTVAL
  INTO :new.ID_TYDNE
  FROM dual;
END;
/
/*Èíselník Týdne*/
insert into tyden (nazev) values ('Sudý');
insert into tyden (nazev) values ('Lichý');
alter table tyden read only;

/*Sekvence a trigger Rolí*/
CREATE SEQUENCE ROLE_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_ROLE
BEFORE INSERT ON ROLE
FOR EACH ROW
 WHEN (new.ID_ROLE IS NULL) 
BEGIN
  SELECT ROLE_SEQ.NEXTVAL
  INTO :new.ID_ROLE
  FROM dual;
END;
/
/*Èíselník Rolí*/
insert into role (nazev_role) values ('Administrátor');
insert into role (nazev_role) values ('Registrovaný');
alter table role read only;

/*Sekvence a trigger predmetu*/
CREATE SEQUENCE PREDMET_SEQ 
 START WITH 1 
 INCREMENT BY 1;
 
 CREATE OR REPLACE TRIGGER TRIG_PREDMET
BEFORE INSERT ON PREDMET
FOR EACH ROW
 WHEN (new.id_predmetu IS NULL) 
BEGIN
  SELECT predmet_SEQ.NEXTVAL
  INTO :new.id_predmetu
  FROM dual;
END;

/*Sekvence a trigger predmetu*/
CREATE SEQUENCE UCEBNA_SEQ
 START WITH 1
 INCREMENT BY 1;
 
 CREATE OR REPLACE TRIGGER TRIG_UCEBNA
BEFORE INSERT ON UCEBNA
FOR EACH ROW
  WHEN (new.id_ucebny IS NULL)
BEGIN
  SELECT ucebna_SEQ.NEXTVAL
  INTO :new.id_ucebny
  FROM dual;
END;