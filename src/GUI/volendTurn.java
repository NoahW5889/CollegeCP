package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class volendTurn implements ActionListener{
	private Board _b;

	public volendTurn(Board b) {
	
		_b = b;;
	}

	public void actionPerformed(ActionEvent e) {
		if(_b.getBluCnt()>=1&&_b.getRedCnt()>=1&&_b.getAssCnt()>=1) {
		_b.volendTurn();
		}
	}



}
