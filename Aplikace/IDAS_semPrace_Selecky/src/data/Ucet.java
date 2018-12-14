package data;

/**
 *
 * @author Libor Selecky
 */
public class Ucet {

    private int id;
    private String nickname;
    private String heslo;

    public Ucet(String nickname, String heslo) {
        this.nickname = nickname;
        this.heslo = heslo;
    }

    public Ucet(int id, String nickname, String heslo) {
        this.id = id;
        this.nickname = nickname;
        this.heslo = heslo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

}
