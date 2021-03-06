package pt.altran.roothless.model;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


/**
 * Created by Altran on 05/06/2017.
 */
public class Database {

   Cluster cluster;
   Session session;
   String query;
   User user;

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
       query = "CREATE TABLE IF NOT EXISTS users(\n" +
               "\tnick varchar,\n" +
               "\tname varchar,\n" +
               "\temail varchar,\n" +
               "\tpass varchar,\n" +
               "\thighscore int,\n" +
               "\tPRIMARY KEY (nick)\n" +
               ");";
       session.execute(query);
       session.close();

   }

   public void register(User user){

       cluster = Cluster.builder().addContactPoint("localhost").build();
       session = cluster.connect("plane");
       query = "INSERT INTO users (nick, name, email, pass)" +
               " values ('" +
               user.getNick() + "', '" +
               user.getName() + "' , '" +
               user.getEmail() + "', '" +
               user.getPass() + "')";

       session.execute(query);
       session.close();

   }

   public boolean exists (User user){

       cluster = Cluster.builder().addContactPoint("localhost").build();
       session = cluster.connect("plane");
       query = "SELECT nick FROM users WHERE nick = '" + user.getNick() + "';" ;
       ResultSet rs = session.execute(query);
       Row row = rs.one();
       session.close();

       if(row == null){
           return false;
       }else {
           return true;
       }

   }

   public boolean authenticate (User user){

       return false;
   }

   public ResultSet getAll (){

       cluster = Cluster.builder().addContactPoint("localhost").build();
       session = cluster.connect();
       query = "select * from plane.users";
       ResultSet rs = session.execute(query);
       session.close();
       return rs;
   }


}

