package data;

/**
 *
 * @author Libor Selecky
 */
public class Akce {

    private int id;
    private int rozsah;
    private int kapacita;
    private Uzivatel vyucujici;
    private Predmet predmet;
    private Zpusob zpusob;
    private Ucebna ucebna;
    private Tyden tyden;

    public Akce(int rozsah, int kapacita, Uzivatel vyucujici, Predmet predmet, Zpusob zpusob, Ucebna ucebna, Tyden tyden) {
        this.rozsah = rozsah;
        this.kapacita = kapacita;
        this.vyucujici = vyucujici;
        this.predmet = predmet;
        this.zpusob = zpusob;
        this.ucebna = ucebna;
        this.tyden = tyden;
    }

    public Akce(int id, int rozsah, int kapacita, Uzivatel vyucujici, Predmet predmet, Zpusob zpusob, Ucebna ucebna, Tyden tyden) {
        this.id = id;
        this.rozsah = rozsah;
        this.kapacita = kapacita;
        this.vyucujici = vyucujici;
        this.predmet = predmet;
        this.zpusob = zpusob;
        this.ucebna = ucebna;
        this.tyden = tyden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRozsah() {
        return rozsah;
    }

    public void setRozsah(int rozsah) {
        this.rozsah = rozsah;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public Uzivatel getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(Uzivatel vyucujici) {
        this.vyucujici = vyucujici;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Zpusob getZpusob() {
        return zpusob;
    }

    public void setZpusob(Zpusob zpusob) {
        this.zpusob = zpusob;
    }

}
