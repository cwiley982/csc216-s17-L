package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the extended functionality of Course from 
 * Guided Project 2.
 * @author Sarah Heckman
 */
public class ExtendedCourseTest {
	
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
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Tests Course.getShortDisplayArray().
	 */
	@Test
	public void testGetShortDisplayArray() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		String [] actualShortDisplay = c.getShortDisplayArray();
		assertEquals(NAME, actualShortDisplay[0]);
		assertEquals(SECTION, actualShortDisplay[1]);
		assertEquals(TITLE, actualShortDisplay[2]);
		assertEquals("MW 1:30PM-2:45PM", actualShortDisplay[3]);
	}

	/**
	 * Tests Course.getLongDisplayArray().
	 */
	@Test
	public void testGetLongDisplayArray() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		String [] actualLongDisplay = c.getLongDisplayArray();
		assertEquals(NAME, actualLongDisplay[0]);
		assertEquals(SECTION, actualLongDisplay[1]);
		assertEquals(TITLE, actualLongDisplay[2]);
		assertEquals("" + CREDITS, actualLongDisplay[3]);
		assertEquals(INSTRUCTOR_ID, actualLongDisplay[4]);
		assertEquals("MW 1:30PM-2:45PM", actualLongDisplay[5]);
		assertEquals("", actualLongDisplay[6]);
	}
	
	/**
	 * Test that isDuplicate method return the correct value.
	 */
	@Test
	public void testIsDuplicate(){
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		
		assertFalse(c1.isDuplicate(null));
		assertTrue(c1.isDuplicate(c2));
		
		c2.setActivityTime(START_TIME, 1500);
		assertFalse(c1.isDuplicate(c2));
		
		c2.setActivityTime(START_TIME, END_TIME);
		c2.setInstructorId("xxx");
		assertFalse(c1.isDuplicate(c2));
		
		c2.setInstructorId(INSTRUCTOR_ID);
		c2.setTitle("AA");
		assertFalse(c1.isDuplicate(c2));
		
		c2.setTitle(TITLE);
		c2.setMeetingDays("MWF");
		assertFalse(c1.isDuplicate(c2));
		
		Course c3 = new Course("MSE101", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertFalse(c2.isDuplicate(c3));
		
		c2.setMeetingDays(MEETING_DAYS);
		c2.setTitle(TITLE);
		assertTrue(c1.isDuplicate(c2));
		
		c2.setSection("069");
		assertFalse(c1.isDuplicate(c2));
		
		c2.setActivityTime(0, END_TIME);
		assertFalse(c1.isDuplicate(c2));
		
	}

}
