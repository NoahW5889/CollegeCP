package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
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

public class GUI implements Observer {
	
	private Driver _windowHolder;
	private Board _board;
	private JPanel _cardPanel;
	public static JTextField entry;
	private JPanel controlPanel;
	private JPanel turnPanel;
	private JPanel responsePanel;
	JMenu toolsMenu = new JMenu("File"); 
	JMenuBar menuBar = new JMenuBar();
	
	
	public GUI(Board b, JPanel mp, Driver driver) {
		_windowHolder = driver;
		_board = b;
		
		JPanel _mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		JMenuItem NewGame = new JMenuItem("New Game"); // Create a menu item.
		NewGame.addActionListener(new NewGameHandler(_board));        // Add listener to menu item.
		NewGame.setFont(new Font("Courier", Font.BOLD, 20));
		toolsMenu.add(NewGame);
		
		
		JMenuItem quitGame = new JMenuItem("Quit");   // Create a menu item.
		quitGame.addActionListener(new ExitHandler(_board));         // Add listener to menu item.
		quitGame.setFont(new Font("Courier", Font.BOLD, 20));
		toolsMenu.add(quitGame); // Add menu item to menu.
		
		
		toolsMenu.setFont(new Font("Courier", Font.BOLD, 24));
		menuBar.add(toolsMenu);
		_mainPanel.add(menuBar);
		
		
		_cardPanel = new JPanel();
		_cardPanel.setLayout(new GridLayout(5,5));
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		_mainPanel.add(middlePanel);
		
		responsePanel = new JPanel();
		responsePanel.setLayout(new BoxLayout(responsePanel, BoxLayout.X_AXIS));
		_mainPanel.add(responsePanel);
		
		
				
		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		_mainPanel.add(controlPanel);
		
		middlePanel.add(_cardPanel);
		
		turnPanel = new JPanel();
		turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.X_AXIS));
		controlPanel.add(turnPanel);
		
		JButton submit = new JButton("Submit");
		setButtonProperties(submit);
		controlPanel.add(submit);
		submit.addActionListener(new SubmitHandler(_board));
		
		entry = new JTextField();
		controlPanel.add(entry);
		
		JButton exit = new JButton("Exit");
		setButtonProperties(exit);
		controlPanel.add(exit);
		exit.addActionListener(new ExitHandler(_board));
		
		JButton newGame = new JButton("New Game");
		setButtonProperties(newGame);
		controlPanel.add(newGame);
		newGame.addActionListener(new NewGameHandler(_board));
		

		JButton endTurn = new JButton("End Turn");
		setButtonProperties(endTurn);
		controlPanel.add(endTurn);
		endTurn.addActionListener(new volendTurn(_board));
		
		_board.startGame();
		_board.addObserver(this);
	}
	
	@Override
	public void update() {
		
		
		_cardPanel.removeAll();
		ArrayList<Person> codeNames = _board.mainBoard;
		for(int i = 0; i<codeNames.size();i++) {
			if(codeNames.get(i).getRevealed()==false) {
			JButton add = new JButton("<html>"+codeNames.get(i).getCodeName()+"<br>Team: Unknown");
			setButtonProperties(add);
			add.addActionListener(new cardHandler(_board,codeNames.get(i).getCodeName()));
			_cardPanel.add(add);
			}
			else {
				JButton add = new JButton("<html>"+codeNames.get(i).getCodeName()+"<br>"+codeNames.get(i).getTeam());
				setButtonProperties(add);
				if(codeNames.get(i).getTeam()=="bystander")
					add.setBackground(Color.lightGray);
				else if(codeNames.get(i).getTeam()=="red")
					add.setBackground(Color.red);
				else
					add.setBackground(Color.blue);
				_cardPanel.add(add);
			}
		}
		
		responsePanel.removeAll();
		JLabel response = new JLabel(_board.reply);
		JLabel redLeft = new JLabel("Red: "+_board.redCnt);
		JLabel bluLeft = new JLabel("Blue: "+_board.bluCnt);
		setLabelProperties(response);
		setLabelProperties(redLeft);
		redLeft.setBackground(Color.red);
		setLabelProperties(bluLeft);
		bluLeft.setBackground(Color.blue);
		responsePanel.add(redLeft);
		responsePanel.add(response);
		responsePanel.add(bluLeft);
		
		turnPanel.removeAll();
		JLabel turn = new JLabel("Turn: "+_board.turn);
		setLabelProperties(turn);
		if(_board.turn=="red")
			turn.setBackground(Color.red);
		else
			turn.setBackground(Color.blue);
		turnPanel.add(turn);
		
		// This should be last statement of this method:
		updateJFrameIfNotHeadless();
	}
	
	public void updateJFrameIfNotHeadless() {
		if (_windowHolder != null) {
			_windowHolder.updateJFrame();
		}
	}


	
	public void setButtonProperties(JButton button) {
		button.setFont(new Font("Courier", Font.BOLD, 30));
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	}
	
	public void setLabelProperties(JLabel label) {
		label.setFont(new Font("Courier", Font.BOLD, 30));
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);
		label.setOpaque(true);
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	}
	
}
