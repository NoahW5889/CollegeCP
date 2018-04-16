package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class eggHandler2 implements ActionListener {
		private Board _b;
		
		/**
		 * links Board class
		 * @param b the proper Board class to be used
		 */
	public eggHandler2(Board b) {
		// TODO Auto-generated constructor stub
		_b=b;
	}
	
	/**
	 * happens when action is performed, resulting in egg2 method to trigger
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		_b.egg2();
	}

}
