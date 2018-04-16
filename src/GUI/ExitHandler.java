package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class ExitHandler implements ActionListener {
	private Board _b;

	/**
	 * links Board class
	 * @param b the proper Board class to be used
	 */
	public ExitHandler(Board b) {
	
		_b = b;;
	}

	/**
	 * happens when action is performed, resulting in exit method to trigger
	 */
	public void actionPerformed(ActionEvent e) {
		_b.exit();
	}
}
