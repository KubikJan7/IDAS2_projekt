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