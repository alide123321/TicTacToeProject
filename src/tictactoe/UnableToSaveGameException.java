/*
Ali Aldaghishy - ata5388@psu.edu
Yasir Almutairi - yta5032@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.


*/
package tictactoe;

public class UnableToSaveGameException extends Exception {

   /**
    * Constructs a new UnableToSaveGameException with the specified detail message.
    *
    * @author: Yasir Almutairi
    * @param message the detail message
    */
   public UnableToSaveGameException(String message) {
      super(message);
   }
}
