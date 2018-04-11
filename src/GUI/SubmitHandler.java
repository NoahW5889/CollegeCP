package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.Board;

public class SubmitHandler implements ActionListener {
	private Board _b;
	
	public SubmitHandler(Board b) {
		_b = b; 
	}
	

	public void actionPerformed(ActionEvent e) {
		if(_b.validClue()==false) {
			_b.reply="invalid clue";
		}
		else {
			_b.reply="valid clue";
		}
		_b.clear();
		_b.notifyObservers();
		
	}
}
