package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.Board;

public class cardHandler implements ActionListener {
	private Board _b;
	private String _c;
	
	public cardHandler(Board b, String c) {
		_b = b;
		_c = c;
	}
	

	public void actionPerformed(ActionEvent e) {
		GUI.entry.setText(_c);
		_b.submit();
	}
}
