package data;

/**
 *
 * @author Libor Selecky
 */
public class Plan {
    private int id;
    private int verze;
    private Obor obor;
    
    public Plan(int verze, Obor obor) {
        this.verze = verze;
        this.obor = obor;
    }

    public Plan(int id, int verze, Obor obor) {
        this.id = id;
        this.verze = verze;
        this.obor = obor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVerze() {
        return verze;
    }

    public void setVerze(int verze) {
        this.verze = verze;
    }

    public Obor getObor() {
        return obor;
    }

    public void setObor(Obor obor) {
        this.obor = obor;
    }

    @Override
    public String toString() {
        return "Plan{" + "verze=" + verze + '}';
    }
}

