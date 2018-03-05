package code;

public class Person {
	
	private String codeName;	//holds codeName for instance
	private Boolean revealed;	//holds reveal status for instance
	private String team;	//holds team for instance
	
	/*
	 * constructor creating person object
	 * @param _code takes in codeName to set codeName of person to
	 * @param _team takes in team to be set for person and sets team to it
	 * @param revealed autoset to false
	 */
	public Person(String _code, String _team) {
		codeName = _code;
		team = _team;
		revealed = false;
	}
	
	/*
	 * gets team of requested person
	 * @return team of requested person
	 */
	public String getTeam() {
		return team;
	}
	
	/*
	 * gets codeName of requested person
	 * @return codeName of requested person
	 */
	public String getCodeName() {
		return codeName;
	}

	/*
	 * gets revealed status of requested person
	 * @return revealed status of requested person
	 */
	public Boolean getRevealed() {
		return revealed;
	}

	/*
	 * sets revealed status of requested person to param
	 * @param revealed holds status input to set revealed of person to (true or false)
	 */
	public void setRevealed(Boolean revealed) {
		this.revealed = revealed;
	}
}
