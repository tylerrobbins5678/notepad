package us.tylerrobbins.notes;

public class ConnectionFailedException extends Exception {
  public ConnectionFailedException(String errorMessage) {
    super(errorMessage);
  }
}
