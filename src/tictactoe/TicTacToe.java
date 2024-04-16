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

   public char CheckWinner() {
      char winner = ' ';

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

      return winner;
   }

   public boolean isPlayer1Turn() {
      return Player1Turn;
   }

}
