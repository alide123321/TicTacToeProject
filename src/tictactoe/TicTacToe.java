package tictactoe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class TicTacToe implements Serializable {
   private final static String COMPUTERNNAME = "COMPUTER";
   private final static String DEFAULTPLAYERNAME = "Player 1";
   private final static String FILENAME = "TicTacToeGameSave.ser";
   private static HashMap<String, int[]> ScoreList = new HashMap<String, int[]>(); // [Wins, Ties, Losses]
   private Board B;
   private boolean GameOver;
   private boolean Player1Turn;
   private String Player1Name;
   private String Player2Name;

   public TicTacToe(String player1Name, String player2Name) {
      B = new Board();
      Player1Name = player1Name;
      Player2Name = player2Name;
      Player1Turn = true;
      GameOver = false;
   }

   public TicTacToe(String player1Name) {
      this(player1Name, COMPUTERNNAME);
   }

   public TicTacToe() {
      this(DEFAULTPLAYERNAME, COMPUTERNNAME);
   }

   /**
    * @param pos The position on the board.
    * @return True if the game has a winner after each move
    * @throws UsedSpacesException        if the position is already used
    * @throws NotAValidPositionException if not 1 <= pos <= 9
    * @throws GameOverException          if the game is already over
    * @throws UnableToPlayException      if there is already a winner and the game
    *                                    is not over
    */
   public boolean Play(int pos)
         throws UsedSpacesException, NotAValidPositionException, GameOverException, UnableToPlayException {

      if (isGameOver())
         throw new GameOverException("Game is already over");
      if (CheckWinner() != ' ')
         throw new UnableToPlayException("There is already a winner Please End the game");

      B.Play(Player1Turn, pos);
      Player1Turn = !Player1Turn;

      if (CheckWinner() != ' ')
         return true;

      if (Player2Name.equals(COMPUTERNNAME)) {

         int playPOS;
         do {

            playPOS = (int) (Math.random() * 9) + 1;

         } while (!B.ValidPosition(playPOS));

         B.Play(Player1Turn, playPOS);
         Player1Turn = !Player1Turn;
      }

      return CheckWinner() != ' ';

   }

   /**
    * @return the character showing the winner ('X' or 'O'),
    *         or (' ') if no winner yet,
    *         or ('T') if it's a tie
    */
   public char CheckWinner() {
      char winner = ' ';

      // ? There has to be a better way of doing these checks

      for (int i = 0; i < 3; i++) {
         if ((B.getChar(i, 0) == B.getChar(i, 1)) && (B.getChar(i, 1) == B.getChar(i, 2)) && (B.getChar(i, 0) != ' '))
            winner = B.getChar(i, 0);

         if ((B.getChar(0, i) == B.getChar(1, i)) && (B.getChar(1, i) == B.getChar(2, i)) && (B.getChar(0, i) != ' '))
            winner = B.getChar(0, i);
      }

      if ((B.getChar(0, 0) == B.getChar(1, 1)) && (B.getChar(1, 1) == B.getChar(2, 2)) && (B.getChar(0, 0) != ' '))
         winner = B.getChar(0, 0);

      if ((B.getChar(0, 2) == B.getChar(1, 1)) && (B.getChar(1, 1) == B.getChar(2, 0)) && (B.getChar(0, 2) != ' '))
         winner = B.getChar(0, 2);

      if (winner == ' ') {

         boolean tie = true;

         for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               if (B.getChar(i, j) == ' ') {
                  tie = false;
               }
            }
         }

         if (tie) {
            return 'T';
         }
      }

      return winner;
   }

   /**
    * Loads a Tic-Tac-Toe game from a file.
    * 
    * @return The loaded TicTacToe game.
    * @throws UnableToLoadGameException If there is an error loading the game.
    */
   public static TicTacToe loadGame() throws UnableToLoadGameException {
      TicTacToe game = null;
      // Try to read the cart object from a file
      try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(FILENAME))) {

         game = (TicTacToe) OIS.readObject();
      } catch (FileNotFoundException e) {

         throw new UnableToLoadGameException("Error: File not found. " + e.getMessage());
      } catch (IOException e) {

         throw new UnableToLoadGameException("General I/O exception: " + e.getMessage());
      } catch (ClassNotFoundException e) {

         throw new UnableToLoadGameException("Class not found:" + e.getMessage());
      }

      return game;
   }

   /**
    * Saves the current game state to a file.
    * 
    * @throws UnableToSaveGameException if there is an error while saving the game
    */
   public void SaveGame() throws UnableToSaveGameException {
      // Try to write the cart object to a file
      try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(FILENAME))) {

         OOS.writeObject(this);
         OOS.flush();
      } catch (FileNotFoundException e) {

         throw new UnableToSaveGameException("Error: File not found. " + e.getMessage());
      } catch (IOException e) {

         throw new UnableToSaveGameException("General I/O exception: " + e.getMessage());
      }

   }

   /**
    * Ends the game and updates the scores based on the winner.
    * 
    * @param printScores a boolean value indicating whether to print the scores
    *                    after the game ends
    * @throws GameOverException if the game is already over
    */
   public void endGame(boolean print) throws GameOverException {

      if (isGameOver())
         throw new GameOverException("Game is already over");

      char winner = CheckWinner();

      int[] P1scores;
      int[] P2scores;
      try {
         P1scores = getScoreArr(Player1Name, false);
      } catch (PlayerNotFoundException e) {
         P1scores = new int[] { 0, 0, 0 };
      }
      try {
         P2scores = getScoreArr(Player2Name, false);
      } catch (PlayerNotFoundException e) {
         P2scores = new int[] { 0, 0, 0 };
      }

      if (winner == 'T') {
         if (print)
            System.out.println("It's a tie!");

         P1scores[1]++;
         P2scores[1]++;

         ScoreList.put(Player1Name, P1scores);
         ScoreList.put(Player2Name, P2scores);

      } else if (winner == 'X') {
         if (print)
            System.out.println(Player1Name + " wins!");

         P1scores[0]++;
         P2scores[2]++;

         ScoreList.put(Player1Name, P1scores);
         ScoreList.put(Player2Name, P2scores);

      } else if (winner == 'O') {

         if (print)
            System.out.println(Player2Name + " wins!");

         P1scores[2]++;
         P2scores[0]++;

         ScoreList.put(Player1Name, P1scores);
         ScoreList.put(Player2Name, P2scores);

      } else {
         if (print)
            System.out.println("Game Over! No winner!");

         P1scores[1]++;
         P2scores[1]++;

         ScoreList.put(Player1Name, P1scores);
         ScoreList.put(Player2Name, P2scores);
      }

      if (print) {
         try {
            getScoreArr(Player1Name, true);
            getScoreArr(Player2Name, true);
         } catch (PlayerNotFoundException e) {
            System.err.println("INTERNAL ERROR PLAYER NAME CHANGED DURING GAME!");
         }

      }

      GameOver = true;

   }

   /**
    * Checks if the game is over.
    * 
    * @return true if the game is over, false otherwise.
    */
   public boolean isGameOver() {
      return GameOver;
   }

   /**
    * Clears the game board and resets the game state.
    * After calling this method, the board will be empty, the turn will be set to
    * Player 1,
    * and the game over flag will be set to false.
    */
   public void clearBoard() {
      B = new Board();
      Player1Turn = true;
      GameOver = false;
   }

   /**
    * Prints the current state of the Tic-Tac-Toe board.
    */
   public void printBoard() {

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            System.out.print(B.getChar(i, j));
            if (j < 2)
               System.out.print("|");
         }
         System.out.println();
         if (i < 2)
            System.out.println("-----");
      }
   }

   /**
    * Returns the current state of the game board.
    *
    * @return a 2D char array representing the game board
    */
   public char[][] getBoard() {
      return B.getBoard();
   }

   /**
    * Returns the character at the specified position on the TicTacToe board.
    *
    * @param pos the position on the board (1-9)
    * @return the character at the specified position
    */
   public char getChar(int pos) throws NotAValidPositionException {
      if (pos < 1 || pos > 9)
         throw new NotAValidPositionException();

      return B.getChar((pos % 3), ((pos - 1) % 3));
   }

   /**
    * Returns the name of Player 1.
    *
    * @return the name of Player 1
    */
   public String getPlayer1Name() {
      return Player1Name;
   }

   /**
    * Returns the name of player 2.
    *
    * @return the name of player 2
    */
   public String getPlayer2Name() {
      return Player2Name;
   }

   /**
    * Returns the current player's turn.
    * 
    * @return The character representing the current player's turn ('X' or 'O').
    */
   public char getPlayerTurn() {
      return Player1Turn ? 'X' : 'O';
   }

   /**
    * Returns the score list.
    *
    * @return the score list as a HashMap with String keys and int[] values.
    */
   public static HashMap<String, int[]> getScoreList() {
      return ScoreList;
   }

   /**
    * Retrieves the score list for a given player.
    *
    * @param player the name of the player
    * @param print  a boolean value indicating whether to print the score list
    * @return an array of integers representing the scores of the player
    * @throws PlayerNotFoundException if the player is not found in the score list
    */
   public static int[] getScoreArr(String player, boolean print) throws PlayerNotFoundException {
      if (!ScoreList.containsKey(player)) {
         if (print)
            System.out.println("Player not found in the score list");
         throw new PlayerNotFoundException("Player not found in the score list");
      }

      int[] scores = ScoreList.get(player);

      if (print)
         System.out.printf("%s: (%d - %d - %d) \n",
               player,
               scores[0],
               scores[1],
               scores[2]);

      return scores;
   }

   /**
    * Returns a boolean value indicating whether it is currently Player 1's turn.
    *
    * @return true if it is Player 1's turn, false otherwise
    */
   public boolean isPlayer1Turn() {
      return Player1Turn;
   }

}
