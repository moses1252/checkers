package checkers;

import java.util.Scanner;

public class Game {
    // Game components
    private Board board;  // The game board
    private Scanner scanner;
    private boolean gameRunning;
    
    /**
     * Constructor - initializes the game
     */
    public Game() {
        board = new Board();  // CREATE THE BOARD - this was missing!
        scanner = new Scanner(System.in);
        gameRunning = true;
    }
    
    /**
     * Starts the game and handles the main game loop
     */
    public void start() {
        System.out.println("=== WELCOME TO CHECKERS ===\n");
        System.out.println("Player 1 is RED (O)");
        System.out.println("Player 2 is BLACK (X)");
        System.out.println("\nRed (O) moves first!\n");
        
        // Main game loop (simplified for now)
        int turnCount = 0;
        while (gameRunning && turnCount < 3) {  // Only 3 turns for testing
            playTurn(turnCount % 2 == 0 ? "RED" : "BLACK");
            turnCount++;
        }
        
        System.out.println("\n=== TEST COMPLETE ===");
        System.out.println("Game and Board classes are working! Ready to add Move validation next.");
        
        // Ask if they want to play again
        playAgain();
    }
    
    /**
     * Handles a single player's turn
     */
    private void playTurn(String currentColor) {
        System.out.println("\n" + currentColor + " player's turn");
        System.out.print("Are you ready? (press enter to continue) ");
        scanner.nextLine();  // Wait for user to press enter
        
        // Display the board
        displayTestBoard();
        
        // Get the piece they want to move
        System.out.println("\nSelect the piece you want to move:");
        int fromRow = getRowInput("Enter row: ");
        int fromCol = getColumnInput("Enter column: ");
        
        // Get where they want to move it
        System.out.println("\nWhere do you want to move it?");
        int toRow = getRowInput("Enter row: ");
        int toCol = getColumnInput("Enter column: ");
        
        // Show what they selected
        System.out.println("\nYou selected piece at (" + fromRow + ", " + fromCol + ")");
        System.out.println("Piece: " + board.getPiece(fromRow, fromCol));
        System.out.println("Moving to (" + toRow + ", " + toCol + ")");
        
        // Actually move the piece on the board
        board.movePiece(fromRow, fromCol, toRow, toCol);
        System.out.println("\nPiece moved!");
    }
    
    /**
     * Displays the board with row and column numbers
     */
    private void displayTestBoard() {
        System.out.println("\n  0 1 2 3 4 5 6 7");  // Column numbers
        System.out.println("  ---------------");
        
        for (int row = 0; row < 8; row++) {
            System.out.print(row + "|");  // Row number
            board.displayRow(row);  // Use the Board class to display the row!
            System.out.println();
        }
    }
    
    /**
     * Gets a valid row input from the user (0-7)
     */
    private int getRowInput(String prompt) {
        int row = -1;
        while (row < 0 || row > 7) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (row < 0 || row > 7) {
                    System.out.println("Invalid row! Please enter 0-7");
                }
            } else {
                System.out.println("Invalid input! Please enter a number");
                scanner.next();  // Clear invalid input
            }
        }
        scanner.nextLine();  // Clear the newline
        return row;
    }
    
    /**
     * Gets a valid column input from the user (0-7)
     */
    private int getColumnInput(String prompt) {
        int col = -1;
        while (col < 0 || col > 7) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                col = scanner.nextInt();
                if (col < 0 || col > 7) {
                    System.out.println("Invalid column! Please enter 0-7");
                }
            } else {
                System.out.println("Invalid input! Please enter a number");
                scanner.next();  // Clear invalid input
            }
        }
        scanner.nextLine();  // Clear the newline
        return col;
    }
    
    /**
     * Asks if players want to play again
     */
    private void playAgain() {
        System.out.print("\nRun test again? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("y") || response.equals("yes")) {
            gameRunning = true;
            board = new Board();  // Reset the board with new pieces
            start();  // Start a new test
        } else {
            System.out.println("\nTest complete!");
            scanner.close();
        }
    }
    
    /**
     * Main method - starts the game
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}