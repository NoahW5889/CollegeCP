package edu.buffalo.cse116;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Board {
	
	int[][] board = new int[5][5];
	String[] list = new String[25];
	
	public ArrayList<String> readCSVFile(String filename){
		ArrayList<String> codeNames = new ArrayList<String>();
    	try { for(String each: Files.readAllLines(Paths.get(filename))) {
    		codeNames.add(each);
    	}
    	}
    	catch (IOException ex){
            ex.printStackTrace();
        }
    	  return codeNames;
    }
	
	public void createList(ArrayList<String> x) {
		for(int i=0;i<x.size();i++) {
			list[i]=x.get((int) (Math.random()*25));
		}
	}
	
}
