package com.dvinformatics.repository;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component("readPiecesRecords")
public class ReadPiecesRecords {

	private ObjectInputStream input;
	private ArrayList<PieceRecordSerializable> pieceRecords=new ArrayList<PieceRecordSerializable>();
	
	
	public ArrayList<PieceRecordSerializable> getPieceRecords() {
		return pieceRecords;
	}
	
	public void setPieceRecords(ArrayList<PieceRecordSerializable> pieceRecord) {
		this.pieceRecords = pieceRecord;
	}
	
	public void openFile() {
		try {
			input= new ObjectInputStream(
					new FileInputStream("pieces.txt"));
		} catch (IOException ioException) {
			System.err.print("Error opening file!");
			System.exit(1);
		}
	}
	//Read the records, remove the " from the word and insert both 
	//the word and the encoding word in the Hash Map
	public void readRecords() {
		
		PieceRecordSerializable record;
		
			try {
				while(true){
					record=(PieceRecordSerializable) input.readObject();
					pieceRecords.add(record);
				}
			}catch(EOFException eofException)
			{
				return;
			}
			catch(ClassNotFoundException classNoFoundException){
				System.err.print("Unable to craete Object!");
			}
			catch(IOException ioException) {
			System.err.print("Error during read from file!");
			System.exit(1);
		}
			
	
	}
	
	public void readRecordsToList(ArrayList list) {
		
		PieceRecordSerializable record;
		
		
		
			try {
				
				while(true){
			
					record=(PieceRecordSerializable) input.readObject();
					
					list.add(record);
				}
			}catch(EOFException eofException)
			{
				return;
			}
			catch(ClassNotFoundException classNoFoundException){
				System.err.print("Unable to craete Object!");
			}
			catch(IOException ioException) {
			System.err.print("Error during read from file!");
			System.exit(1);
		}
			
			
	
	}
	public void closeFile(){
		try{
			if (input !=null)
				input.close();
			
		}
		catch(IOException ioException)
		{
			System.err.println("Error closing file !!!");
			System.exit(1);
		}
		
	}

}
