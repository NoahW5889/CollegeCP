package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;



public class Board {
	
	private String[] list = new String[25];	//Used to hold 25 and only 25 codeNames
	private ArrayList<String> codeNames = new ArrayList<String>();	//Used to store codeNames list in
	String readinFile=null;
	private ArrayList<Person> mainBoard = new ArrayList<Person>();	//Creation of board
	private String turn=null;	//holds "red" or "blue" to determine turn
	private int assCnt=1;	//states 1 assassin card, when assCnt equals 0, an assassin has been chosen
	private int redCnt=9;	//states 9 red agent cards, when redCnt equals 0, all red agents have been chosen
	private int bluCnt=8;	//states 8 blue agent cards, when assCnt equals 0, all blue agents have been chosen
	private ArrayList<Observer> _observers;	
	private String reply;
	private String curClue;
	private ArrayList<String> kamiWords=new ArrayList<String>();
	private String prevTurn;
	private String lastGuess;
	
	/*
	 * constructor used to send in filename to read for codeNames
	 * @param readCSVFile Takes in the gameWords.txt and stores in codeNames
	 */		
	public Board(String file) {
		
		readinFile=file;
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
		setKamiWords(new ArrayList<String>());
		setReply("Start of Game");
		setList(new String[25]);
		setMainBoard(new ArrayList<Person>());
		setAssCnt(1);
		setRedCnt(9);
		setBluCnt(8);
		Collections.shuffle(getCodeNames());
		createList();
		fillBoard();
		Collections.shuffle(getMainBoard());
		setTurn("Red Spy");
		setCurClue("");
		notifyObservers();
	}

	/*
	 * Reads codeNames from a file, stores them in ArrayList BULLET POINT 3
	 * @param filename list of game words from a text file
	 * @return an arraylist of game words read in from a file
	 */
	public ArrayList<String> readCSVFile(String filename){
		setCodeNames(new ArrayList<String>());
		
    	try { 
    		for(String each: Files.readAllLines(Paths.get(filename))) {
    			getCodeNames().add(each);
    			
    		}
    	}catch (IOException ex){
            ex.printStackTrace();
        }
    	  return getCodeNames();
    }
	
	/*
	 * Creates a list of 25 random codeNames/words from the list created in readCSVFile 
	 * @param list[] sets each element in list , to a random word in codeNames
	 */
	public void createList() {
	
		for(int i=0;i<25;i++) {
			int rand = (int) (Math.random()*getCodeNames().size());
			int rand2 = (int) (Math.random()*getCodeNames().size());
			getList()[i]=getCodeNames().get(rand);
			if(1>=i) {
			getKamiWords().add(getCodeNames().get(rand2));
			}
			if(getCodeNames().size()<25) {
				readCSVFile(readinFile);
			}
			getCodeNames().remove(rand);
			
		}
	
	}
	
	
	/*
	 * Checks if clue is legal or not
	 * @return true if clue is valid
	 * @return false if clue is not valid
	 */
	public boolean validClue() { //checks if a clue is legal, BULLET POINT 7
		String h= GUI.GUI.entry.getText();
		if(h.length() > 15) {
			setReply("Invalid Clue. String is too long.");
			return false;
		}
		
		String pl = h.replaceAll("[^a-zA-Z]", "");
		for(int i=0;i<25;i++) {
			if(pl==null||pl.trim().isEmpty()||(pl.equalsIgnoreCase((getMainBoard().get(i).getCodeName()))&&getMainBoard().get(i).getRevealed()==false)) {
				setReply("Invalid Clue.");
				return false;
			}
		}
		boolean hasNum = false;
		String placeHolder = h.replaceAll("[^\\d.]", "");
		if(placeHolder.trim().isEmpty()) {
			setReply("Invalid Clue. No Number.");
			return false;
		}
		for(char a: h.toCharArray()) {
			if(Character.isDigit(a)) {
				hasNum = true;
			}
		}
		if(hasNum == true) {
			long result = Long.parseLong(placeHolder);
			if(getTurn() == "Red Spy" && getRedCnt() < result) {
				return false;
			}else if(getTurn() == "Blue Spy" && getBluCnt() < result) {
				return false;
			}
		}
		
		return true;
	}
	
	public int currentTurnCnt() {
		if(getTurn()=="red" || getTurn() == "Red Spy") {
			return getRedCnt();
		}
		else {
			return getBluCnt();
		}
	}
	
	public void easterEgg() {
		if(getTurn()=="red") {
			setRedCnt(0);
			setReply("Game Over Red Team Wins(EE)");
		}
		if(getTurn()=="blue") {
			setBluCnt(0);
			setReply("Game Over Blue Team Wins(EE)");
		}
		notifyObservers();
	}
	
	public void validClues() {
		if(validClue()==true) {
			setReply("The Clue is Valid");
			if(getTurn() == "Red Spy") {
				setTurn("red");
			}else if(getTurn() == "Blue Spy") {
				setTurn("blue");
			}
			setCurClue(GUI.GUI.entry.getText());
		}
		clear();
		notifyObservers();
	}
	/*
	 * Fills board to size 25 players
	 * @param add a player to mainBoard, while setting its team depending on number in string 
	 */
	public void fillBoard() {
		
		
		for(int i=0;i<25;i++) {
			Person person=null;
			if(i<9) {
			person = new Person(getCodeNames().get(i),"red");
			}			
			else if(i>=9&&i<17) {
				person = new Person(getCodeNames().get(i),"blue");				
			}
			else if(i>=18&&i<25) {
				person = new Person(getCodeNames().get(i),"bystander");
			}
				
			else {
				person = new Person(getCodeNames().get(i),"assassin");
			}
			getMainBoard().add(person);
		}
	}
	
	/*
	 * Takes in entry/chosen card, determines if correct
	 * @param entered players choice
	 * @return if correct,incorrect or skipped turn
	 */
	public String choose(String entered) {	
		setLastGuess(entered);
		
		if(entered==null||entered.equals(null)||entered.trim().isEmpty()||entered.isEmpty()) {
			return "Invalid Entry. Try Again.";
		}
		else if(entered.length() > 15) {
			return "Entry is too long.";
		}
		else if(entered.equalsIgnoreCase("rules")||entered.equalsIgnoreCase("rule")) {
			return ("<html>===============Rules.==============="
					+ "<br>Please refer to video."
					+ "<br>https://www.youtube.com/watch?v=sy0AnMDcap0&t=20s"
					+ "<br>===============");
		}
		
			
		else if(entered.equalsIgnoreCase("skip")) {
			if(getTurn()=="red" ) {
				setPrevTurn("red");
				setTurn("Buffer");
				return "Red Team Skips their turn.";
			}
			else if(getTurn() == "Red Spy") {
				setTurn("Blue Spy");
				return "Red Team Skips their turn.";
			}
			else if(getTurn() == "Blue Spy") {
				setTurn("Red Spy");
				return "Blue Team Skips their turn.";
			}
			else {
				setPrevTurn("blue");
				setTurn("Buffer");
				return "Blue Team Skips their turn.";
			}
			
			
		}
		else if(getTurn()=="red") {
			for(int i=0;i<getMainBoard().size();i++) {
				if(getMainBoard().get(i).getCodeName().equalsIgnoreCase(entered)) {
					if(getMainBoard().get(i).getTeam()=="red"&&getMainBoard().get(i).getRevealed()!=true) {
						setRedCnt(getRedCnt() - 1);
						getMainBoard().get(i).setRevealed(true);
						setPrevTurn("blue");
						setTurn("Buffer");
						return "Correct Guess!";
						
					}
					else if(getMainBoard().get(i).getTeam()=="assassin") {
						return assassPressed();
					}
					else if(getMainBoard().get(i).getTeam()=="bystander") {
						getMainBoard().get(i).setRevealed(true);
						setPrevTurn("red");
						setTurn("Buffer");
						return "Incorrect, Bystander revealed.";
					}
				}
			}
			setPrevTurn("red");
			setTurn("Buffer");
			return "Incorrect Guess.";
		}
		
		else if(getTurn()=="blue") {
			for(int i=0;i<getMainBoard().size();i++) {
				if(getMainBoard().get(i).getCodeName().equalsIgnoreCase(entered)) {
					if(getMainBoard().get(i).getTeam()=="blue"&&getMainBoard().get(i).getRevealed()!=true) {
						setBluCnt(getBluCnt() - 1);
						getMainBoard().get(i).setRevealed(true);
						setPrevTurn("red");
						setTurn("Buffer");
						return "Correct Guess!";
					}
					else if(getMainBoard().get(i).getTeam()=="assassin") {
						
						return assassPressed();
					}
					else if(getMainBoard().get(i).getTeam()=="bystander") {
						getMainBoard().get(i).setRevealed(true);
						setPrevTurn("blue");
						setTurn("Buffer");
						return "Incorrect, Bystander revealed.";
					}
				}
			}
			setPrevTurn("blue");
				setTurn("Buffer");
				return "Incorrect Guess.";
			}
		if(getTurn() == "Blue Spy" || getTurn() == "Red Spy") {
			return "Enter a Clue and a num.";
		}
		return "ERROR";
	}
	
	/*
	 * Returns whether the game has been won or not
	 * @return if the game has been won or not 
	 */
	public void gameState(String q) {	
		
		if(q=="red") {
			setReply("Red Team Has Won the Game");
		}
		 if(q=="blue") {
			setReply("Red Team Has Won the Game");
		}
		 if(q=="assass") {
			setReply(assassPressed());
		}
		
		notifyObservers();
	}
	
	public boolean checkGuess(String last) {
		if(last.equalsIgnoreCase("skip")) 
			return false;
		else if(getTurn()=="red") {
			for(int i=0;i<getMainBoard().size();i++) {
				if(getMainBoard().get(i).getCodeName().equalsIgnoreCase(last)) {
					if(getMainBoard().get(i).getTeam()=="red"&&getMainBoard().get(i).getRevealed()!=true) 
						return true;	
				}
			}
		}
		else if(getTurn()=="blue") {
			for(int i=0;i<getMainBoard().size();i++) {
				if(getMainBoard().get(i).getCodeName().equalsIgnoreCase(last)) {
					if(getMainBoard().get(i).getTeam()=="blue"&&getMainBoard().get(i).getRevealed()!=true) 
						return true;	
				}
			}
		}
		return false;
	}
	
	public void volendTurn() {
		if(getTurn()=="red") {
			setPrevTurn("red");
			setTurn("Buffer");
			setReply("Red Team Skips their turn.");
		}
		else if(getTurn() == "Red Spy") {
			setTurn("Blue Spy");
			setReply("Red Team Skips their turn.");
		}
		else if(getTurn() == "Blue Spy") {
			setTurn("Red Spy");
			setReply("Blue Team Skips their turn.");
		}
		else{
			setPrevTurn("blue");
			setTurn("Buffer");
			setReply("Blue Team Skips their turn.");
		}
		notifyObservers();
	}
	/*
	 * Method called when assassin is chosen, returns winner
	 * @return which team has not lost the game
	 */
	public String assassPressed() {	
		setAssCnt(getAssCnt() - 1);
		if(getTurn()=="red") {
			return "Assassin chosen by Red Team! Blue Team Wins!";
			//In future there will be a system.exit(0);
		}
		else return "Assassin chosen by Blue Team! Red Team Wins!";
		//In future there will be a system.exit(0);
	}

	public void submit() {
		setReply(choose(GUI.GUI.entry.getText()));
		notifyObservers();
		clear();
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

	public void egg2() {
		setRedCnt(1337);
		setBluCnt(1337);
		setAssCnt(1337);
		setTurn("egg");
		notifyObservers();
	}

	public int getRedCnt() {
		return redCnt;
	}

	public void setRedCnt(int redCnt) {
		this.redCnt = redCnt;
	}

	public int getBluCnt() {
		return bluCnt;
	}

	public void setBluCnt(int bluCnt) {
		this.bluCnt = bluCnt;
	}

	public int getAssCnt() {
		return assCnt;
	}

	public void setAssCnt(int assCnt) {
		this.assCnt = assCnt;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public ArrayList<Person> getMainBoard() {
		return mainBoard;
	}

	public void setMainBoard(ArrayList<Person> mainBoard) {
		this.mainBoard = mainBoard;
	}

	public ArrayList<String> getKamiWords() {
		return kamiWords;
	}

	public void setKamiWords(ArrayList<String> kamiWords) {
		this.kamiWords = kamiWords;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getCurClue() {
		return curClue;
	}

	public void setCurClue(String curClue) {
		this.curClue = curClue;
	}

	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}

	public ArrayList<String> getCodeNames() {
		return codeNames;
	}

	public void setCodeNames(ArrayList<String> codeNames) {
		this.codeNames = codeNames;
	}
	
	public void setPrevTurn(String prev) {
		this.prevTurn = prev;
	}
	
	public String getPrevTurn(){
		return prevTurn;
	}

	public String getLastGuess() {
		// TODO Auto-generated method stub
		return lastGuess;
	}
	
	public void setLastGuess(String las) {
		this.lastGuess = las;
	}

	
}