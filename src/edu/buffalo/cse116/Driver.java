package edu.buffalo.cse116;

import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Driver implements Runnable {
	
	private Board _board;
	
	public Driver(Board b) {
		_board=b;
	}
	
	public static void main(String[] args) {
		Board b = new Board("src/GameWords.txt");
		SwingUtilities.invokeLater(new Driver(b));
		b.startGame();
		
		for(int i=0;i<b.mainBoard.size();i++) {
			System.out.println("Codename: "+b.mainBoard.get(i).getCodeName()+", Team: "+b.mainBoard.get(i).getTeam());
		}
		
		System.out.println();
		
		while(b.redCnt!=0&&b.bluCnt!=0&&b.assCnt!=0) {
			System.out.println(b.turn+" teams turn");
			System.out.println("Clue: ");
			Scanner console = new Scanner(System.in);
			System.out.println("Enter guess: ");
	    	String guess = console.nextLine();
	    	System.out.println(b.choose(guess));
		}
		
		/* TESTING GAME SETTUP
		System.out.print("Board Size: "+b.mainBoard.size());
		System.out.println();
		System.out.println();
		System.out.print("List of Codenames: ");
		for(int i=0;i<b.list.length;i++) {
		System.out.print(b.list[i]+", ");
		}
		System.out.println();
		System.out.print("List Size: "+b.list.length);
		System.out.println();
		System.out.println();
		System.out.println("On Board Players");
		System.out.println("===============");
		for(int i=0;i<b.list.length;i++) {
			System.out.println("CodeName: "+b.mainBoard.get(i).getCodeName()+"\n"+"Team: "+b.mainBoard.get(i).getTeam()+"\n"+"Revealed: "+b.mainBoard.get(i).getRevealed()+"\n===============");
			}
			*/
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
