package edu.buffalo.cse116;

import javax.swing.SwingUtilities;

public class Driver implements Runnable {
	
	private Board _board;
	
	public Driver(Board b) {
		_board=b;
	}
	
	public static void main(String[] args) {
		Board b = new Board("GameWords.txt");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
