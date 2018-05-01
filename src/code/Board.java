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
	private String turn="red";	//holds "red" or "blue" to determine turn
	private int assCnt=1;	//states 1 assassin card, when assCnt equals 0, an assassin has been chosen
	private int redCnt=9;	//states 9 red agent cards, when redCnt equals 0, all red agents have been chosen
	private int bluCnt=8;	//states 8 blue agent cards, when assCnt equals 0, all blue agents have been chosen
	private int grnCnt=0;
	private int playerCnt;
	private ArrayList<Observer> _observers;	
	private String reply;
	private String curClue;
	private ArrayList<String> kamiWords=new ArrayList<String>();
	private String prevTurn;
	private String lastGuess="";
	private int maxGuess;
	private int curGuessCnt=1;
	private ArrayList<String> elimPlayers=new ArrayList<String>();
	
	/**
	 * constructor used to send in filename to read for codeNames
	 * @param readCSVFile Takes in the gameWords.txt and stores in codeNames
	 * sets _observers to new arraylist
	 * sets readinFile to the file parameter
	 */		
	public Board(String file) {
		
		readinFile=file;
		readCSVFile(file);
		_observers = new ArrayList<Observer>();
	}
	
	/**
	 * Starts the game
	 * list for easter egg created
	 * reply is set to"start of Game"
	 * list is created
	 * board is created
	 * assCnt is count set to 1
	 * redcnt is set to 9
	 * blueCnt is set to 8
	 * turn is set to "red"
	 * codeNames is shuffled
	 * creates list of words for game
	 * fills board with spys
	 * shuffles the main board
	 * sets turn to "Red Spy"
	 * sets CurClu to empty string
	 * All observers are notified
	 */
	public void playerSet(int q) {
		
		setPlayerCnt(q);
		startGame();
		notifyObservers();
	}
	
	public void startGame() {
		if(playerCnt==3) {
			setRedCnt(6);
			setBluCnt(5);
			setGrnCnt(5);
			setAssCnt(2);	
		}
		else {
			setAssCnt(1);
			setRedCnt(9);
			setBluCnt(8);
			
		}
		setKamiWords(new ArrayList<String>());
		setReply("Start of Game. Red SpyMasters Turn.");
		setList(new String[25]);
		setMainBoard(new ArrayList<Person>());
		Collections.shuffle(getCodeNames());
		createList();
		fillBoard();
		Collections.shuffle(getMainBoard());
		setTurn("Red SpyMaster");
		setCurClue("");
		notifyObservers();
	}

	/**
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
	
	/**
	 * Creates a list of 25 random codeNames/words and 2 KamiWords(for easter egg) 
	 * from the list created in readCSVFile
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
	
	
	/**
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
				setReply("Invalid Clue. Empty.");
				return false;
			}
		}
		boolean hasNum = false;
		String placeHolder = h.replaceAll("[^\\d.]", "");
		if(placeHolder.trim().isEmpty()) {
			setReply("Invalid Clue. No Number.");
			return false;
		}
		maxGuess = Integer.valueOf(placeHolder.trim())+1;
		for(char a: h.toCharArray()) {
			if(Character.isDigit(a)) {
				hasNum = true;
			}
		}
		if(hasNum == true) {
			long result = Long.parseLong(placeHolder);
			if(getTurn() == "Red SpyMaster" && getRedCnt() < result) {
				return false;
			}else if(getTurn() == "Blue SpyMaster" && getBluCnt() < result) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Returns the current spy count
	 * @return RedCnt if turn is red
	 * @return BluCnt if turn is blue
	 */	
	public int currentTurnCnt() {
		if(getTurn()=="red" || getTurn() == "Red SpyMaster") {
			return getRedCnt();
		}
		else if(getTurn()=="blue" || getTurn() == "Blue SpyMaster") {
			return getBluCnt();
		}
		else return getGrnCnt();
	}
	/**Method that Ends the game automatically for who ever clicked on it
	 * 
	 * If turn is equal to red or Red Spy red team wins
	 * Otherwise blue team wins
	 */	
	public void easterEgg() {
		if(getTurn()=="red"||getTurn()=="Red SpyMaster") {
			setRedCnt(0);
			setReply("Game Over Red Team Wins(EE)");
		}
		else if(getTurn()=="blue"){
			setBluCnt(0);
			setReply("Game Over Blue Team Wins(EE)");
		}
		else{
			setGrnCnt(0);
			setReply("Game Over Green Team Wins(EE)");
		}
		notifyObservers();
	}
	
	/**
	 * Method that checks if a clue is valid or not
	 *If validClue() returns true the turn is switched
	 *If validClue() returns false the the reply is changed and the spymaster is allowed to choose again
	 *CurClu is set to the text at entry
	 *The textfield is cleared
	 *All observers are notified
	 */
	public void validClues() {
		if(validClue()==true) {
			if(getTurn() == "Red SpyMaster") {
				setTurn("red");
				setReply("The Clue is Valid. "+getTurn()+"s turn.");
			}else if(getTurn() == "Blue SpyMaster") {
				setTurn("blue");
				setReply("The Clue is Valid. "+getTurn()+"s turn.");
			}
			else if(getTurn() == "Green SpyMaster") {
				setTurn("green");
				setReply("The Clue is Valid. "+getTurn()+"s turn.");
			}
			setCurClue(GUI.GUI.entry.getText());
		}
		clear();
		notifyObservers();
	}
	/**
	 * Fills board to size 25 players
	 * @param add a player to mainBoard, while setting its team depending on number in string 
	 */
	public void fillBoard() {
		if(playerCnt==3) {
			for(int i=0;i<25;i++) {
				Person person=null;
				if(i<6) {
				person = new Person(getCodeNames().get(i),"red");
				}			
				else if(i>=6&&i<11) {
					person = new Person(getCodeNames().get(i),"blue");				
				}
				else if(i>=11&&i<16) {
					person = new Person(getCodeNames().get(i),"green");
				}
				else if(i>=16&&i<23) {
					person= new Person(getCodeNames().get(i),"bystander");
				}
				else {
					person = new Person(getCodeNames().get(i),"assassin");
				}
				getMainBoard().add(person);
			}
			
		}
		
		else {
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
	}
	
	/**
	 * Takes in entry/chosen card, determines if correct
	 * 
	 * @param entered players choice
	 * @return if correct,incorrect or skipped turn
	 */
	public String choose(String entered) {	
		setLastGuess(entered);
		
		if(!(curGuessCnt<maxGuess)) {
			if(getTurn()=="red")
				setPrevTurn("red");
			else
				setPrevTurn("blue");
			
			curGuessCnt=0;
			if(checkGuess(entered)==true) {
				setTurn("Buffer");
				return "Correct Guess! Max Guess Count Met.";
			}
			else {
				setTurn("Buffer");
				return "Incorrect Guess. Max Guess Count Met.";
			}
		}
		
		if(entered==null||entered.equals(null)||entered.trim().isEmpty()||entered.isEmpty()) {
			return "Invalid Entry. Empty. Try Again.";
		}
		else if(entered.length() > 15) {
			return "Entry is too long. Try Again.";
		}
			
		else if(entered.equalsIgnoreCase("skip")) {
			if(getTurn()=="red" ) {
				setPrevTurn("red");
				setTurn("Buffer");
				curGuessCnt=0;
				return "Red Team Skips their turn. Blue SpyMasters Turn.";
			}
			else if(getTurn() == "Red SpyMaster") {
				setTurn("Blue SpyMaster");
				return "Red Team Skips their turn. Blue SpyMasters Turn.";
			}
			else if(getTurn() == "Green SpyMaster") {
				setTurn("Red SpyMaster");
				return "Green Team Skips their turn. Red SpyMasters Turn.";
			}
			else if(getTurn() == "Blue SpyMaster") {
				if(getPlayerCnt()==3) {
					setTurn("Green SpyMaster");	
					return "Blue Team Skips their turn. Green SpyMasters Turn.";
				}
				else {
				setTurn("Red SpyMaster");
				return "Blue Team Skips their turn. Red SpyMasters Turn.";
				}
			}
			else if(getTurn() == "green") {
				setTurn("Red SpyMaster");
				return "Green Team Skips their turn. Red SpyMasters Turn.";
			}
			else {
				setPrevTurn("blue");
				setTurn("Buffer");
				curGuessCnt=0;
				return "Blue Team Skips their turn. Red SpyMasters Turn.";
			}
			
			
		}
		else if(getTurn()=="red") {
			return redChoose(entered);
		}
		
		else if(getTurn()=="blue") {
			return bluChoose(entered);
			}
		if(getTurn() == "Blue SpyMaster" || getTurn() == "Red SpyMaster"||getTurn()=="Green SpyMFaster") {
			return "Enter a Clue and a num.";
		}
		return "ERROR";
	}
	
	/**
	 * Returns whether the game has been won or not
	 * @return if the game has been won or not 
	 */
	public void gameState(String q) {	
		
		if(q=="red") {
			setReply("Red Team Has Won the Game");
		}
		 if(q=="blue") {
			setReply("Blue Team Has Won the Game");
		}
		 if(q=="assass") {
			setReply(assassPressed());
		}
		
		notifyObservers();
	}
	/**
	 * Method that checks the guess
	 * If skip or any codeName is entered false is returned
	 * If not equal to any codeName or skip true is returned
	 * @param last
	 * @return
	 */
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
	/**
	 * Method for ending turn voluntarily 
	 * If turn equals red or blue it sets turn to buffer ,and updates PrevTurn
	 * If turn equals Blue Spy it changes the turn to Red Spy
	 * If turn equals Red Spy it changes the turn to Blue Spy
	 * Sets reply box to say who skipped their turn
	 * Notifys all observers
	 */
	

	public void volendTurn() {
		
		if(getTurn()=="red") {
			setPrevTurn("red");
			setTurn("Buffer");
			setReply("Red Team Skips their turn. Blue SpyMasters Turn.");
		}
		else if(getTurn()=="green") {
			setPrevTurn("green");
			setTurn("Buffer");
			setReply("Green Team Skips their turn. Red SpyMasters Turn.");
		}
		else if(getTurn()=="Green SpyMaster") {
			setTurn("Red SpyMaster");
			setReply("Green Team Skips their turn. Red SpyMasters Turn.");
		}
		else if(getTurn() == "Red SpyMaster") {
			setTurn("Blue SpyMaster");
			setReply("Red Team Skips their turn. Blue SpyMasters Turn.");
		}
		else if(getTurn() == "Blue SpyMaster") {
			if(playerCnt==3) {
				setTurn("Green SpyMaster");
				setReply("Blue Team Skips their turn. Green SpyMasters Turn.");
			}
			else {
			setTurn("Red SpyMaster");
			setReply("Blue Team Skips their turn. Red SpyMasters Turn.");
			}
			
		}
		
		else if(getTurn()=="blue") {
			if(playerCnt==3) {
				setPrevTurn("blue");
				setTurn("Buffer");
				setReply("Blue Team Skips their turn. Green SpyMasters Turn.");
			}
			else {
			setPrevTurn("blue");
			setTurn("Buffer");
			setReply("Blue Team Skips their turn. Red SpyMasters Turn.");
			}
			
		}
		else{
		
			setReply("Error");
		}
		notifyObservers();
	}
	/**
	 * Method called when assassin is chosen, returns winner
	 * @return which team has not lost the game
	 */
	public String assassPressed() {	
		setAssCnt(getAssCnt() - 1);
		if(getPlayerCnt()==3){
			if(getTurn()=="red") {
				elimPlayers.add("red");
				return "Assassin chosen by Red Team! Red Team Eliminated";//In future there will be a system.exit(0);
			}
			if(getTurn()=="green") {
				elimPlayers.add("green");
				return "Assassin chosen by Green Team! Green Team Eliminated!";//In future there will be a system.exit(0);
			}
			
			else {
				elimPlayers.add("blue");
				return "Assassin chosen by Blue Team! Blue Team Eliminated!";
			}
			}
		
		else {
		if(getTurn()=="red") {
			return "Assassin chosen by Red Team! Blue Team Wins!";//In future there will be a system.exit(0);
		}
		
		else return "Assassin chosen by Blue Team! Red Team Wins!";
		}
	}
	/**
	 * Method for guessing 
	 * Sets the reply text to what is returned by the choose function,with parameters GUI.GUI.entry.getText())
	 * Notifys all observers
	 * Clears the textfield
	 */
	public void submit() {
		setReply(choose(GUI.GUI.entry.getText()));
		notifyObservers();
		clear();
	}
	/**
	 * Method for adding an observer to the _observers Arraylist
	 * @param obs
	 * adds obs to the _observers arraylist
	 */
	public void addObserver(Observer obs) {
		_observers.add(obs);
		notifyObservers();
	}
	/**
	 * Method that notify observers 
	 * calls the update method which updates the GUI
	 */
	public void notifyObservers() {
		for (Observer obs : _observers) {
			obs.update();
		}
	}
	/**
	 * Method for emptying the textfield
	 * sets GUI.GUI.entry to an empty string
	 */
	public void clear() {
		GUI.GUI.entry.setText("");
	}
	/**
	 * Method for exiting the application
	 * calls system.exit(0) which  exits the appication
	 */
	public void exit() {
		System.exit(0);
		
	}
	/**
	 * method for starting a new game
	 * calls  the startGame method 
	 */
	public void newGame() {
		startGame();
	}
	/**method for the second easter egg
	 * sets redCnt,bluCnt,assCnt to 1337 and sets turn to "egg"
	 * notifies all observers 
	 */
	public void egg2() {
		setRedCnt(1337);
		setBluCnt(1337);
		setAssCnt(1337);
		setTurn("egg");
		notifyObservers();
	}
	/**
	 * Getter method redCnt
	 * @return current redCnt
	 */
	public int getRedCnt() {
		return redCnt;
	}
	/**
	 * Setter method for redCnt
	 * @param redCnt new int
	 */
	public void setRedCnt(int redCnt) {
		this.redCnt = redCnt;
	}
	/**
	 * Getter method for bluCnt
	 * @return bluCnt current bluCnt
	 */
	public int getBluCnt() {
		return bluCnt;
	}
	/**
	 * Setter method for Blue Cnt
	 * @param bluCnt new int
	 */
	public void setBluCnt(int bluCnt) {
		this.bluCnt = bluCnt;
	}
	/**
	 * Getter method assCnt
	 * @return assCnt current assCnt
	 */
	public int getAssCnt() {
		return assCnt;
	}
	/**
	 * Setter method for assCnt
	 * @param assCnt new int
	 */
	public void setAssCnt(int assCnt) {
		this.assCnt = assCnt;
	}
	/**
	 * Getter method for curent turn
	 * @return current turn
	 */
	public String getTurn() {
		return turn;
	}
	/**
	 * Setter method for turn
	 * Sets turn to new turn
	 * @param turn new turn
	 */
	public void setTurn(String turn) {
		this.turn = turn;
	}
	/**
	 * Getter method for mainBoard
	 * @return the mainboard arraylist of person
	 */
	public ArrayList<Person> getMainBoard() {
		return mainBoard;
	}
	/**
	 * Setter method for mainboard
	 * Sets mainBoard to an arraylist of person
	 * @param mainBoard arraylist of person
	 */
	public void setMainBoard(ArrayList<Person> mainBoard) {
		this.mainBoard = mainBoard;
	}
	/**
	 * Getter  method for KamiWords
	 * @return the arraylist of words to be used with the easter egg
	 */
	public ArrayList<String> getKamiWords() {
		return kamiWords;
	}
	/**
	 * Setter method for the Kamiwords
	 * Sets the list that will be used with easter egg
	 * @param kamiWords arraylist of strings
	 */
	public void setKamiWords(ArrayList<String> kamiWords) {
		this.kamiWords = kamiWords;
	}
	/**
	 * Getter method for reply
	 * @return the text that will be displayed in the reply box
	 */
	public String getReply() {
		return reply;
	}
	/**
	 * Setter method for reply
	 * Sets the text that will be displayed in the reply box
	 * @param reply the reply generated by the games current state
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}
	/**
	 * Getter Method for CurClue
	 * @return s String that is the current clue
	 */
	public String getCurClue() {
		return curClue;
	}
	/**
	 * Setter method for curClue
	 * @param curClue the current clue
	 */
	public void setCurClue(String curClue) {
		this.curClue = curClue;
	}
	/**
	 * Getter method for List
	 * @return the list variable
	 */
	public String[] getList() {
		return list;
	}
	/**
	 * Setter method for list
	 * @param list an array of strings
	 */
	public void setList(String[] list) {
		this.list = list;
	}
	/**
	 * Getter method for code names
	 * @return the codeNames arraylist
	 */
	public ArrayList<String> getCodeNames() {
		return codeNames;
	}
	/**
	 * Setter method for codenames
	 * @param codeNames an arraylist of type string
	 */
	public void setCodeNames(ArrayList<String> codeNames) {
		this.codeNames = codeNames;
	}
	/**
	 * Setter method for preTurn
	 * @param prev the previous turn 
	 */
	public void setPrevTurn(String prev) {
		this.prevTurn = prev;
	}
	/**
	 * Returns the previous turn
	 * @return the turn before this one
	 */
	public String getPrevTurn(){
		return prevTurn;
	}
	/**
	 * Return the last guess
	 * @return The last guess entered
	 */
	public String getLastGuess() {
		// TODO Auto-generated method stub
		return lastGuess;
	}
	/**
	 * Sets last guess to las
	 * @param las the previous guess 
	 */
	public void setLastGuess(String las) {
		this.lastGuess = las;
	}
	/**
	 * Returns the maximum gues allowed
	 * @return maxGuess
	 */
	public int getMaxGuess() {
		return this.maxGuess;
	}
	/**
	 * Return the Current Guess Count
	 * @return curGuessCnt
	 */
	public int getCurGuessCnt() {
		return this.curGuessCnt;
	}
	
	public int getPlayerCnt() {
		return playerCnt;
	
	}
	public void setPlayerCnt(int q) {
		playerCnt=q;
	}
	public void setGrnCnt(int q) {
		grnCnt=q;
	}
	public int getGrnCnt() {
		return grnCnt;
	}
	
	public void setElimPlayer(ArrayList<String> y) {
		elimPlayers=y;
	}
	
	public ArrayList<String> getElimPlayer(){
		return elimPlayers;
	}
	
	private String redChoose(String entered) {
		 
				for(int i=0;i<getMainBoard().size();i++) {
					if(getMainBoard().get(i).getCodeName().equalsIgnoreCase(entered)) {
						if(getMainBoard().get(i).getTeam()=="red"&&getMainBoard().get(i).getRevealed()!=true) {
							setRedCnt(getRedCnt() - 1);
							getMainBoard().get(i).setRevealed(true);
							setPrevTurn("blue");
							setTurn("red");
							curGuessCnt+=1;
							return "Correct Guess. Still Red Teams Turn.";
							
						}
						else if(getMainBoard().get(i).getTeam()=="assassin") {
							return assassPressed();
						}
						else if(getMainBoard().get(i).getTeam()=="bystander") {
							getMainBoard().get(i).setRevealed(true);
							setPrevTurn("red");
							setTurn("Buffer");
							curGuessCnt=0;
							return "Incorrect Guess. Blue SpyMasters Turn.";
						}
						if(getMainBoard().get(i).getTeam()=="blue"&&getMainBoard().get(i).getRevealed()!=true) {
							setBluCnt(getBluCnt() - 1);
							getMainBoard().get(i).setRevealed(true);
							setPrevTurn("red");
							setTurn("Buffer");
							curGuessCnt=0;
							return "Incorrect Guess. Blue SpyMasters Turn.";
							
						}
					}
					
					
				}
				setPrevTurn("red");
				setTurn("Buffer");
				curGuessCnt=0;
				return "Incorrect Guess. Blue SpyMasters Turn.";
			}
	
			private String bluChoose(String entered) {
				for(int i=0;i<getMainBoard().size();i++) {
					if(getMainBoard().get(i).getCodeName().equalsIgnoreCase(entered)) {
						if(getMainBoard().get(i).getTeam()=="blue"&&getMainBoard().get(i).getRevealed()!=true) {
							setBluCnt(getBluCnt() - 1);
							getMainBoard().get(i).setRevealed(true);
							setPrevTurn("red");
							setTurn("blue");
							curGuessCnt+=1;
							return "Correct Guess! Still Blue Teams turn";
						}
						else if(getMainBoard().get(i).getTeam()=="assassin") {
							
							return assassPressed();
						}
						else if(getMainBoard().get(i).getTeam()=="bystander") {
							getMainBoard().get(i).setRevealed(true);
							setPrevTurn("blue");
							setTurn("Buffer");
							curGuessCnt=0;
							return "Incorrect Guess. Red SpryMasters Turn.";
						}
						if(getMainBoard().get(i).getTeam()=="red"&&getMainBoard().get(i).getRevealed()!=true) {
							setRedCnt(getRedCnt() - 1);
							getMainBoard().get(i).setRevealed(true);
							setPrevTurn("blue");
							setTurn("Buffer");
							curGuessCnt=0;
							return "Incorrect Guess. Red SpryMasters Turn.";
						}
					}
				}
				setPrevTurn("blue");
					setTurn("Buffer");
					curGuessCnt=0;
					return "Incorrect Guess. Red SpryMasters Turn.";
				}
				
			}
	
