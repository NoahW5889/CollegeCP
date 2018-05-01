package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class enterHandler implements ActionListener {
	private Board _b;
	
	public enterHandler(Board b) {
		_b=b;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(_b.getBluCnt()>=1&&_b.getRedCnt()>=1&&_b.getAssCnt()>=1) {
			if(_b.getTurn()=="red"||_b.getTurn()=="blue")
				_b.submit();
			else
				_b.validClues();
		}
		
	}

}
