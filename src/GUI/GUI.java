package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import code.Board;
import code.Driver;
import code.Observer;
import code.Person;

/**
 * @author Noah Wutz, Maurice Campbell, Fulton Lin
 */
public class GUI implements Observer {
	
	private Driver _windowHolder;
	private Board _board;
	private JPanel _mainPanel;
	private JPanel _cardPanel;
	public static JTextField entry;
	private JPanel controlPanel;
	private JPanel turnPanel;
	private JPanel responsePanel;
	private JPanel startMenu;
	private JMenu toolsMenu; 
	private JMenuBar menuBar;
	private String winPhase=null;
	private JButton exit;
	private JButton newGame;
	private JPanel middlePanel;
	private JButton submit;
	private JButton endTurn;
	private JLabel curTurCnt;
	private JLabel turn;
	private JPanel menu;
	private JPanel rulesPanel;
	private JLabel rule;
	
	
	/**
	 * Class Construcot setting up, creating, labeling, and setting JButtons, JLabels,
	 * Jpanels, etc... Ties Board class into this class.
	 * 
	 * @param b	Ties Board class into here for implemention of GUI/creation
	 * @param mp	Brings in mainPanel from driver to be ground or GUI
	 * @param driver	Connects Driver to class for implementation of GUI/creation
	 */
	public GUI(Board b, JPanel mp, Driver driver) {
		_windowHolder = driver;
		_board = b;
		
		_mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		toolsMenu = new JMenu("File");
		menuBar = new JMenuBar();
		 
		JMenuItem NewGame = new JMenuItem("New Game 2 Players");
		NewGame.addActionListener(new NewGameHandler(_board)); 
		NewGame.setFont(new Font("Courier", Font.BOLD, 20));
		toolsMenu.add(NewGame);
		
		JMenuItem NewGame3 = new JMenuItem("New Game 3 Players");
		NewGame3.addActionListener(new newGameHandler3(3,_board)); 
		NewGame3.setFont(new Font("Courier", Font.BOLD, 20));
		toolsMenu.add(NewGame3);
		
		JMenuItem menuItem = new JMenuItem("Menu");
		menuItem.addActionListener(new menuHandler(_board)); 
		menuItem.setFont(new Font("Courier", Font.BOLD, 20));
		toolsMenu.add(menuItem);
		
		JMenuItem rules = new JMenuItem("Rules");
		rules.addActionListener(new RulesHandler(_board));
		rules.setFont(new Font("Courier", Font.BOLD, 20));
		toolsMenu.add(rules);
		
		JMenuItem quitGame = new JMenuItem("Quit");
		quitGame.addActionListener(new ExitHandler(_board));
		quitGame.setFont(new Font("Courier", Font.BOLD, 20));
		toolsMenu.add(quitGame);
		
		toolsMenu.setFont(new Font("Courier", Font.BOLD, 24));
		menuBar.add(toolsMenu);
		_mainPanel.add(menuBar);
		
		startMenu = new JPanel();
		startMenu.setFont(new Font("COurier", Font.BOLD, 24));
		_mainPanel.add(startMenu);
		
		_cardPanel = new JPanel();
		_cardPanel.setLayout(new GridLayout(5,5));
		
		 middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		_mainPanel.add(middlePanel);
		
		responsePanel = new JPanel();
		responsePanel.setLayout(new BoxLayout(responsePanel, BoxLayout.X_AXIS));
		_mainPanel.add(responsePanel);
				
		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		_mainPanel.add(controlPanel);
		
		middlePanel.add(_cardPanel);
		
		menu = new JPanel();
		menu.setLayout(new GridLayout(10,7));
		menu.setBackground(Color.white);
		menuSetup();
		menu.setVisible(false);
		_mainPanel.add(menu);
		
		rulesPanel = new JPanel();
		rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.X_AXIS));
		rulesPanel.setBackground(Color.white);
		rulesPanel.setVisible(false);
		_mainPanel.add(rulesPanel);
		
		rule = new JLabel("<html>==========================Rules.=========================" + 
				 "<br>Please refer to Link." + 
				 "<br>https://czechgames.com/files/rules/codenames-rules-en.pdf" + 
				 "<br>==========================================================");
		
		setLabelProperties(rule);
		rulesPanel.add(rule);
		JButton back = new JButton("Back");
		back.addActionListener(new menuHandler(_board));
		setButtonProperties(back);
		rulesPanel.add(back);
		
		
		turnPanel = new JPanel();
		turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.X_AXIS));
		controlPanel.add(turnPanel);
		
		submit = new JButton("Submit");
		setButtonProperties(submit);
		controlPanel.add(submit);
		submit.addActionListener(new SubmitHandler(_board));
		
		entry = new JTextField();
		entry.addActionListener(new enterHandler(_board));
			
	
		controlPanel.add(entry);
		
		exit = new JButton("Exit");
		setButtonProperties(exit);
		exit.addActionListener(new ExitHandler(_board));
		
		newGame = new JButton("New Game");
		setButtonProperties(newGame);
		newGame.addActionListener(new NewGameHandler(_board));
		

		endTurn = new JButton("End Turn");
		setButtonProperties(endTurn);
		controlPanel.add(endTurn);
		endTurn.addActionListener(new volendTurn(_board));
		
		
		_board.startGame();
		_board.addObserver(this);
	}
	
	/**
	 * Called when GUI is needed to update in order to display
	 * the proper texts, buttons, etc...
	 */
	@Override
	public void update() {
	
		if(_board.getTurn()=="rules")
			rules();
		
		else if(_board.getTurn()=="menu")
			menu();
		
		else if(_board.getTurn()=="Buffer") 
			buffer();
		
		
		else if(_board.getTurn()=="egg") 
			egg();

		else if(_board.getRedCnt()==0) {
			entry.setEditable(false);
			_cardPanel.removeAll();
				winPhase="red";
				winner();
		}
		else if(_board.getBluCnt()==0) {
			entry.setEditable(false);
			_cardPanel.removeAll();
			winPhase="blue";
			winner();
		}
		else if(_board.getGrnCnt()==0&&_board.getPlayerCnt()==3) {
			entry.setEditable(false);
			_cardPanel.removeAll();
			winPhase="green";
			winner();
		}
		
		else if(_board.getAssCnt()<=0&&_board.getPlayerCnt()==3) {
			if(_board.getElimPlayer().contains("red")==false) {
				entry.setEditable(false);
				_cardPanel.removeAll();
				winPhase="red";
				winner();
			}
			else if(_board.getElimPlayer().contains("green")==false) {
				entry.setEditable(false);
				_cardPanel.removeAll();
				winPhase="green";
				winner();
				
			}
			else{
				entry.setEditable(false);
				_cardPanel.removeAll();
				winPhase="blue";
				winner();
				
			}
		}
		else if(_board.getAssCnt()<2&&_board.getAssCnt()>0&&_board.getPlayerCnt()==3&&_board.getTurn()=="Blue SpyMaster"&&_board.getElimPlayer().contains("blue")) {
			_board.elimReply();
			_board.setTurn("Green SpyMaster");
			_board.notifyObservers();
		}
		
		else if(_board.getAssCnt()<2&&_board.getAssCnt()>0&&_board.getPlayerCnt()==3&&_board.getTurn()=="Red SpyMaster"&&_board.getElimPlayer().contains("red")) {
			_board.elimReply();
			_board.setTurn("Blue SpyMaster");
			_board.notifyObservers();
		}
		
		else if(_board.getAssCnt()<2&&_board.getAssCnt()>0&&_board.getPlayerCnt()==3&&_board.getTurn()=="Green SpyMaster"&&_board.getElimPlayer().contains("green")) {
			_board.elimReply();
			_board.setTurn("Red SpyMaster");
			_board.notifyObservers();
		}
		
		
		else if(_board.getAssCnt()==0&&_board.getPlayerCnt()!=3) {
		
			
			entry.setEditable(false);
			_cardPanel.removeAll();
			if(_board.getTurn()=="red")
				winPhase="blue";
			else
				winPhase="red";
			winner();
			
		}
		
		else {
			rulesPanel.setVisible(false);
			menu.setVisible(false);
			_cardPanel.setVisible(true);
			entry.setEditable(true);
			responsePanel.setVisible(true);
			submit.setVisible(true);
			entry.setVisible(true);
			turnPanel.setVisible(true);
			endTurn.setVisible(true);
			controlPanel.setVisible(true);
			
			controlPanel.remove(exit);
			controlPanel.remove(newGame);
		if (_board.getTurn()=="Red SpyMaster"||_board.getTurn()=="Blue SpyMaster"||_board.getTurn()=="Green SpyMaster") 
				spyBoard();
	
		else 
			playerBoard();	
		}
		
		resetOther();
		// This should be last statement of this method:
		updateJFrameIfNotHeadless();
	}
	
	/**
	 * Resets @param responsePanel and @param turnPanel in order to succesfully and fully update
	 * the game board to display the proper items. sets @param curClue and @param curTurCnt to
	 * new JLabels displaying current clue, guess, spys left, and max guess.
	 */
	private void resetOther() {
		responsePanel.removeAll();
		
		JLabel response = new JLabel(_board.getReply());
		JLabel curClu = null;
		if(_board.getTurn() == "red" || _board.getTurn() == "blue") {
			curClu = new JLabel("Current Clue: "+_board.getCurClue().replaceAll("[^a-zA-Z0-9 ]", "")+"||Max Guess: "+_board.getMaxGuess());
			curTurCnt = new JLabel("Spy's left: "+_board.currentTurnCnt()+"||Current Guess: "+_board.getCurGuessCnt());
		}else {
			curClu = new JLabel("Current Clue: ");
			curTurCnt = new JLabel("Spy's left: "+_board.currentTurnCnt());
		}
		
		setLabelProperties(response);
		setLabelProperties(curClu);
		setLabelProperties(curTurCnt);
		curClu.setBackground(Color.lightGray);
		curTurCnt.setBackground(Color.lightGray);
		curTurCnt.setFont(new Font("Courier", Font.BOLD, 27));
		curClu.setFont(new Font("Courier", Font.BOLD, 27));
		responsePanel.add(curClu);
		responsePanel.add(response);
		responsePanel.add(curTurCnt);
		
		turnPanel.removeAll();
		turn = new JLabel("Turn: "+_board.getTurn());
		setLabelProperties(turn);
		if(_board.getTurn()=="red" || _board.getTurn() == "Red SpyMaster")
			turn.setBackground(Color.red);
		else if(_board.getTurn()=="green" || _board.getTurn() == "Green SpyMaster")
			turn.setBackground(Color.green);
		else if(_board.getTurn()=="Buffer")
			turn.setBackground(Color.gray);
		else
			turn.setBackground(Color.blue);
		turnPanel.add(turn);
	
	}
	/**
	 * Resets @param _cardPanel viewed by red/blue guesser. Recreates board adding
	 * buttons(@param add) for unrevealed characters and labels for revealed characters along with displaying
	 * team if revealed, otherwise just displaying codename.
	 */
	private void playerBoard() {
		_cardPanel.removeAll();
		ArrayList<Person> codeNames = _board.getMainBoard();
		for(int i = 0; i<codeNames.size();i++) {
			if(codeNames.get(i).getRevealed()==false) {
			JButton add = new JButton("<html>"+"<br>"+codeNames.get(i).getCodeName());
			setButtonProperties(add);
			if(_board.getKamiWords().contains(codeNames.get(i).getCodeName())) {
				add.addActionListener(new easterEggHandler(_board));
				}
			else{
			add.addActionListener(new cardHandler(_board,codeNames.get(i).getCodeName()));
				}
			_cardPanel.add(add);
			}
			else {
				JLabel add;
				if(codeNames.get(i).getTeam()=="red")
					add = new JLabel("Red Agent");
				else if(codeNames.get(i).getTeam()=="blue")
					add = new JLabel("Blue Agent");
				else if(codeNames.get(i).getTeam()=="bystander")
					add = new JLabel("Innocent Bystander");
				else if(codeNames.get(i).getTeam()=="green")
					add = new JLabel("Green Agent");
				else
					add = new JLabel("Assassin");
				setLabelProperties(add);
				if(codeNames.get(i).getTeam()=="bystander")
					add.setBackground(Color.lightGray);
				else if(codeNames.get(i).getTeam()=="red")
					add.setBackground(Color.red);
				else if(codeNames.get(i).getTeam()=="green")
					add.setBackground(Color.green);
				else
					add.setBackground(Color.blue);
				_cardPanel.add(add);
			}
		}
	}
	
	private void menuSetup() {
		JLabel welcome = new JLabel("     Welcome");
		setLabelProperties(welcome);
		welcome.setBorder(null);
		JLabel to = new JLabel("      to");
		setLabelProperties(to);
		to.setBorder(null);
		JLabel codeNames = new JLabel("CodeNames!");
		setLabelProperties(codeNames);
		codeNames.setBorder(null);
		JLabel author = new JLabel("Coded By:");
		setLabelProperties(author);
		author.setBorder(null);
		JLabel noah = new JLabel("Noah Wutz");
		setLabelProperties(noah);
		noah.setBorder(null);
		JLabel maurice = new JLabel("Maurice C.");
		setLabelProperties(maurice);
		maurice.setBorder(null);
		JLabel fulton = new JLabel("Fulton Lin");
		setLabelProperties(fulton);
		fulton.setBorder(null);
		JLabel tim = new JLabel("Tim");
		setLabelProperties(tim);
		tim.setBorder(null);
		JLabel select = new JLabel("Select Number");
		setLabelProperties(select);
		select.setBorder(null);
		JLabel players = new JLabel("  Of Players");
		setLabelProperties(players);
		players.setBorder(null);
		JButton twoPlay = new JButton("2 Players");
		twoPlay.addActionListener(new NewGameHandler(_board));
		setButtonProperties(twoPlay);
		JButton threePlay = new JButton("3 Players");
		threePlay.addActionListener(new newGameHandler3(3,_board));
		setButtonProperties(threePlay);
		JButton rules = new JButton("Rules");
		rules.addActionListener(new RulesHandler(_board));
		setButtonProperties(rules);
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ExitHandler(_board));
		setButtonProperties(quit);
		
		for(int i=0;i<68;i++) {
			if(i==2){
				menu.add(welcome);
				menu.add(to);
				menu.add(codeNames);
			}
			else if(i==19)
				menu.add(author);
			else if(i==26)
				menu.add(noah);
			else if(i==33)
				menu.add(maurice);
			else if(i==40)
				menu.add(fulton);
			else if(i==47)
				menu.add(tim);
			else if(i==15)
				menu.add(select);
			else if(i==22)
				menu.add(players);
			else if(i==36)
				menu.add(twoPlay);
			else if(i==43)
				menu.add(threePlay);
			else if(i==57)
				menu.add(rules);
			else if(i==64)
				menu.add(quit);
			else {
			JLabel space = new JLabel("        ");
			setLabelProperties(space);
			space.setVisible(false);
			menu.add(space);
			}
		}
	}
	
private void menu() {
		rulesPanel.setVisible(false);
		_cardPanel.setVisible(false);
		responsePanel.setVisible(false);
		submit.setVisible(false);
		entry.setVisible(false);
		turnPanel.setVisible(false);
		endTurn.setVisible(false);
		controlPanel.setVisible(false);
		menu.setVisible(true);
	}
	
private void rules() {
	_cardPanel.setVisible(false);
	responsePanel.setVisible(false);
	submit.setVisible(false);
	entry.setVisible(false);
	turnPanel.setVisible(false);
	endTurn.setVisible(false);
	controlPanel.setVisible(false);
	menu.setVisible(false);
	rulesPanel.setVisible(true);
}

	/**
	 * Resets @param _cardPanel viewed by red/blue spyMaster. Recreates board adding
	 * labels(@param add) for unrevealed characters and labels for revealed characters along with displaying
	 * team and codenames for spymasters to view.
	 */
	private void spyBoard() {
		_cardPanel.removeAll();
		ArrayList<Person> codeNames = _board.getMainBoard();
		
		
		for(int i = 0; i<codeNames.size();i++) {
			JLabel add;
			if(codeNames.get(i).getRevealed()==false) 
				add = new JLabel("<html>"+codeNames.get(i).getCodeName()+"<br>Team: "+codeNames.get(i).getTeam());
			else {
				if(codeNames.get(i).getTeam()=="red")
					add = new JLabel("Red Agent");
				else if(codeNames.get(i).getTeam()=="blue")
					add = new JLabel("Blue Agent");
				else if(codeNames.get(i).getTeam()=="bystander")
					add = new JLabel("Innocent Bystander");
				else if(codeNames.get(i).getTeam()=="green")
					add = new JLabel("Green Agent");
				else
					add = new JLabel("Assassin");
			}
				setLabelProperties(add);
				if(codeNames.get(i).getTeam()=="bystander")
					add.setBackground(Color.lightGray);
				else if(codeNames.get(i).getTeam()=="red")
					add.setBackground(Color.red);
				else if(codeNames.get(i).getTeam()=="blue")
					add.setBackground(Color.blue);
				else if(codeNames.get(i).getTeam()=="green")
					add.setBackground(Color.green);
				else {
					add.setBackground(Color.black);
					add.setForeground(Color.WHITE);
					
				}
				if(codeNames.get(i).getRevealed()==true) {
					add.setBackground(Color.magenta);
					
				}
				if(_board.getKamiWords().contains(codeNames.get(i).getCodeName())) {
					add.setBackground(Color.yellow);
				}
				_cardPanel.add(add);
			
		}

	}

	/**
	 * Removes all cards on @param _cardPanel and replaces them with @param winner (JLabel)
	 * displaying "Easter Eggs Are Cool :)" alternating in colors representing teams. This 
	 * only happens after game has been won and proper
	 * JButton on win screen has been pressed. sets entry field to Editable(false).
	 */
	private void egg() {
		_cardPanel.removeAll();
		entry.setEditable(false);
		for(int i=0;i<25;i++) {
			JLabel winner = new JLabel("<html>Easter Eggs<br>Are Cool :)");
			setLabelProperties(winner);
			if(i%2==0)
				winner.setBackground(Color.red);
			else
				winner.setBackground(Color.blue);
			_cardPanel.add(winner);
		}
	}

	/**
	 * This is a buffer screen used to prevent the guessers from seeing the 
	 * board (@param _cardPanel) after guessing incorrectly, ending turn, or
	 * reaching max guesses. This replaces all cards on @param _cardPanel with reacurring
	 * JButtons(@param buff) that all display "Press to start next turn.". Happens
	 * after guessers turns only.
	 */
	private void buffer() {	
		entry.setEditable(false);
		_cardPanel.removeAll();
		for(int i=0;i<25;i++) {
			JButton buff = new JButton("<html>Press to start<br>next turn.");
			buff.addActionListener(new bufHandler(_board));
			setButtonProperties(buff);
			if(_board.getPrevTurn()=="red"&&_board.checkGuess(_board.getLastGuess())==false)
				buff.setBackground(Color.blue);
			else if(_board.getPrevTurn()=="green"&&_board.checkGuess(_board.getLastGuess())==false)
				buff.setBackground(Color.red);
			
			else
				if(_board.getPlayerCnt()==3) {
					buff.setBackground(Color.green);
				}
				else buff.setBackground(Color.red);
			_cardPanel.add(buff);
		}
	}

	/**
	 * This only happens when a winner has been determined. Removes all cards
	 * from @param _card Panel, sets visability of @params submit, responsePanel,
	 * entry, turnPanel, and endTurn to false. Sets reply displayed then adds new
	 * JButtons(@param winner) onto @_cardPanel displaying which team has won.
	 * This also sets special random button to link to easter egg method.
	 */
	private void winner() {
		Random ran = new Random();
		int secret = ran.nextInt(25);
		_cardPanel.removeAll();
		submit.setVisible(false);
		responsePanel.setVisible(false);
		entry.setVisible(false);
		turnPanel.setVisible(false);
		endTurn.setVisible(false);
		_board.setReply(winPhase);
		for(int i=0;i<25;i++) {
			if(i==secret) {
				JButton winner = new JButton(winPhase.toUpperCase()+" TEAM WINS!");
				setButtonProperties(winner);
				if(winPhase=="red")
					winner.setBackground(Color.red);
				else if(winPhase=="green")
				winner.setBackground(Color.green);
				else
					winner.setBackground(Color.blue);
				winner.addActionListener(new eggHandler2(_board));
				_cardPanel.add(winner);
			}
			else {
			JButton winner = new JButton(winPhase.toUpperCase()+" TEAM WINS!!!");
			setButtonProperties(winner);
			if(winPhase=="red")
				winner.setBackground(Color.red);
			else if(winPhase=="green")
				winner.setBackground(Color.green);
			else
				winner.setBackground(Color.blue);
			_cardPanel.add(winner);
			}
			controlPanel.add(exit);
			controlPanel.add(newGame);
		}
	}

	/**
	 * updates JFramed] of @param _windowHolder
	 */
	public void updateJFrameIfNotHeadless() {
		if (_windowHolder != null) {
			_windowHolder.updateJFrame();
		}
	}


	/**
	 * sets JButtons design up making them more appealing
	 * Font: Courier, Bold, size: 30
	 * @param button	the button to be set up this way
	 */
	public void setButtonProperties(JButton button) {
		button.setFont(new Font("Courier", Font.BOLD, 30));
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	}
	
	/**
	 * sets JLabels design up making them more appealing
	 * Font: Courier, Bold, size: 30
	 * @param label	the label to be set up this way
	 */
	public void setLabelProperties(JLabel label) {
		label.setFont(new Font("Courier", Font.BOLD, 30));
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);
		label.setOpaque(true);
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	}
	
}
