package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class bufHandler implements ActionListener {

	private Board _b;
	
	public bufHandler(Board b) {
		_b=b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(_b.getPrevTurn()=="blue"&&_b.checkGuess(_b.getLastGuess())==false)
			_b.setTurn("Red Spy");
		else
			_b.setTurn("Blue Spy");
		_b.notifyObservers();
	}

}
