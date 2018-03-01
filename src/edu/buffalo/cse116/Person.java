package edu.buffalo.cse116;

public class Person {
	
	private String codeName;
	private Boolean revealed;
	private String team;
	

	public Person(String _code, String _team) {
		codeName = _code;
		team = _team;
		revealed = false;
	}
	
	
	public String getTeam() {
		return team;
	}
	
	public String getCodeName() {
		return codeName;
	}

	public Boolean getRevealed() {
		return revealed;
	}

	public void setRevealed(Boolean revealed) {
		this.revealed = revealed;
	}
}
