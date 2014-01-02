import static org.junit.Assert.*;

import org.junit.Test;

import com.dvinformatics.business.PuzzleSolution;

/**
 * 
 */

/**
 * @author Dimitrios Vourkas
 *
 */
public class TestPuzzleSolution {

	PuzzleSolution tester=new PuzzleSolution();
	@Test
	public void testCheckCorner() {
		
		assertFalse(tester.checkCorner(1,1));
		assertTrue(tester.checkCorner(0, 1));
	}
	@Test
	public void testCheckHorizontallSide(){
		
		int[][] matrixA={{1,1,0,1,0},{1,1,1,1,0},{1,1,1,1,0},{0,1,1,1,1},{0,0,1,0,0}};
		int[][] matrixB={{0,0,0,1,1},{1,1,1,1,0},{1,1,1,1,1},{0,1,1,1,0},{0,1,0,1,0}};
		assertFalse(tester.checkHorizontalSide(matrixA, matrixB));
		
	}
	
	@Test
	public void testCheckHorizontallSide2(){
		
		int[][] matrixA={{1,1,0,1,0},{1,1,1,1,0},{1,1,1,1,0},{0,1,1,1,1},{0,0,1,0,0}};
		int[][] matrixB={{0,0,0,1,1},{1,1,1,1,0},{1,1,1,1,1},{0,1,1,1,0},{0,1,0,1,0}};
		assertFalse(tester.checkHorizontalSide2(matrixA, matrixB));
		
	}
	
	@Test
	public void testCheckRightVerticalSide(){
		int[][] matrixA={{1,1,0,1,0},{1,1,1,1,0},{1,1,1,1,0},{0,1,1,1,1},{0,0,1,0,0}};
		int[][] matrixB={{0,0,0,1,1},{1,1,1,1,0},{1,1,1,1,1},{0,1,1,1,0},{0,1,0,1,0}};
		assertTrue(tester.checkRightVerticalSide(matrixA, matrixB));
		assertFalse(tester.checkHorizontalVerticalUpSide(matrixA, matrixB));
	}

}
