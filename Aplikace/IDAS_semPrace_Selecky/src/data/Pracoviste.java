package data;

/**
 *
 * @author Libor Selecky
 */
public class Pracoviste {

    private Fakulta fakulta;
    private Katedra katedra;

    public Pracoviste(Fakulta fakulta, Katedra katedra) {
        this.fakulta = fakulta;
        this.katedra = katedra;
    }

    public Fakulta getFakulta() {
        return fakulta;
    }

    public void setFakulta(Fakulta fakulta) {
        this.fakulta = fakulta;
    }

    public Katedra getKatedra() {
        return katedra;
    }

    public void setKatedra(Katedra katedra) {
        this.katedra = katedra;
    }

    @Override
    public String toString() {
        return fakulta.getZkratka() + "-   " + katedra.getZkratka();
    }

}
