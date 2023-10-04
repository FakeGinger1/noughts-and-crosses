/*
 * Play.java
 *
 * Play a game of noughts and crosses
 * includes main method
 */
 
 package noughts;

import java.util.Random;
import java.util.Scanner;

class Play{
    Game game;   // the noughts and crosses game
    Scanner input;
        public static void main(String[] args) {
            // main method - just create a Play object
            new Play();
        }

        public Play () {
            // constructor
            System.out.println("Welcome to noughts and crosses"); 
            game = new Game();  // create game board
            input = new Scanner(System.in);  // Scanner for user input
            while (true) { // infinite loop
                game.printBoard(); // print board
                playerTurn(); // human turn
                game.printBoard(); // print board
                computerTurn(); // computer tuen
        }
    }
    public void playerTurn()  {
        // Player turn: just read in a sqaure and claim it for human
        System.out.print("Take a square (1-9): ");
                // Reading data using readLine
        int square = input.nextInt();
        while ((square<1)||(square>9)||(!game.isEmpty(square))){//user input validation
            if(square<1||square>9){//choice not in bounds of board
                System.out.println("Your choice is out of bounds of the board, try again:");
            }
            else if(game.getBox(square)==BoxStatus.Computer) {//square taken by computer
                System.out.println("That square is taken by the computer, try again:");
            }
            else{//square taken by human
                System.out.println("That square is taken by you, try again:");
            }
            square = input.nextInt();
        }
        game.setHuman(square);
    }

    public void computerTurn() {
        // computer turn - currently does nothing other than print out a message
        Random rand = new Random();
        int square = rand.nextInt(10);
        while ((square==0)||!game.isEmpty(square)){
            square = rand.nextInt(10);
        }
        game.setComputer(square);
    }
}