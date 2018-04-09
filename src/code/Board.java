package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
	
	public String[] list = new String[25];	//Used to hold 25 and only 25 codeNames
	public ArrayList<String> codeNames = new ArrayList<String>();	//Used to store codeNames list in
	public ArrayList<Person> mainBoard = new ArrayList<Person>();	//Creation of board
	public String turn=null;	//holds "red" or "blue" to determine turn
	public int assCnt=1;	//states 1 assassin card, when assCnt equals 0, an assassin has been chosen
	public int redCnt=9;	//states 9 red agent cards, when redCnt equals 0, all red agents have been chosen
	public int bluCnt=8;	//states 8 blue agent cards, when assCnt equals 0, all blue agents have been chosen
	private ArrayList<Observer> _observers;	
	
	/*
	 * constructor used to send in filename to read for codeNames
	 * @param readCSVFile Takes in the gameWords.txt and stores in codeNames
	 */		
	public Board(String file) {
		readCSVFile(file);
		_observers = new ArrayList<Observer>();
	}
	
	/*Starts the game
	 * codeNames is shuffled
	 * list is created
	 * board is filled
	 * board is shuffled three times
	 * turn is set to "red"
	 */
	public void startGame() {
		list=new String[25];
		mainBoard=new ArrayList<Person>();
		Collections.shuffle(codeNames);
		createList();
		fillBoard();
		Collections.shuffle(mainBoard);
		turn="red";
		notifyObservers();
	}

	/*
	 * Reads codeNames from a file, stores them in ArrayList BULLET POINT 3
	 * @param filename list of game words from a text file
	 * @return an arraylist of game words read in from a file
	 */
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
	
	/*
	 * Creates a list of 25 random codeNames/words from the list created in readCSVFile 
	 * @param list[] sets each element in list , to a random word in codeNames
	 */
	public void createList() { 
		for(int i=0;i<25;i++) {
			int rand = (int) (Math.random()*codeNames.size());
			list[i]=codeNames.get(rand);
			codeNames.remove(rand);
		}
	}
	
	/*
	 * Checks if clue is legal or not
	 * @param the clue enetered by the player
	 * @return true if clue is valid
	 * @return false if clue is not valid
	 */
	public boolean validClue(String h) { //checks if a clue is legal, BULLET POINT 7
		for(int i=0;i<25;i++) {
			if(h==null||h.trim().isEmpty()||h.equalsIgnoreCase((mainBoard.get(i).getCodeName()))&&mainBoard.get(i).getRevealed()==false) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Fills board to size 25 players
	 * @param add a player to mainBoard, while setting its team depending on number in string 
	 */
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
	
	/*
	 * Takes in entry/chosen card, determines if correct
	 * @param entered players choice
	 * @return if correct,incorrect or skipped turn
	 */
	public String choose(String entered) {	
		
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
	
	/*
	 * Returns whether the game has been won or not
	 * @return if the game has been won or not 
	 */
	public String gameState() {	
		
		if(redCnt==0||bluCnt==0||assCnt==0) {
		return "The game has been won.";
		}
		else return "No one has won the game.";
	}
	
	/*
	 * Method called when assassin is chosen, returns winner
	 * @return which team has not lost the game
	 */
	public String assassPressed() {	
		if(turn=="red") {
			return "Assassin chosen by Red Team! Blue Team Wins!";
			//In future there will be a system.exit(0);
		}
		else return "Assassin chosen by Blue Team! Red Team Wins!";
		//In future there will be a system.exit(0);
	}

	public void submit() {
		choose(GUI.GUI.entry.getText());
		clear();
		notifyObservers();
	}

	public boolean checkGuess(String guess) {
		for (int i=0;i<mainBoard.size();i++) {
			if (mainBoard.get(i).getCodeName().equalsIgnoreCase(guess)) {
				mainBoard.get(i).setRevealed(true);
				return true;
			}
		}
		return false;
	}
	
	public void addObserver(Observer obs) {
		_observers.add(obs);
		notifyObservers();
	}

	public void notifyObservers() {
		for (Observer obs : _observers) {
			obs.update();
		}
	}
	
	public void clear() {
		GUI.GUI.entry.setText("");
	}
	
	public void exit() {
		System.exit(0);
		
	}

	public void newGame() {
		startGame();
	}
}
