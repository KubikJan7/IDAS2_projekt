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
insert into tyden (nazev) values ('Každý');
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
/
/*Sekvence a trigger predmetu*/
CREATE SEQUENCE PREDMET_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_PREDMET
BEFORE INSERT ON PREDMET
FOR EACH ROW
 WHEN (new.id_predmetu IS NULL) 
BEGIN
  SELECT predmet_SEQ.NEXTVAL
  INTO :new.id_predmetu
  FROM dual;
END;
/
/*Sekvence a trigger Uceben*/
CREATE SEQUENCE UCEBNA_SEQ
 START WITH 1
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_UCEBNA
BEFORE INSERT ON UCEBNA
FOR EACH ROW
  WHEN (new.id_ucebny IS NULL)
BEGIN
  SELECT ucebna_SEQ.NEXTVAL
  INTO :new.id_ucebny
  FROM dual;
END;
/
/*Sekvence a trigger Uzivatelu*/
CREATE SEQUENCE UZIVATEL_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_UZIVATEL
BEFORE INSERT ON UZIVATEL
FOR EACH ROW
 WHEN (new.id_uzivatele IS NULL) 
BEGIN
  SELECT UZIVATEL_SEQ.NEXTVAL
  INTO :new.id_uzivatele
  FROM dual;
END;
/
/*Sekvence a trigger Uctu*/
CREATE SEQUENCE UCET_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_UCET
BEFORE INSERT ON UCET
FOR EACH ROW
 WHEN (new.id_uctu IS NULL) 
BEGIN
  SELECT UCET_SEQ.NEXTVAL
  INTO :new.id_uctu
  FROM dual;
END;
/
/*Sekvence a trigger oboru*/
CREATE SEQUENCE stud_obor_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_stud_obor
BEFORE INSERT ON stud_obor
FOR EACH ROW
 WHEN (new.id_oboru IS NULL) 
BEGIN
  SELECT stud_obor_SEQ.NEXTVAL
  INTO :new.id_oboru
  FROM dual;
END;
/
/*Sekvence a trigger Stud_Plan*/
CREATE SEQUENCE stud_plan_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_stud_plan
BEFORE INSERT ON stud_plan
FOR EACH ROW
 WHEN (new.id_planu IS NULL) 
BEGIN
  SELECT stud_plan_SEQ.NEXTVAL
  INTO :new.id_planu
  FROM dual;
END;
/
/*Sekvence a trigger Pred_V_Planu*/
CREATE SEQUENCE pred_v_planu_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_pred_v_planu
BEFORE INSERT ON pred_v_planu
FOR EACH ROW
 WHEN (new.id_pred_planu IS NULL) 
BEGIN
  SELECT pred_v_planu_SEQ.NEXTVAL
  INTO :new.id_pred_planu
  FROM dual;
END;
/
/*Sekvence a trigger Rozvrhova_Akce*/
CREATE SEQUENCE rozvrhova_akce_SEQ 
 START WITH 1 
 INCREMENT BY 1;
/
 CREATE OR REPLACE TRIGGER TRIG_rozvrhova_akce
BEFORE INSERT ON rozvrhova_akce
FOR EACH ROW
 WHEN (new.id_akce IS NULL) 
BEGIN
  SELECT rozvrhova_akce_SEQ.NEXTVAL
  INTO :new.id_akce
  FROM dual;
END;
/