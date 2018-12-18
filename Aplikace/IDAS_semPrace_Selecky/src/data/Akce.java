package data;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author Libor Selecky
 */
public class Akce {

    private int id;
    private LocalDate datum;
    private int casOd;
    private int rozsah;
    private int kapacita;
    private Uzivatel vyucujici;
    private Predmet predmet;
    private Zpusob zpusob;
    private Ucebna ucebna;
    private Tyden tyden;
    private String denTabulka;

    public Akce(LocalDate datum, int casOd, int rozsah, int kapacita, Uzivatel vyucujici, Predmet predmet, Zpusob zpusob, Ucebna ucebna, Tyden tyden) {
        this.datum = datum;
        this.casOd = casOd;
        this.rozsah = rozsah;
        this.kapacita = kapacita;
        this.vyucujici = vyucujici;
        this.predmet = predmet;
        this.zpusob = zpusob;
        this.ucebna = ucebna;
        this.tyden = tyden;
        urciDenTabulka(datum, tyden);
    }

    public Akce(int id, LocalDate datum, int casOd, int rozsah, int kapacita, Uzivatel vyucujici, Predmet predmet, Zpusob zpusob, Ucebna ucebna, Tyden tyden) {
        this.id = id;
        this.datum = datum;
        this.casOd = casOd;
        this.rozsah = rozsah;
        this.kapacita = kapacita;
        this.vyucujici = vyucujici;
        this.predmet = predmet;
        this.zpusob = zpusob;
        this.ucebna = ucebna;
        this.tyden = tyden;
        urciDenTabulka(datum, tyden);
    }

    private void urciDenTabulka(LocalDate date, Tyden tyden) {
        if (tyden == null) {
            denTabulka = date.toString();
        } else {
            DayOfWeek denTydne = date.getDayOfWeek();
            switch (denTydne) {
                case MONDAY:
                    denTabulka = "Pondělí";
                    break;
                case TUESDAY:
                    denTabulka = "Úterý";
                    break;
                case WEDNESDAY:
                    denTabulka = "Středa";
                    break;
                case THURSDAY:
                    denTabulka = "Čtvrtek";
                    break;
                case FRIDAY:
                    denTabulka = "Pátek";
                    break;
                case SATURDAY:
                    denTabulka = "Sobota";
                    break;
                case SUNDAY:
                    denTabulka = "Neděle";
                    break;
            }
        }
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
        urciDenTabulka(datum, tyden);
    }

    public int getCasOd() {
        return casOd;
    }

    public void setCasOd(int casOd) {
        this.casOd = casOd;
    }

    public Ucebna getUcebna() {
        return ucebna;
    }

    public void setUcebna(Ucebna ucebna) {
        this.ucebna = ucebna;
    }

    public Tyden getTyden() {
        return tyden;
    }

    public void setTyden(Tyden tyden) {
        this.tyden = tyden;
        urciDenTabulka(datum, tyden);
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
