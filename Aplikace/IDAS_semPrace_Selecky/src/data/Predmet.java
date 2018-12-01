package data;

/**
 *
 * @author Libor Selecky
 */
public class Predmet {

    private int id;
    private String zkratka;
    private String nazev;
    private int kredity;
    private Semestr semestr;
    private Zakonceni zakonceni;
    private Forma forma;

    public Predmet(String zkratka, String nazev, int kredity, Semestr semestr, Zakonceni zakonceni, Forma forma) {
        this.zkratka = zkratka;
        this.nazev = nazev;
        this.kredity = kredity;
        this.semestr = semestr;
        this.zakonceni = zakonceni;
        this.forma = forma;
    }

    public Predmet(int id, String zkratka, String nazev, int kredity, Semestr semestr, Zakonceni zakonceni, Forma forma) {
        this.id = id;
        this.zkratka = zkratka;
        this.nazev = nazev;
        this.kredity = kredity;
        this.semestr = semestr;
        this.zakonceni = zakonceni;
        this.forma = forma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getKredity() {
        return kredity;
    }

    public void setKredity(int kredity) {
        this.kredity = kredity;
    }

    public Semestr getSemestr() {
        return semestr;
    }

    public void setSemestr(Semestr semestr) {
        this.semestr = semestr;
    }

    public Zakonceni getZakonceni() {
        return zakonceni;
    }

    public void setZakonceni(Zakonceni zakonceni) {
        this.zakonceni = zakonceni;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
