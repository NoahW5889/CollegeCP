package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class eggHandler2 implements ActionListener {
		private Board _b;
	public eggHandler2(Board b) {
		// TODO Auto-generated constructor stub
		_b=b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		_b.egg2();
	}

}
