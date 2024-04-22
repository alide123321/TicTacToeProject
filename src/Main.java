
/*
Ali Aldaghishy
ata5388@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.

This program shows the implementation of the "tictactoe" package.

*/
import java.util.Scanner;

import tictactoe.*;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		TicTacToe game = new TicTacToe();

		boolean ValidChoice = false;

		System.out.print("Welcome to Tic Tac Toe! ");
		do {
			System.out.println("(Please enter the number of the option you would like to choose)");
			System.out.println("1)\tSinglePlayer");
			System.out.println("2)\tMultiPlayer");
			System.out.println("3)\tLoad Game");
			System.out.println("4)\tExit");

		} while (ValidChoice);

	}
}