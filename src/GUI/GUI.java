package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import code.Board;
import code.Driver;
import code.Person;

public class GUI {
	
	private Driver _windowHolder;
	private Board _board;
	private JPanel _cardPanel;
	
	public GUI(Board b, JPanel mp, Driver driver) {
		_windowHolder = driver;
		_board = b;
		
		JPanel _mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		_cardPanel = new JPanel();
		_cardPanel.setLayout(new GridLayout(5,5));
		_mainPanel.add(_cardPanel);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		_mainPanel.add(middlePanel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		_mainPanel.add(controlPanel);
		
		JButton submit = new JButton("Submit");
		setButtonProperties(submit);
		_mainPanel.add(submit);
		submit.addActionListener(new SubmitHandler(b));
		
		JButton exit = new JButton("Exit");
		setButtonProperties(exit);
		_mainPanel.add(exit);
		exit.addActionListener(new ExitHandler(b));
		
		JButton newGame = new JButton("New Game");
		setButtonProperties(newGame);
		_mainPanel.add(newGame);
		newGame.addActionListener(new NewGameHandler(b));
		
		b.startGame();
		update();
	}
	
public void update() {
		
		_cardPanel.removeAll();
		ArrayList<Person> codeNames = _board.mainBoard;
		for(int i = 0; i<codeNames.size();i++) {
			if(codeNames.get(i).getRevealed()==false) {
			JLabel add = new JLabel("<html>"+codeNames.get(i).getCodeName()+"<br>Team: Unknown");
			setLabelProperties(add);
			_cardPanel.add(add);
			}
			else {
				JLabel add = new JLabel("<html>"+codeNames.get(i).getCodeName()+"<br>"+codeNames.get(i).getTeam());
				setLabelProperties(add);
				_cardPanel.add(add);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
