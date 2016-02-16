package algorithms;

import java.util.Scanner;

/**
 * 
 * Provides a board to play four in a row add checkers in turn with
 * addChecker(player, atColumn)
 * 
 */
public class FourInARow {
	private int rows = 6, columns = 7;
	private final int yellow = 1, red = -1, empty = 0;
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
	 * current player is switched
	 * 
	 * @param column
	 *            between 0 and 6
	 */
	public void addChecker(int column) {
		if (column < 0 || column > 6)
			throw new IllegalArgumentException("Invalid board position");
		board[nextEmptySpot(0, column)][column] = currentPlayer;
		currentPlayer = -currentPlayer;
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
	 * Indicates if a checker leads to a winning combination
	 * @param row of the checker
	 * @param column of the checker
	 * @return 1 (yellow wins), -1 (red wins), 0 (no winner)
	 */
	public int doWeHaveAWinner(int row, int column){
		if(board[row][column] == empty) return empty;
		int possibleWinner = board[row][column];
		if(checkWinnerinColumn(row, column)) return possibleWinner;
		return empty;
	}
	
	private boolean checkWinnerinColumn(int row, int column){
		if(positionOfRowNotInBoard(row, -3)) return false;
		int result = 0;
		for(int i = 0; i < 4; i++){
			result += board[row - i][column]; 
		}
		return ( Math.abs(result) == 4)? true : false;
	}
	
	private boolean positionOfRowNotInBoard(int row,  int rowOffset){
		if(row + rowOffset < 0 || row + rowOffset > 5) return true;
		return false;
	}
	
	private boolean positionOfColumnNotInBoard(int column, int columnOffset){
		if(column + columnOffset < 0 || column + columnOffset > 6) return false;
		return true;
	}
	
	/**
	 * Sets a predefined board state
	 * @param board with 6 rows and 7 columns, cells contain -1, 0 or 1
	 */
	public void setBoard(int[][] board){
		this.board = board;
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
		
		while(true){
			int column = scanner.nextInt();
			game.addChecker(column);
			System.out.print(game.toString());
		}
	}

}
