package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.Board;

public class easterEggHandler implements ActionListener{
	private Board _b;

	/**
	 * links Board class
	 * @param b the proper Board class to be used
	 */
	public easterEggHandler(Board b) {
		_b=b;
	}

	/**
	 * happens when action is performed, resulting in easterEgg method to trigger
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		_b.easterEgg();
	}

}
