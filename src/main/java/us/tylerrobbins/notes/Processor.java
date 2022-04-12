package us.tylerrobbins.notes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Processor {
  void addNote(String note);

  void deleteNote(int lineNumber);

  void commit() throws IOException, SQLException;

  ArrayList<String> getNotes();
}
