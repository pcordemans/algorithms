package algorithms.h1.connect4;

import java.util.Scanner;

/**
 * 
 * Provides a board to play four in a row add checkers in turn with
 * addChecker(player, atColumn)
 * 
 */
public class FourInARow {
	private final int rows = 6, columns = 7;
	private final static int yellow = 1, red = -1, empty = 0;
	private int board[][] = new int[rows][columns];
	private int currentPlayer = yellow;

	/**
	 * Start with an empty board
	 */
	public FourInARow() {
		clearBoard();
	}

	private void clearBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board[i][j] = empty;
			}
		}
	}

	/**
	 * the current player adds a checker to the column of his choice, then the
	 * current player is switched, checks if a player wins
	 * 
	 * @param column
	 *            between 0 and 6
	 * @return 0 if no winner, -1 if red wins and 1 if yellow wins           
	 */
	public int addChecker(int column) {
		if (column < 0 || column > 6)
			throw new IllegalArgumentException("Invalid board position");
		int row = nextEmptySpot(0, column);
		board[row][column] = currentPlayer;
		currentPlayer = -currentPlayer;
		return 0;		
	}

	/**
	 * checks the board for the first empty row of a given column
	 * 
	 * @param row
	 *            to check
	 * @param column
	 *            to check
	 * @return row index of first empty spot in a column
	 */
	private int nextEmptySpot(int row, int column) {
		if (board[row][column] == empty)
			return row;
		else
			return nextEmptySpot(row + 1, column);
	}	
	
	/**
	 * Gets the current board state
	 * @return the current state
	 */
	public int[][] getBoardState(){
		return board;
	}

	public String toString() {
		StringBuilder b = new StringBuilder("\n---------------\n|");
		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j < columns; j++) {
				switch (board[i][j]) {
				case yellow:
					b.append("Y");
					break;
				case red:
					b.append("R");
					break;
				case empty:
					b.append(" ");
					break;
				}
				b.append("|");
			}
			b.append("\n---------------\n");
			if (i != 0)
				b.append("|");
		}

		return b.toString();
	}

	public static void main(String[] args) {
		FourInARow game = new FourInARow();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("*** Four in a row ***");
		System.out.println("Enter column number between 0 and 6 to insert checker");
		System.out.print(game.toString());
		
		int winner = 0;
		
		while(winner == 0){
			int column = scanner.nextInt();
			winner = game.addChecker(column);
			System.out.print(game.toString());
		}
		
		if (winner == yellow) System.out.print("Yellow");
		else System.out.print("Red");
		System.out.println(" wins!");
		scanner.close();
	}

}
