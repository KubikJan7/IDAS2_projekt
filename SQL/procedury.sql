-- Obory
create or replace PROCEDURE vlozObor
  (p_zkratka STUD_OBOR.ZKRATKA%TYPE, p_nazev STUD_OBOR.NAZEV%TYPE, p_id_formy STUD_OBOR.FORMA_VYUKY_ID_FORMY%TYPE, p_info STUD_OBOR.INFO%TYPE)
IS
BEGIN
	INSERT INTO STUD_OBOR ( ZKRATKA, NAZEV,FORMA_VYUKY_ID_FORMY,INFO)
	values (p_zkratka, p_nazev, p_id_formy, p_info);
END;
/
create or replace PROCEDURE upravObor
  (p_id_oboru STUD_OBOR.ID_OBORU%TYPE, p_zkratka STUD_OBOR.ZKRATKA%TYPE, p_nazev STUD_OBOR.NAZEV%TYPE, p_id_formy STUD_OBOR.FORMA_VYUKY_ID_FORMY%TYPE, p_info STUD_OBOR.INFO%TYPE)
IS
BEGIN
	UPDATE STUD_OBOR
	SET ZKRATKA = p_zkratka,NAZEV = p_nazev, FORMA_VYUKY_ID_FORMY = p_id_formy,INFO = p_info
    WHERE ID_OBORU = p_id_oboru;
END;
/
create or replace PROCEDURE smazObor (p_id_oboru STUD_OBOR.ID_OBORU%TYPE)
IS
BEGIN
    DELETE FROM STUD_OBOR
    WHERE ID_OBORU = p_id_oboru;
END;
/

-- Studijní plán oboru
create or replace PROCEDURE vlozPlan
  (p_verze STUD_PLAN.VERZE%TYPE, p_id_oboru STUD_PLAN.STUD_OBOR_ID_OBORU%TYPE)
IS
BEGIN
	INSERT INTO STUD_PLAN (VERZE,STUD_OBOR_ID_OBORU)
	values (p_verze,p_id_oboru);
END;
/
create or replace PROCEDURE upravPlan
  (p_id_planu STUD_PLAN.ID_PLANU%TYPE, p_verze STUD_PLAN.VERZE%TYPE, p_id_oboru STUD_PLAN.STUD_OBOR_ID_OBORU%TYPE)
IS
BEGIN
	UPDATE STUD_PLAN
	SET VERZE = p_verze,STUD_OBOR_ID_OBORU = p_id_oboru
    WHERE ID_PLANU = p_id_planu;
END;
/
create or replace PROCEDURE smazPlan (p_id_planu STUD_PLAN.ID_PLANU%TYPE)
IS
BEGIN
    DELETE FROM STUD_PLAN
    WHERE ID_PLANU = p_id_planu;
END;
/

-- Uzivatel
create or replace PROCEDURE vlozUzivatele
  (p_jmeno UZIVATEL.JMENO%TYPE, p_prijmeni UZIVATEL.PRIJMENI%TYPE, p_titulPred UZIVATEL.TITUL_PRED%TYPE, 
  p_titulZa UZIVATEL.TITUL_ZA%TYPE, p_email UZIVATEL.EMAIL%TYPE, p_mobil UZIVATEL.MOBIL%TYPE, p_telefon UZIVATEL.TELEFON%TYPE,
  p_zkratkaKatedry UZIVATEL.KATEDRA_ZKRATKA_KATEDRY%TYPE, p_idRole UZIVATEL.ROLE_ID_ROLE%TYPE)
IS
BEGIN
	Insert into UZIVATEL (JMENO, PRIJMENI, TITUL_PRED, TITUL_ZA, EMAIL, MOBIL, TELEFON, KATEDRA_ZKRATKA_KATEDRY, ROLE_ID_ROLE)
    values (p_jmeno, p_prijmeni, p_titulPred, p_titulZa, p_email, p_mobil, p_telefon, p_zkratkaKatedry, p_idRole);
END;
/
create or replace PROCEDURE upravUzivatele
  (p_id UZIVATEL.ID_UZIVATELE%TYPE, p_jmeno UZIVATEL.JMENO%TYPE, p_prijmeni UZIVATEL.PRIJMENI%TYPE, p_titulPred UZIVATEL.TITUL_PRED%TYPE, 
  p_titulZa UZIVATEL.TITUL_ZA%TYPE, p_email UZIVATEL.EMAIL%TYPE, p_mobil UZIVATEL.MOBIL%TYPE, p_telefon UZIVATEL.TELEFON%TYPE,
  p_zkratkaKatedry UZIVATEL.KATEDRA_ZKRATKA_KATEDRY%TYPE, p_idRole UZIVATEL.ROLE_ID_ROLE%TYPE)
IS
BEGIN
	update UZIVATEL set JMENO = p_jmeno, PRIJMENI = p_prijmeni, TITUL_PRED = p_titulPred, TITUL_ZA = p_titulZa,
    EMAIL = p_email, MOBIL = p_mobil, TELEFON = p_telefon, KATEDRA_ZKRATKA_KATEDRY = p_zkratkaKatedry, ROLE_ID_ROLE = p_idRole
    where ID_UZIVATELE = p_id;
END;
/
create or replace PROCEDURE smazUzivatele
  (p_id UZIVATEL.ID_UZIVATELE%TYPE)
IS
BEGIN
	delete from UZIVATEL
    where ID_UZIVATELE = p_id;
END;
/

-- Ucet
create or replace PROCEDURE vlozUcet
  (p_idUzivatele UCET.UZIVATEL_ID_UZIVATELE%TYPE, p_nick UCET.NICK%TYPE, p_heslo UCET.HESLO%TYPE, p_idRole ROLE.ID_ROLE%TYPE)
IS
v_idUcet UCET.ID_UCTU%TYPE;
v_idRole ROLE.ID_ROLE%TYPE;
BEGIN
    SELECT ROLE_ID_ROLE INTO v_idRole FROM uzivatel WHERE id_uzivatele = p_idUzivatele;
    IF (NOT(v_idRole = 3)) THEN
    raise_application_error(-20001, 'Uživatel už účet má!');
    END IF;
    SELECT ID_UCTU INTO v_idUcet FROM UCET WHERE uzivatel_id_uzivatele = p_idUzivatele;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            IF (jeJmenoUnikatni(p_nick)) THEN
                INSERT INTO UCET (NICK, HESLO, UZIVATEL_ID_UZIVATELE)
                VALUES (p_nick, p_heslo, p_idUzivatele);
    
                UPDATE UZIVATEL SET ROLE_ID_ROLE = p_idRole WHERE ID_UZIVATELE = p_idUzivatele;
            ELSE
            raise_application_error(-20002, 'Uživatel s tímto jménem již existuje!');
            END IF;
END;
/
create or replace PROCEDURE upravUcet
  (p_idUzivatele UCET.UZIVATEL_ID_UZIVATELE%TYPE,p_nick UCET.NICK%TYPE, p_heslo UCET.HESLO%TYPE, p_idRole ROLE.ID_ROLE%TYPE)
IS
v_nickUctu UCET.NICK%TYPE;
BEGIN
    select nick into v_nickUctu from ucet where UZIVATEL_ID_UZIVATELE = p_idUzivatele;
    IF (jeJmenoUnikatni(p_nick) OR v_nickUctu = p_nick) THEN
        UPDATE UCET SET NICK = p_nick, HESLO = p_heslo
        WHERE uzivatel_id_uzivatele = p_idUzivatele;
    
        UPDATE UZIVATEL SET ROLE_ID_ROLE = p_idRole WHERE ID_UZIVATELE = p_idUzivatele;
    ELSE
        raise_application_error(-20001, 'Uživatel s tímto jménem již existuje!');
    END IF;
END;
/
create or replace PROCEDURE odeberUcet
  (p_idUzivatele UCET.UZIVATEL_ID_UZIVATELE%TYPE)
IS
v_idUctu UCET.ID_UCTU%TYPE;
BEGIN
    SELECT ID_UCTU INTO v_idUctu FROM ucet WHERE uzivatel_id_uzivatele = p_idUzivatele;
	UPDATE UZIVATEL SET ROLE_ID_ROLE = 3 WHERE id_uzivatele = p_idUzivatele;
    DELETE FROM UCET WHERE id_uctu = v_idUctu;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            raise_application_error(-20001, 'Tento uživatel nemá účet!');
END;
/

-- Obrazek
create or replace PROCEDURE vlozObrazek
  (p_idUzivatele OBRAZEK.uzivatel_id_uzivatele%TYPE,p_nazev OBRAZEK.NAZEV%TYPE, p_pripona OBRAZEK.PRIPONA%TYPE, p_obsah OBRAZEK.OBSAH%TYPE)
IS
BEGIN
    INSERT INTO 
    OBRAZEK (NAZEV, PRIPONA, OBSAH, VYTVORENO, MODIFIKACE, UZIVATEL_ID_UZIVATELE)
    VALUES (p_nazev, p_pripona, p_obsah, SYSDATE, SYSDATE, p_idUzivatele);     
        
    exception
        when DUP_VAL_ON_INDEX THEN
            update OBRAZEK
            set nazev = p_nazev, pripona = p_pripona, obsah = p_obsah, modifikace = SYSDATE
            where uzivatel_id_uzivatele = p_idUzivatele;
END;
/
create or replace PROCEDURE smazObrazek
  (p_idUzivatele OBRAZEK.uzivatel_id_uzivatele%TYPE)
IS
BEGIN
    DELETE FROM
    OBRAZEK WHERE uzivatel_id_uzivatele = p_idUzivatele;
	
	EXCEPTION
        WHEN NO_DATA_FOUND THEN
            raise_application_error(-20001, 'Uživatel nemá obrázek!');
END;
/

-- Ucebna
create or replace PROCEDURE vlozUcebnu
  (p_nazev UCEBNA.NAZEV_UCEBNY%TYPE, p_kapacita UCEBNA.KAPACITA%TYPE)
IS
BEGIN
    INSERT INTO 
    UCEBNA (NAZEV_UCEBNY, KAPACITA)
    VALUES (p_nazev, p_kapacita);     
END;
/
create or replace PROCEDURE upravUcebnu
  (p_id UCEBNA.ID_UCEBNY%TYPE, p_nazev UCEBNA.NAZEV_UCEBNY%TYPE, p_kapacita UCEBNA.KAPACITA%TYPE)
IS
BEGIN
	update UCEBNA set nazev_ucebny = p_nazev, kapacita = p_kapacita
    where ID_UCEBNY = p_id;
END;
/
create or replace PROCEDURE smazUcebnu
  (p_id UCEBNA.ID_UCEBNY%TYPE)
IS
BEGIN
    DELETE FROM
    UCEBNA WHERE id_ucebny = p_id;
	
	EXCEPTION
        WHEN NO_DATA_FOUND THEN
            raise_application_error(-20001, 'Učebna neexistuje!');
END;
/