package data;

/**
 *
 * @author Libor Selecky
 */
public class Obor {

    private int id;
    private String zkratka;
    private String nazev;
    private String info;
    private Forma forma;

    public Obor(String zkratka, String nazev, String info, Forma forma) {
        this.zkratka = zkratka;
        this.nazev = nazev;
        this.info = info;
        this.forma = forma;
    }

    public Obor(int id, String zkratka, String nazev, String info, Forma forma) {
        this.id = id;
        this.zkratka = zkratka;
        this.nazev = nazev;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }
    
}
