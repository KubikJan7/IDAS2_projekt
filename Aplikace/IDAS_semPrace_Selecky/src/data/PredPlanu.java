package data;

/**
 *
 * @author Libor Selecky
 */
public class PredPlanu {
    private int id;
    private int kredity;
    private int dopRocnik;
    private Predmet predmet;
    private Zakonceni zakonceni;
    private Semestr semestr;
    private Kategorie kategorie;
    private Plan studPlan;

    public PredPlanu(int kredity, int dopRocnik, Predmet predmet, Zakonceni zakonceni, Semestr semestr, Kategorie kategorie, Plan studPlan) {
        this.kredity = kredity;
        this.dopRocnik = dopRocnik;
        this.predmet = predmet;
        this.zakonceni = zakonceni;
        this.semestr = semestr;
        this.kategorie = kategorie;
        this.studPlan = studPlan;
    }
    
    public PredPlanu(int id, int kredity, int dopRocnik, Predmet predmet, Zakonceni zakonceni, Semestr semestr, Kategorie kategorie, Plan studPlan) {
        this.id = id;
        this.kredity = kredity;
        this.dopRocnik = dopRocnik;
        this.predmet = predmet;
        this.zakonceni = zakonceni;
        this.semestr = semestr;
        this.kategorie = kategorie;
        this.studPlan = studPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKredity() {
        return kredity;
    }

    public void setKredity(int kredity) {
        this.kredity = kredity;
    }

    public int getDopRocnik() {
        return dopRocnik;
    }

    public void setDopRocnik(int dopRocnik) {
        this.dopRocnik = dopRocnik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Zakonceni getZakonceni() {
        return zakonceni;
    }

    public void setZakonceni(Zakonceni zakonceni) {
        this.zakonceni = zakonceni;
    }

    public Semestr getSemestr() {
        return semestr;
    }

    public void setSemestr(Semestr semestr) {
        this.semestr = semestr;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Plan getStudPlan() {
        return studPlan;
    }

    public void setStudPlan(Plan studPlan) {
        this.studPlan = studPlan;
    }

    @Override
    public String toString() {
        return "PredPlanu{" + "id=" + id + ", kredity=" + kredity + ", dopRocnik=" + dopRocnik + ", predmet=" + predmet + ", zakonceni=" + zakonceni + ", semestr=" + semestr + ", kategorie=" + kategorie + ", studPlan=" + studPlan + '}';
    }
    
    
}
