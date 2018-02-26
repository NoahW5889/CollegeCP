package edu.buffalo.cse116;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Board {
	
	int[][] board = new int[5][5];
	String[] list = new String[25];
	ArrayList<String> codeNames = new ArrayList<String>();
	
	public ArrayList<String> readCSVFile(String filename){
		codeNames = new ArrayList<String>();
    	try { for(String each: Files.readAllLines(Paths.get(filename))) {
    		codeNames.add(each);
    	}
    	}
    	catch (IOException ex){
            ex.printStackTrace();
        }
    	  return codeNames;
    }
	
	public String[] createList(ArrayList<String> x) {
		for(int i=0;i<25;i++) {
			list[i]=x.get((int) (Math.random()*codeNames.size()));
		}
		return list;
	}
	
}
