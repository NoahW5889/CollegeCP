package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class RulesHandler implements ActionListener {

	private Board _b;
	
	public RulesHandler(Board b) {
		_b=b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		_b.setReply("<html>==========================Rules.=========================="
					+ "<br>Please refer to Link."
					+ "<br>https://czechgames.com/files/rules/codenames-rules-en.pdf"
					+ "<br>=========================================================="
					+ "<br>"+_b.getTurn()+"s turn.");
		_b.notifyObservers();

	}

}
