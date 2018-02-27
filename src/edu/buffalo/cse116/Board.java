package edu.buffalo.cse116;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Board {
	
	String[] list = new String[25];
	ArrayList<String> codeNames = new ArrayList<String>();
	ArrayList<Person> mainBoard = new ArrayList<Person>();
	
	
	public ArrayList<String> readCSVFile(String filename){ //Reads the GameWords.txt file putting all the codenames/words into and arraylist
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
	
	public void fillBord() {
		for(int i=0;i<25;i++) {
			Person person = new Person();
			person.setCodeName(list[i]);
			mainBoard.add(person);
		}
	}
}
