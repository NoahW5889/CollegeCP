package edu.buffalo.cse116;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.Collections;

public class Board {
	
	String[] list = new String[25];
	ArrayList<String> codeNames = new ArrayList<String>();
	ArrayList<Person> mainBoard = new ArrayList<Person>();
	
	
	public ArrayList<String> readCSVFile(String filename){
		codeNames = new ArrayList<String>();
    	try { for(String each: Files.readAllLines(Paths.get(filename))) {
    		codeNames.add(each);
    	}
    	}
    	catch (IOException ex){
            ex.printStackTrace();
        }
    	  return codeNames;
    }
	
	
	public void createList() { //creates a list of 25 random codenames/words from the list created in readCSVFile
		for(int i=0;i<25;i++) {
			int rand = (int) (Math.random()*codeNames.size());
			list[i]=codeNames.get(rand);
			codeNames.remove(rand);
		}
	}
	
	public boolean validClue(String h) {
		for(int i=0;i<25;i++) {
			if(h.equalsIgnoreCase(list[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	public void fillBoard() {
		for(int i=0;i<25;i++) {
			Person person = new Person();
			person.setCodeName(list[i]);
			person.setRevealed(false);
			if(i<9)
				person.setTeam("red");
			else if(i>=9&&i<17)
				person.setTeam("blue");
			else if(i>=18&&i<25)
				person.setTeam("bystander");
			else
				person.setTeam("assassin");
			mainBoard.add(person);
		}
	}
	
	public void shuffle() {
		Collections.shuffle(mainBoard);
	}
}
