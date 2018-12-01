package data;

/**
 *
 * @author Libor Selecky
 */
public class PredOboru {

    private int id;
    private int rocnik;
    private Kategorie kategorie;
    private Predmet predmet;
    private int odhad;

    public PredOboru(int rocnik, Kategorie kategorie, Predmet predmet) {
        this.rocnik = rocnik;
        this.kategorie = kategorie;
        this.predmet = predmet;
    }

    public PredOboru(int id, int rocnik, Kategorie kategorie, Predmet predmet, int odhad) {
        this.id = id;
        this.rocnik = rocnik;
        this.kategorie = kategorie;
        this.predmet = predmet;
        this.odhad = odhad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRocnik() {
        return rocnik;
    }

    public void setRocnik(int rocnik) {
        this.rocnik = rocnik;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public int getOdhad() {
        return odhad;
    }

    public void setOdhad(int odhad) {
        this.odhad = odhad;
    }

}
