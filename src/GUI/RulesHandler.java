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
		_b.setReply("<html>==========================Rules.=========================="
					+ "<br>Please refer to Link."
					+ "<br>https://czechgames.com/files/rules/codenames-rules-en.pdf"
					+ "<br>=========================================================="
					+ "<br>"+_b.getTurn()+"s turn.");
		_b.notifyObservers();

	}

}
