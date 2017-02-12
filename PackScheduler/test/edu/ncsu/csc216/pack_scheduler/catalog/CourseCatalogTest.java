/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class
 * 
 * @author Caitlyn Wiley
 *
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	/** Expected output from saving catalog */
	private final String expectedCatalog = "test-files/expected_saved_course_catalog.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
		
	/**
	 * tests CourseCatalog.loadCoursesFromFile()
	 */
	@Test
	public void testLoadCoursesFromFile() {
		try {
			CourseCatalog catalog = new CourseCatalog();
			catalog.loadCoursesFromFile(validTestFile);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			CourseCatalog catalog = new CourseCatalog();
			catalog.loadCoursesFromFile(invalidTestFile);
			assertEquals(0, catalog.getCourseCatalog().length);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			CourseCatalog catalog = new CourseCatalog();
			catalog.loadCoursesFromFile("does NOT exist");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file does NOT exist", e.getMessage());
		}
	}

	/**
	 * tests CourseCatalog.addCourseToCatalog()
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, catalog.getCourseCatalog().length);
	}
	
	/**
	 * tests CourseCatalog.removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		catalog.addCourseToCatalog(NAME, TITLE, "002", 4, "jtking", "MW", 1330, 1445);
		catalog.removeCourseFromCatalog(NAME, "002");
		assertEquals(1, catalog.getCourseCatalog().length);
	}
	
	/**
	 * tests CourseCatalog.newCourseCatalog()
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, catalog.getCourseCatalog().length);
		catalog.newCourseCatalog();
		assertEquals(0, catalog.getCourseCatalog().length);
	}
	
	/**
	 * tests CourseCatalog.getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		try {
			catalog.loadCoursesFromFile(validTestFile);
		} catch (IllegalArgumentException e) {
			fail();
		}
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(catalog.getCourseFromCatalog(NAME, SECTION), course);
	}
	
	/**
	 * tests CourseCatalog.getCourseCatalog()
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910, 1100);
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "spbalik", "MW", 1120, 1310);
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "tbdimitr", "TH", 1120, 1310);
		String[][] expectedArray = new String[3][4];
		expectedArray[0] = new String[] {"CSC116", "001", "Intro to Programming - Java", "MW 9:10AM-11:00AM"};
		expectedArray[1] = new String[] {"CSC116", "002", "Intro to Programming - Java", "MW 11:20AM-1:10PM"};
		expectedArray[2] = new String[] {"CSC116", "003", "Intro to Programming - Java", "TH 11:20AM-1:10PM"};
		assertEquals(expectedArray[0][1], catalog.getCourseCatalog()[0][1]);
		assertEquals(expectedArray[2][3], catalog.getCourseCatalog()[2][3]);
	}
	
	/**
	 * tests CourseCatalog.saveCourseCatalog()
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910, 1100);
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "spbalik", "MW", 1120, 1310);
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "tbdimitr", "TH", 1120, 1310);
		assertEquals(3, catalog.getCourseCatalog().length);
		catalog.saveCourseCatalog("test-files/actual_saved_course_catalog.txt");
		checkFiles("test-files/actual_saved_course_catalog.txt", expectedCatalog);
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
