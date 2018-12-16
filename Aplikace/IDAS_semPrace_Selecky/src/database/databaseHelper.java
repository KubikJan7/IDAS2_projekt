package database;

import data.Akce;
import data.Fakulta;
import data.Forma;
import data.Katedra;
import data.Kategorie;
import data.Obor;
import data.Obrazek;
import data.Pracoviste;
import data.Pracoviste2;
import data.PredOboru;
import data.Predmet;
import data.Role;
import data.Semestr;
import data.Ucebna;
import data.Ucet;
import data.Uzivatel;
import data.Zakonceni;
import data.Zpusob;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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

    public Uzivatel dejPrihlasenehoUzivatele(String nick, String heslo) throws SQLException {
        Uzivatel uzivatel = null;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from uzivatel_view\n"
                + "where id_uzivatele = vratIdUzivatele(?,?)");
        stmt.setString(1, nick);
        stmt.setString(2, heslo);
        ResultSet rset = stmt.executeQuery();
        rset.next();
        int id = rset.getInt("id_uzivatele");
        String jmeno = rset.getString("jmeno");
        String prijmeni = rset.getString("prijmeni");
        String titulPred = rset.getString("titul_pred");
        String titulZa = rset.getString("titul_za");
        String email = rset.getString("email");
        String mobil = rset.getString("mobil");
        String telefon = rset.getString("telefon");
        int id_role = rset.getInt("id_role");
        String nazev_role = rset.getString("nazev_role");
        String zkratka_katedry = rset.getString("zkratka_katedry");
        String nazev_katedry = rset.getString("nazev_katedry");
        String zkratka_fakulty = rset.getString("zkratka_fakulty");
        String nazev_fakulty = rset.getString("nazev_fakulty");
        int id_uctu = rset.getInt("id_uctu");
        String user = rset.getString("nick");
        String pass = rset.getString("heslo");
        int id_souboru = rset.getInt("id_souboru");
        uzivatel = new Uzivatel(id, titulPred, jmeno, prijmeni, titulZa, email, mobil, telefon,
                new Ucet(id_uctu, user, pass),
                new Role(id_role, nazev_role),
                id_souboru,
                new Pracoviste(new Fakulta(zkratka_fakulty, nazev_fakulty),
                        new Katedra(zkratka_katedry, nazev_katedry)));
        return uzivatel;
    }

    public Obrazek dejObrazek(int id) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM OBRAZEK WHERE uzivatel_id_uzivatele =" + id);
        ResultSet rset = stmt.executeQuery();
        rset.next();
        int idSoub = rset.getInt("id_souboru");
        String nazev = rset.getString("nazev");
        String pripona = rset.getString("pripona");
        Date vytvoreno = rset.getDate("vytvoreno");
        Date modif = rset.getDate("modifikace");
        InputStream is = rset.getBinaryStream("obsah");
        Obrazek obr = new Obrazek(idSoub, nazev, pripona, is, vytvoreno.toString(), modif.toString());
        return obr;
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

    public ArrayList<Uzivatel> dejKartaVyucujici() throws SQLException {
        ArrayList<Uzivatel> vyucujici = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM uzivatel_view");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            int id = rset.getInt("id_uzivatele");
            String jmeno = rset.getString("jmeno");
            String prijmeni = rset.getString("prijmeni");
            String titulPred = rset.getString("titul_pred");
            String titulZa = rset.getString("titul_za");
            String email = rset.getString("email");
            String mobil = rset.getString("mobil");
            String telefon = rset.getString("telefon");
            int id_role = rset.getInt("id_role");
            String nazev_role = rset.getString("nazev_role");
            String zkratka_katedry = rset.getString("zkratka_katedry");
            String nazev_katedry = rset.getString("nazev_katedry");
            String zkratka_fakulty = rset.getString("zkratka_fakulty");
            String nazev_fakulty = rset.getString("nazev_fakulty");
            int id_uctu = rset.getInt("id_uctu");
            String user = rset.getString("nick");
            String pass = rset.getString("heslo");
            int id_souboru = rset.getInt("id_souboru");
            vyucujici.add(new Uzivatel(id, titulPred, jmeno, prijmeni, titulZa, email, mobil, telefon,
                    new Ucet(id_uctu, user, pass),
                    new Role(id_role, nazev_role),
                    id_souboru,
                    new Pracoviste(new Fakulta(zkratka_fakulty, nazev_fakulty),
                            new Katedra(zkratka_katedry, nazev_katedry))));
        }
        return vyucujici;
    }

    public ArrayList<Role> dejRole() throws SQLException {
        ArrayList<Role> role = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ROLE");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            int id = rset.getInt("id_role");
            String nazev = rset.getString("nazev_role");
            role.add(new Role(id, nazev));
        }
        return role;
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
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM stud_obory_view");
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            int id = rset.getInt("id_oboru");
            String zkr = rset.getString("zkratka");
            String naz = rset.getString("nazev");
            String info = rset.getString("info");
            int idFor = rset.getInt("id_formy");
            String nazFor = rset.getString("nazev_formy");
            obory.add(new Obor(id, zkr, naz, info,
                    new Forma(idFor, nazFor)));
        }
        return obory;
    }

    public ArrayList<Predmet> dejKartaPredmety() throws SQLException {
        ArrayList<Predmet> predmety = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM predmet");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            predmety.add(new Predmet(rset.getInt("id_predmetu"),
                    rset.getString("zkratka"),
                    rset.getString("nazev")));
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
// TODO Upravit Akce dle noveho modelu
//        Connection conn = OracleConnector.getConnection();
//        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM akce_view");
//        ResultSet rset = stmt.executeQuery();
//        Uzivatel vyucujici = null;
//        Predmet predmet = null;
//        Zpusob zpusob = null;
//        ArrayList<Uzivatel> listVyuc = dejKartaVyucujici();
//        ArrayList<Predmet> listPred = dejKartaPredmety();
//        ArrayList<Zpusob> listZpus = dejZpusobVyuky();
//        while (rset.next()) {
//            Iterator<Uzivatel> vyuc = listVyuc.iterator();
//            while (vyuc.hasNext()) {
//                Uzivatel akt = vyuc.next();
//                if (akt.getId() == rset.getInt("id_vyucujiciho")) {
//                    vyucujici = akt;
//                    break;
//                }
//            }
//            Iterator<Predmet> predmety = listPred.iterator();
//            while (predmety.hasNext()) {
//                Predmet akt = predmety.next();
//                if (akt.getId() == rset.getInt("id_predmetu")) {
//                    predmet = akt;
//                    break;
//                }
//            }
//            Iterator<Zpusob> zpusoby = listZpus.iterator();
//            while (zpusoby.hasNext()) {
//                Zpusob akt = zpusoby.next();
//                if (akt.getId() == rset.getInt("id_zpusobu")) {
//                    zpusob = akt;
//                    break;
//                }
//            }
//            akce.add(new Akce(rset.getInt("id_akce"), rset.getInt("rozsah_hodin"), rset.getInt("kapacita"), vyucujici, predmet, zpusob));
//        }
        return akce;
    }

    public ArrayList<Ucebna> dejKartaUcebny() throws SQLException {
        ArrayList<Ucebna> ucebny = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ucebna");
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            int id = rset.getInt("id_ucebny");
            String nazev = rset.getString("nazev_ucebny");
            int kapacita = rset.getInt("kapacita");
            ucebny.add(new Ucebna(id, nazev, kapacita));
        }
        return ucebny;
    }

    //Insert vyučujícího
    public void insertDataVyuc(Uzivatel vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call vlozUzivatele(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        stmt.setString(1, vyuc.getJmeno());
        stmt.setString(2, vyuc.getPrijmeni());
        stmt.setString(3, vyuc.getTitulPred());
        stmt.setString(4, vyuc.getTitulZa());
        stmt.setString(5, vyuc.getEmail());
        if (vyuc.getMobil() == null) {
            stmt.setNull(6, java.sql.Types.INTEGER);
        } else {
            stmt.setString(6, vyuc.getMobil());
        }
        if (vyuc.getTelefon() == null) {
            stmt.setNull(7, java.sql.Types.INTEGER);
        } else {
            stmt.setString(7, vyuc.getTelefon());
        }
        stmt.setString(8, vyuc.getPracoviste().getKatedra().getZkratka());
        stmt.setInt(9, vyuc.getRole().getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert uctu vyucujiciho
    public void insertDataUcet(Uzivatel uziv) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call vlozUcet(?, ?, ?, ?)}");
        stmt.setInt(1, uziv.getId());
        stmt.setString(2, uziv.getUcet().getNickname());
        stmt.setString(3, uziv.getUcet().getHeslo());
        stmt.setInt(4, uziv.getRole().getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert obrazku uzivatele
    public void insertDataObrazek(int idUziv, Obrazek obr) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call vlozObrazek(?, ?, ?, ?)}");
        stmt.setInt(1, idUziv);
        stmt.setString(2, obr.getNazev());
        stmt.setString(3, obr.getPripona());
        stmt.setBinaryStream(4, obr.getObsah());
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
        PreparedStatement stmt = conn.prepareStatement("{call vlozObor(?, ?, ?, ?)}");
        stmt.setString(1, obor.getZkratka());
        stmt.setString(2, obor.getNazev());
        stmt.setInt(3, obor.getForma().getId());
        stmt.setString(4, obor.getInfo());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert předmětu
    public void insertDataPredmet(Predmet pred) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("{call vlozPredmet(?,?)}");
        stmt.setString(1, pred.getNazev());
        stmt.setString(2, pred.getZkratka());
        stmt.executeUpdate();
        conn.commit();
    }

    //Insert ucebny
    public void insertDataUcebna(Ucebna uceb) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call vlozUcebnu(?, ?)}");
        stmt.setString(1, uceb.getNazev());
        stmt.setInt(2, uceb.getKapacita());
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
    public void updateDataVyuc(Uzivatel vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call upravUzivatele(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        stmt.setInt(1, vyuc.getId());
        stmt.setString(2, vyuc.getJmeno());
        stmt.setString(3, vyuc.getPrijmeni());
        stmt.setString(4, vyuc.getTitulPred());
        stmt.setString(5, vyuc.getTitulZa());
        stmt.setString(6, vyuc.getEmail());
        if (vyuc.getMobil() == null) {
            stmt.setNull(7, java.sql.Types.INTEGER);
        } else {
            stmt.setString(7, vyuc.getMobil());
        }
        if (vyuc.getTelefon() == null) {
            stmt.setNull(8, java.sql.Types.INTEGER);
        } else {
            stmt.setString(8, vyuc.getTelefon());
        }
        stmt.setString(9, vyuc.getPracoviste().getKatedra().getZkratka());
        stmt.setInt(10, vyuc.getRole().getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update uctu
    public void updateDataUcet(Uzivatel vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call upravUcet(?, ?, ?, ?)}");
        stmt.setInt(1, vyuc.getId());
        stmt.setString(2, vyuc.getUcet().getNickname());
        stmt.setString(3, vyuc.getUcet().getHeslo());
        stmt.setInt(4, vyuc.getRole().getId());
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
        PreparedStatement stmt = conn.prepareStatement("{call upravObor(?,?,?,?,?)}");
        stmt.setInt(1, obor.getId());
        stmt.setString(2, obor.getZkratka());
        stmt.setString(3, obor.getNazev());
        stmt.setInt(4, obor.getForma().getId());
        stmt.setString(5, obor.getInfo());

        stmt.executeUpdate();
        conn.commit();
    }

    //Update ucebny
    public void updateDataUcebna(Ucebna uceb) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareCall("{call upravUcebnu(?,?,?)}");
        stmt.setInt(1, uceb.getId());
        stmt.setString(2, uceb.getNazev());
        stmt.setInt(3, uceb.getKapacita());
        stmt.executeUpdate();
        conn.commit();
    }

    //Update předmětu
    public void updateDataPredmet(Predmet pred) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("{call upravPredmet(?,?,?)}");
        stmt.setInt(1, pred.getId());
        stmt.setString(2, pred.getNazev());
        stmt.setString(3, pred.getZkratka());
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
    public void deleteDataVyuc(Uzivatel vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareCall("{call smazUzivatele(?)}");
        stmt.setInt(1, vyuc.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete vyučujícího
    public void deleteDataUcebny(Ucebna uceb) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareCall("{call smazUcebnu(?)}");
        stmt.setInt(1, uceb.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete uctu uzivatele
    public void deleteDataUcet(Uzivatel vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareCall("{call odeberUcet(?)}");
        stmt.setInt(1, vyuc.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete obrazku uzivatele
    public void deleteDataObrazek(Uzivatel vyuc) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareCall("{call smazObrazek(?)}");
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
        PreparedStatement stmt = conn.prepareStatement("{call smazObor(?)}");
        stmt.setInt(1, obor.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    //Delete předmětu
    public void deleteDataPredmet(Predmet pred) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("{call smazPredmet(?)}");
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
