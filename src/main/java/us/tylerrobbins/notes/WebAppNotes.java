package us.tylerrobbins.notes;

import java.io.IOException;
import java.util.Scanner;

public class WebAppNotes {

  public static void main(String[] args) {

    boolean running = true;
    Scanner input = new Scanner(System.in);
    FileHandler notepad = new FileHandler("notes.txt");

    // initial print of notes
    {
      int i = 1;
      for (String line : notepad.getNotes()) {
        System.out.print(i + " : ");
        System.out.println(line);
        i++;
      }
    }


    while (running) {
      System.out.println("add note (a), delete note (d), save changes(s), view notes(v), exit(e)");
      String cmd = input.nextLine();
      if (cmd.equals("s")) {
        // save
        try {
          notepad.commit();
        } catch (IOException e) {
          System.out.println("Unable to save");
        }
        System.out.println("Changes saved");

      } else if (cmd.equals("a")) {
        // add
        System.out.println("Enter note \n");
        String note = input.nextLine();
        notepad.addNote(note);
      } else if (cmd.equals("d")) {
        // delete by line number
        System.out.println("Enter line number");
        String noteNumberStr = input.nextLine();

        try {
          // take input from line, then convert to int
          int noteNumberInt = Integer.parseInt(noteNumberStr);
          notepad.deleteNote(noteNumberInt - 1);

        } catch (NumberFormatException e) {
          System.out.println("invalid entry : " + noteNumberStr);
          continue;
        } catch (IndexOutOfBoundsException e) {
          System.out.println("invalid line : " + noteNumberStr);
        }

      } else if (cmd.equals("v")) {
        int i = 1;
        for (String line : notepad.getNotes()) {
          System.out.print(i + " : " + line + "\n");
          i++;
        }
      } else if (cmd.equals("e")) {
        break;
      }
    }
  }
}
