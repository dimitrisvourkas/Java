package com.dvinformatics.repository;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component("pieceRecordSerializable")
public class PieceRecordSerializable implements Serializable{

	private String  pieceID;
	private String  color;

	//All matrix views (rotated and reversed)
	private int[][] piece1=new int[5][5];
	private int[][] piece2=new int[5][5];
	private int[][] piece3=new int[5][5];
	private int[][] piece4=new int[5][5];
	private int[][] piece5=new int[5][5];
	private int[][] piece6=new int[5][5];
	private int[][] piece7=new int[5][5];
	private int[][] piece8=new int[5][5];
	
	//Constructor
	public PieceRecordSerializable() {
	
	}

	//Constructor
	public PieceRecordSerializable(String pieceID, String color,
			int[][] piece1, int[][] piece2, int[][] piece3, int[][] piece4,
			int[][] piece5, int[][] piece6, int[][] piece7, int[][] piece8) {
		super();
		this.pieceID = pieceID;
		this.color = color;
		this.piece1 = piece1;
		this.piece2 = piece2;
		this.piece3 = piece3;
		this.piece4 = piece4;
		this.piece5 = piece5;
		this.piece6 = piece6;
		this.piece7 = piece7;
		this.piece8 = piece8;
	}

	//Start getter and setter
	public String getPieceID() {
		return pieceID;
	}

	public void setPieceID(String pieceID) {
		this.pieceID = pieceID;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int[][] getPiece1() {
		return piece1;
	}

	public void setPiece1(int[][] piece1) {
		this.piece1 = piece1;
	}

	public int[][] getPiece2() {
		return piece2;
	}

	public void setPiece2(int[][] piece2) {
		this.piece2 = piece2;
	}

	public int[][] getPiece3() {
		return piece3;
	}

	public void setPiece3(int[][] piece3) {
		this.piece3 = piece3;
	}

	public int[][] getPiece4() {
		return piece4;
	}

	public void setPiece4(int[][] piece4) {
		this.piece4 = piece4;
	}

	public int[][] getPiece5() {
		return piece5;
	}

	public void setPiece5(int[][] piece5) {
		this.piece5 = piece5;
	}

	public int[][] getPiece6() {
		return piece6;
	}

	public void setPiece6(int[][] piece6) {
		this.piece6 = piece6;
	}

	public int[][] getPiece7() {
		return piece7;
	}

	public void setPiece7(int[][] piece7) {
		this.piece7 = piece7;
	}

	public int[][] getPiece8() {
		return piece8;
	}

	public void setPiece8(int[][] piece8) {
		this.piece8 = piece8;
	}
//End getter and setter
	
	
}
