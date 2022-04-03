package webappnotes;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WebAppNotes {

	public static void main(String[] args) {
		
		boolean running = true;
		Scanner input = new Scanner(System.in);
		FileHandler notepad = new FileHandler("notes.txt");
		
		// initial print of notes 
		{
			int i = 1;
			for(String line : notepad.getNotes()) {
				System.out.print(i + " : "); 
				System.out.println(line);
				i++;
			}
		}

		
		while(running) {
			System.out.println("add note (a), delete note (d), save changes(s), view notes(v)");
			String cmd = input.nextLine();
			if(cmd.equals("s")) {
				//save
				try {
					notepad.commit();
				} catch (IOException e) {
					System.out.println("Unable to save");
				}
				System.out.println("Changes saved");
			}
			else if(cmd.equals("a")) {
				System.out.println("Enter note \n");
				String note = input.nextLine();
				notepad.addNote(note);
			}
			else if(cmd.equals("d")) {
				System.out.println("Enter line number");
				int noteNumber = input.nextInt();
				String note = input.nextLine();
				
				notepad.deleteNote(noteNumber - 1);
			}
			else if(cmd.equals("v")) {
				int i = 1;
				for(String line : notepad.getNotes()) {
					System.out.print(i + " : "); System.out.println(line);
					i++;
				}
			}
		}
	}
}
