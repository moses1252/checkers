package checkers;

public class Move {
    // Move coordinates
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    
    // Reference to the board to check piece positions
    private Board board;
    
    /**
     * Constructor - creates a move with start and end positions
     */
    public Move(int fromRow, int fromCol, int toRow, int toCol, Board board) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.board = board;
    }
    
    /**
     * Checks if this move is valid according to checkers rules
     * Returns true if valid, false otherwise
     */
    public boolean isValid() {
        // Check 1: Are both positions on the board?
        if (!board.isValidPosition(fromRow, fromCol) || !board.isValidPosition(toRow, toCol)) {
            System.out.println("Error: Position is outside the board!");
            return false;
        }
        
        // Check 2: Is there actually a piece at the starting position?
        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null) {
            System.out.println("Error: No piece at starting position!");
            return false;
        }
        
        // Check 3: Is the destination empty? (for now - we'll handle jumps later)
        Piece destination = board.getPiece(toRow, toCol);
        if (destination != null) {
            System.out.println("Error: Destination is not empty!");
            return false;
        }
        
        // Check 4: Is the move diagonal?
        if (!isDiagonal()) {
            System.out.println("Error: Checkers pieces must move diagonally!");
            return false;
        }
        
        // Check 5: Is it a normal move (1 square) or a jump (2 squares)?
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if (rowDiff == 1 && colDiff == 1) {
            // Normal move - check if going the right direction
            return isCorrectDirection(piece);
        } else if (rowDiff == 2 && colDiff == 2) {
            // Jump move - validate the jump
            return isValidJump(piece);
        } else {
            System.out.println("Error: Can only move 1 square (normal) or 2 squares (jump)!");
            return false;
        }
    }
    
    /**
     * Checks if the move is diagonal
     */
    private boolean isDiagonal() {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // Diagonal means row difference equals column difference
        return rowDiff == colDiff && rowDiff > 0;
    }
    
    /**
     * Checks if the piece is moving in the correct direction
     * Red pieces move DOWN (increasing row numbers)
     * Black pieces move UP (decreasing row numbers)
     * King pieces can move either direction
     */
    private boolean isCorrectDirection(Piece piece) {
        // King pieces can move any direction
        if (piece.isKing()) {
            return true;
        }
        
        if (piece.getColor().equals("RED")) {
            // Red moves DOWN (row increases)
            if (toRow > fromRow) {
                return true;
            } else {
                System.out.println("Error: Red pieces must move downward!");
                return false;
            }
        } else if (piece.getColor().equals("BLACK")) {
            // Black moves UP (row decreases)
            if (toRow < fromRow) {
                return true;
            } else {
                System.out.println("Error: Black pieces must move upward!");
                return false;
            }
        }
        
        return false;
    }
    
    /**
     * Checks if this is a valid jump over an opponent's piece
     * Returns true if the jump is valid
     */
    public boolean isValidJump(Piece piece) {
        // Calculate the middle square (the piece being jumped)
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;
        
        Piece middlePiece = board.getPiece(midRow, midCol);
        
        // Check if there's a piece in the middle
        if (middlePiece == null) {
            System.out.println("Error: No piece to jump over!");
            return false;
        }
        
        // Check if it's an opponent piece
        if (!piece.getColor().equals(middlePiece.getColor())) {
            // Opponent piece - valid jump! But check direction
            return isCorrectDirection(piece);
        } else {
            System.out.println("Error: Can't jump over your own pieces!");
            return false;
        }
    }
    
    /**
     * Checks if this move is a jump (2 squares diagonal)
     */
    public boolean isJump() {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        return rowDiff == 2 && colDiff == 2;
    }
    
    /**
     * Gets the middle row (for jump moves)
     */
    public int getMiddleRow() {
        return (fromRow + toRow) / 2;
    }
    
    /**
     * Gets the middle column (for jump moves)
     */
    public int getMiddleCol() {
        return (fromCol + toCol) / 2;
    }
    
    // Getters
    public int getFromRow() { return fromRow; }
    public int getFromCol() { return fromCol; }
    public int getToRow() { return toRow; }
    public int getToCol() { return toCol; }
}