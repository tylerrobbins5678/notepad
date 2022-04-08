package us.tylerrobbins.notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
  private String server;
  private String IPaddr;
  private String databaseName;
  private String user;
  private String password;

  /**
   * @param server
   * @param IPaddr
   * @param databaseName
   * @param user
   * @param password
   * @throws ConnectionFailedException
   */

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

  // test connection
  public boolean testConnection() {
    Connection connection = null;
    try {
      System.out.println("testing connection");
      connection = DriverManager.getConnection(
          "jdbc:" + this.server + "://" + this.IPaddr + "/" + this.databaseName, this.user,
          this.password);
      return true;

    } catch (SQLException e) {
      return false;
    }
  }
}
