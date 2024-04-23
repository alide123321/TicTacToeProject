
/*
Ali Aldaghishy
ata5388@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.

This program shows the implementation of the project ("tictactoe" package).


*/
import java.util.InputMismatchException;
import java.util.Scanner;

import tictactoe.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TicTacToe game = null;

		boolean playAgain = false;

		do {

			String Player1 = "";
			String Player2 = "";
			int GameModeChoice = 0;

			System.out.println("Welcome to Tic Tac Toe! ");
			do {
				System.out.println("[Please a number corresponding to the option you would like to choose]");
				System.out.println("1) SinglePlayer");
				System.out.println("2) MultiPlayer");
				System.out.println("3) Load Game");
				System.out.println("4) Exit");
				System.out.println("");

				try {
					GameModeChoice = sc.nextInt();

					if (GameModeChoice < 1 || GameModeChoice > 4) {
						throw new InputMismatchException();
					}

				} catch (InputMismatchException e) {
					System.out.println("[Invalid input - Please enter a number between 1 and 4]");
					sc.nextLine(); // Clear the input buffer
				}

				if (GameModeChoice == 3) {
					try {
						game = TicTacToe.loadGame();
						System.out.println("Game loaded!");
					} catch (UnableToLoadGameException e) {
						System.out.println("No saved game found. Please select another option.");
						GameModeChoice = 0;
					}

				}

			} while (GameModeChoice < 1 || GameModeChoice > 4);

			if (GameModeChoice == 4) {
				System.out.println("Goodbye!");
				System.exit(0);
			}

			if (GameModeChoice != 3) {
				System.out.printf("Enter Player%s name: ", (GameModeChoice == 1 ? "" : " 1's"));
				Player1 = sc.next();

				if (GameModeChoice == 2) {
					System.out.printf("Enter Player 2's name: ");
					Player2 = sc.next();
				} else {
					Player2 = TicTacToe.COMPUTERNNAME;
				}

				game = new TicTacToe(Player1, Player2);
			} else {

				do {
					try {
						System.out.println("Would you like to restart the game? [R/N]");

						char continueGameChoice = sc.next().charAt(0);

						if (Character.toUpperCase(continueGameChoice) != 'R'
								&& Character.toUpperCase(continueGameChoice) != 'N') {
							throw new InputMismatchException();

						}

						if (Character.toUpperCase(continueGameChoice) == 'N') {
							game.restartGame();
						}

						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter 'R' or 'N'.");
						sc.nextLine(); // Clear the input buffer
					}
				} while (true);

			}

			System.out.printf("%nGame has started! [it will automatically save after each turn]%n");
			System.out.printf("Player 1: %s%n", game.getPlayer1Name());
			System.out.printf("Player 2: %s%n%n", game.getPlayer2Name());
			game.printBoard();

			playGame(game);

			System.out.println("Would you like to play again? [Y/N]");
			char playAgainChoice = sc.next().charAt(0);
			if (Character.toUpperCase(playAgainChoice) == 'Y') {
				playAgain = true;
				game.restartGame();
			} else {
				playAgain = false;
				try {
					game.saveGame();
				} catch (UnableToSaveGameException e) {

					System.out.println("Unable to save game. Game will not be saved.");
				}
			}
		} while (playAgain);

		System.out.println("Goodbye! Thanks for playing!");

		sc.close();
	}

	public static void playGame(TicTacToe game) {
		Scanner sc = new Scanner(System.in);
		int pos = 0;

		while (!game.isGameOver()) {
			try {
				System.out.printf("%s's turn: ", (game.isPlayer1Turn() ? game.getPlayer1Name() : game.getPlayer2Name()));
				System.out.println("Enter a number between 1 and 9 to place your move.");
				pos = sc.nextInt();
				boolean gameOutput = game.play(pos);
				if (gameOutput) {
					game.endGame(true);
				}
				game.printBoard();
				game.saveGame();

			} catch (UsedSpacesException e) {

				System.out.println("That position is already taken. Please try again.");
			} catch (NotAValidPositionException e) {

				System.out.println("Invalid position. Please enter a number between 1 and 9.");
			} catch (GameOverException e) {

				System.out.println("Game over! " + e.getMessage());
			} catch (UnableToPlayException e) {

				System.out.println("Unable to play. Please try again.");
			} catch (InputMismatchException e) {

				System.out.println("Invalid input. Please enter a number between 1 and 9.");
				sc.nextLine(); // Clear the input buffer
			} catch (UnableToSaveGameException e) {

				System.out.println("Invalid input. Please enter a number between 1 and 9.");
			}
		}

	}
}