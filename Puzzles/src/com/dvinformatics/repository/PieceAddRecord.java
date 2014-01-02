package com.dvinformatics.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pieceAddRecord")
public class PieceAddRecord {
	
	//Color enumeration
	private enum Color {BLUE, RED, PURPLE, GREEN};
	
	private ObjectOutputStream output;
	
	//Spring autowired
	@Autowired
	public PieceRecordSerializable record;

	// Open the file
	public void openFile() {

		try {
			output = new ObjectOutputStream(new FileOutputStream("pieces.txt"));
		} catch (IOException ioException) {
			System.err.println("Error opening file !!!");
		}
	}
	
	//Constructor
	public PieceAddRecord() {
		
	}
	
	// Add the record to the file
	public void addRecords() {
		String line=null;
		String piece_ID = null;
		System.out
				.printf("%s \n",
						"Enter the piece's ID, and the description in an array 5X5.");
	
		System.out
				.printf("%s\n %39s\n %40s\n %38s\n %38s\n %38s\n %38s\n %38s\n",
						"Piece description will be an 5X5 array like : ",
						        "| 12345",
						  "==============",
						 "1.| 00100",
						 "2.| 01110",
						 "3.| 11111",
						 "4.| 01110",
						 "5.| 00100");
		Scanner input = new Scanner(System.in);
		//For all colors add values
		for(Color color:Color.values()){
					
			//	System.out.println("Insrt values for "+color+" color.\n");
				System.out.println("Insert the piece ID:");
		
		
		//Piece ID
		for(int  k=0;k<=5;k++){
		piece_ID = input.nextLine();
		//Matrix for all views
		  int[][] piece1=new int[5][5];
		   int[][] piece2=new int[5][5];
			int[][] piece3=new int[5][5];
			int[][] piece4=new int[5][5];
			int[][] piece5=new int[5][5];
			int[][] piece6=new int[5][5];
			int[][] piece7=new int[5][5];
			int[][] piece8=new int[5][5];
			
			System.out.println("Pieces matrix :");
			try {// put to the file
				
				//Insert values for piece using 0 and 1
				//put 0 if there is not a little square and 1 if there is.
				for(int i=0;i<5;i++){
					System.out.println("Insert the "+(i+1)+"st line :\n");
					line = input.nextLine();
					for(int j=0;j<5;j++){
					      	// input.nextLine();
							piece1[i][j]=Integer.parseInt(Character.toString(line.charAt(j)));
						}
					}
				
			//Auto create rotated and reversed matrix
				//rotate piece1 and create piece 2
				rotateMatrix(piece1,piece2);
		        //	rotate piece2 and create piece 3
			     rotateMatrix(piece2,piece3);
			   //rotate piece3 and create piece 4
			     rotateMatrix(piece3,piece4);
			     
			    //Pieces 5,6,7,8 are reversed pieces 1,2,3,4
			   //flip piece1 and create piece 5
			     flipMatrix(piece1,piece5);
			   //flip piece2 and create piece 6
			     flipMatrix(piece2,piece6);
			   //flip piece3 and create piece 7
			     flipMatrix(piece3,piece7);
			   //flip piece4 and create piece 8
			     flipMatrix(piece4,piece8);
			//Add the piece to the file
				record = new PieceRecordSerializable(piece_ID, color.toString(), piece1, piece2, piece3, piece4, piece5, piece6, piece7, piece8);
				output.writeObject(record); 
				
				System.out
						.printf("%s \n",
								"Enter the piece's ID, and the description in an array 5X5.");
			} catch (IOException ioException) {
				System.err.println("Error writing file !!!");
			} catch (NoSuchElementException noSuchElementException) {
				System.err.println("Invalid input try again.");
				input.nextLine();
			}
		}
		}
	}

	// Close the file
	public void closeFile() {
		try {
			if (output != null)
				output.close();

		} catch (IOException ioException) {
			System.err.println("Error closing file !!!");
			System.exit(1);
		}

	}
//Method that rotate the matrix
public void rotateMatrix(int[][] matrix,int[][] rotatedMatrix) {
	int[] left=new int[5];
	int[] right=new int[5];
	int[] up=new int[5];
	int[] bottom=new int[5];
	for (int i=0;i<=4;i++){
		
		rotatedMatrix[0][i]=matrix[4-i][0];
		rotatedMatrix[i][4]=matrix[0][i];
		rotatedMatrix[4][i]=matrix[4-i][4];
		rotatedMatrix[i][0]=matrix[4][i];
	}
	for(int i=1;i<=3;i++){
		for(int j=1;j<=3;j++){
			rotatedMatrix[i][j]=1;
		}
	}
}

//Method that flip the matrix
public void flipMatrix(int[][] matrix,int[][] reversedMatrix){
	for(int i=0;i<=4;i++){
		for(int j=0;j<=1;j++){
			reversedMatrix[i][j]=matrix[i][4-j];
			reversedMatrix[i][2]=matrix[i][2];
			reversedMatrix[i][4-j]=matrix[i][j];
			
		}
	}
	
}

}
