package Demo;

import java.util.Arrays;

public class Player {

	static String player = "X";

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public static void swapTurn(boolean turn) {
		player = turn ? "O" : "X";
	}

	public static String checkWinner(int user_choose) {

		int[][] winningCombinations = { { 0, 1, 2 }, // First row
				{ 3, 4, 5 }, // Second row
				{ 6, 7, 8 }, // Third row
				{ 0, 3, 6 }, // First column
				{ 1, 4, 7 }, // Second column
				{ 2, 5, 8 }, // Third column
				{ 0, 4, 8 }, // Diagonal from top-left to bottom-right
				{ 2, 4, 6 } // Diagonal from top-right to bottom-left
		};

		// Iterate through winning combinations
		for (int[] combination : winningCombinations) {
			String line = "" + ShowBoard.board[combination[0]] + ShowBoard.board[combination[1]]
					+ ShowBoard.board[combination[2]];
			// For X winner
			if (line.equals("XXX")) {
				return "X";
			}

			// For O winner
			else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(ShowBoard.board).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8) {
				return "draw";
			}
		}
		// If no winner is found
		return null;
	}
}
