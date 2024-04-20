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
   protected void Play(boolean player1, int pos) throws UsedSpacesException, NotAValidPositionException {

      if (pos < 1 || pos > 9)
         throw new NotAValidPositionException();

      if (board[(pos % 3)][((pos - 1) % 3)] != ' ')
         throw new UsedSpacesException();

      // used some cool math to conver board pos to 2d array (I'm a genius)
      board[(pos % 3)][((pos - 1) % 3)] = player1 ? 'X' : 'O';

   }

   // just returns the char at the position
   protected char getChar(int pos) throws NotAValidPositionException {
      if (pos < 1 || pos > 9)
         throw new NotAValidPositionException();

      return board[(pos % 3)][((pos - 1) % 3)];
   }

   // returns the board
   protected char[][] getBoard() {
      return board;
   }

   // validates position
   protected boolean ValidPosition(int pos) {
      return (board[(pos % 3)][((pos - 1) % 3)] == ' ');
   }

}
