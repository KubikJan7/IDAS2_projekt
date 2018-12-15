package data;

/**
 *
 * @author Libor Selecky
 */
public class Uzivatel {

    private int id;
    private String titulPred;
    private String jmeno;
    private String prijmeni;
    private String titulZa;
    private String email;
    private int mobil;
    private int telefon;
    private Ucet ucet;
    private Role role;
    private int idObrazku;
    private Pracoviste pracoviste;

    public Uzivatel(String titulPred, String jmeno, String prijmeni, String titulZa, String email, int mobil, int telefon, Role role, Pracoviste pracoviste) {
        this.titulPred = titulPred;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.titulZa = titulZa;
        this.email = email;
        this.mobil = mobil;
        this.telefon = telefon;
        this.role = role;
        this.pracoviste = pracoviste;
    }

    public Uzivatel(String titulPred, String jmeno, String prijmeni, String titulZa, String email, int mobil, int telefon, Ucet ucet, Role role, int obrazek, Pracoviste pracoviste) {
        this.titulPred = titulPred;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.titulZa = titulZa;
        this.email = email;
        this.mobil = mobil;
        this.telefon = telefon;
        this.ucet = ucet;
        this.role = role;
        this.idObrazku = obrazek;
        this.pracoviste = pracoviste;
    }

    public Uzivatel(int id, String titulPred, String jmeno, String prijmeni,
            String titulZa, String email, int mobil, int telefon,
            Ucet ucet, Role role, int obrazek, Pracoviste pracoviste) {
        this.id = id;
        this.titulPred = titulPred;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.titulZa = titulZa;
        this.email = email;
        this.mobil = mobil;
        this.telefon = telefon;
        this.ucet = ucet;
        this.role = role;
        this.idObrazku = obrazek;
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

    public Ucet getUcet() {
        return ucet;
    }

    public void setUcet(Ucet ucet) {
        this.ucet = ucet;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getIdObrazku() {
        return idObrazku;
    }

    public void setIdObrazku(int idObrazku) {
        this.idObrazku = idObrazku;
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
