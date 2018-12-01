package data;

/**
 *
 * @author Libor Selecky
 */
public class Pracoviste2 {

    private String zkratkaFak;
    private String fakulta;
    private String zkratkaKat;
    private String katedra;

    public Pracoviste2(String zkratkaFak, String fakulta, String zkratkaKat, String katedra) {
        this.zkratkaFak = zkratkaFak;
        this.fakulta = fakulta;
        this.zkratkaKat = zkratkaKat;
        this.katedra = katedra;
    }

    public String getZkratkaFak() {
        return zkratkaFak;
    }

    public void setZkratkaFak(String zkratkaFak) {
        this.zkratkaFak = zkratkaFak;
    }

    public String getFakulta() {
        return fakulta;
    }

    public void setFakulta(String fakulta) {
        this.fakulta = fakulta;
    }

    public String getZkratkaKat() {
        return zkratkaKat;
    }

    public void setZkratkaKat(String zkratkaKat) {
        this.zkratkaKat = zkratkaKat;
    }

    public String getKatedra() {
        return katedra;
    }

    public void setKatedra(String katedra) {
        this.katedra = katedra;
    }
    
}
