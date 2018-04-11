package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;
import code.Board;
import code.Person;

public class TestCases {

	Board board = new Board("src/GameWords.txt");	//Create/links board to this class, sends in file to read for codeNames
	String filename = "src/GameWords.txt";	//Used in testing to show where to find file for codeNames
	
	//Long list of all codeNames to assure they were read and put in list correctly
	String[] file = {"AFRICA","AGENT","AIR","ALIEN","ALPS","AMAZON","AMB"
			+ "ULANCE","AMERICA","ANGEL","ANTARCTICA","APPLE","ARM","ATLANTIS"
			,"AUSTRALIA","AZTEC","BACK","BALL","BAND","BANK","BAR","BARK","BAT"
			,"BATTERY","BEACH","BEAR","BEAT","BED","BEIJING","BELL","BELT","BERLIN"
			,"BERMUDA","BERRY","BILL","BLOCK","BOARD","BOLT","BOMB","BOND","BOOM"
			,"BOOT","BOTTLE","BOW","BOX","BRIDGE","BRUSH","BUCK","BUFFALO","BUG"
			,"BUGLE","BUTTON","CALF","CANADA","CAP","CAPITAL","CAR","CARD","CARROT"
			,"CASINO","CAST","CAT","CELL","CENTAUR","CENTER","CHAIR","CHANGE","CHARGE"
			,"CHECK","CHEST","CHICK","CHINA","CHOCOLATE","CHURCH","CIRCLE","CLIFF"
			,"CLOAK","CLUB","CODE","COLD","COMIC","COMPOUND","CONCERT","CONDUCTOR"
			,"CONTRACT","COOK","COPPER","COTTON","COURT","COVER","CRANE","CRASH"
			,"CRICKET","CROSS","CROWN","CYCLE","CZECH","DANCE","DATE","DAY","DEATH"
			,"DECK","DEGREE","DIAMOND","DICE","DINOSAUR","DISEASE","DOCTOR","DOG"
			,"DRAFT","DRAGON","DRESS","DRILL","DROP","DUCK","DWARF","EAGLE","EGYPT"
			,"EMBASSY","ENGINE","ENGLAND","EUROPE","EYE","FACE","FAIR","FALL","FAN"
			,"FENCE","FIELD","FIGHTER","FIGURE","FILE","FILM","FIRE","FISH","FLUTE"
			,"FLY","FOOT","FORCE","FOREST","FORK","FRANCE","GAME","GAS","GENIUS"
			,"GERMANY","GHOST","GIANT","GLASS","GLOVE","GOLD","GRACE","GRASS","GREECE"
			,"GREEN","GROUND","HAM","HAND","HAWK","HEAD","HEART","HELICOPTER","HIMALAYAS"
			,"HOLE","HOLLYWOOD","HONEY","HOOD","HOOK","HORN","HORSE","HORSESHOE","HOSPITAL"
			,"HOTEL","ICE","ICE CREAM","INDIA","IRON","IVORY","JACK","JAM","JET","JUPITER"
			,"KANGAROO","KETCHUP","KEY","KID","KING","KIWI","KNIFE","KNIGHT","LAB","LAP"
			,"LASER","LAWYER","LEAD","LEMON","LEPRECHAUN","LIFE","LIGHT","LIMOUSINE","LINE"
			,"LINK","LION","LITTER","LOCH NESS","LOCK","LOG","LONDON","LUCK","MAIL","MAMMOTH"
			,"MAPLE","MARBLE","MARCH","MASS","MATCH","MERCURY","MEXICO","MICROSCOPE"
			,"MILLIONAIRE","MINE","MINT","MISSILE","MODEL","MOLE","MOON","MOSCOW","MOUNT"
			,"MOUSE","MOUTH","MUG","NAIL","NEEDLE","NET","NEW YORK","NIGHT","NINJA","NOTE"
			,"NOVEL","NURSE","NUT","OCTOPUS","OIL","OLIVE","OLYMPUS","OPERA","ORANGE","ORGAN"
			,"PALM","PAN","PANTS","PAPER","PARACHUTE","PARK","PART","PASS","PASTE","PENGUIN"
			,"PHOENIX","PIANO","PIE","PILOT","PIN","PIPE","PIRATE","PISTOL","PIT","PITCH","PLANE"
			,"PLASTIC","PLATE","PLATYPUS","PLAY","PLOT","POINT","POISON","POLE","POLICE","POOL"
			,"PORT","POST","POUND","PRESS","PRINCESS","PUMPKIN","PUPIL","PYRAMID","QUEEN","RABBIT"
			,"RACKET","RAY","REVOLUTION","RING","ROBIN","ROBOT","ROCK","ROME","ROOT","ROSE"
			,"ROULETTE","ROUND","ROW","RULER","SATELLITE","SATURN","SCALE","SCHOOL","SCIENTIST"
			,"SCORPION","SCREEN","SCUBA DIVER","SEAL","SERVER","SHADOW","SHAKESPEARE","SHARK"
			,"SHIP","SHOE","SHOP","SHOT","SINK","SKYSCRAPER","SLIP","SLUG","SMUGGLER","SNOW"
			,"SNOWMAN","SOCK","SOLDIER","SOUL","SOUND","SPACE","SPELL","SPIDER","SPIKE","SPINE"
			,"SPOT","SPRING","SPY","SQUARE","STADIUM","STAFF","STAR","STATE","STICK","STOCK"
			,"STRAW","STREAM","STRIKE","STRING","SUB","SUIT","SUPERHERO","SWING","SWITCH","TABLE"
			,"TABLET","TAG","TAIL","TAP","TEACHER","TELESCOPE","TEMPLE","THEATER","THIEF","THUMB"
			,"TICK","TIE","TIME","TOKYO","TOOTH","TORCH","TOWER","TRACK","TRAIN","TRIANGLE","TRIP"
			,"TRUNK","TUBE","TURKEY","UNDERTAKER","UNICORN","VACUUM","VAN","VET","WAKE","WALL"
			,"WAR","WASHER","WASHINGTON","WATCH","WATER","WAVE","WEB","WELL","WHALE","WHIP","WIND"
			,"WITCH","WORM","YARD","area","book","business","case","child","company","country"
			,"day","eye","fact","family","government","group","hand","home","job","life","lot"
			,"man","money","month","mother","Mr","night","number","part","people","place"
			,"point","problem","program","question","right","room","school","state","story"
			,"student","study","system","thing","time","water","way","week","woman","word"
			,"work","world","year"};
	ArrayList<String> codeNames = new ArrayList<String>();	//used in testing to store codeNames
	int bluPos = 0;	//used to hold blue agent position
	int redPos = 0;	//used to hold red agent position
	int assPos = 0;	//used to hold assassin position
	int byPos = 0;	//used to hold bystander position

	/*Creates arrayList to compare to readCSVFile in Board class
	 * @param x 	holds String[] list to add into ArrayList<String>
	 * @return ArrayList<String> of codeNames
	 */
	public ArrayList<String> createArrayList(String[] x) {
		for(int i=0;i<x.length;i++	) {
			codeNames.add(x[i]);
		}
		return codeNames;
	}

	/*
	 * used to test if any nulls are in board.list
	 * @param selected	String list holding selected codeNames
	 * @return false if the list has a null in it
	 * @return true if the list is null free
	 */
	public boolean noNull(String[] selected) {	
		for(int i=0;i<board.list.length;i++) {
			if(board.list[i]==null||board.list[i].equals(null)||board.list[i].trim().isEmpty())
				return false;
		}
		return true;
	}

	
	/*
	 * assures there are no repeated codeNames in board.list
	 * @param input	holds String list of codeNames
	 * @return false if there is a repeated codeName
	 * @return true	if there are no repeated codeNames
	 */
	public boolean noDoubles(String[] input) {	
		for(int i=0;i<board.list.length;i++) {
			String check = board.list[i];
			for(int q=0;q<board.list.length;q++) {
				if(board.list[q].equals(check)&&q!=i)
					return false;
			}
		}
		return true;
	}
	
	/*
	 * assures the correct team size for string entered ex. "red" should equal 9
	 * @param x	holds string of requested team size
	 * @return size of requested team
	 */
	public int teamSize(String x) {	
		int size = 0;
		for(int i=0;i<board.mainBoard.size();i++) {
			if(x.equalsIgnoreCase(board.mainBoard.get(i).getTeam()))
				size++;
			}
		return size;
	}
	
	/*
	 * assures the board was shuffled, compares original to after shuffle board
	 * @param original holds original order of board.mainBoard selected codeNames list
	 * @return false if shuffle() method did not shuffle/randomize list
	 * @return true if shuffle() method did shuffle/randomize list
	 */
	public boolean shuffleSuccess() {	
		ArrayList<Person> original = board.mainBoard;
		board.startGame();
		if(original.toString()==(board.mainBoard.toString()))
			return false;
		else
			return true;
	}
	
	/*
	 *gets position of a blue agent for testing 
	 * @param sets bluPos to hold position of a blue agent
	 */
	public void bluePosition() {	//
		for(int i=0;i<board.mainBoard.size();i++) {
			if(board.mainBoard.get(i).getTeam()=="blue")
				bluPos=i;
		}
	}
	
	/*
	 *gets position of a red agent for testing 
	 * @param sets redPos to hold position of a red agent
	 */
	public void redPosition() {
		for(int i=0;i<board.mainBoard.size();i++) {
			if(board.mainBoard.get(i).getTeam()=="red")
				redPos=i;
		}
	}
	
	/*
	 *gets position of a assassin for testing 
	 * @param sets assPos to hold position of a assassin
	 */
	public void assassinPosition() {
		for(int i=0;i<board.mainBoard.size();i++) {
			if(board.mainBoard.get(i).getTeam()=="assassin")
				assPos=i;
		}
	}
	
	/*
	 *gets position of a bystander for testing 
	 * @param sets byPos to hold position of a bystander
	 */
	public void bystanderPosition() {
		for(int i=0;i<board.mainBoard.size();i++) {
			if(board.mainBoard.get(i).getTeam()=="bystander")
				byPos=i;
		}
	}

	@Test
	public void testStartGame() {	//tests that the game sets up correctly
		ArrayList<String> codeNames = new ArrayList<String>();	//new ArrayList to set original unshuffled codeNames to
		codeNames.addAll(board.codeNames);	//sets new ArrayList to original unshuffled CodeNames
		board.startGame();	//calls startGame() method
		assertNotEquals(codeNames, board.codeNames);	//Makes sure the codeNames ArrayList got shuffled
		assertEquals(board.mainBoard.size(),25);	//Makes sure board size is 25
		assertEquals(board.turn,"red");	//Makes sure starting turn is red
	}
	
	@Test
	public void testCreateList(){	//tests to make sure the list was created and filled successfully
		assertTrue(board.list.length==25); //Tests to make sure list has selected 25 names
		board.createList();// Must create list before test for noNull
		assertTrue(noNull(board.list));// Testing to make sure there are no nulls in list
		assertTrue(noDoubles(board.list));// Assures there are no repeated codeNames
	}
	
	@Test
	public void testFillBoard() {	//tests the fillBoard method to make sure it is filled/created correctly
		board.createList();// Must create list before test for noNull
		board.fillBoard(); // Fills board with persons
		assertTrue(board.mainBoard.size()==25); //Testing Board Size (5x5=25)
		
	}
	
	@Test
	public void testShuffle() {	//tests the shuffle method, makes sure the board gets randomized
		board.createList();// Must create list before test for shuffle
		board.fillBoard(); // Fills board with persons
		assertTrue(shuffleSuccess()); //makes sure the board got shuffled
	}
	
	@Test
	public void testReadCSVFile(){	//tests readCSVFile to ensure the file filled with codeNames was put into a list
		assertEquals(board.readCSVFile(filename),createArrayList(file)); // Tests of Code Names file was read correctly
	}

	@Test
	public void testChoose() {	//tests the choose method to make sure the proper response is return based upon choice
		board.createList();// Must create list before test for noNull
		board.fillBoard(); // Fills board with persons
		redPosition();	//gets position of red agent for testing
		bluePosition();	//gets position of blue agent for testing
		assassinPosition();	//gets position of assassin red agent for testing
		bystanderPosition();	//gets position of bystander for testing
		
		assertEquals(board.choose(null),"Invalid Entry. Try Again"); //enters null as a choice
		assertEquals(board.choose(""),"Invalid Entry. Try Again");	//enters empty string as choice
		assertEquals(board.choose("  "),"Invalid Entry. Try Again");	//enters spaces as choice
		assertEquals(board.choose("rules"),"===============\nRules.\n===============\nPlease refer to video."	//enters rules as choice
				+ "\nhttps://www.youtube.com/watch?v=sy0AnMDcap0&t=20s"
				+ "\n===============");
		
		board.turn="red";	//sets turn to red team
		assertEquals(board.choose("skip"),"Red Team Skips their turn.");	//tests skip turn choice
		assertEquals(board.turn,"blue");	//tests making sure skip method changed turns
		board.turn="blue";	//sets turn to blue team
		assertEquals(board.choose("skip"),"Blue Team Skips their turn.");	//tests skip turn choice
		assertEquals(board.turn,"red");	//tests making sure skip method changed turns
		
		board.turn="red";	//sets turn to red team
		assertEquals(board.choose(board.mainBoard.get(redPos).getCodeName()),"Correct Guess!");	//tests red turn choosing red agent
		assertEquals(board.choose(board.mainBoard.get(bluPos).getCodeName()),"Incorrect Guess.");	//tests red turn choosing blue agent
		assertEquals(board.turn,"blue");	//tests making sure incorrect guess changed turns
		board.turn="red";	//sets turn to red team
		assertEquals(board.choose(board.mainBoard.get(assPos).getCodeName()),"Assassin chosen by Red Team! Blue Team Wins!");	//tests red turn choosing assassin
		assertEquals(board.choose(board.mainBoard.get(byPos).getCodeName()),"Incorrect, Bystander revealed");	//tests red turn choosing bystander
		assertEquals(board.turn,"blue");	//tests making sure incorrect guess changed turns
		board.turn="blue";	//sets turn to blue team
		assertEquals(board.choose(board.mainBoard.get(bluPos).getCodeName()),"Correct Guess!");	//tests blue turn choosing blue agent
		assertEquals(board.choose(board.mainBoard.get(redPos).getCodeName()),"Incorrect Guess.");	//tests blue turn choosing red agent
		assertEquals(board.turn,"red");	//tests making sure incorrect guess changed turns
		board.turn="blue";	//sets turn to blue team
		assertEquals(board.choose(board.mainBoard.get(assPos).getCodeName()),"Assassin chosen by Blue Team! Red Team Wins!");	//tests blue turn choosing assassin
		assertEquals(board.choose(board.mainBoard.get(byPos).getCodeName()),"Incorrect, Bystander revealed");	//tests blue turn choosing bystander
		assertEquals(board.turn,"red");	//tests making sure incorrect guess changed turns
	}
	
	@Test
	public void testGameState() {	//tests game state (win, lose, or not finished)
		assertEquals(board.gameState(),"No one has won the game.");	//tests at start of game
		board.redCnt=0;	//adjusts red agent count to 0
		assertEquals(board.gameState(),"The game has been won.");	//tests with 0 red agents not revealed
		board.redCnt=9;	//adjusts red agent count to 9
		board.bluCnt=0;	//adjusts blue agent count to 0
		assertEquals(board.gameState(),"The game has been won.");	//tests with 0 blue agents not revealed
		board.bluCnt=8;	//adjusts blue agent count to 8
		board.assCnt=0;	//adjusts assassin count to 0
		assertEquals(board.gameState(),"The game has been won.");	//tests with 0 assassin agents not revealed
	}
	
	@Test
	public void testPersonClass() {
		Person test = new Person("test codeName", "test team");	//creates new person using person class and sets name and team
		assertEquals(test.getCodeName(),"test codeName");	//tests that codeName was set correctly
		assertEquals(test.getTeam(),"test team");	//tests that team was set correctly
		assertFalse(test.getRevealed());	//tests that revealed status is false
		test.setRevealed(true);	//sets revealed status to true
		assertTrue(test.getRevealed());	//tests that revealed status is now true
		Person test2 = new Person(null,null);	//creates new person with both nulls to ensure no crashes
		assertEquals(test2.getCodeName(),null);	//assures no crashes with null as codeName
		assertEquals(test2.getTeam(),null);	//assures no crashes with null as team
		assertFalse(test2.getRevealed());	//tests that reveal status is false
		test2.setRevealed(true);	//sets reveal status to true
		assertTrue(test2.getRevealed());	//tests that reveal status is true
	}
	
	@Test
	public void testAssassinPressed() {	//tests when assassin is pressed
		board.turn="red";	//changes turn to red
		assertEquals(board.assassPressed(),"Assassin chosen by Red Team! Blue Team Wins!");	//tests when assassin is chosen by red team
		board.turn="blue";	//changes turn to blue
		assertEquals(board.assassPressed(),"Assassin chosen by Blue Team! Red Team Wins!");	//tests when assassin is chosen by blue team
	}
	
	@Test
	public void testTeamSize() {	//tests to make sure there are the correct number of agents per team along with bystanders and assassins
		board.createList();// Must create list before test for team sizes
		board.fillBoard(); // Fills board with persons
		assertEquals(9,teamSize("red")); //assures 9 red agents
		assertEquals(8,teamSize("blue")); //assures 8 blue agents
		assertEquals(7,teamSize("bystander")); //assures 7 bystanders 
		assertEquals(1,teamSize("assassin")); //assures 1 assassin
	}
	
	@Test
	public void testClues() {	//tests if legal clue method works
		board.fillBoard();	// Fills board with persons
	/*	assertFalse(board.validClue(board.list[(int) (Math.random()*25)]));	//chooses random codeName from lists and tests it as clue
		assertTrue(board.validClue("asjdkfa 123"));	//random testing
		assertFalse(board.validClue(null));	//testing null input
		assertFalse(board.validClue(""));	//testing empty string input
		*/
	}
}
