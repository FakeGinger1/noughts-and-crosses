/*
 * Play.java
 *
 * Play a game of noughts and crosses
 * includes main method
 */
 
 package noughts;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Laura Orlowska
 */
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
            System.out.println("Do you want to play the first move? (Y/N):");
            String firstMove = input.nextLine();
            while(!Objects.equals(firstMove, "Y") && !Objects.equals(firstMove, "N")){
                System.out.println("Please choose Y OR N:");
                firstMove = input.nextLine();
            }
            while (true){
                if (Objects.equals(firstMove, "N")) { //player first move
                    computerFirst();
                }
                playerTurn(); //player moves
                game.printBoard(); //print board
                if(game.getResult()!=WinStatus.INCOMPLETE){
                    game.printResultMessage();
                    break;
                }
                computerTurn(); //computer moves
                game.printBoard();
                if(game.getResult()!=WinStatus.INCOMPLETE){
                    game.printResultMessage();
                    break;
                }
            }
    }
    public void playerTurn()  {
        // Player turn: just read in a square and claim it for human
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
        // computer turn, calls getBestMove to calculate the best possible move for Computer
        game.setComputer(ComputerGameplay.getBestMove(game));
    }


    public void computerFirst(){
        game.setComputer(1);
        game.printBoard();
    }


}