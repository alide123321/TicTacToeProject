package tictactoe;

/**
 * This exception is thrown when a player tries to make a move on a space that
 * is already used in the Tic Tac Toe game.
 */
public final class UsedSpacesException extends Exception {
   public UsedSpacesException() {
      super("That space is already used try a different position (Hint: Use the .PrintBoard() method to see what's available)");
   }
}