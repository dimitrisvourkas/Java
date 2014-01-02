package com.dvinformatics.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvinformatics.model.Puzzle;

import com.dvinformatics.repository.PieceAddRecord;
import com.dvinformatics.repository.PieceRecordSerializable;
import com.dvinformatics.repository.ReadPiecesRecords;

/*
 * Dimitrios Vourkas
 * 
 */

/*
 * The puzzle has the form bellow:
 * ********************************
 * 	      		_____
 * 				|   4 |
 * 		_____	|____|_____
 * 		|	5  |  1  |   6   |
 * 		|____|____|_____|
 *			    |  2  |
 * 		     	|____|
 * 			    |  3  |
 * 		     	|____|
 * ******************************
 */      
@Component("puzzleSolution")
public class PuzzleSolution {

	@Autowired
	public PieceRecordSerializable pieceRecordSerializable;
	@Autowired
	public ReadPiecesRecords readPiecesRecords;
	@Autowired
	public PieceAddRecord pieceAddRecord;
	@Autowired
	public Puzzle puzzle;

	
	ArrayList<PieceRecordSerializable> puzzleList = new ArrayList<PieceRecordSerializable>();

	List<int[][]> puzzleListSol = new ArrayList<int[][]>();
	List<PieceRecordSerializable> piecesArrayList = new ArrayList<PieceRecordSerializable>();

	// Constructor
	public PuzzleSolution() {

	}

	// Constructor
	public PuzzleSolution(PieceRecordSerializable pieceRecordSerializable,
			ReadPiecesRecords readPiecesRecords, PieceAddRecord pieceAddRecord,
			 Puzzle puzzle,
			ArrayList<PieceRecordSerializable> piecesArrayList,
			ArrayList<PieceRecordSerializable> puzzleList,
			ArrayList<PieceRecordSerializable> colorArrayList, String pieceID) {
		super();
		this.pieceRecordSerializable = pieceRecordSerializable;
		this.readPiecesRecords = readPiecesRecords;
		this.pieceAddRecord = pieceAddRecord;
		this.puzzle = puzzle;
		
	}

	// For the position number 6 the piece must fits with the pieces in the
	// positions
	// 4, 1, 2 and 3
	private boolean checkIfPieceFits6(int[][] matrix) {

		// Get the piece of the puzzle at the position 1
		int[][] matrixToFitPos1 = puzzleListSol.get(0);
		// Get the piece of the puzzle at the position 2
		int[][] matrixToFitPos2 = puzzleListSol.get(1);
		// Get the piece of the puzzle at the position 3
		int[][] matrixToFitPos3 = puzzleListSol.get(2);
		// Get the piece of the puzzle at the position 4
		int[][] matrixToFitPos4 = puzzleListSol.get(3);
		// if the down side of piece that there is in the puzzle
		// fits with the up side of the new piece returns true
		if (checkCorner(matrixToFitPos1[0][4], matrix[0][0])
				&& checkCorner(matrixToFitPos1[4][4], matrix[4][0])
				&& checkLeftVerticalSide(matrix, matrixToFitPos1)
				&& checkCorner(matrixToFitPos2[4][4], matrix[4][4])
				&& checkHorizontalDownVerticalSide(matrix, matrixToFitPos2)
				&& checkCorner(matrixToFitPos3[0][4], matrix[4][4])
				&& checkCorner(matrixToFitPos3[4][4], matrix[0][4])
				&& checkRightVerticalRightVerticalSide(matrix, matrixToFitPos3)
				&& checkCorner(matrixToFitPos4[0][4], matrix[0][4])
				&& checkVerticalHorizontalUpSide(matrix, matrixToFitPos4))
			
			return true;

		return false;
	}

	// For the position number 5 the piece must fits with the pieces in the
	// positions
	// 4, 1, 2 and 3
	private boolean checkIfPieceFits5(int[][] matrix) {
		// Get the piece of the puzzle at the position 1
		int[][] matrixToFitPos1 = puzzleListSol.get(0);
		// Get the piece of the puzzle at the position 2
		int[][] matrixToFitPos2 = puzzleListSol.get(1);
		// Get the piece of the puzzle at the position 3
		int[][] matrixToFitPos3 = puzzleListSol.get(2);
		// Get the piece of the puzzle at the position 4
		int[][] matrixToFitPos4 = puzzleListSol.get(3);
		// if the down side of piece that there is in the puzzle
		// fits with the up side of the new piece returns true
		if (checkCorner(matrixToFitPos1[0][0], matrix[0][4])
				&& checkCorner(matrixToFitPos1[0][4], matrix[4][4])
				&& checkRightVerticalSide(matrix, matrixToFitPos1)
				&& checkCorner(matrixToFitPos2[0][0], matrix[4][4])
				&& checkCorner(matrixToFitPos2[0][4], matrix[0][4])
				&& checkHorizontalVerticalDownSide(matrix, matrixToFitPos2)
				&& checkCorner(matrixToFitPos3[0][0], matrix[0][4])
				&& checkCorner(matrixToFitPos3[0][4], matrix[0][0])
				&& checkLeftVerticalLeftVerticalSide(matrix, matrixToFitPos3)
				&& checkCorner(matrixToFitPos4[0][0], matrix[0][0])
				&& checkCorner(matrixToFitPos4[4][0], matrix[0][4])
				&& checkHorizontalVerticalUpSide(matrix, matrixToFitPos4))
			return true;

		return false;
	}

	// For the position number 4 the piece must fits with the pieces in the
	// position
	// 3 and 1
	private boolean checkIfPieceFits4(int[][] matrix) {
		// Get the piece of the puzzle at the position 1
		int[][] matrixToFit = puzzleListSol.get(0);
		// Get the piece of the puzzle at the position 3
		int[][] matrixToFit1 = puzzleListSol.get(2);
		// if the down side of piece that there is in the puzzle
		// fits with the up side of the new piece returns true
		if (checkCorner(matrixToFit[0][0], matrix[4][0])
				&& checkCorner(matrixToFit[0][4], matrix[4][4])
				&& checkHorizontalSide2(matrix, matrixToFit)
				&& checkCorner(matrixToFit1[4][0], matrix[0][0])
				&& checkCorner(matrixToFit1[4][4], matrix[0][4])
				&& checkHorizontalSide(matrix, matrixToFit1))
			return true;

		return false;
	}

	private boolean checkIfPieceFits3(int[][] matrix) {
		int[][] matrixToFit = puzzleListSol.get(1);
		// if the down side of piece that there is in the puzzle
		// fits with the up side of the new piece returns true
		if (checkCorner(matrixToFit[4][0], matrix[0][0])
				&& checkCorner(matrixToFit[4][4], matrix[0][4])
				&& checkHorizontalSide(matrix, matrixToFit))
			return true;

		return false;
	}

	// Check if the top side of the new piece fits
	// with the down side of the piece above.
	// The sum of the corners must be <=1 and the matrix's items
	// from 1 until 3 must be ==1
	private boolean checkIfPieceFits2(int[][] matrix) {

		int[][] matrixToFit = puzzleListSol.get(0);
		// if the down side of piece that there is in the puzzle
		// fits with the up side of the new piece returns true
		if (checkCorner(matrixToFit[4][0], matrix[0][0])
				&& checkCorner(matrixToFit[4][4], matrix[0][4])
				&& checkHorizontalSide(matrix, matrixToFit))
			return true;

		return false;
	}

	
	// Check the items at the corner. Must be <=1
	public boolean checkCorner(int a, int b) {
		if (a + b <= 1)
			return true;
		return false;
	}
	
	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public boolean checkHorizontalSide(int[][] matrixA, int[][] matrixB) {
		for (int j = 1; j <= 3; j++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[0][j] + matrixB[4][j] !=1) {
				return false;

			}

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkHorizontalSide2(int[][] matrixA, int[][] matrixB) {
		for (int j = 1; j <= 3; j++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[4][j] + matrixB[0][j] !=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkLeftVerticalSide(int[][] matrixA, int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[i][0] + matrixB[i][4] !=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkRightVerticalSide(int[][] matrixA, int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[i][4] + matrixB[i][0] !=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkHorizontalVerticalUpSide(int[][] matrixA,
			int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[0][i] + matrixB[i][0] !=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkHorizontalVerticalDownSide(int[][] matrixA,
			int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[4][i] + matrixB[i][0]!=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkHorizontalDownVerticalSide(int[][] matrixA,
			int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[4][i] + matrixB[i][4]!=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkLeftVerticalLeftVerticalSide(int[][] matrixA,
			int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[i][0] + matrixB[i][0] !=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public boolean checkRightVerticalRightVerticalSide(int[][] matrixA,
			int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[i][4] + matrixB[i][4] !=1)
				return false;

		}
		return true;
	}

	// The items sum of the matrix from the position one until the position
	// three
	// must be equals to one
	public  boolean checkVerticalHorizontalUpSide(int[][] matrixA,
			int[][] matrixB) {
		for (int i = 1; i <= 3; i++) {
			// If the sum is equals to zero or is equal to 2 then return false
			if (matrixA[0][i] + matrixB[i][4]!=1)
				return false;

		}
		return true;
	}

	// This method check if a piece fits in a certain position
	public boolean pieceFits(int[][] matrix, int position) {
		Boolean fits = false;
		switch (position) {
		case 1:
			fits = true;
			break;
		case 2:
			fits = checkIfPieceFits2(matrix);
			break;
		case 3:
			fits = checkIfPieceFits3(matrix);
			break;
		case 4:
			fits = checkIfPieceFits4(matrix);
			break;
		case 5:
			fits = checkIfPieceFits5(matrix);
			break;
		case 6:
			fits = checkIfPieceFits6(matrix);
			break;
		}
		return fits;
	}

	
	//The method creates four lists one list for each color
	//and call the // create(list,position) to find the solution
	//when the the method return print the solution
	public void createColorList() {
		ArrayList<PieceRecordSerializable> colorArrayListB = new ArrayList<PieceRecordSerializable>();
		ArrayList<PieceRecordSerializable> colorArrayListR = new ArrayList<PieceRecordSerializable>();
		ArrayList<PieceRecordSerializable> colorArrayListP = new ArrayList<PieceRecordSerializable>();
		ArrayList<PieceRecordSerializable> colorArrayListG = new ArrayList<PieceRecordSerializable>();
		readPiecesRecords.openFile();
		readPiecesRecords.readRecords();
		piecesArrayList = readPiecesRecords.getPieceRecords();
		readPiecesRecords.closeFile();
		for (PieceRecordSerializable prs : piecesArrayList) {
			if (prs.getColor().equals("BLUE")) {
				colorArrayListB.add(prs);
			} else if (prs.getColor().equals("RED")) {
				colorArrayListR.add(prs);
			} else if (prs.getColor().equals("PURPLE")) {
				colorArrayListP.add(prs);
			} else {
				colorArrayListG.add(prs);
			}
		}

		try {
			System.out.println("\n Puzzle solution for the BLUE color \n");
			create(colorArrayListB, 1);
			puzzle.insertToMatrix(puzzleListSol);
			//Creates the solution file
			puzzle.createFile("blue_color_Solution");
			puzzleListSol.clear();
			System.out.println("\n Puzzle solution for the RED color \n");
			create(colorArrayListR, 1);
			puzzle.insertToMatrix(puzzleListSol);
			//Creates the solution file
			puzzle.createFile("red_color_Solution");
			puzzleListSol.clear();
			System.out.println("\n Puzzle solution for the PURPLE color \n");
			create(colorArrayListP, 1);
			puzzle.insertToMatrix(puzzleListSol);
			//Creates the solution file
			puzzle.createFile("purple_color_Solution");
			puzzleListSol.clear();
			System.out.println("\n Puzzle solution for the GREEN color \n");
			create(colorArrayListG, 1);
			puzzle.insertToMatrix(puzzleListSol);
			//Creates the solution file
			puzzle.createFile("green_color_Solution");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	//Recursive search of solutions.
	//First the method creates a list with the rotations and flips for each piece.
	//Next checks if the piece fits in the puzzle
	public void create(List<PieceRecordSerializable> list,int position)
			throws Exception {
		if (list.isEmpty())
			return;
		
		//For each piece 
		for (PieceRecordSerializable prs : list) {
			
			//Add the rotates and flips pieces into the matrixList
			ArrayList<int[][]> matrixList = new ArrayList<int[][]>();
			
			matrixList.add(prs.getPiece1());
			matrixList.add(prs.getPiece2());
			matrixList.add(prs.getPiece3());
			matrixList.add(prs.getPiece4());
			matrixList.add(prs.getPiece5());
			matrixList.add(prs.getPiece6());
			matrixList.add(prs.getPiece7());
			matrixList.add(prs.getPiece8());
			
			for (int[][] mtr : matrixList) {
				//For each matrix into the list check if the matrix fits
				if (pieceFits(mtr, position) && puzzleListSol.size() < 6) {
					List<PieceRecordSerializable> subList = new ArrayList<PieceRecordSerializable>(
							list);
					//Add the matrix to the solution list
					puzzleListSol.add(mtr);
					//Remove the piece from the list
					subList.remove(prs);
					//Increase the position
					position++;
					//Recursive call
					create(subList, position);
					
					}

			}
			
		}
		
	}

	
	//Set and Get Methods
	public PieceRecordSerializable getPieceRecordSerializable() {
		return pieceRecordSerializable;
	}

	public void setPieceRecordSerializable(
			PieceRecordSerializable pieceRecordSerializable) {
		this.pieceRecordSerializable = pieceRecordSerializable;
	}

	public PieceAddRecord getPieceAddRecord() {
		return pieceAddRecord;
	}

	public void setPieceAddRecord(PieceAddRecord pieceAddRecord) {
		this.pieceAddRecord = pieceAddRecord;
	}

}
