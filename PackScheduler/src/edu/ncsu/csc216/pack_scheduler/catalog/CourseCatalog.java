/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;


import java.io.FileNotFoundException;
import java.io.IOException;

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
		
	}
	
	/**
	 * constructs an empty catalog
	 */
	public void newCourseCatalog() {
		
	}
	
	/**
	 * loads course records into the catalog. Any FileNotFoundExceptions are caught and an  IllegalArgumentException is thrown to the client.
	 * @param fileName
	 */
	public void loadCoursesFromFile(String fileName) {
		
	}
	
	/**
	 * Adds a Course with the following fields to the catalog and returns true if the Course is 
	 * added and false if the Course already exists in the catalog. If there is an error 
	 * constructing the Course, the IllegalArgumentException is allowed to propagate to the client.
	 * @param name
	 * @param title
	 * @param section
	 * @param credits
	 * @param instructorId
	 * @param meetingDays
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean addCoursesToCatalog(String name, String title, String section, int credits, String instructorId, String meetingDays, int startTime, int endTime) {
		return false;
	}
	
	/**
	 * returns true if the Course is removed from the catalog and false if the Course is not in the 
	 * catalog.
	 * @param name
	 * @param section
	 * @return
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		return false;
	}
	
	/**
	 * returns the Course from the catalog with the given name and section. Returns null if the 
	 * Course isn't in the catalog.
	 * @param name
	 * @param section
	 * @return
	 */
	public Course getCourseFromCatalog(String name, String section) {
		return null;
	}
	
	/**
	 * returns the name, section, title, and meeting information for Courses in the catalog.
	 * @return
	 */
	public String[][] getCourseCatalog() {
		return null;
	}
	
	/**
	 * saves the catalog course records to the given file. Any IOExceptions are caught and an IllegalArgumentException is thrown to the client.
	 * @param fileName
	 */
	public void saveCourseCatalog(String fileName) {
		
	}
}
