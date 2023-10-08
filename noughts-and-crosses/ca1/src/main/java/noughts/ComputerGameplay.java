/*
 * ComputerGameplay.java
 *
 * Utility class for the Computers gameplay using my implementation of minimax algorithm.
 */

package noughts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Laura Orlowska
 */

public class ComputerGameplay {

    public static int score(Game game){//score possibleBoards from perspective of Computer.
        int score = 0;
        switch (game.getResult()){
            case HUMAN:score =  -10;break; //if board result = Human win, set score to -10
            case COMPUTER: score = 10;break; //if board result = Computer win, set score to 10
        }
        return score;
    }

    public static Map<Integer,Game> getPossibleMoves(Game game){//returns HashMap of all the possible
        // next moves for the current board. Key = Number where move is placed on board, Value =
        //game object showing board after possible move is made
        Map<Integer,Game> possibleBoards = new HashMap<>();

        for (int i=1;i<10;i++) {
            Game currentBoard = copyBoard(game);//copy current board
            if (currentBoard.getBox(i)==BoxStatus.Empty){ //skip over all taken squares and place
                // current player in next empty spot of the iteration.
                if (getCurrentTurn(currentBoard)==BoxStatus.Computer){
                    //System.out.println("c"+currentBoard.currentTurn);
                    currentBoard.setComputer(i);
                }
                else{currentBoard.setHuman(i);}
                possibleBoards.put(i,currentBoard);//add move index and game board to possibleBoards
            }
        }
        return possibleBoards;
    }
    public static int findMaxScore(Game game){//finds best option for Computer
        System.out.println("max"+game.getResult());
        game.printBoard();
        if (game.getResult()!=WinStatus.INCOMPLETE){//base case for recursion
            return score(game);//assign a score to the completed possible game
        }
        int maxScore=-10;
        for(Game possibleMove:getPossibleMoves(game).values()){//iterate through every possible game generated from game board
            maxScore=Math.max(maxScore,findMinScore(possibleMove));//find max possible score
        }
        System.out.println(maxScore);
        return maxScore;
    }

    public static int findMinScore(Game game){//finds best option for Human
        System.out.println("min"+game.getResult());
        game.printBoard();
        if (game.getResult()!=WinStatus.INCOMPLETE){//base case for recursion
            return score(game);//assign a score to the completed possible game
        }
        int minScore=10;
        for(Game possibleMove:getPossibleMoves(game).values()){
            minScore=Math.min(minScore,findMaxScore(possibleMove));//find min possible score
        }
        System.out.println(minScore);
        return minScore;
    }

    public static int getBestMove(Game game){//returns best move for Computer
        Map<Integer,Game> possibleBoards = getPossibleMoves(game);//square number taken by possible move and game board after that move
        ArrayList<Integer> scores = new ArrayList<>(); //tracks scores of finished possible games (corresponds to key,value pairs in possibleBoards)

        for (Game possibleMove:possibleBoards.values()){
            scores.add(findMinScore(possibleMove));//add scores generated by mutual recursion of the findMinScore and findMaxScore methods
        }

        int maxScore = scores.get(0);
        int maxIndex=0;
        for(int i=1;i<scores.size();i++){//find max score and index of that score i.e. the best score the Computer can get
            if(scores.get(i)>maxScore){
                maxScore=scores.get(i);
                maxIndex=i;
            }
        }
        for(int key:possibleBoards.keySet()){
            possibleBoards.get(key).printBoard();
        }
        System.out.println(scores);
        int bestMove = (int) possibleBoards.keySet().toArray()[maxIndex];//using maxIndex get key (i.e. the placement index of the best possible move for Computer)
        System.out.println(bestMove);
        return bestMove;//return the best move
    }

    public static Game copyBoard(Game game){//make copy of current game object
        Game boardCopy = new Game();//new Game object for copy
        if (game.getFirstTurn()==BoxStatus.Computer){
            boardCopy.setFirstTurn(BoxStatus.Computer);
        }
        for(int i=1;i<10;i++){//iterate through game and copy positions to boardCopy
            if (game.getBox(i)==BoxStatus.Computer){
                boardCopy.setComputer(i);
            }
            else if (game.getBox(i)==BoxStatus.Human) {
                boardCopy.setHuman(i);
            }
            else{
                boardCopy.setEmpty(i);
            }
        }
        return boardCopy;
    }

    public static BoxStatus getCurrentTurn(Game game) {//returns the current turn of the game
        BoxStatus currentTurn=BoxStatus.Human ;
        int humanCount = 0;
        int computerCount = 0;
        System.out.println(game.getFirstTurn());
        if(game.getFirstTurn()==BoxStatus.Computer){
            humanCount++;
        }
        for(BoxStatus i:game.board){
            if(i==BoxStatus.Human){
                humanCount++;
            }
            else if(i==BoxStatus.Computer){
                computerCount++;
            }
        }
        if(humanCount>computerCount){
            currentTurn=BoxStatus.Computer;
        }
        System.out.println(currentTurn);
        return currentTurn;
    }



}
