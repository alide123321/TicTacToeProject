package tictactoe;

public class TicTacToe {
   Board B;
   boolean Multiplayer;
   boolean Player1Turn;

   public TicTacToe(boolean multiplayer) {
      B = new Board();
      Multiplayer = multiplayer;
      Player1Turn = true;
   }

   /**
    * @param pos The position on the board.
    * @return True if the game has a winner after each move
    * @throws UsedSpacesException        if the position is already used
    * @throws NotAValidPositionException if not 1 <= pos <= 9
    */
   public boolean Play(int pos) throws UsedSpacesException, NotAValidPositionException {

      B.Play(Player1Turn, pos);
      Player1Turn = !Player1Turn;

      if (CheckWinner() != ' ')
         return true;

      if (!Multiplayer) {

         int playPOS;
         do {

            playPOS = (int) (Math.random() * 9) + 1;

         } while (!B.ValidPosition(playPOS));

         B.Play(Player1Turn, playPOS);
         Player1Turn = !Player1Turn;
      }

      return CheckWinner() != ' ';

   }

   /**
    * @return the character showing the winner ('X' or 'O'),
    *         or (' ') if no winner yet,
    *         or ('T') if it's a tie
    */
   public char CheckWinner() {
      char winner = ' ';

      // ? There has to be a better way of doing these checks

      for (int i = 0; i < 3; i++) {
         if ((B.getChar(i, 0) == B.getChar(i, 1)) && (B.getChar(i, 1) == B.getChar(i, 2)) && (B.getChar(i, 0) != ' '))
            winner = B.getChar(i, 0);

         if ((B.getChar(0, i) == B.getChar(1, i)) && (B.getChar(1, i) == B.getChar(2, i)) && (B.getChar(0, i) != ' '))
            winner = B.getChar(0, i);
      }

      if ((B.getChar(0, 0) == B.getChar(1, 1)) && (B.getChar(1, 1) == B.getChar(2, 2)) && (B.getChar(0, 0) != ' '))
         winner = B.getChar(0, 0);

      if ((B.getChar(0, 2) == B.getChar(1, 1)) && (B.getChar(1, 1) == B.getChar(2, 0)) && (B.getChar(0, 2) != ' '))
         winner = B.getChar(0, 2);

      if (winner == ' ') {

         boolean tie = true;

         for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               if (B.getChar(i, j) == ' ') {
                  tie = false;
               }
            }
         }

         if (tie) {
            return 'T';
         }
      }

      return winner;
   }

   public boolean isPlayer1Turn() {
      return Player1Turn;
   }

}
