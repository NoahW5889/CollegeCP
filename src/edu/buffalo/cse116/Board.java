package edu.buffalo.cse116;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
	
	String[] list = new String[25];	//Used to hold 25 and only 25 codeNames
	ArrayList<String> codeNames = new ArrayList<String>();	//Used to store codeNames list in
	ArrayList<Person> mainBoard = new ArrayList<Person>();	//Creation of board
	String turn=null;	//holds "red" or "blue" to determine turn
	int assCnt=1;	//states 1 assassin card, when assCnt equals 0, an assassin has been chosen
	int redCnt=9;	//states 9 red agent cards, when redCnt equals 0, all red agents have been chosen
	int bluCnt=8;	//states 8 blue agent cards, when assCnt equals 0, all blue agents have been chosen
	
	public Board(String file) {	//constructor used to send in filename to read for codeNames
		readCSVFile(file);
	}
	
	public void startGame() {	//start game BULLET POINT 6 || refer to createList() & fillBoard() for instances created, auto set revealed=false in person class
		Collections.shuffle(codeNames);
		createList();
		fillBoard();
		shuffle();
		turn="red";
	}

	public ArrayList<String> readCSVFile(String filename){	//Reads codeNames from a file, stores them in ArrayList BULLET POINT 3
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
	
	
	public void createList() { //Creates a list of 25 random codeNames/words from the list created in readCSVFile BULLET POINT 4
		for(int i=0;i<25;i++) {
			int rand = (int) (Math.random()*codeNames.size());
			list[i]=codeNames.get(rand);
			codeNames.remove(rand);
		}
	}
	
	public boolean validClue(String h) { //checks if a clue is legal, BULLET POINT 7
		for(int i=0;i<25;i++) {
			if(h==null||h.trim().isEmpty()||h.equalsIgnoreCase((mainBoard.get(i).getCodeName()))&&mainBoard.get(i).getRevealed()==false) {
				return false;
			}
		}
		return true;
	}
	
	public void fillBoard() {	//Fills board to size 25 (25 location instances) BULLET POINT 2, BULLET POINT 5.1
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
	
	public String choose(String entered) {	//Takes in entry/chosen card, determines if correct,BULLET POINT 8 (along with other commands)
		
		if(entered==null||entered.equals(null)||entered.trim().isEmpty()||entered.isEmpty())
			return "Invalid Entry. Try Again";
		
		else if(entered.equalsIgnoreCase("rules")) {
			return ("===============\nRules.\n===============\nPlease refer to video."
					+ "\nhttps://www.youtube.com/watch?v=sy0AnMDcap0&t=20s"
					+ "\n===============");
		}
			
		else if(entered.equalsIgnoreCase("skip")) {
			if(turn=="red") {
				turn = "blue";
				return "Red Team Skips their turn.";
			}
			else {
				turn = "red";
				return "Blue Team Skips their turn.";
			}
		}
		
		else if(turn=="red") {
			for(int i=0;i<mainBoard.size();i++) {
				if(mainBoard.get(i).getCodeName().equalsIgnoreCase(entered)) {
					if(mainBoard.get(i).getTeam()=="red"&&mainBoard.get(i).getRevealed()!=true) {
						redCnt-=1;
						mainBoard.get(i).setRevealed(true);
						return "Correct Guess!";
						
					}
					else if(mainBoard.get(i).getTeam()=="assassin") {
						assCnt-=1;
						return assassPressed();
					}
					else if(mainBoard.get(i).getTeam()=="bystander") {
						mainBoard.get(i).setRevealed(true);
						turn = "blue";
						return "Incorrect, Bystander revealed";
					}
				}
			}
			turn = "blue";
			return "Incorrect Guess.";
		}
		
		else if(turn=="blue") {
			for(int i=0;i<mainBoard.size();i++) {
				if(mainBoard.get(i).getCodeName().equalsIgnoreCase(entered)) {
					if(mainBoard.get(i).getTeam()=="blue"&&mainBoard.get(i).getRevealed()!=true) {
						bluCnt-=1;
						mainBoard.get(i).setRevealed(true);
						return "Correct Guess!";
					}
					else if(mainBoard.get(i).getTeam()=="assassin") {
						assCnt-=1;
						return assassPressed();
					}
					else if(mainBoard.get(i).getTeam()=="bystander") {
						mainBoard.get(i).setRevealed(true);
						turn = "red";
						return "Incorrect, Bystander revealed";
					}
				}
			}
				turn = "red";
				return "Incorrect Guess.";
			}
		return "ERROR";
	}
	
	public String gameState() {	//Returns whether the game has been won or not, BULLET POINT 9
		
		if(redCnt==0||bluCnt==0||assCnt==0) {
		return "The game has been won.";
		}
		else return "No one has won the game.";
	}
	
	public String assassPressed() {	//Method called when assassin is chosen, returns winner, BULLET POINT 10
		if(turn=="red") {
			return "Assassin chosen by Red Team! Blue Team Wins!";
			//In future there will be a system.exit(0);
		}
		else return "Assassin chosen by Blue Team! Red Team Wins!";
		//In future there will be a system.exit(0);
	}
	
	public void shuffle() {	//Shuffles board locations, 3 times for thorough shuffle BULLET POINT 5.2
		Collections.shuffle(mainBoard);
		Collections.shuffle(mainBoard);
		Collections.shuffle(mainBoard);
	}
}
