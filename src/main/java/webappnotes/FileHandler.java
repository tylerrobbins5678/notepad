package webappnotes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
	
	File file;
	ArrayList<String> notes = new ArrayList<String>();
	String fileName;
	//constructor
	public FileHandler(String fileName) {
		this.fileName = fileName;
		try {
			this.file = new File(this.fileName);
			//create new file if not found
			if (this.file.createNewFile()) {
				System.out.println("File not found, " + this.fileName + ".txt created");
			}
			
			else {
				// read contents of file and store in list
				Scanner scanner = new Scanner(this.file);
				while(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					this.notes.add(line);
				}
			}
			
		}
		// handle exception / print
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void addNote(String line) {
		this.notes.add(line);
	}
	
	public void deleteNote(int index){
		try {
			this.notes.remove(index);
		}
		catch(IndexOutOfBoundsException e) {
			// invalid index do nothing
		}
	}
	
	public ArrayList<String> getNotes(){
		return this.notes;
	}
	
	public void commit() throws IOException {
		//rewrite file with contents from notes list
		FileWriter writer = new FileWriter(this.file, false);
		for(String line : this.notes) {
			writer.write(line);
		}
		writer.close();
	}
}
