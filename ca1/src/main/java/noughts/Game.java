/*
 * Game.java
 *
 * Represents a game of noughts and crosses
 */

package noughts;

import java.util.*;

/**
 *
 * @author ereiter and Laura Orlowska
 */
public class Game {

    BoxStatus[] board = new BoxStatus[9];  // board contains 9 boxes
    //BoxStatus currentTurn = BoxStatus.Empty;
    BoxStatus firstTurn = BoxStatus.Human;//tracks who took first turn in game

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
        board[n-1] = BoxStatus.Human;//turn finished
        //currentTurn=BoxStatus.Computer;//current turn now computer
    }

    public void setComputer(int n) {
        // computer claims square N
        board[n-1] = BoxStatus.Computer;//turn finished
        //currentTurn=BoxStatus.Human;//current turn now human
    }

    public BoxStatus getFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(BoxStatus firstTurn) {
        this.firstTurn = firstTurn;
    }

    //public BoxStatus getCurrentTurn(){return currentTurn;}
    //public void setCurrentTurn(BoxStatus currentTurn) {this.currentTurn = currentTurn;}

    public void setEmpty(int n){
        board[n-1]=BoxStatus.Empty;
    }

    public void resetBoard(){
        //all board squares back to empty
        for(int i = 0; i<9; i++)
            board[i] = BoxStatus.Empty;
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
        // print the board on System.out
        System.out.println("Board");
        System.out.printf("| %c %c %c |\n", boxChar(1), boxChar(2), boxChar(3));
        System.out.printf("| %c %c %c |\n", boxChar(4), boxChar(5), boxChar(6));
        System.out.printf("| %c %c %c |\n", boxChar(7), boxChar(8), boxChar(9));
    }

    public WinStatus getResult(){//returns the state of the board
        List<BoxStatus> computerWin = Arrays.asList(BoxStatus.Computer,BoxStatus.Computer,BoxStatus.Computer);//winning computer list
        List<BoxStatus> humanWin = Arrays.asList(BoxStatus.Human,BoxStatus.Human,BoxStatus.Human);//wining human list
        for(int j=0;j<8;j++){//checks all winning combinations for Computer or Human win
            List<BoxStatus> checkCombo = checkWinningCombos(j);
            if (checkCombo.equals(computerWin)) {
                return WinStatus.COMPUTER; //board complete or incomplete and Computer won
            } else if (checkCombo.equals(humanWin)) {
                return WinStatus.HUMAN;//board complete or incomplete and Human won
            }
        }
        for(int i=1;i<10;i++){
            if(this.getBox(i)==BoxStatus.Empty){
                return WinStatus.INCOMPLETE;//board not complete and no wins found
            }
        }
        return WinStatus.DRAW;//board complete and no wins founds
    }

    public List<BoxStatus> checkWinningCombos(int i){
        List<BoxStatus> winningCombo = new ArrayList<>();
        switch (i){//all possible winning states
            case 0: winningCombo = List.of(this.getBox(1),this.getBox(2),this.getBox(3));break;
            case 1: winningCombo = List.of(this.getBox(4),this.getBox(5),this.getBox(6));break;
            case 2: winningCombo = List.of(this.getBox(7),this.getBox(8),this.getBox(9));break;
            case 3: winningCombo = List.of(this.getBox(1),this.getBox(4),this.getBox(7));break;
            case 4: winningCombo = List.of(this.getBox(2),this.getBox(5),this.getBox(8));break;
            case 5: winningCombo = List.of(this.getBox(3),this.getBox(6),this.getBox(9));break;
            case 6: winningCombo = List.of(this.getBox(1), this.getBox(5), this.getBox(9));break;
            case 7: winningCombo = List.of(this.getBox(3),this.getBox(5),this.getBox(7));break;
            default:
        }
        return winningCombo;
    }

    public void printResultMessage(){
        //print message of game result
        switch (this.getResult()){
            case COMPUTER:System.out.println("Computer Wins!");break;
            case HUMAN:System.out.println("Well Done! You Win!");break;
            case DRAW:System.out.println("It's A Draw");break;

        }
    }
}









