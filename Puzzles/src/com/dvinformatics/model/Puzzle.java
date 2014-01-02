package com.dvinformatics.model;
/*
 * The puzzle consist of an 20X215 array and this can 
 * be implemented by six arrays 9X9 an every array 
 * side have to match with a certain side of an other array .
 * The algorithm tries to  put the first three pieces with views which
 * doesn't have symmetric pieces and next the six pieces with the minimum 
 * matchings. For every piece that inserted copy the values of the
 * side to the symmetric position.
 * 
 * 
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("puzzle")
public class Puzzle {
	private String[][]  pieceMatrix=new String[20][15];
		
	public Puzzle() {
	}
	
	public String[][] getPieceMatrix() {
		return pieceMatrix;
	}

	public void setPieceMatrix(String[][] pieceMatrix) {
		this.pieceMatrix = pieceMatrix;
	}
	
//The method print the puzzle solution
//First initialise a 20X15 matrix and next
//insert in the appropriate position each matrix of the puzzleList
public void insertToMatrix(List<int[][]> puzzleList){

	 int k=5,l=0;
	 //
	 for(int i=0;i<=19;i++){
			for(int j=0;j<=14;j++){
				pieceMatrix[i][j]=" ";
			}
		}
	//Checks the position and put the matrix in the appropriate position
		for (int item=0;item<=puzzleList.size()-1;item++){
		   if(item<=2){
		   for(int i=0;i<=4;i++){
			   for(int j=0;j<=4;j++){
				   //System.out.print(puzzleLIstSolution.get(item)[i][j]);
				   if(puzzleList.get(item)[i][j]==1){
					   pieceMatrix[k+i][j+5]="O";
				   }
				  
			   }
			 

		   }
		  // p++;
		   k+=5;
		   }
		  
	      else if(item==3){
			   for(int i=0;i<=4;i++){
				   for(int j=0;j<=4;j++){
					   if(puzzleList.get(item)[i][j]==1){
						   pieceMatrix[i][j+5]="O";
					   }
					  
				   }
				   //System.out.println();
			   }
			   k+=5;
			  // p++;
			   
		   }
		   
		   else{
			  
			   for(int i=0;i<=4;i++){
				   for(int j=0;j<=4;j++){
					   if(puzzleList.get(item)[i][j]==1){
						   pieceMatrix[i+5][j+l]="O";
					   }
					
				   }
		   }
			   l+=10;
		   }
		  
		}  
		

}
	//The method print the solution
/*	public void printPuzzle(){
		for(int i=0;i<20;i++){
			for(int j=0;j<15;j++){
				System.out.print(pieceMatrix[i][j]);
			}
			System.out.println();
		}
	}*/
	
	//The method creates the txt file for each solution
	public void createFile(String color){
		File file = new File("Solution_Files/"+color+".txt");  
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		// Write each string in the array on a separate line  
		for(int i=0;i<20;i++){
			for(int j=0;j<15;j++){
				out.print(pieceMatrix[i][j]);
			}
			out.println();
		}
		  
		out.close();  
		System.out.println("The solution file has been created");
	}
	
}
