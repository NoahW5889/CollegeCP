package edu.buffalo.cse116;

public class Person {
	
	private String codeName;	//holds codeName for instance
	private Boolean revealed;	//holds reveal status for instance
	private String team;	//holds team for instance
	
	public Person(String _code, String _team) {	//constructor creating person object
		codeName = _code;
		team = _team;
		revealed = false;
	}
	
	public String getTeam() {	//returns team of person
		return team;
	}
	
	public String getCodeName() {	//returns codeName of person
		return codeName;
	}

	public Boolean getRevealed() {	//returns reveal status of person
		return revealed;
	}

	public void setRevealed(Boolean revealed) {	//sets reveal status of person
		this.revealed = revealed;
	}
}
