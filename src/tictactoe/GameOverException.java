/*
Ali Aldaghishy - ata5388@psu.edu
Yasir Almutairi - yta5032@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.


*/

package tictactoe;

public class GameOverException extends Exception {

   /**
    * Constructs a new GameOverException with the specified detail message.
    *
    * @author: Ali Aldaghishy
    * @param message the detail message
    */
   public GameOverException(String message) {
      super(message);
   }
}
