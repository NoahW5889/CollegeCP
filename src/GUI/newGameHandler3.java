package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class newGameHandler3 implements ActionListener {
	
	private Board _b;
	private int g;
	
	public newGameHandler3(int q,Board b) {
		_b=b;
		g=q;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_b.playerSet(g);
		
	}

}
