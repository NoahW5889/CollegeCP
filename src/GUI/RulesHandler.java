package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class RulesHandler implements ActionListener {

	private Board _b;
	
	/**
	 * links Board class
	 * @param b the proper Board class to be used
	 */
	public RulesHandler(Board b) {
		_b=b;
	}

	/**
	 * happens when action is performed, resulting in @param setReply
	 * to display link to rules
	 */
	public void actionPerformed(ActionEvent arg0) {
		_b.setTurn("rules");
		_b.notifyObservers();

	}

}
