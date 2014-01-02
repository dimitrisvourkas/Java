package com.dvinformatics.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.dvinformatics.repository.LoadDictionary;

/*
 * TThe class loads a word from the input file and looks for a matching word 
 * in the dictionary. If it will finds the appropriate word then puts the word in 
 * to the list. The dictionary loads the encoding words in a HashMap<String, String>
 *  with key the word and value the encoding word. The method find(...) takes
 * three parameters  find(List<String> list, String num, String remaining) where 
 * --list-- is the list where the method adds the matching words, --num-- is the part 
 * of the   word if the word consist for more than one words and --remaining-- is the 
 * remaining phone number
 */
@Component("encodingNumbers")
public class EncodedNumbers {

	private Scanner inputNumbers;
	String numString;
	HashMap<String, String> list = new HashMap<String, String>();
	@Autowired
	LoadDictionary loadDictionary;

	// Close File Function
	public void closeFile() {
		if (inputNumbers != null) {
			inputNumbers.close();
		}
	}

	// OPen File Function
	public void openFile() {
		try {
			loadDictionary.openFile();

			inputNumbers = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.print("File not found!");
			System.exit(1);
		}
	}

	/*
	 * Read all then records from the dictionary and for ever phone number call
	 * the addToList function
	 */
	public void readRecords() {

		loadDictionary.readRecords();

		while (inputNumbers.hasNext()) {
			try {
				numString = inputNumbers.nextLine();
				// The encoded list has all the encoded phones number
				List<String> encodedList = addToList(numString);
				// Print the encoded phone numbers
				for (String s : encodedList) {
					// Check that encoded word is OK!
					if (numString.replaceAll("\\W", "").length() == s
							.replaceAll("\\W", "").trim().length())
						System.out.println(numString + ": " + s);
				}

			} catch (NoSuchElementException elemantException) {
				System.err.println("File improperly formed");
				System.exit(1);
			} catch (IllegalStateException stateException) {
				System.err.println("Error reading from file");
				System.exit(1);

			}
		}

	}

	/*
	 * This method searches for matching words. If a word matches exactly then
	 * add tothe list the word else if the word matches with the first part of
	 * the word and remainsone letter then add to the list the word with the
	 * last number of the phone number elseif the phone number matches with the
	 * word except the first letter then add in the listthe first number of the
	 * phone and the word else if the word matches except the firstand the last
	 * letter then add to the list the first phone number, the word and the last
	 * phone number. In a case that the word is a part of the phone number then
	 * calledrecursively the find method.
	 */
	public void find(List<String> list, String num, String remaining) {

		String withOutDash = remaining.replaceAll("\\W", "");
		Map<String, String> map = loadDictionary.getDictionary();
		Iterator<Map.Entry<String, String>> str = map.entrySet().iterator();

		while (str.hasNext()) {
			Map.Entry<String, String> entry = str.next();
			// Checks the phone number with the word
			if (withOutDash.equals(entry.getValue().toString())) {

				list.add(num + " " + entry.getKey().toString());

			} else if (entry.getValue().equals(
					withOutDash.substring(0, withOutDash.length() - 1))) {

				list.add(num + " " + entry.getKey() + " "
						+ withOutDash.substring(entry.getKey().length() - 1));

			} else if (withOutDash.substring(1, withOutDash.length()).equals(
					entry.getValue())) {

				list.add(num + " " + withOutDash.substring(0, 1) + " "
						+ entry.getKey());

			}

			else if (withOutDash.length() > 2
					&& withOutDash.substring(1, withOutDash.length() - 1)
							.equals(entry.getValue())) {

				list.add(num + " " + withOutDash.substring(0, 1) + " "
						+ entry.getKey() + " "
						+ withOutDash.substring(withOutDash.length() - 1));

			}
			// Recursively call
			else if (withOutDash.endsWith(entry.getValue().toString())) {

				find(list,
						withOutDash.substring(0,
								withOutDash.indexOf(entry.getValue())),
						entry.getKey());

			}
			// Recursively call
			else if (withOutDash.startsWith(entry.getValue().toString())) {

				find(list, entry.getKey(), withOutDash.substring(entry
						.getValue().length(), withOutDash.length()));

			}

		}

	}

	// The method calls the find method and at the end returns
	// the list with the encoded phones numbers
	public LinkedList<String> addToList(String phoneNumber) {
		LinkedList<String> returningList = new LinkedList<String>();
		find(returningList, "", phoneNumber);
		return returningList;
	}

}
