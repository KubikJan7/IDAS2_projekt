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