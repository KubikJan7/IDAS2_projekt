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

-- Funkce ověření unikátnosti uživatelského jména
create or replace FUNCTION jeJmenoUnikatni (p_nick UCET.NICK%TYPE)
RETURN BOOLEAN IS 
   v_jeUnikatni     BOOLEAN := FALSE;
   v_nick           UCET.NICK%TYPE;
BEGIN 
    SELECT NICK INTO v_nick
    FROM UCET
    WHERE NICK = p_nick;
    RETURN v_jeUnikatni;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            v_jeUnikatni := TRUE;
            RETURN v_jeUnikatni;
END;