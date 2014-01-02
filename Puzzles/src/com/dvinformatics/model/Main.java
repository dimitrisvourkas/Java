package com.dvinformatics.model;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dvinformatics.business.PuzzleSolution;

public class Main {

	public static void main(String[] args) throws Exception {

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		
		PuzzleSolution puzzleSolution = appContext.getBean("puzzleSolution",
				PuzzleSolution.class);
		
	  puzzleSolution.createColorList();
	}

}
