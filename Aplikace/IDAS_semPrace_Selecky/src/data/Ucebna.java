package data;

/**
 *
 * @author Libor Selecky
 */
public class Ucebna {

    private int id;
    private String nazev;
    private int kapacita;

    public Ucebna(String nazev, int kapacita) {
        this.nazev = nazev;
        this.kapacita = kapacita;
    }

    public Ucebna(int id, String nazev, int kapacita) {
        this.id = id;
        this.nazev = nazev;
        this.kapacita = kapacita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
