package tictactoe;

public class Board {
   private char[][] board = new char[3][3];

   protected Board() {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            board[i][j] = ' ';
         }
      }
   }

   protected boolean Play(boolean player1, int pos) throws UsedSpacesException, NotAValidPositionException {

      if (pos < 1 || pos > 9)
         throw new NotAValidPositionException();

      if (board[(pos % 3)][((pos - 1) % 3)] != ' ')
         throw new UsedSpacesException();

      board[(pos % 3)][((pos - 1) % 3)] = player1 ? 'X' : 'O';

      return true;
   }

   protected char getChar(int i, int j) {
      return board[i][j];
   }

   protected boolean ValidPosition(int pos) {
      return (board[(pos % 3)][((pos - 1) % 3)] == ' ');
   }

}
