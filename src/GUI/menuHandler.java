package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class menuHandler implements ActionListener {
	private Board _b;
	
	public menuHandler(Board b) {
		_b=b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_b.setTurn("menu");
		_b.newGame();
		_b.notifyObservers();
		
	}

}
