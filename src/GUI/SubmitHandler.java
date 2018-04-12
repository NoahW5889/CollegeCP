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
		if(_b.turn=="red"||_b.turn=="blue")
			_b.submit();
		else
			_b.validClues();
	}
}
