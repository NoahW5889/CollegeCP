package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class volendTurn implements ActionListener{
	private Board _b;

	/**
	 * links Board class
	 * @param b the proper Board class to be used
	 */
	public volendTurn(Board b) {
	
		_b = b;;
	}

	/**
	 * happens when action is performed, resulting in volendTurn method to trigger
	 */
	public void actionPerformed(ActionEvent e) {
		if(_b.getBluCnt()>=1&&_b.getRedCnt()>=1&&_b.getAssCnt()>=1) {
		_b.volendTurn();
		}
	}



}
