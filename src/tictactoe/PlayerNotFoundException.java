/*
Ali Aldaghishy - ata5388@psu.edu
Yasir Almutairi - yta5032@psu.edu

I declare that what has been written in this work has been written by me and that no part has been copied from scientific publications,
the Internet or from other online sources or was already presented in the academic field by me or by other students.


*/
package tictactoe;

public class PlayerNotFoundException extends Exception {

   /**
    * This exception is thrown when a player is not found in the Tic Tac Toe game.
    * 
    * @author: Yasir Almutairi
    */
   public PlayerNotFoundException(String message) {
      super(message);
   }
}
