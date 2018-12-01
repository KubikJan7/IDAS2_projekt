package database;

import data.Akce;
import data.Fakulta;
import data.Forma;
import data.Katedra;
import data.Kategorie;
import data.Obor;
import data.Pracoviste;
import data.Pracoviste2;
import data.PredOboru;
import data.Predmet;
import data.Semestr;
import data.Vyucujici;
import data.Zakonceni;
import data.Zpusob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class databaseHelper {

    public databaseHelper(String login, String pswd) throws SQLException {
        myInit(login, pswd);
    }

    public static void myInit(String login, String pswd) throws SQLException {
        OracleConnector.setUpConnection("fei-sql1.upceucebny.cz", 1521, "IDAS12", login, pswd);
    }

    public ArrayList<Fakulta> dejFakulty() throws SQLException {
        ArrayList<Fakulta> fakulty = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FAKULTA");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            fakulty.add(new Fakulta(rset.getString("zkratka_fakulty"), rset.getString("nazev_fakulty")));
        }
        return fakulty;
    }

    public ArrayList<Katedra> dejKatedry() throws SQLException {
        ArrayList<Katedra> katedry = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM katedra");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            katedry.add(new Katedra(rset.getString("zkratka_katedry"), rset.getString("nazev_katedry")));
        }
        return katedry;
    }

    public ArrayList<Zakonceni> dejZpusobyZakon() throws SQLException {
        ArrayList<Zakonceni> zakonceni = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM zpusob_zakonceni");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            zakonceni.add(new Zakonceni(rset.getString("zkr_zak"), rset.getString("nazev")));
        }
        return zakonceni;
    }

    public ArrayList<Semestr> dejSemestry() throws SQLException {
        ArrayList<Semestr> semestry = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM semestr");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            semestry.add(new Semestr(rset.getString("zkr_sem"), rset.getString("nazev")));
        }
        return semestry;
    }

    public ArrayList<Forma> dejFormyVyuky() throws SQLException {
        ArrayList<Forma> formy = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM forma_vyuky");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            formy.add(new Forma(rset.getInt("id_formy"), rset.getString("nazev_formy")));
        }
        return formy;
    }

    public ArrayList<Zpusob> dejZpusobVyuky() throws SQLException {
        ArrayList<Zpusob> zpusoby = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM zpusob_vyuky");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            zpusoby.add(new Zpusob(rset.getInt("id_nazvu"), rset.getString("nazev")));
        }
        return zpusoby;
    }

    public ArrayList<Kategorie> dejKategorie() throws SQLException {
        ArrayList<Kategorie> kategorie = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM kat_predmetu");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            kategorie.add(new Kategorie(rset.getString("zkr_kat"), rset.getString("nazev")));
        }
        return kategorie;
    }

    public ArrayList<Vyucujici> dejKartaVyucujici() throws SQLException {
        ArrayList<Vyucujici> vyucujici = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vyucujici_view");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            vyucujici.add(new Vyucujici(rset.getInt("id_vyucujiciho"),
                    rset.getString("titul_pred"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("titul_za"),
                    rset.getString("email"),
                    rset.getInt("mobil"),
                    rset.getInt("telefon"),
                    new Pracoviste(new Fakulta(rset.getString("fakulta"), rset.getString("nazev_fakulty")),
                            new Katedra(rset.getString("katedra"), rset.getString("nazev_katedry")))));
        }
        return vyucujici;
    }

    public ArrayList<Pracoviste2> dejKartaPracoviste() throws SQLException {
        ArrayList<Pracoviste2> pracoviste = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pracoviste_view");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            pracoviste.add(new Pracoviste2(rset.getString("zkratka_fakulty"),
                    rset.getString("nazev_fakulty"),
                    rset.getString("zkratka_katedry"),
                    rset.getString("nazev_katedry")));
        }
        return pracoviste;
    }

    public ArrayList<Obor> dejKartaObory() throws SQLException {
        ArrayList<Obor> obory = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM stud_obor");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            obory.add(new Obor(rset.getInt("id_oboru"),
                    rset.getString("zkratka"),
                    rset.getString("nazev"),
                    rset.getString("info")));
        }
        return obory;
    }

    public ArrayList<Predmet> dejKartaPredmety() throws SQLException {
        ArrayList<Predmet> predmety = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM predmety_view");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            predmety.add(new Predmet(rset.getInt("id_predmetu"),
                    rset.getString("zkratka"),
                    rset.getString("nazev"),
                    rset.getInt("kredity"),
                    new Semestr(rset.getString("zkr_sem"), rset.getString("semestr")),
                    new Zakonceni(rset.getString("zkr_zakonceni"), rset.getString("zakonceni")),
                    new Forma(rset.getInt("id_forma"), rset.getString("forma"))));
        }
        return predmety;
    }

    public ArrayList<PredOboru> dejKartaPredOboru(int idOboru) throws SQLException {
        ArrayList<PredOboru> predOb = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM predmety_oboru_view where id_oboru = " + idOboru);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Iterator<Predmet> predmety = dejKartaPredmety().iterator();
            int idPredmetu = rset.getInt("predob_id_predmet");
            Predmet predmet = null;
            while (predmety.hasNext()) {
                predmet = predmety.next();
                if (predmet.getId() == idPredmetu) {
                    break;
                }
            }
            predOb.add(new PredOboru(rset.getInt("id_pred_oboru"),
                    rset.getInt("dop_rocnik"),
                    new Kategorie(rset.getString("zkr_kat"), rset.getString("kategorie")),
                    predmet, rset.getInt("odhad")));
        }
        return predOb;
    }

    public ArrayList<Akce> dejKartaAkce() throws SQLException {
        ArrayList<Akce> akce = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM akce_view");
        ResultSet rset = stmt.executeQuery();
        Vyucujici vyucujici = null;
        Predmet predmet = null;
        Zpusob zpusob = null;
        ArrayList<Vyucujici> listVyuc = dejKartaVyucujici();
        ArrayList<Predmet> listPred = dejKartaPredmety();
        ArrayList<Zpusob> listZpus = dejZpusobVyuky();
        while (rset.next()) {
            Iterator<Vyucujici> vyuc = listVyuc.iterator();
            while (vyuc.hasNext()) {
                Vyucujici akt = vyuc.next();
                if (akt.getId() == rset.getInt("id_vyucujiciho")) {
                    vyucujici = akt;
                    break;
                }
            }
            Iterator<Predmet> predmety = listPred.iterator();
            while (predmety.hasNext()) {
                Predmet akt = predmety.next();
                if (akt.getId() == rset.getInt("id_predmetu")) {
                    predmet = akt;
                    break;
                }
            }
            Iterator<Zpusob> zpusoby = listZpus.iterator();
            while (zpusoby.hasNext()) {
                Zpusob akt = zpusoby.next();
                if (akt.getId() == rset.getInt("id_zpusobu")) {
                    zpusob = akt;
                    break;
                }
            }
            akce.add(new Akce(rset.getInt("id_akce"), rset.getInt("rozsah_hodin"), rset.getInt("kapacita"), vyucujici, predmet, zpusob));
        }
        return akce;
    }

    //Insert vyučujícího
    public void insertDataVyuc(Vyucujici vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO vyucujici(JMENO, PRIJMENI, TITUL_PRED, TITUL_ZA, KATEDRA_ZKRATKA_KATEDRY, EMAIL, TELEFON, MOBIL) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, vyuc.getJmeno());
        stmt.setString(2, vyuc.getPrijmeni());
        stmt.setString(3, vyuc.getTitulPred());
        stmt.setString(4, vyuc.getTitulZa());
        stmt.setString(5, vyuc.getPracoviste().getKatedra().getZkratka());
        stmt.setString(6, vyuc.getEmail());
        stmt.setInt(7, vyuc.getTelefon());
        stmt.setInt(8, vyuc.getMobil());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert pracoviště
    public void insertDataPrac(Pracoviste2 prac) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO katedra(zkratka_katedry, nazev_katedry, fakulta_zkratka_fakulty) "
                + "VALUES (?, ?, ?)");
        stmt.setString(1, prac.getZkratkaKat());
        stmt.setString(2, prac.getKatedra());
        stmt.setString(3, prac.getZkratkaFak());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert oboru
    public void insertDataObor(Obor obor) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO stud_obor(nazev,zkratka,info) "
                + "VALUES (?, ?, ?)");
        stmt.setString(1, obor.getNazev());
        stmt.setString(2, obor.getZkratka());
        stmt.setString(3, obor.getInfo());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert předmětu
    public void insertDataPredmet(Predmet pred) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO predmet(nazev,zkratka,kredity,zpusob_zakonceni_zkr_zak,forma_vyuky_id_formy,semestr_zkr_sem) "
                + "VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setString(1, pred.getNazev());
        stmt.setString(2, pred.getZkratka());
        stmt.setInt(3, pred.getKredity());
        stmt.setString(4, pred.getZakonceni().getZkratka());
        stmt.setInt(5, pred.getForma().getId());
        stmt.setString(6, pred.getSemestr().getZkratka());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert rozvrhové akce
    public void insertDataAkce(Akce akce) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO rozvrhova_akce(rozsah_hodin,kapacita,vyucujici_id_vyucujiciho,predmet_id_predmetu,zpusob_vyuky_id_nazvu) "
                + "VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, akce.getRozsah());
        stmt.setInt(2, akce.getKapacita());
        stmt.setInt(3, akce.getVyucujici().getId());
        stmt.setInt(4, akce.getPredmet().getId());
        stmt.setInt(5, akce.getZpusob().getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert predmet oboru
    public void insertDataPredOboru(PredOboru predOb, Obor ob) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO pred_v_oboru(dop_rocnik,stud_obor_id_oboru,kat_predmetu_zkr_kat,predmet_id_predmetu) "
                + "VALUES (?, ?, ?, ?)");
        stmt.setInt(1, predOb.getRocnik());
        stmt.setInt(2, ob.getId());
        stmt.setString(3, predOb.getKategorie().getZkratka());
        stmt.setInt(4, predOb.getPredmet().getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update vyučujícího
    public void updateDataVyuc(Vyucujici vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE vyucujici SET titul_pred = ?,jmeno = ?, prijmeni = ?,"
                + " titul_za = ?, katedra_zkratka_katedry = ?, email = ?, mobil = ?, telefon = ? where id_vyucujiciho = ?");
        stmt.setString(1, vyuc.getTitulPred());
        stmt.setString(2, vyuc.getJmeno());
        stmt.setString(3, vyuc.getPrijmeni());
        stmt.setString(4, vyuc.getTitulZa());
        stmt.setString(5, vyuc.getPracoviste().getKatedra().getZkratka());
        stmt.setString(6, vyuc.getEmail());
        stmt.setInt(7, vyuc.getMobil());
        stmt.setInt(8, vyuc.getTelefon());
        stmt.setInt(9, vyuc.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update pracoviště
    public void updateDataPrac(Pracoviste2 prac) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE katedra SET nazev_katedry = ?, fakulta_zkratka_fakulty = ?"
                + "where zkratka_katedry = ?");
        stmt.setString(1, prac.getKatedra());
        stmt.setString(2, prac.getZkratkaFak());
        stmt.setString(3, prac.getZkratkaKat());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update oboru
    public void updateDataObor(Obor obor) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update stud_obor SET nazev = ?,zkratka = ?, info = ?"
                + "where id_oboru = ?");
        stmt.setString(1, obor.getNazev());
        stmt.setString(2, obor.getZkratka());
        stmt.setString(3, obor.getInfo());
        stmt.setInt(4, obor.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update předmětu
    public void updateDataPredmet(Predmet pred) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update predmet SET nazev = ?,zkratka = ?,"
                + "kredity = ?,zpusob_zakonceni_zkr_zak = ?,forma_vyuky_id_formy = ?,semestr_zkr_sem= ? "
                + "where id_predmetu = ?");
        stmt.setString(1, pred.getNazev());
        stmt.setString(2, pred.getZkratka());
        stmt.setInt(3, pred.getKredity());
        stmt.setString(4, pred.getZakonceni().getZkratka());
        stmt.setInt(5, pred.getForma().getId());
        stmt.setString(6, pred.getSemestr().getZkratka());
        stmt.setInt(7, pred.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update rozvrhové akce
    public void updateDataAkce(Akce akce) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update rozvrhova_akce SET rozsah_hodin = ?,kapacita = ?,"
                + "vyucujici_id_vyucujiciho = ?,predmet_id_predmetu = ?,zpusob_vyuky_id_nazvu = ?"
                + "where id_akce = ?");
        stmt.setInt(1, akce.getRozsah());
        stmt.setInt(2, akce.getKapacita());
        stmt.setInt(3, akce.getVyucujici().getId());
        stmt.setInt(4, akce.getPredmet().getId());
        stmt.setInt(5, akce.getZpusob().getId());
        stmt.setInt(6, akce.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update predmet oboru
    public void updateDataPredOboru(PredOboru predOb, Obor ob) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("update pred_v_oboru SET dop_rocnik = ?,stud_obor_id_oboru = ?"
                + ",kat_predmetu_zkr_kat = ?"
                + ",predmet_id_predmetu = ?"
                + "where id_pred_oboru = ?");
        stmt.setInt(1, predOb.getRocnik());
        stmt.setInt(2, ob.getId());
        stmt.setString(3, predOb.getKategorie().getZkratka());
        stmt.setInt(4, predOb.getPredmet().getId());
        stmt.setInt(5, predOb.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete vyučujícího
    public void deleteDataVyuc(Vyucujici vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM vyucujici WHERE id_vyucujiciho = ?");
        stmt.setInt(1, vyuc.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete pracoviště
    public void deleteDataPrac(Pracoviste2 prac) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM katedra WHERE zkratka_katedry = ?");
        stmt.setString(1, prac.getZkratkaKat());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete oboru
    public void deleteDataObor(Obor obor) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM stud_obor where id_oboru = ?");
        stmt.setInt(1, obor.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete předmětu
    public void deleteDataPredmet(Predmet pred) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM predmet where id_predmetu = ?");
        stmt.setInt(1, pred.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete rozvrhové akce
    public void deleteDataAkce(Akce akce) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM rozvrhova_akce where id_akce = ?");
        stmt.setInt(1, akce.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete predmetu oboru
    public void deleteDataPredOboru(PredOboru predOb) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM pred_v_oboru where id_pred_oboru = ?");
        stmt.setInt(1, predOb.getId());
        stmt.executeUpdate();
        conn.commit();
    }

}
