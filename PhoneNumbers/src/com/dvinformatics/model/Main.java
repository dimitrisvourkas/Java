package com.dvinformatics.model;

//import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dvinformatics.service.EncodedNumbers;

public class Main {

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		EncodedNumbers encodingNumbers = appContext.getBean("encodingNumbers",
				EncodedNumbers.class);
		encodingNumbers.openFile();
		encodingNumbers.readRecords();
	}

}
