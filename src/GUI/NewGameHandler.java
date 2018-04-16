package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class NewGameHandler implements ActionListener {
	private Board _b;

	/**
	 * links Board class
	 * @param b the proper Board class to be used
	 */
	public NewGameHandler(Board b) {
	
		_b = b;;
	}

	/**
	 * happens when action is performed, resulting in newGame method to trigger
	 */
	public void actionPerformed(ActionEvent e) {
		_b.newGame();
	}
}