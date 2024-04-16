package tictactoe;

/**
 * This exception is thrown when an invalid position is provided in the
 * TicTacToe game.
 * It extends the Exception class.
 */
public class NotAValidPositionException extends Exception {
   public NotAValidPositionException() {
      super("That position is not valid try a different position (Hint: Use the .PrintBoard() method to see what's available)");
   }
}
