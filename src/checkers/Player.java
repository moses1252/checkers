package checkers;

public class Player {
//	Properties: color, name
//	Methods: selectPiece(), selectDestination()
	
	private String player1;
	private String player2;
	private String red;
	private String black;
	
	public Player() {
		player1 = "p1";
		player2 = "p2";
		red = "red";
		black = "balck";
	}


	public String getPlayer1() {
		return player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public String getRed() {
		return red;
	}

	public String getBlack() {
		return black;
	}
	@Override
	public String toString() {
		return(player1 + ": " + red + "\n" + player2 + ": " + black);
	}

}
