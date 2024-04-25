/*
Ali Aldaghishy - ata5388@psu.edu
Yasir Almutairi - yta5032@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.


*/
package tictactoe;

public class NotAValidPositionException extends Exception {

   /**
    * Constructs a new NotAValidPositionException with a default error message.
    * The error message suggests using the `.PrintBoard()` method to see the
    * available positions.
    * 
    * @author: Ali Aldaghishy
    */

   public NotAValidPositionException() {
      super("That position is not valid try a different position (Hint: Use the .PrintBoard() method to see what's available)");
   }
}
