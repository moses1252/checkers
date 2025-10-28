package checkers;

public class Piece {
    // Piece properties
    private String color;      // "RED" or "BLACK"
    private int row;           // Current row position
    private int col;           // Current column position
    private boolean isKing;    // Is this piece a king?
    
    /**
     * Constructor - creates a regular (non-king) piece
     */
    public Piece(String color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.isKing = false;  // Starts as regular piece
    }
    
    /**
     * Gets the piece's color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Gets the piece's current row
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Gets the piece's current column
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Updates the piece's position
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Checks if this piece is a king
     */
    public boolean isKing() {
        return isKing;
    }
    
    /**
     * Promotes this piece to a king
     */
    public void makeKing() {
        this.isKing = true;
    }
    
    /**
     * Gets the display character for this piece
     * 'O' = Red regular, 'R' = Red king
     * 'X' = Black regular, 'K' = Black king
     */
    public char getDisplayChar() {
        if (color.equals("RED")) {
            return isKing ? 'R' : 'O';
        } else {
            return isKing ? 'K' : 'X';
        }
    }
    
    /**
     * String representation of the piece
     */
    @Override
    public String toString() {
        String type = isKing ? "King" : "Regular";
        return color + " " + type + " piece at (" + row + ", " + col + ")";
    }
}