package checkers;

public class Player {

	//Player data fields
	private String name;
	private String color;
	
	//default constructor
	public Player() {
		
	}
	
	//constructor
	public Player(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	//get player name
	public String getName() {
		return name;
	}
	
	// get player color
	public String getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return name + " (" + color + ")";
	}



}
