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

/*Rozvrhove akce*/
create or replace view akce_view as select id_akce as , datum, cas_od as hodina, rozsah_hodin as doba, 
rozvrhova_akce.kapacita as mist, uzivatel_id_uzivatele as id_uzivatel, titul_pred as titul, jmeno, prijmeni ,
predmet_id_predmetu as id_predmet, predmet.NAZEV as predmet,
zpusob_vyuky_id_nazvu as id_zpusobu, zpusob_vyuky.nazev as zpusob,
ucebna_id_ucebny, nazev_ucebny as ucebna,
id_tydne, tyden.nazev as tyden
from rozvrhova_akce
join uzivatel on id_uzivatele = uzivatel_id_uzivatele
join predmet on id_predmetu = predmet_id_predmetu
join zpusob_vyuky on id_nazvu = zpusob_vyuky_id_nazvu
left join ucebna on id_ucebny = ucebna_id_ucebny
left join tyden on id_tydne = tyden_id_tydne;

/*Studijní plány*/
create or replace view plany_view as
select id_planu,verze,
id_oboru,nazev,zkratka,info,
id_formy,nazev_formy
from stud_plan
join stud_obor on stud_obor_id_oboru = id_oboru
join forma_vyuky on id_formy = forma_vyuky_id_formy;