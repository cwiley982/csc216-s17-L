/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Handle the schedule 
 * @author Kaiwen Li
 *
 */
public class WolfScheduler {
	private String title;
	private ArrayList<Activity> schedule;
	private ArrayList<Course> catalog;
	
	
	/**
	 * Constructor 
	 * @param fileName input filename
	 */
	public WolfScheduler(String fileName) {
		try {
			setCatalog(CourseRecordIO.readCourseRecords(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file");
		}
		resetSchedule();
		setTitle("My Schedule");
	}

	/**
	 * Get schedule.
	 * @return the schedule
	 */
	public ArrayList<Activity> getSchedule() {
		return schedule;
	}

	/** 
	 * Set new Schedule.
	 * @param schedule the schedule to set
	 */
	public void setSchedule(ArrayList<Activity> schedule) {
		this.schedule = schedule;
	}

	/** 
	 * Get the catalog
	 * @return the catalog
	 */
	public ArrayList<Course> getCatalog() {
		return catalog;
	}

	/**
	 * Set new catalog
	 * @param arrayList the catalog to set
	 */
	public void setCatalog(ArrayList<Course> arrayList) {
		this.catalog = arrayList;
	}
	
	/**
	 * Put catalog into a 2D array
	 * @return catalog in 2D array 	
	 */
	public String[][] getCourseCatalog() {
		String[][] c = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			c[i][0] = catalog.get(i).getName();
			c[i][1] = catalog.get(i).getSection();
			c[i][2] = catalog.get(i).getTitle();
			c[i][3] = catalog.get(i).getMeetingString();
		}
		return c;
	}
	
	/**
	 * Put schedule into a 2D array
	 * @return schedule in 2D array
	 */
	public String[][] getScheduledActivities() {
		String[][] c = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			c[i] = schedule.get(i).getShortDisplayArray();
		}
		return c;
	}
	
	/**
	 * Put full schedule in 2D array
	 * @return full schedule in 2D array
	 */
	public String[][] getFullScheduledActivities() {
		String[][] c = new String[schedule.size()][7];
		for (int i = 0; i < schedule.size(); i++) {
			c[i] = schedule.get(i).getLongDisplayArray();
		}
		return c;
	}
	
	/**
	 * Get tile of the schedule
	 * @return title of the schedule
	 */
	public String getTitle() {
		
		return title;
	}
	
	/**
	 * Export the schedule
	 * @param fileName output file name
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
	
	/**
	 * Get Course from catalog
	 * @param name name of the course
	 * @param section section of the course
	 * @return a Course object
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
	 * Add a course
	 * @param name name of the course
	 * @param section section of the course
	 * @return true if the action is valid
	 */
	public boolean addCourse(String name, String section) {
		Course c = null;
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i) instanceof Course) {
				c = (Course) schedule.get(i);
			
			if (c.getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name );
			}
		}
		}
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				for (int j = 0; j < schedule.size(); j++) {
					if (schedule.get(j).isDuplicate(catalog.get(i))) {
						return false;
					}
					
					if (!catalog.get(i).getMeetingDays().equals("A")) {
						try {
							schedule.get(j).checkConflict(catalog.get(i));
						} catch (ConflictException e) {
							throw new IllegalArgumentException("The course cannot be added due to a conflict.");
						}
					}
				}
				schedule.add(schedule.size(), catalog.get(i));
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * Remove a course
	 * @param idx index
	 * @return true if the action is valid
	 */
	public boolean removeActivity(int idx) {
		if (idx >= 0 && idx < schedule.size()) {
			schedule.remove(idx);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * reset the schedule
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Activity>(0);
	}
	
	/**
	 * Set new title 
	 * @param title new title to set
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
		
	}
	/**
	 * Add an Event	
	 * @param title event title	
	 * @param meetingDays event meeting days
	 * @param startTime event start time
	 * @param endTime event end time
	 * @param weeklyRepeat weekly repeat of the event
	 * @param eventDetails details
	 */
	public void addEvent(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails){
		Event e1 = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
		for(int i = 0; i < schedule.size(); i++){
			if(title.equals(schedule.get(i).getTitle())){
				throw new IllegalArgumentException("You have already created an event called " + title);
			}
			try {
				e1.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		
		schedule.add(e1);
	}
	
}
