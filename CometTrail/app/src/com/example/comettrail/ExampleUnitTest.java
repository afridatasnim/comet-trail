package com.example.comettrail;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.java.com.example.comettrail.CourseManager;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void checkoff(){
		ArrayList<String> test1 = new ArrayList<String>();
		ArrayList<String> test2 = new ArrayList<String>();

		for(int i = 0; i < 15; i++){
			String a = "element" + i;
			test1.add(i, a);
		}

		test2.add("element2");
		test2.add("element13");
		test2.add("element5");

		//test1.add(0, "math1");


		CourseManager cm = new CourseManager(test1,  test2);

		ArrayList<String> subtractedTests = cm.compare();

		//print test1
		for(int i = 0; i < 15; i++){
			System.out.println(test1.get(i));
		}
		System.out.println(" ");
		//print test2
		for(int i = 0; i < 3; i++){
			System.out.println(test2.get(i));
		}
		System.out.println(" ");
		//print test1 - test2
		for(int i = 0; i < subtractedTests.size(); i++){
			System.out.println(subtractedTests.get(i));
		}
		System.out.println(" ");

		assertEquals(4,2+2);
	}
}