/*
Ali Aldaghishy
ata5388@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.


*/
package tictactoe;

import java.io.Serializable;

public class Board implements Serializable {
   private char[][] board = new char[3][3];

   protected Board() {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            board[i][j] = ' ';
         }
      }
   }

   /**
    * @param player1 if it's player 1's turn then true
    * @param pos     the position on the board where the move is to be played (1-9)
    * @throws UsedSpacesException        if the position is already used
    * @throws NotAValidPositionException if not 1 <= pos <= 9
    */
   protected void playBoard(boolean player1, int pos) throws UsedSpacesException, NotAValidPositionException {

      if (pos < 1 || pos > 9)
         throw new NotAValidPositionException();

      if (board[((pos - 1) / 3)][((pos - 1) % 3)] != ' ')
         throw new UsedSpacesException();

      // used some cool math to conver board 1D pos to 2d array (I'm a genius)
      board[((pos - 1) / 3)][((pos - 1) % 3)] = player1 ? 'X' : 'O';

   }

   /**
    * Returns the character at the specified position on the board.
    *
    * @param i the row index of the position
    * @param j the column index of the position
    * @return the character at the specified position
    */
   protected char getChar(int i, int j) {
      return board[i][j];
   }

   /**
    * Returns the current state of the game board.
    *
    * @return a 2D char array representing the game board
    */
   protected char[][] getBoard() {
      return board;
   }

   /**
    * Checks if the given position on the board is valid.
    *
    * @param pos The position to check.
    * @return true if the position is valid, false otherwise.
    */
   protected boolean ValidPosition(int pos) {
      return (board[((pos - 1) / 3)][((pos - 1) % 3)] == ' ');
   }

}
