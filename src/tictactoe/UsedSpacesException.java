/*
Ali Aldaghishy
ata5388@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.


*/
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