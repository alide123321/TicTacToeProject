package tictactoe;

public class NotAValidPositionException extends Exception {
   public NotAValidPositionException() {
      super("That position is not valid try a different position (Hint: Use the .PrintBoard() method to see what's available)");
   }
}
