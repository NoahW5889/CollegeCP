package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class bufHandler implements ActionListener {

	private Board _b;
	
	/**
	 * links Board class
	 * @param b the proper Board class to be used
	 */
	public bufHandler(Board b) {
		_b=b;
	}

	/**
	 * happens when action is performed, resulting in the setting of 
	 * turns to change along with reply and method called of
	 * notifyObservers
	 */
	public void actionPerformed(ActionEvent arg0) {
		_b.buffer();
	}

}
