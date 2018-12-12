-- Generated by Oracle SQL Developer Data Modeler 17.4.0.355.2121
--   at:        2018-12-12 23:09:03 CET
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



CREATE TABLE fakulta (
    zkratka_fakulty   CHAR(6 CHAR) NOT NULL,
    nazev_fakulty     NVARCHAR2(100) NOT NULL
);

ALTER TABLE fakulta ADD CONSTRAINT fakulta_pk PRIMARY KEY ( zkratka_fakulty );

CREATE TABLE forma_vyuky (
    id_formy      NUMBER(2) NOT NULL,
    nazev_formy   NVARCHAR2(30) NOT NULL
);

ALTER TABLE forma_vyuky ADD CONSTRAINT forma_vyuky_pk PRIMARY KEY ( id_formy );

CREATE TABLE kat_predmetu (
    zkr_kat   CHAR(1 CHAR) NOT NULL,
    nazev     NVARCHAR2(30) NOT NULL
);

ALTER TABLE kat_predmetu ADD CONSTRAINT kat_predmetu_pk PRIMARY KEY ( zkr_kat );

CREATE TABLE katedra (
    zkratka_katedry           CHAR(6 CHAR) NOT NULL,
    nazev_katedry             NVARCHAR2(100) NOT NULL,
    fakulta_zkratka_fakulty   CHAR(6 CHAR) NOT NULL
);

ALTER TABLE katedra ADD CONSTRAINT katedra_pk PRIMARY KEY ( zkratka_katedry );

CREATE TABLE obrazek (
    id_souboru              NUMBER NOT NULL,
    nazev                   VARCHAR2(45) NOT NULL,
    pripona                 VARCHAR2(10 CHAR) NOT NULL,
    obsah                   BLOB NOT NULL,
    vytvoreno               DATE NOT NULL,
    modifikace              DATE NOT NULL,
    uzivatel_id_uzivatele   NUMBER(6) NOT NULL
);

CREATE UNIQUE INDEX obrazek__idx ON
    obrazek ( uzivatel_id_uzivatele ASC );

ALTER TABLE obrazek ADD CONSTRAINT obrazek_pk PRIMARY KEY ( id_souboru );

CREATE TABLE pred_v_planu (
    id_pred_planu              NUMBER(6) NOT NULL,
    kredity                    NUMBER(2) NOT NULL,
    dop_rocnik                 NUMBER(1),
    kat_predmetu_zkr_kat       CHAR(1 CHAR) NOT NULL,
    predmet_id_predmetu        NUMBER(5) NOT NULL,
    zpusob_zakonceni_zkr_zak   CHAR(6 CHAR) NOT NULL,
    semestr_zkr_sem            CHAR(2 CHAR) NOT NULL,
    stud_plan_id_planu         NUMBER(6) NOT NULL
);

CREATE UNIQUE INDEX pred_v_planu__idx ON
    pred_v_planu ( predmet_id_predmetu ASC );

ALTER TABLE pred_v_planu ADD CONSTRAINT pred_v_planu_pk PRIMARY KEY ( id_pred_planu );

CREATE TABLE predmet (
    id_predmetu   NUMBER(5) NOT NULL,
    nazev         NVARCHAR2(50) NOT NULL,
    zkratka       NVARCHAR2(10) NOT NULL
);

ALTER TABLE predmet ADD CONSTRAINT predmet_pk PRIMARY KEY ( id_predmetu );

CREATE TABLE role (
    id_role      NUMBER(3) NOT NULL,
    nazev_role   NVARCHAR2(30) NOT NULL
);

ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY ( id_role );

CREATE TABLE rozvrhova_akce (
    id_akce                 NUMBER(6) NOT NULL,
    datum                   DATE NOT NULL,
    cas_od                  TIMESTAMP NOT NULL,
    rozsah_hodin            NUMBER(3) NOT NULL,
    kapacita                NUMBER(3) NOT NULL,
    uzivatel_id_uzivatele   NUMBER(6) NOT NULL,
    predmet_id_predmetu     NUMBER(5) NOT NULL,
    zpusob_vyuky_id_nazvu   NUMBER(6) NOT NULL,
    ucebna_id_ucebny        NUMBER(6)
);

ALTER TABLE rozvrhova_akce ADD CONSTRAINT rozvrhova_akce_pk PRIMARY KEY ( id_akce );

CREATE TABLE semestr (
    zkr_sem   CHAR(2 CHAR) NOT NULL,
    nazev     VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE semestr ADD CONSTRAINT semestr_pk PRIMARY KEY ( zkr_sem );

CREATE TABLE stud_obor (
    id_oboru               NUMBER(3) NOT NULL,
    nazev                  NVARCHAR2(50) NOT NULL,
    zkratka                CHAR(10 CHAR) NOT NULL,
    info                   NVARCHAR2(1000),
    forma_vyuky_id_formy   NUMBER(2) NOT NULL
);

ALTER TABLE stud_obor ADD CONSTRAINT stud_obor_pk PRIMARY KEY ( id_oboru );

CREATE TABLE stud_plan (
    id_planu             NUMBER(6) NOT NULL,
    verze                NUMBER(4) NOT NULL,
    stud_obor_id_oboru   NUMBER(3) NOT NULL
);

ALTER TABLE stud_plan ADD CONSTRAINT stud_plan_pk PRIMARY KEY ( id_planu );

CREATE TABLE tyden (
    id_tydne   NUMBER(2) NOT NULL,
    nazev      NVARCHAR2(50) NOT NULL
);

ALTER TABLE tyden ADD CONSTRAINT tyden_pk PRIMARY KEY ( id_tydne );

CREATE TABLE tyden_akce (
    rozvrhova_akce_id_akce   NUMBER(6) NOT NULL,
    tyden_id_tydne           NUMBER(2) NOT NULL
);

ALTER TABLE tyden_akce ADD CONSTRAINT tyden_akce_pk PRIMARY KEY ( rozvrhova_akce_id_akce,
tyden_id_tydne );

CREATE TABLE ucebna (
    id_ucebny      NUMBER(6) NOT NULL,
    nazev_ucebny   NVARCHAR2(30) NOT NULL,
    kapacita       NUMBER(3) NOT NULL
);

ALTER TABLE ucebna ADD CONSTRAINT ucebna_pk PRIMARY KEY ( id_ucebny );

CREATE TABLE ucet (
    id_uctu                 NUMBER(10) NOT NULL,
    nick                    VARCHAR2(45 CHAR) NOT NULL,
    heslo                   VARCHAR2(45 CHAR) NOT NULL,
    uzivatel_id_uzivatele   NUMBER(6) NOT NULL
);

CREATE UNIQUE INDEX ucet__idx ON
    ucet ( uzivatel_id_uzivatele ASC );

ALTER TABLE ucet ADD CONSTRAINT ucet_pk PRIMARY KEY ( id_uctu );

ALTER TABLE ucet ADD CONSTRAINT ucet_nick_un UNIQUE ( nick );

CREATE TABLE uzivatel (
    id_uzivatele              NUMBER(6) NOT NULL,
    jmeno                     NVARCHAR2(40) NOT NULL,
    prijmeni                  NVARCHAR2(40) NOT NULL,
    titul_pred                NVARCHAR2(15),
    titul_za                  NVARCHAR2(15),
    email                     VARCHAR2(70 CHAR),
    mobil                     CHAR(9 CHAR),
    telefon                   CHAR(13 CHAR),
    katedra_zkratka_katedry   CHAR(6 CHAR) NOT NULL,
    role_id_role              NUMBER(3) NOT NULL
);

ALTER TABLE uzivatel ADD CONSTRAINT uzivatel_pk PRIMARY KEY ( id_uzivatele );

CREATE TABLE zpusob_vyuky (
    id_nazvu   NUMBER(6) NOT NULL,
    nazev      NVARCHAR2(30) NOT NULL
);

ALTER TABLE zpusob_vyuky ADD CONSTRAINT zpusob_vyuky_pk PRIMARY KEY ( id_nazvu );

CREATE TABLE zpusob_zakonceni (
    zkr_zak   CHAR(6 CHAR) NOT NULL,
    nazev     NVARCHAR2(30) NOT NULL
);

ALTER TABLE zpusob_zakonceni ADD CONSTRAINT zpusob_zakonceni_pk PRIMARY KEY ( zkr_zak );

ALTER TABLE katedra
    ADD CONSTRAINT katedra_fakulta_fk FOREIGN KEY ( fakulta_zkratka_fakulty )
        REFERENCES fakulta ( zkratka_fakulty );

ALTER TABLE obrazek
    ADD CONSTRAINT obrazek_uzivatel_fk FOREIGN KEY ( uzivatel_id_uzivatele )
        REFERENCES uzivatel ( id_uzivatele );

ALTER TABLE pred_v_planu
    ADD CONSTRAINT pred_v_planu_kat_predmetu_fk FOREIGN KEY ( kat_predmetu_zkr_kat )
        REFERENCES kat_predmetu ( zkr_kat );

ALTER TABLE pred_v_planu
    ADD CONSTRAINT pred_v_planu_predmet_fk FOREIGN KEY ( predmet_id_predmetu )
        REFERENCES predmet ( id_predmetu );

ALTER TABLE pred_v_planu
    ADD CONSTRAINT pred_v_planu_semestr_fk FOREIGN KEY ( semestr_zkr_sem )
        REFERENCES semestr ( zkr_sem );

ALTER TABLE pred_v_planu
    ADD CONSTRAINT pred_v_planu_stud_plan_fk FOREIGN KEY ( stud_plan_id_planu )
        REFERENCES stud_plan ( id_planu );

ALTER TABLE pred_v_planu
    ADD CONSTRAINT pred_v_planu_zpus_zakonceni_fk FOREIGN KEY ( zpusob_zakonceni_zkr_zak )
        REFERENCES zpusob_zakonceni ( zkr_zak );

ALTER TABLE rozvrhova_akce
    ADD CONSTRAINT rozvrhova_akce_predmet_fk FOREIGN KEY ( predmet_id_predmetu )
        REFERENCES predmet ( id_predmetu );

ALTER TABLE rozvrhova_akce
    ADD CONSTRAINT rozvrhova_akce_ucebna_fk FOREIGN KEY ( ucebna_id_ucebny )
        REFERENCES ucebna ( id_ucebny );

ALTER TABLE rozvrhova_akce
    ADD CONSTRAINT rozvrhova_akce_uzivatel_fk FOREIGN KEY ( uzivatel_id_uzivatele )
        REFERENCES uzivatel ( id_uzivatele );

ALTER TABLE rozvrhova_akce
    ADD CONSTRAINT rozvrhova_akce_zpusob_vyuky_fk FOREIGN KEY ( zpusob_vyuky_id_nazvu )
        REFERENCES zpusob_vyuky ( id_nazvu );

ALTER TABLE stud_obor
    ADD CONSTRAINT stud_obor_forma_vyuky_fk FOREIGN KEY ( forma_vyuky_id_formy )
        REFERENCES forma_vyuky ( id_formy );

ALTER TABLE stud_plan
    ADD CONSTRAINT stud_plan_stud_obor_fk FOREIGN KEY ( stud_obor_id_oboru )
        REFERENCES stud_obor ( id_oboru );

ALTER TABLE tyden_akce
    ADD CONSTRAINT tyden_akce_rozvrhova_akce_fk FOREIGN KEY ( rozvrhova_akce_id_akce )
        REFERENCES rozvrhova_akce ( id_akce );

ALTER TABLE tyden_akce
    ADD CONSTRAINT tyden_akce_tyden_fk FOREIGN KEY ( tyden_id_tydne )
        REFERENCES tyden ( id_tydne );

ALTER TABLE ucet
    ADD CONSTRAINT ucet_uzivatel_fk FOREIGN KEY ( uzivatel_id_uzivatele )
        REFERENCES uzivatel ( id_uzivatele );

ALTER TABLE uzivatel
    ADD CONSTRAINT uzivatel_katedra_fk FOREIGN KEY ( katedra_zkratka_katedry )
        REFERENCES katedra ( zkratka_katedry );

ALTER TABLE uzivatel
    ADD CONSTRAINT uzivatel_role_fk FOREIGN KEY ( role_id_role )
        REFERENCES role ( id_role );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            19
-- CREATE INDEX                             3
-- ALTER TABLE                             38
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
