package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Kaiwen Li
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new File(fileName));
	    SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException | NullPointerException e) {
	        	// skip
	        } 
	    }
	    if (courses.get(0) == null) {
			courses.remove(0);
		}
	    
	    fileReader.close();
	    return courses;
	}

    /**
     * Writes the given list of Courses to 
     * @param fileName input filename 
     * @param courses course catalog
     * @throws IOException if file can't be written to
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < courses.size(); i++) {
    	    fileWriter.println(courses.get(i).toString());
    	}

    	fileWriter.close();
        
    }
    
    private static Course readCourse(String nextLine) {
		Scanner lineScan = new Scanner(nextLine);
		lineScan.useDelimiter(",");
		Course course = null;
		try {
			String name = lineScan.next();
			String title = lineScan.next();
			String section = lineScan.next();
			int credits = lineScan.nextInt();
			String instructorId = lineScan.next();
			String meetingDays = lineScan.next();
			int startTime = 0;
			int endTime = 0;
			if (meetingDays.equals("A")) {
				try {
					startTime = lineScan.nextInt();
					endTime = lineScan.nextInt();
					course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
				} catch (NoSuchElementException e) {
					course = new Course(name, title, section, credits, instructorId, meetingDays);
				}
			} else {
				startTime = lineScan.nextInt();
				endTime = lineScan.nextInt();
				course = new Course(name, title, section, credits, instructorId, meetingDays, 
						startTime, endTime);
			}
			lineScan.close();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		return course;
    }  

}