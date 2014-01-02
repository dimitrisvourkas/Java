import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.dvinformatics.repository.PieceAddRecord;

/**
 * 
 */

/**
 * @author Dimitrios Vourkas
 *
 */
public class testAddRecord {
	PieceAddRecord tester=new PieceAddRecord();
	@Test
	public void testRotateMatrix() {
		
		int[][] matrixA={{1,1,0,1,0},{1,1,1,1,0},{1,1,1,1,0},{0,1,1,1,1},{0,0,1,0,0}};
		int[][] rotatedA={{0,0,1,1,1},{0,1,1,1,1},{1,1,1,1,0},{0,1,1,1,1},{0,1,0,0,0}};
		int[][] matrixC=new int[5][5];
		tester.rotateMatrix(matrixA, matrixC);
		assertArrayEquals(rotatedA, matrixC);
	}
	
	@Test
	public void testFlipMatrix() {
	
		int[][] matrixA={{1,1,0,1,0},{1,1,1,1,0},{1,1,1,1,0},{0,1,1,1,1},{0,0,1,0,0}};
		int[][] flipA={{0,1,0,1,1},{0,1,1,1,1},{0,1,1,1,1},{1,1,1,1,0},{0,0,1,0,0}};
		int[][] matrixC=new int[5][5];
		tester.flipMatrix(matrixA, matrixC);
		assertArrayEquals(flipA, matrixC);
	}

}
