package algorithms.h1.connect4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FourInARowTest {
	
	FourInARow game;
	int board[][];
	
	private final int yellow = 1, red = -1, empty =0;
	
	@Before
	public void setup(){
		 game = new FourInARow();
		 board = new int[6][7];
	}

	@Test
	public void testGettingEmptyBoardState() {		
		assertArrayEquals(board, game.getBoardState());
	}
	
	@Test
	public void testAddASingleChecker(){
		game.addChecker(0);
		
		board[0][0] = yellow;
		
		assertArrayEquals(board, game.getBoardState());		
	}
	
	@Test
	public void testAddTwoCheckersDifferentColumns(){
		game.addChecker(0);
		game.addChecker(1);
		
		board[0][0] = yellow;
		board[0][1] = red;
		
		assertArrayEquals(board, game.getBoardState());
	}
	
	@Test
	public void testAddTwoCheckersSameColumns(){
		game.addChecker(2);
		game.addChecker(2);
		
		board[0][2] = yellow;
		board[1][2] = red;
		
		assertArrayEquals(board, game.getBoardState());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddCheckerToColumnOutsideBoard(){
		game.addChecker(8);
	}
	
	@Test
	public void testDoWeHaveAWinnerWithEmptyPosition(){
		assertEquals(empty, game.doWeHaveAWinner(0, 0));		
	}
	
	@Test
	public void testWinnerRedColumn(){
		board[0][0] = red;
		board[1][0] = red;
		board[2][0] = red;
		board[3][0] = red;
		
		game.setBoard(board);
		
		assertEquals(empty, game.doWeHaveAWinner(2, 0));
		assertEquals(red, game.doWeHaveAWinner(3, 0));
	}
	
	@Test
	public void testWinnerYellowRow(){
		board[0][0] = yellow;
		board[0][1] = yellow;
		board[0][2] = yellow;
		board[0][3] = yellow;
		
		game.setBoard(board);
		
		assertEquals(yellow, game.doWeHaveAWinner(0, 0));
	}
	
	@Test
	public void testWinnerYellowDiagonalLeftUp(){
		board[0][0] = yellow;
		board[1][1] = yellow;
		board[2][2] = yellow;
		board[3][3] = yellow;
		
		game.setBoard(board);
		
		assertEquals(yellow, game.doWeHaveAWinner(0, 0));
		assertEquals(yellow, game.doWeHaveAWinner(2, 2));
	}
	
	@Test
	public void testWinnerRedDiagonalRightUp(){
		board[4][0] = red;
		board[3][1] = red;
		board[2][2] = red;
		board[1][3] = red;
		
		game.setBoard(board);
		
		assertEquals(red, game.doWeHaveAWinner(4, 0));
		assertEquals(red, game.doWeHaveAWinner(2, 2));
	}
}
