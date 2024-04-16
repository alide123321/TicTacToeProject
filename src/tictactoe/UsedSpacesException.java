package tictactoe;

public final class UsedSpacesException extends Exception {
   public UsedSpacesException() {
      super("That space is already used try a different position (Hint: Use the .PrintBoard() method to see what's available)");
   }
}