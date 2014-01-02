package com.dvinformatics.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

/**
 * @author Dimitrios Vourkas The class loads the words from the dictionary
 *         removes the " character from the words and insert all the dictionary
 *         in a Hash Map with key the word and value the encoding word eg.
 *         Festival 40346658 verha"ngnisvoll 60295191636888 Exportautos
 *         02882457483 zuzufu"gen 070747901 Gegner 909102
 */

@Component("loadDictionary")
public class LoadDictionary {
	// The set where the dictionary will been inserted
	private HashMap<String, String> dictionary = new HashMap<String, String>();

	private Scanner inputDictionary;

	public LoadDictionary() {
		super();
	}

	public void closeFile() {
		if (inputDictionary != null) {
			inputDictionary.close();
		}
	}

	// Function that check every letter in the word next encode the word
	// and return the encoding word
	public String encodeWord(String word) {

		StringBuilder encodingWord = new StringBuilder();
		StringBuffer tmp = new StringBuffer(word);
		String firstCharacter;

		while (tmp.length() > 0) {

			firstCharacter = tmp.substring(0, 1);
			tmp.deleteCharAt(0);

			switch (firstCharacter) {

			case "e":
			case "E":
			case "z":
			case "Z":
				encodingWord.append("0");
				break;
			case "j":
			case "J":
			case "n":
			case "N":
			case "q":
			case "Q":
				encodingWord.append("1");
				break;
			case "r":
			case "R":
			case "w":
			case "W":
			case "x":
			case "X":
				encodingWord.append("2");

				break;
			case "d":
			case "D":
			case "s":
			case "S":
			case "y":
			case "Y":
				encodingWord.append("3");

				break;
			case "f":
			case "F":
			case "t":
			case "T":
				encodingWord.append("4");
				break;
			case "a":
			case "A":
			case "m":
			case "M":
				encodingWord.append("5");

				break;
			case "c":
			case "C":
			case "i":
			case "I":
			case "v":
			case "V":

				encodingWord.append("6");
				break;
			case "b":
			case "B":
			case "k":
			case "K":
			case "u":
			case "U":
				encodingWord.append("7");

				break;
			case "l":
			case "L":
			case "o":
			case "O":
			case "p":
			case "P":

				encodingWord.append("8");
				break;
			case "g":
			case "G":
			case "h":
			case "H":

				encodingWord.append("9");

				break;

			}

		}
		return encodingWord.toString();
	}

	public HashMap<String, String> getDictionary() {
		return dictionary;
	}

	public Scanner getInputDictionary() {
		return inputDictionary;
	}

	public Scanner getInputNumbers() {
		return inputDictionary;
	}

	// Open the dictionary.txt file
	public void openFile() {
		try {
			inputDictionary = new Scanner(new File("dictionary.txt"));
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.print("File not found!");
			System.exit(1);
		}
	}

	// Read the records, remove the " from the word and insert both
	// the word and the encoding word in the Hash Map
	public void readRecords() {
		while (inputDictionary.hasNext()) {
			try {
				String insertToMap = inputDictionary.nextLine();

				dictionary.put(insertToMap,
						encodeWord(insertToMap.replace("\"", "")));
			} catch (NoSuchElementException elemantException) {
				System.err.println("File improperly formed");
				System.exit(1);
			} catch (IllegalStateException stateException) {
				System.err.println("Error reading from file");
				System.exit(1);

			}
		}

	}

	public void setDictionary(HashMap<String, String> dictionary) {
		this.dictionary = dictionary;
	}

	public void setInputDictionary(Scanner inputDictionary) {
		this.inputDictionary = inputDictionary;
	}

	public void setInputNumbers(Scanner inputNumbers) {
		this.inputDictionary = inputNumbers;
	}

}