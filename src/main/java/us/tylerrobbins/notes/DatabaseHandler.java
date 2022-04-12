package us.tylerrobbins.notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>
 * server, IPaddr, database name, user, password
 * </p>
 * 
 * @param server the name of the database engine mysql, mariadb, oracle, etc.
 * @param IPaddr the ip address with the server
 * @param databaseName the name of the database
 * @param user username for sign on
 * @param password password for user
 * @throws ConnectionFailedException
 */

public class DatabaseHandler implements Processor {
  private String server;
  private String IPaddr;
  private String databaseName;
  private String user;
  private String password;
  private Connection connection;


  public DatabaseHandler(String server, String IPaddr, String databaseName, String user,
      String password) throws ConnectionFailedException {
    this.server = server;
    this.IPaddr = IPaddr;
    this.databaseName = databaseName;
    this.user = user;
    this.password = password;

    if (!this.testConnection()) {
      throw new ConnectionFailedException("could not establish connection to database");
    }
  }

  // test connection return true if working, return false if error
  public boolean testConnection() {
    try {
      this.connect();
      this.close();
      return true;

    } catch (SQLException e) {
      return false;
    }
  }

  private void connect() throws SQLException {
    this.connection = DriverManager.getConnection(
        "jdbc:" + this.server + "://" + this.IPaddr + "/" + this.databaseName, this.user,
        this.password);
  }

  private void close() {
    try {
      this.connection.close();
    } catch (SQLException e) {
      // do nothing
    }
  }

  public void addNote(String line) {
    PreparedStatement preparedStatement = null;
    try {
      this.connect();
      preparedStatement = this.connection.prepareStatement("insert into notes (note) values (?);");
      preparedStatement.setString(1, line);
      int rowsEffected = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();

    } finally {
      // close
      try {
        preparedStatement.close();
      } catch (SQLException e) {
      }
      this.close();
    }

  }

  public void deleteNote(int index) {
    PreparedStatement preparedStatement = null;
    try {
      this.connect();
      preparedStatement = this.connection.prepareStatement("delete from notes where id = ?;");
      preparedStatement.setInt(1, index);
      int rowsEffected = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();

    } finally {
      // close
      try {
        preparedStatement.close();
      } catch (SQLException e) {
      }
      this.close();
    }
  }

  public ArrayList<String> getNotes() {
    ResultSet rs = null;
    ArrayList<String> notes = new ArrayList<String>();
    PreparedStatement preparedStatement = null;
    try {
      this.connect();
      preparedStatement = this.connection.prepareStatement("select id,note from notes;");
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        notes.add(rs.getString(1) + " : " + rs.getString(2));
      }

    } catch (SQLException e) {
      e.printStackTrace();

    } finally {
      // close
      try {
        rs.close();
      } catch (SQLException e1) {
      }
      try {
        preparedStatement.close();
      } catch (SQLException e) {
      }
      this.close();
    }

    return notes;
  }


  public void commit() {
    // rewrite file with contents from notes list
  }
}
