package pt.altran.roothless.service;

import pt.altran.roothless.model.Database;
import pt.altran.roothless.model.User;

/**
 * Created by Altran on 06/06/2017.
 */
public class UserService {

    private Database database;

    public UserService() {
    }

    public UserService(Database database) {
        this.database = database;
    }

    public boolean authenticate() {

        boolean authenticate = false;

//        if(database.authenticate())
        return authenticate;

    }

    public boolean register(User user) {

        boolean exists = false;

            if (!database.exists(user)) {
                database.register(user);
                exists = true;
            } else {
                exists = false;
            }

        return exists;
    }
}
