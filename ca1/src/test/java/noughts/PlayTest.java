package noughts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class PlayTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Test getResult
     */
    @Test
    public void testGetResult(){
        Game testBoard = new Game();//initialize fully empty board
        assertEquals(testBoard.getResult(),WinStatus.INCOMPLETE);//test empty board

        //partially fill board with Human and Computer
        testBoard.setHuman(1);
        testBoard.setComputer(6);
        testBoard.setHuman(2);
        testBoard.setComputer(9);
        assertEquals(testBoard.getResult(),WinStatus.INCOMPLETE);//test partially filled board

        //human win in first row
        testBoard.setHuman(3);
        assertEquals(testBoard.getResult(),WinStatus.HUMAN);//test human win case for first row

        //Computer win in third row
        testBoard.resetBoard();
        testBoard.setComputer(7);
        testBoard.setComputer(8);
        testBoard.setComputer(9);
        assertEquals(testBoard.getResult(),WinStatus.COMPUTER);//test computer win case in third row

        //Computer win in third column
        testBoard.resetBoard();
        testBoard.setComputer(3);
        testBoard.setComputer(6);
        testBoard.setComputer(9);
        assertEquals(testBoard.getResult(),WinStatus.COMPUTER);//test computer win case in third column

        //Human win in second column
        testBoard.resetBoard();
        testBoard.setHuman(2);
        testBoard.setHuman(5);
        testBoard.setHuman(8);
        assertEquals(testBoard.getResult(),WinStatus.HUMAN);//test human win case for first row and third column

        //Human win in first diagonal
        testBoard.resetBoard();
        testBoard.setHuman(1);
        testBoard.setHuman(5);
        testBoard.setHuman(9);
        assertEquals(testBoard.getResult(),WinStatus.HUMAN);//test human win case for first diagonal

        //Computer win in second diagonal
        testBoard.resetBoard();
        testBoard.setComputer(3);
        testBoard.setComputer(5);
        testBoard.setComputer(7);
        assertEquals(testBoard.getResult(),WinStatus.COMPUTER);//test computer win case in second diagonal
    }

    /**
     * Test copyBoard
     */
    @Test
    public void testCopyBoard(){
        //initialise and fill test board
        Game testBoard = new Game();
        testBoard.setHuman(1);
        testBoard.setComputer(6);
        testBoard.setHuman(2);
        testBoard.setComputer(9);

        Game copiedBoard = ComputerGameplay.copyBoard(testBoard);
        for(int i=1;i<10;i++) {//check if fields successfully copied
            assertEquals(copiedBoard.boxChar(i), testBoard.boxChar(i));
        }
    }

    /**
     * Test getCurrentTurn for human and computer taking first turn
     */
    @Test
    public void testGetCurrentTurn(){
        //initialise and fill test board
        Game testBoard = new Game();
        testBoard.setHuman(1);
        testBoard.setComputer(6);
        testBoard.setHuman(2);
        testBoard.setComputer(4);

        assertEquals(ComputerGameplay.getCurrentTurn(testBoard),BoxStatus.Human);

        testBoard.resetBoard();
        testBoard.setComputer(6);
        testBoard.setFirstTurn(BoxStatus.Computer);
        testBoard.setHuman(2);
        testBoard.setComputer(4);
        testBoard.setHuman(1);

        assertEquals(ComputerGameplay.getCurrentTurn(testBoard),BoxStatus.Computer);
    }

    /**
     * Test getPossibleMoves
     */
    @Test
    public void testGetPossibleMoves(){
        Game testBoard = new Game();
        testBoard.setHuman(1);
        testBoard.setComputer(6);
        testBoard.setHuman(2);

        Set<Integer> expectedKeys = new HashSet<>();//check if generates correct possible moves
        expectedKeys.add(3);
        expectedKeys.add(4);
        expectedKeys.add(5);
        expectedKeys.add(7);
        expectedKeys.add(8);
        expectedKeys.add(9);

        assertEquals(ComputerGameplay.getPossibleMoves(testBoard).keySet(),expectedKeys);
        assertEquals(BoxStatus.Computer,ComputerGameplay.getPossibleMoves(testBoard).get(4).getBox(4));//check correct square filled with correct player
        assertEquals(BoxStatus.Computer,ComputerGameplay.getPossibleMoves(testBoard).get(9).getBox(9));
    }

    /**
     * Test scoring method
     */
    @Test
    public void testScore(){
        Game testHumanWinBoard = new Game();
        testHumanWinBoard.setHuman(1);
        testHumanWinBoard.setHuman(2);
        testHumanWinBoard.setHuman(3);

        assertEquals(ComputerGameplay.score(testHumanWinBoard),-10);

        Game testComputerWinBoard = new Game();
        testComputerWinBoard.setComputer(1);
        testComputerWinBoard.setComputer(5);
        testComputerWinBoard.setComputer(9);

        assertEquals(ComputerGameplay.score(testComputerWinBoard),10);
    }

    /**
     * Test move mechanics for computer
     */
    @Test
    public void testGetBestMove(){
        Game testBoard = new Game();
        testBoard.setHuman(1);
        testBoard.setComputer(5);
        testBoard.setHuman(3);

        assertEquals(2,ComputerGameplay.getBestMove(testBoard));//check human first, computer blocks human

        testBoard.resetBoard();
        testBoard.setHuman(6);
        testBoard.setFirstTurn(BoxStatus.Human);
        testBoard.setComputer(3);
        testBoard.setHuman(4);
        testBoard.setComputer(5);
        testBoard.setHuman(1);

        assertEquals(7,ComputerGameplay.getBestMove(testBoard));//check human first, computer win possibility


        testBoard.resetBoard();
        testBoard.setComputer(9);
        testBoard.setFirstTurn(BoxStatus.Computer);
        testBoard.setHuman(1);
        testBoard.setComputer(3);
        testBoard.setHuman(5);

        assertEquals(6,ComputerGameplay.getBestMove(testBoard));//check computer first, computer win possibility

        testBoard.resetBoard();
        testBoard.setComputer(1);
        testBoard.setHuman(3);
        testBoard.setComputer(5);
        testBoard.setHuman(9);

        assertEquals(6,ComputerGameplay.getBestMove(testBoard));//check computer first, computer blocks human


    }

}
