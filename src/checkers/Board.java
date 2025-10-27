package checkers;

public class Board {
    // 8x8 game board - stores characters representing pieces
    // 'O' = Red piece, 'X' = Black piece, '_' = Empty space
    private char[][] gameBoard;
    
    /**
     * Constructor - initializes the board with starting positions
     */
    public Board() {
        gameBoard = new char[8][8];
        initializeBoard();
    }
    
    /**
     * Sets up the board with pieces in starting positions
     * Red (O) on top (rows 0-2), Black (X) on bottom (rows 5-7)
     * Pieces only go on dark squares (checkerboard pattern)
     */
    private void initializeBoard() {
        // Fill the entire board with empty spaces first
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gameBoard[row][col] = '_';
            }
        }
        
        // Place RED pieces (O) on rows 0, 1, 2
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                // Only place on "dark" squares (checkerboard pattern)
                if ((row + col) % 2 == 1) {
                    gameBoard[row][col] = 'O';
                }
            }
        }
        
        // Place BLACK pieces (X) on rows 5, 6, 7
        for (int row = 5; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Only place on "dark" squares (checkerboard pattern)
                if ((row + col) % 2 == 1) {
                    gameBoard[row][col] = 'X';
                }
            }
        }
    }
    
    /**
     * Displays a specific row of the board
     * Used by Game class to print row-by-row with row numbers
     */
    public void displayRow(int row) {
        for (int col = 0; col < 8; col++) {
            System.out.print(gameBoard[row][col] + " ");
        }
    }
    
    /**
     * Displays the entire board (alternative method)
     */
    public void displayBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print(gameBoard[row][col] + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Gets the piece at a specific position
     * Returns the character at that position ('O', 'X', or '_')
     */
    public char getPiece(int row, int col) {
        if (isValidPosition(row, col)) {
            return gameBoard[row][col];
        }
        return '_';  // Return empty if invalid position
    }
    
    /**
     * Moves a piece from one position to another
     * Does NOT validate if the move is legal (that's for Move class)
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        // Get the piece at the starting position
        char piece = gameBoard[fromRow][fromCol];
        
        // Place it at the destination
        gameBoard[toRow][toCol] = piece;
        
        // Clear the starting position
        gameBoard[fromRow][fromCol] = '_';
    }
    
    /**
     * Checks if a position is within the board boundaries
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
    
    /**
     * Returns a string representation of the board
     */
    @Override
    public String toString() {
        String board = "";
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board += gameBoard[row][col] + " ";
            }
            board += "\n";
        }
        return board;
    }
}