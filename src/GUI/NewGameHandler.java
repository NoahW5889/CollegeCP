package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class NewGameHandler implements ActionListener {
	private Board _b;

	public NewGameHandler(Board b) {
	
		_b = b;;
	}

	public void actionPerformed(ActionEvent e) {
		_b.newGame();
	}
}