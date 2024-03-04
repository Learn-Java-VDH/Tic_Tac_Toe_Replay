package Demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShowBoard {
	private final int SINGLE_PLAYER = 1;
	private final int MULTI_PLAYER = 2;
	private final int EXIT = 3;
	private boolean game_loop = true;
	private boolean play_loop = true;
	private boolean in_mode = false;
	private final int PLAY = 1;
	private final int BACK_TO_MENU = 2;
	private boolean turn = false;
	static String[] board;

	Scanner in = new Scanner(System.in);

	public ShowBoard() {

	}

	private void createBoardGame(int num) {
		board = new String[num];
	}

	private void checkTurn() {
		System.out.println(Player.player + " turn");
	}

	public void menu() {
		System.out.println("-----Menu-----");
		System.out.println("1. Single Player");
		System.out.println("2. Multi Player");
		System.out.println("3. Exit");
		System.out.println("-----End------");
	}

	private void chooseMode(int mode) {
		this.in_mode = true;
		String header = mode == 1 ? " Single Player Mode" : "Multi Player Mode";
		System.out.println("\n");
		System.out.println("-----" + header + "-----");
		System.out.println("1. Play");
		System.out.println("2. Back");
		System.out.println("-----Please choose------");

	}

	private void modeSingle() {

	}

	private void showBoard() {
		System.out.println("|---||---||---|");
		int count = 0;
		for (int i = 1; i < ShowBoard.board.length + 1; i++) {
			if (ShowBoard.board[i - 1] == null) {
				ShowBoard.board[i - 1] = String.valueOf(i);
			}
			if (count == 3) {
				System.out.println("\n|---||---||---|");
				count = 0;
			}
			count++;
			System.out.print("| " + ShowBoard.board[i - 1] + " |");
		}
		System.out.println("\n|---||---||---|");
	}

	private boolean checkSlot(int user_choose) {
		if (ShowBoard.board[user_choose - 1] == "X" || ShowBoard.board[user_choose - 1] == "O") {
			return false;
		}
		ShowBoard.board[user_choose - 1] = turn ? "O" : "X";
		System.out.println(ShowBoard.board[user_choose - 1] + " Choose turn");
		return true;
	}

	private void gameStart() {
		int choose;
		if (!this.play_loop) {
			this.play_loop = true;
			this.checkTurn();
			in = new Scanner(System.in);
		} else {
			System.out.println("X first");
		}

		while (this.play_loop && in.hasNext()) {
			try {
				choose = in.nextInt();
				if (!(choose > 0 && choose <= 9)) {
					System.out.println("Please choose again!");
					continue;
				}
				// check that slot was filled
				Boolean check = checkSlot(choose);
				if (check) {
					Player.swapTurn(!turn);
					this.turn = !this.turn;
					this.checkTurn();

				} else {
					System.out.println("You cannot check this box");
					System.out.println("Please choose again");
				}
				this.showBoard();
				String check_winner = Player.checkWinner(choose);
				if (check_winner == null) {
					continue;
				} else {
					if (check_winner == "draw") {
						System.out.println("Draw!!");
					} else {
						System.out.println(check_winner + " is winner!!!");
					}

					this.play_loop = false;
					this.game_loop = true;
					this.menu();
					this.ChooseMemu();
					break;

				}
			} catch (InputMismatchException e) {
				this.play_loop = false;
				this.gameStart();
				return;
			}
		}
	}

	private void play() {
		this.createBoardGame(9);
		this.showBoard();
		this.gameStart();
	}

	private void modeMulti(int user_choose) {

		switch (user_choose) {
		case PLAY: {
			this.game_loop = false;
			System.out.println("Exit loop");
			this.play();
			break;
		}
		case BACK_TO_MENU: {
			this.in_mode = false;
			this.menu();
			break;
		}
		default:
			System.out.println("\n");
			System.out.println("-----Choose again------");
		}
	}

	private void exitProgram() {
		this.game_loop = false;
		in.close();
		System.out.println("-----See ya------");
	}

	public void ChooseMemu() {

		if (this.game_loop) {
			System.out.println("-----Please choose------");
		} else {
			System.out.println("-----Please choose again------");
			this.game_loop = true;
			in = new Scanner(System.in);
		}
		if (this.in_mode) {
			in = new Scanner(System.in);
			this.in_mode = false;
			System.out.println("exit mode");
		}
		try {

			int choose;
			while (this.game_loop) {

				try {
					choose = in.nextInt();
				} catch (InputMismatchException e) {
					this.game_loop = false;
					this.ChooseMemu();
					return;
				}

				if (this.in_mode) {
					this.modeMulti(choose);

				} else {
					switch (choose) {
					case SINGLE_PLAYER: {

						this.chooseMode(choose);
						break;
					}
					case MULTI_PLAYER: {
						this.chooseMode(choose);
						break;
					}
					case EXIT: {
						this.exitProgram();
						break;
					}
					default:
						System.out.println("\n");
						System.out.println("-----Please choose again------");
						continue;
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
