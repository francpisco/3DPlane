package pt.altran.roothless.model;

/**
 * Created by Altran on 30/05/2017.
 */

public class User {

    private String Name;
    private String email;
    private String pass;
    private Game game;

    public User(String name, String email, String pass, Game game) {
        Name = name;
        this.email = email;
        this.pass = pass;
        this.game = game;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
