/*
 * Game.java
 *
 * Represents a game of noughts and crosses
 */

package noughts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ereiter
 */
public class Game {
    
    BoxStatus[] board = new BoxStatus[9];  // board contains 9 boxes
    static{//list of lists of possible winning combination indexes for when board displayed as list
        List<Integer> firstRow = Arrays.asList(0,1,2);
        List<Integer> secondRow = Arrays.asList(3,4,5);
        List<Integer> thirdRow = Arrays.asList(6,7,8);
        List<Integer> firstColumn = Arrays.asList(0,3,6);
        List<Integer> secondColumn = Arrays.asList(1,4,7);
        List<Integer> thirdColumn = Arrays.asList(2,5,8);
        List<Integer> firstDiagonal = Arrays.asList(0,4,8);
        List<Integer> secondDiagonal = Arrays.asList(2,4,6);

        List<List> winningCombinations = new ArrayList<>();
        winningCombinations.add(firstRow);
        winningCombinations.add(secondRow);
        winningCombinations.add(thirdRow);
        winningCombinations.add(firstColumn);
        winningCombinations.add(secondColumn);
        winningCombinations.add(thirdColumn);
        winningCombinations.add(firstDiagonal);
        winningCombinations.add(secondDiagonal);
    }

    /** Creates a new instance of game */
    public Game() {
        for(int i = 0; i<9; i++)
            board[i] = BoxStatus.Empty;  // initially each box is empty (not taken)
        
    }
    
    public boolean isEmpty(int n) {
        // is a box empty?
        return (board[n-1] == BoxStatus.Empty);
    }
    
    public boolean isComputer(int n) {
        // is a box taken by the Computer?
        return (board[n-1] == BoxStatus.Computer);
    }
    
    public boolean isHuman(int n) {
        // is a box taken by the Human?
        return (board[n-1] == BoxStatus.Human);
    }
    
    public void setHuman(int n) {
        // human claims square N
        board[n-1] = BoxStatus.Human;
    }
    
    public void setComputer(int n) {
        // computer claims square N
        board[n-1] = BoxStatus.Computer;
    }
    
    public BoxStatus getBox(int n) {
        // return square N
        return board[n-1];
    }

    public char boxChar(int n) {
        // return a character which shows whether a square is empty, taken by the computer, or taken by the human
        switch (board[n-1]) {
            case Human: return 'H';
            case Computer: return 'C';
            case Empty: return '.';
        }
        return ' ';
    }

    public void printBoard() {
        // print the noard on System.out
        System.out.println("Board");
        System.out.printf("| %c %c %c |\n", boxChar(1), boxChar(2), boxChar(3));
        System.out.printf("| %c %c %c |\n", boxChar(4), boxChar(5), boxChar(6));
        System.out.printf("| %c %c %c |\n", boxChar(7), boxChar(8), boxChar(9));
    }

    public String getResult(){//Takes board input and displays the current status of the board
        ArrayList<BoxStatus> currentPositions = new ArrayList<>();
        for (int i=1;i<10;i++){
            currentPositions.add(this.getBox(i));
        }
        System.out.println(currentPositions);
        if (currentPositions.contains(BoxStatus.Empty)){
            return "Incomplete";
        }


        return"";

    }

    
}
