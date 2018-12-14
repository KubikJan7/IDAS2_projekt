package data;

/**
 *
 * @author Libor Selecky
 */
public class Role {

    private int id;
    private String nazev;

    public Role(int id, String nazev) {
        this.id = id;
        this.nazev = nazev;
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

    @Override
    public String toString() {
        return nazev;
    }
}
