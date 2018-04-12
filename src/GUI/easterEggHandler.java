package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.Board;

public class easterEggHandler implements ActionListener{
	private Board _b;

	public easterEggHandler(Board b) {
		_b=b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		_b.easterEgg();
	}

}
