-- Funkce pro získání ID uživatele z přihlašovacích údajů
create or replace FUNCTION vratIdUzivatele (p_nick UCET.NICK%TYPE, p_heslo UCET.HESLO%TYPE)
RETURN UCET.UZIVATEL_ID_UZIVATELE%TYPE IS 
    v_id_uziv       UCET.UZIVATEL_ID_UZIVATELE%TYPE;
BEGIN 
    SELECT UZIVATEL_ID_UZIVATELE INTO v_id_uziv
    FROM UCET
    WHERE NICK = p_nick AND HESLO = p_heslo;

    RETURN v_id_uziv;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            raise_application_error(-20001, 'Neexistující jméno nebo heslo.');
END;