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
			if(h==null||h.equalsIgnoreCase((mainBoard.get(i).getCodeName()))&&mainBoard.get(i).getRevealed()==false) {
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
	
	public String choose(String entered) {
		
		if(entered==null||entered.equals(null)||entered.isEmpty())
			return "Invalid Entry. Try Again";
		
		if(entered.equalsIgnoreCase("skip")) {
			if(turn=="red") {
				turn = "blue";
				return "Red Team Skips their turn.";
			}
			else {
				turn = "red";
				return "Blue Team Skips their turn.";
			}
		}
		
		if(turn=="red") {
			for(int i=0;i<mainBoard.size();i++) {
				if(mainBoard.get(i).getCodeName().equalsIgnoreCase(entered)) {
					if(mainBoard.get(i).getTeam()=="red"&&mainBoard.get(i).getRevealed()!=true) {
						redCnt-=1;
						mainBoard.get(i).setRevealed(true);
						return "Correct Guess!";
						
					}
					else if(mainBoard.get(i).getTeam()=="assassin")
						assCnt-=1;
					else if(mainBoard.get(i).getTeam()=="bystander") {
						mainBoard.get(i).setRevealed(true);
						return "Incorrect, Bystander revealed";
					}
				}
			}
			turn = "blue";
			return "Incorrect Guess.";
		}
		
		if(turn=="blue") {
			for(int i=0;i<mainBoard.size();i++) {
				if(mainBoard.get(i).getCodeName().equalsIgnoreCase(entered)&&mainBoard.get(i).getRevealed()!=true) {
					if(mainBoard.get(i).getTeam()=="blue") {
						bluCnt-=1;
						mainBoard.get(i).setRevealed(true);
						return "Correct Guess!";
					}
					else if(mainBoard.get(i).getTeam()=="assassin")
						assCnt-=1;
					else if(mainBoard.get(i).getTeam()=="bystander") {
						mainBoard.get(i).setRevealed(true);
						return "Incorrect, Bystander revealed";
					}
				}
			}
				turn = "red";
				return "Incorrect Guess.";
			}
		return "ERROR";
	}
	
	public String gameState() {
		
		if(redCnt==0||bluCnt==0||assCnt==0) {
		return"the game has been won";
		}
		
		else return"no one has won the game";
	}
	
	
	public String assassPressed() {
		if(turn=="red") {
			return "Red Team Wins!";
		}
		else return "Blue Team Wins!";
	}
	
	public void shuffle() {
		Collections.shuffle(mainBoard);
	}
}
