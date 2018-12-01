package data;

/**
 *
 * @author Libor Selecky
 */
public class Kategorie {

    private String zkratka;
    private String nazev;

    public Kategorie(String zkratka, String nazev) {
        this.zkratka = zkratka;
        this.nazev = nazev;
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

    @Override
    public String toString() {
        return nazev;
    }

}
