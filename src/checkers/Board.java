package checkers;

public class Board {
//	Manages the 8x8 grid
//	Methods: displayBoard(), getPiece(), movePiece(), isValidPosition()
	
	char[][] gameBoard = new char[8][8];
	
	public Board() {
		
	}
	
	public void displayBoard() {
		for(int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard.length; y++) {
				System.out.print('X');
			}
			System.out.println();
		}
	}
	
	
	
	public String toString() {
		String board = "";
		for(int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard.length; y++) {
				board +="X";
			}
			board += "\n";
		}
		return board;
	}

}
