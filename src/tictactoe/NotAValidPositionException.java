/*
Ali Aldaghishy
ata5388@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.


*/
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
