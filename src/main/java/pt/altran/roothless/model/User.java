package pt.altran.roothless.model;

/**
 * Created by Altran on 30/05/2017.
 */

public class User {

    private Integer id;
    private String name;
    private String email;
    private String pass;
    private Game game;

    public User(String name, String email, String pass, Game game) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.game = game;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getId() {
        return id;
    }
}
