/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.Event;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Handle the schedule 
 * @author Kaiwen Li
 * @author Caitlyn Wiley
 * @author Spencer Otten
 *
 */
public class CourseCatalog {
	
	/** a CourseCatalog has a SortedList of Courses that make up the catalog */
	private SortedList<Course> catalog;
	
	/**
	 * constructs an empty catalog
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * constructs an empty catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * loads course records into the catalog. Any FileNotFoundExceptions are caught and an  IllegalArgumentException is thrown to the client.
	 * @param fileName the file to be read
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			ArrayList<Course> list = null;
			list = CourseRecordIO.readCourseRecords(fileName);
			for (int i = 0; i < list.size(); i++) {
				catalog.add(list.get(i));
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Adds a Course with the following fields to the catalog and returns true if the Course is 
	 * added and false if the Course already exists in the catalog. If there is an error 
	 * constructing the Course, the IllegalArgumentException is allowed to propagate to the client.
	 * @param name of the course
	 * @param title of the course
	 * @param section number for the course
	 * @param credits number of credits the course offers
	 * @param instructorId unity id of the course's instructor
	 * @param meetingDays when the course will meet
	 * @param startTime when the course starts
	 * @param endTime when the course ends
	 * @return true if course can be added and isn't a duplicate, false otherwise
	 */
	public boolean addCoursesToCatalog(String name, String title, String section, int credits, String instructorId, String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return false;
			}
		}
		
		catalog.add(course);
		return true;
	}
	
	/**
	 * returns true if the Course is removed from the catalog and false if the Course is not in the 
	 * catalog.
	 * @param name of the course
	 * @param section number for the course
	 * @return true if course can be removed, false if it doesn't exist in the schedule
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns the Course from the catalog with the given name and section. Returns null if the 
	 * Course isn't in the catalog.
	 * @param name of the course
	 * @param section number for the course
	 * @return the course from the catalog with matching name and section number, null if course can't
	 * be found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}
	
	/**
	 * returns the name, section, title, and meeting information for Courses in the catalog.
	 * @return the 2d array with info on all of the courses in the catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size() - 1][4];
		for (int i = 0; i < catalog.size(); i++) {
			catalogArray[i] = catalog.get(i).getShortDisplayArray();
		}
		return catalogArray;
	}
	
	/**
	 * saves the catalog course records to the given file. Any IOExceptions are caught and an IllegalArgumentException is thrown to the client.
	 * @param fileName name of the file to write to
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			ArrayList<Course> catalog2 = new ArrayList<Course>();
			for (int i = 0; i < catalog.size(); i++ ){
				catalog2.add(catalog.get(i));
			}
			CourseRecordIO.writeCourseRecords(fileName, catalog2);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}
}
