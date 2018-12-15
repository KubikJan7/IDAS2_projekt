package data;

/**
 *
 * @author Libor Selecky
 */
public enum EnumOpravneni {
    ADMINISTRATOR("Administrátor"),
    REGISTROVANY("Registrovaný"),
    NEREGISTROVANY("Neregistrovaný");

    private String text = "";

    EnumOpravneni(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
