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
	String turn=null;
	int assCnt=0;
	int redCnt=0;
	int bluCnt=0;
	int gameSt=0;
	
	public Board(String file) {
		readCSVFile(file);
	}

	public void startGame() {
		Collections.shuffle(codeNames);
		createList();
		fillBoard();
		shuffle();
		turn="red";
		assCnt=1;
		redCnt=9;
		bluCnt=8;
	}

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
			if(h.equalsIgnoreCase((list[i]))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void fillBoard() {
		for(int i=0;i<25;i++) {
			Person person=null;
			if(i<9) {
			person = new Person(codeNames.get(i),"red");
			
			}			
			else if(i>=9&&i<17) {
				person = new Person(codeNames.get(i),"blue");				
			}
			else if(i>=18&&i<25) {
				person = new Person(codeNames.get(i),"bystander");
			}
				
			else {
				person = new Person(codeNames.get(i),"assassin");
			}
				
			mainBoard.add(person);
		}
	}
	
	public String gameState() {
		
		if(redCnt==0||bluCnt==0||assCnt==0) {
		return"the game has been won";
		}
		
		else return"no one has won the game";
	}
	
	
	public String assassPressed() {
		if(turn=="red") {
			return "blue team has not lost the game";
		}
		else return "red team has not lost the game";
	}
	
	public void shuffle() {
		Collections.shuffle(mainBoard);
	}
}
