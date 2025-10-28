package checkers;

public class Board {
    // 8x8 game board - stores Piece objects
    // null = Empty space
    private Piece[][] gameBoard;
    
    /**
     * Constructor - initializes the board with starting positions
     */
    public Board() {
        gameBoard = new Piece[8][8];
        initializeBoard();
    }
    
    /**
     * Sets up the board with pieces in starting positions
     * Red pieces on top (rows 0-2), Black pieces on bottom (rows 5-7)
     * Pieces only go on dark squares (checkerboard pattern)
     */
    private void initializeBoard() {
        // Place RED pieces on rows 0, 1, 2
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                // Only place on "dark" squares (checkerboard pattern)
                if ((row + col) % 2 == 1) {
                    gameBoard[row][col] = new Piece("RED", row, col);
                }
            }
        }
        
        // Place BLACK pieces on rows 5, 6, 7
        for (int row = 5; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Only place on "dark" squares (checkerboard pattern)
                if ((row + col) % 2 == 1) {
                    gameBoard[row][col] = new Piece("BLACK", row, col);
                }
            }
        }
        
        // Rows 3 and 4 are empty (already null by default)
    }
    
    /**
     * Displays a specific row of the board
     * Used by Game class to print row-by-row with row numbers
     */
    public void displayRow(int row) {
        for (int col = 0; col < 8; col++) {
            if (gameBoard[row][col] == null) {
                System.out.print("_ ");  // Empty space
            } else {
                System.out.print(gameBoard[row][col].getDisplayChar() + " ");
            }
        }
    }
    
    /**
     * Displays the entire board (alternative method)
     */
    public void displayBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (gameBoard[row][col] == null) {
                    System.out.print("_ ");
                } else {
                    System.out.print(gameBoard[row][col].getDisplayChar() + " ");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Gets the piece at a specific position
     * Returns the Piece object or null if empty
     */
    public Piece getPiece(int row, int col) {
        if (isValidPosition(row, col)) {
            return gameBoard[row][col];
        }
        return null;
    }
    
    /**
     * Gets the display character at a position (for backwards compatibility)
     * Returns 'O', 'X', 'R', 'K', or '_'
     */
    public char getPieceChar(int row, int col) {
        if (isValidPosition(row, col)) {
            if (gameBoard[row][col] == null) {
                return '_';
            }
            return gameBoard[row][col].getDisplayChar();
        }
        return '_';
    }
    
    /**
     * Moves a piece from one position to another
     * Does NOT validate if the move is legal (that's for Move class)
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        // Get the piece at the starting position
        Piece piece = gameBoard[fromRow][fromCol];
        
        if (piece != null) {
            // Update the piece's position
            piece.setPosition(toRow, toCol);
            
            // Move it on the board
            gameBoard[toRow][toCol] = piece;
            gameBoard[fromRow][fromCol] = null;
            
            // Check if piece should be kinged
            checkForKing(piece, toRow);
        }
    }
    
    /**
     * Checks if a piece should become a king
     * Red pieces become kings when they reach row 7 (bottom)
     * Black pieces become kings when they reach row 0 (top)
     */
    private void checkForKing(Piece piece, int row) {
        if (piece.getColor().equals("RED") && row == 7) {
            piece.makeKing();
            System.out.println("RED piece promoted to KING!");
        } else if (piece.getColor().equals("BLACK") && row == 0) {
            piece.makeKing();
            System.out.println("BLACK piece promoted to KING!");
        }
    }
    
    /**
     * Removes a piece at a specific position (used for jumped pieces)
     */
    public void removePiece(int row, int col) {
        if (isValidPosition(row, col)) {
            gameBoard[row][col] = null;
        }
    }
    
    /**
     * Checks if a position is within the board boundaries
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
    
    /**
     * Counts how many pieces a player has left
     */
    public int countPieces(String color) {
        int count = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (gameBoard[row][col] != null && 
                    gameBoard[row][col].getColor().equals(color)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Returns a string representation of the board
     */
    @Override
    public String toString() {
        String board = "";
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (gameBoard[row][col] == null) {
                    board += "_ ";
                } else {
                    board += gameBoard[row][col].getDisplayChar() + " ";
                }
            }
            board += "\n";
        }
        return board;
    }
}