package noughts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

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
        assertEquals(testBoard.getResult(testBoard),WinStatus.INCOMPLETE);//test empty board

        //partially fill board with Human and Computer
        testBoard.setHuman(1);
        testBoard.setComputer(6);
        testBoard.setHuman(2);
        testBoard.setComputer(9);
        assertEquals(testBoard.getResult(testBoard),WinStatus.INCOMPLETE);//test partially filled board

        //human win in first row
        testBoard.setHuman(3);
        assertEquals(testBoard.getResult(testBoard),WinStatus.HUMAN);//test human win case for first row

        //Computer win in third row
        testBoard.resetBoard();
        testBoard.setComputer(7);
        testBoard.setComputer(8);
        testBoard.setComputer(9);
        assertEquals(testBoard.getResult(testBoard),WinStatus.COMPUTER);//test computer win case in third row

        //Computer win in third column
        testBoard.resetBoard();
        testBoard.setComputer(3);
        testBoard.setComputer(6);
        testBoard.setComputer(9);
        assertEquals(testBoard.getResult(testBoard),WinStatus.COMPUTER);//test computer win case in third column

        //Human win in second column
        testBoard.resetBoard();
        testBoard.setHuman(2);
        testBoard.setHuman(5);
        testBoard.setHuman(8);
        assertEquals(testBoard.getResult(testBoard),WinStatus.HUMAN);//test human win case for first row and third column

        //Human win in first diagonal
        testBoard.resetBoard();
        testBoard.setHuman(1);
        testBoard.setHuman(5);
        testBoard.setHuman(9);
        assertEquals(testBoard.getResult(testBoard),WinStatus.HUMAN);//test human win case for first diagonal

        //Computer win in second diagonal
        testBoard.resetBoard();
        testBoard.setComputer(3);
        testBoard.setComputer(5);
        testBoard.setComputer(7);
        assertEquals(testBoard.getResult(testBoard),WinStatus.COMPUTER);//test computer win case in second diagonal
    }


}
