/*Uživatelé*/
create or replace view uzivatel_view as select id_uzivatele, jmeno, prijmeni,titul_pred,titul_za,email,mobil,telefon,
id_role,nazev_role,
zkratka_katedry,nazev_katedry,zkratka_fakulty,nazev_fakulty,
id_uctu,nick,heslo,
id_souboru
from uzivatel
join role on id_role = role_id_role
join katedra on katedra_zkratka_katedry = zkratka_katedry
join fakulta fak on fakulta_zkratka_fakulty = fak.zkratka_fakulty
left join ucet uc on id_uzivatele = uc.uzivatel_id_uzivatele
left join obrazek obr on id_uzivatele = obr.uzivatel_id_uzivatele;

/*Pracoviště*/
CREATE VIEW pracoviste_view AS SELECT zkratka_fakulty,nazev_fakulty,zkratka_katedry,nazev_katedry
FROM fakulta fak JOIN katedra kat ON fak.zkratka_fakulty=kat.fakulta_zkratka_fakulty;

/*Studijní obory*/
CREATE VIEW stud_obory_view AS SELECT id_oboru, zkratka, nazev, info, id_formy, nazev_formy
FROM forma_vyuky f JOIN stud_obor s ON f.id_formy = s.forma_vyuky_id_formy;