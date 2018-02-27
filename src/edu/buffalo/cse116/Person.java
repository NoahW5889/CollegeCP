package edu.buffalo.cse116;

public class Person {
	
	private String codeName;
	private Boolean revealed;
	private String team;
	

	public Person() {
	}
	
	public void setTeam(String x) {
		this.team=x;
	}
	
	public String getTeam() {
		return team;
	}
	
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Boolean getRevealed() {
		return revealed;
	}

	public void setRevealed(Boolean revealed) {
		this.revealed = revealed;
	}
}
