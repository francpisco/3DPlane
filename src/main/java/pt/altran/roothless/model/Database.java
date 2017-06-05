package pt.altran.roothless.model;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;


/**
 * Created by Altran on 05/06/2017.
 */
public class Database {

   Cluster cluster;
   Session session;
   String query;

   public void createDatabase(String name){

       cluster = Cluster.builder().addContactPoint("localhost").build();
       session = cluster.connect();
       query = "CREATE KEYSPACE IF NOT EXISTS " + name + "\n" +
               "WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";
       session.execute(query);
       session.close();
   }

   public  void createTables(){

       cluster = Cluster.builder().addContactPoint("localhost").build();
       session = cluster.connect("plane");
       query = "CREATE TABLE users(\n" +
               "\tid int,\n" +
               "\tname varchar,\n" +
               "\temail varchar,\n" +
               "\tpass varchar,\n" +
               "\tgame list<FROZEN<game>>,\n" +
               "\tPRIMARY KEY (id)\n" +
               ");";
       session.execute(query);
       session.close();

   }

   public void  populateTable(User user){

       cluster = Cluster.builder().addContactPoint("localhost").build();
       session = cluster.connect("plane");
       query = "INSERT INTO users (id, name, email, pass, game)" +
               " values ( " +
               user.getId() + "," +
               user.getName() + "," +
               user.getEmail() + "," +
               user.getPass() + "," +
               "[date: " + user.getGame().getDate()+ "," +
               "highscore: " + user.getGame().getHighScore() + "])";

       session.execute(query);
       session.close();

   }

   public void creatUserType (){

       session = cluster.connect("plane");
       query = "CREATE TYPE game (date date, highscore int)";
       session.execute(query);

   }


}

