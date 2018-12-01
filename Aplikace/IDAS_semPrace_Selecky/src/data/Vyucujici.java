package data;

/**
 *
 * @author Libor Selecky
 */
public class Vyucujici {

    private int id;
    private String titulPred;
    private String jmeno;
    private String prijmeni;
    private String titulZa;
    private String email;
    private int mobil;
    private int telefon;

    private Pracoviste pracoviste;

    public Vyucujici(int id, String titulPred, String jmeno, String prijmeni, String titulZa, String email, int mobil, int telefon, Pracoviste pracoviste) {
        this.id = id;
        this.titulPred = titulPred;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.titulZa = titulZa;
        this.email = email;
        this.mobil = mobil;
        this.telefon = telefon;
        this.pracoviste = pracoviste;
    }

    public Vyucujici(String titulPred, String jmeno, String prijmeni, String titulZa, String email, int mobil, int telefon, Pracoviste pracoviste) {
        this.titulPred = titulPred;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.titulZa = titulZa;
        this.email = email;
        this.mobil = mobil;
        this.telefon = telefon;
        this.pracoviste = pracoviste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulPred() {
        return titulPred;
    }

    public void setTitulPred(String titulPred) {
        this.titulPred = titulPred;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getTitulZa() {
        return titulZa;
    }

    public void setTitulZa(String titulZa) {
        this.titulZa = titulZa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobil() {
        return mobil;
    }

    public void setMobil(int mobil) {
        this.mobil = mobil;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public Pracoviste getPracoviste() {
        return pracoviste;
    }

    public void setPracoviste(Pracoviste pracoviste) {
        this.pracoviste = pracoviste;
    }

    @Override
    public String toString() {
        String text = "";
        if (titulPred != null) {
            text += titulPred + " ";
        }
        text += jmeno + " " + prijmeni;
        if (titulZa != null) {
            text += " " + titulZa;
        }
        return text;
    }

}
