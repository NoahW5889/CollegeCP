package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class ExitHandler implements ActionListener {
	private Board _b;

	public ExitHandler(Board b) {
	
		_b = b;;
	}

	public void actionPerformed(ActionEvent e) {
		_b.exit();
	}
}
