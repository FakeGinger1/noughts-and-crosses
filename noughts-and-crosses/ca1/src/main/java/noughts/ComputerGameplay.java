package noughts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputerGameplay {

    public static int score(Game game){//score projected moves for Computer.
        int result = 0;
        if (game.getResult()==WinStatus.COMPUTER) {//computer win results in +10 for computer
            result = 10;
        }
        else if (game.getResult()==WinStatus.HUMAN){//human win results in -10 for computer
            result = -10;
        }
        else if (game.getResult()==WinStatus.DRAW){//draw is 0
            result = 0;
        }
        return result;
    }

    public static Map<Integer,Game> getPossibleMoves(Game game){//get a list of all the empty spaces on current board
        Map<Integer,Game> possibleBoards = new HashMap<>();

        for (int i=1;i<10;i++) {
            Game currentBoard = copyBoard(game);//copy current board
            if (currentBoard.getBox(i)==BoxStatus.Empty){
                if (getCurrentTurn(currentBoard)==BoxStatus.Computer){
                    currentBoard.setComputer(i);
                }
                else {currentBoard.setHuman(i);}
                possibleBoards.put(i,currentBoard);
            }
        }
        return possibleBoards;
    }
    public static int findMaxScore(Game game){
        System.out.println("max"+game.getResult());
        game.printBoard();
        if (game.getResult()!=WinStatus.INCOMPLETE){
            return score(game);
        }
        int maxScore=-10;
        for(Game possibleMove:getPossibleMoves(game).values()){
            maxScore=Math.max(maxScore,findMinScore(possibleMove));
        }
        System.out.println(maxScore);
        return maxScore;
    }

    public static int findMinScore(Game game){
        System.out.println("min"+game.getResult());
        game.printBoard();
        if (game.getResult()!=WinStatus.INCOMPLETE){
            return score(game);
        }
        int minScore=10;
        for(Game possibleMove:getPossibleMoves(game).values()){
            minScore=Math.min(minScore,findMaxScore(possibleMove));
        }
        System.out.println(minScore);
        return minScore;
    }

    public static int getBestMove(Game game){
        Map<Integer,Game> possibleBoards = getPossibleMoves(game);
        ArrayList<Integer> scores = new ArrayList<>();

        for (Game possibleMove:possibleBoards.values()){
            scores.add(findMinScore(possibleMove));
        }

        int maxScore = scores.get(0);
        int maxIndex=0;
        for(int i=1;i<scores.size();i++){
            if(scores.get(i)>maxScore){
                maxScore=scores.get(i);
                maxIndex=i;
            }
        }
        for(int key:possibleBoards.keySet()){
            possibleBoards.get(key).printBoard();
        }
        System.out.println(scores);
        int bestMove = (int) possibleBoards.keySet().toArray()[maxIndex];
        System.out.println(bestMove);
        return bestMove;
    }

    public static Game copyBoard(Game game){//make copy of current game object
        Game boardCopy = new Game();//new Game object for copy
        for(int i=1;i<10;i++){//iterate through bard and copy positions to boardCopy
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

    public static BoxStatus getCurrentTurn(Game game) {
        BoxStatus currentTurn=BoxStatus.Human;
        int humanCount = 0;
        int computerCount = 0;
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

        return currentTurn;
    }



}
