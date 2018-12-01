package data;

/**
 *
 * @author Libor Selecky
 */
public class Akce {

    private int id;
    private int rozsah;
    private int kapacita;
    private Vyucujici vyucujici;
    private Predmet predmet;
    private Zpusob zpusob;

    public Akce(int rozsah, int kapacita, Vyucujici vyucujici, Predmet predmet, Zpusob zpusob) {
        this.rozsah = rozsah;
        this.kapacita = kapacita;
        this.vyucujici = vyucujici;
        this.predmet = predmet;
        this.zpusob = zpusob;
    }

    public Akce(int id, int rozsah, int kapacita, Vyucujici vyucujici, Predmet predmet, Zpusob zpusob) {
        this.id = id;
        this.rozsah = rozsah;
        this.kapacita = kapacita;
        this.vyucujici = vyucujici;
        this.predmet = predmet;
        this.zpusob = zpusob;
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

    public Vyucujici getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(Vyucujici vyucujici) {
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
