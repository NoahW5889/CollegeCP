package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class bufHandler implements ActionListener {

	private Board _b;
	
	/**
	 * links Board class
	 * @param b the proper Board class to be used
	 */
	public bufHandler(Board b) {
		_b=b;
	}

	/**
	 * happens when action is performed, resulting in the setting of 
	 * turns to change along with reply and method called of
	 * notifyObservers
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(_b.getPlayerCnt()==3) {
			
		}
		else {
		if(_b.getPrevTurn()=="blue"&&_b.checkGuess(_b.getLastGuess())==false)
			_b.setTurn("Red SpyMaster");
		else
			_b.setTurn("Blue SpyMaster");
		_b.setReply(_b.getTurn()+"s turn.");
		_b.notifyObservers();
		}
	}

}
