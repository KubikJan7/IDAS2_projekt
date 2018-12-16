package data;

import java.io.InputStream;
import javafx.scene.image.Image;

/**
 *
 * @author Libor Selecky
 */
public class Obrazek {

    private int id;
    private String nazev;
    private String pripona;
    private InputStream obsah;
    private String vytvoreno;
    private String modifikace;

    public Obrazek(String nazev, String pripona, InputStream obsah) {
        this.nazev = nazev;
        this.pripona = pripona;
        this.obsah = obsah;
    }

    public Obrazek(int id, String nazev, String pripona, InputStream obsah, String vytvoreno, String modifikace) {
        this.id = id;
        this.nazev = nazev;
        this.pripona = pripona;
        this.obsah = obsah;
        this.vytvoreno = vytvoreno;
        this.modifikace = modifikace;
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

    public String getPripona() {
        return pripona;
    }

    public void setPripona(String pripona) {
        this.pripona = pripona;
    }

    public InputStream getObsah() {
        return obsah;
    }

    public void setObsah(InputStream obsah) {
        this.obsah = obsah;
    }

    public String getVytvoreno() {
        return vytvoreno;
    }

    public void setVytvoreno(String vytvoreno) {
        this.vytvoreno = vytvoreno;
    }

    public String getModifikace() {
        return modifikace;
    }

    public void setModifikace(String modifikace) {
        this.modifikace = modifikace;
    }

}
