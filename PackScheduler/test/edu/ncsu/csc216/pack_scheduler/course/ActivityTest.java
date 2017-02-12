package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test the function of Activity
 * @author Kevin Li
 *
 */
public class ActivityTest {
	
	/**
	 * Test Activity.checkConflict() method
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		try {
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}

		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		a1.setMeetingDays("MTH");
		a1.setActivityTime(1445, 1530);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("MTH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		a1.setActivityTime(0, 0);
		a1.setMeetingDays("A");		
		try {
			a1.checkConflict(a2);
			assertEquals("Arranged", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());

		} catch (ConflictException e) {
			fail();

		}

		a1.setMeetingDays("TH");
		a1.setActivityTime(1329, 1430);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 1:29PM-2:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		a2.setActivityTime(0, 0);
		a2.setMeetingDays("A");
		try {
			a1.checkConflict(a2);
			assertEquals("TH 1:29PM-2:30PM", a1.getMeetingString());
			assertEquals("Arranged", a2.getMeetingString());

		} catch (ConflictException e) {
			fail();

		}

		a1.setMeetingDays("TH");
		a1.setActivityTime(1329, 1446);
		a2.setMeetingDays("TH");
		a2.setActivityTime(1330, 1445);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 1:29PM-2:46PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		a1.setMeetingDays("TH");
		a1.setActivityTime(1331, 1446);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 1:31PM-2:46PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		a1.setMeetingDays("TH");
		a1.setActivityTime(1329, 1444);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 1:29PM-2:44PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		a1.setMeetingDays("TH");
		a1.setActivityTime(830, 1130);
		try {
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "TH 8:30AM-11:30AM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail();
		}

		a1.setActivityTime(1330, 1445);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		a1.setActivityTime(1329, 1445);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 1:29PM-2:45PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

		a1.setActivityTime(830, 1330);
		try {
			a1.checkConflict(a2);
			fail(); // ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			// Check that the internal state didn't change during method call.
			assertEquals("TH 8:30AM-1:30PM", a1.getMeetingString());
			assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}

	}

	/**
	 * Test Activity.setActivityTime() method
	 */
	@Test
	public void testSetActivityTime() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		try {
			a1.setActivityTime(1360, 1430);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1330, a1.getStartTime());
		}

		try {
			a1.setActivityTime(1340, 1460);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1445, a1.getEndTime());
		}

		try {
			a1.setActivityTime(-1, 1460);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1330, a1.getStartTime());
		}

		try {
			a1.setActivityTime(1330, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1445, a1.getEndTime());
		}

		try {
			a1.setActivityTime(1440, 1439);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1445, a1.getEndTime());
		}
		
		try {
			a1.setActivityTime(2401, 1439);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1445, a1.getEndTime());
		}
		
		try {
			a1.setActivityTime(1330, 2401);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1445, a1.getEndTime());
		}
		


		
	}

	/**
	 * Test Activity.setMeetingDays() method.
	 */
	@Test
	public void testSetMeetingDays() {
		Activity a1 = null;
		try {
			a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
			a1.setMeetingDays("");
		} catch (IllegalArgumentException e) {
			assertEquals(1330, a1.getStartTime());
		}
		
		a1 = null;
		try {
			a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
			a1.setMeetingDays(null);
		} catch (IllegalArgumentException e) {
			assertEquals(1330, a1.getStartTime());
		}
		
		a1 = null;
		try {
			a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
			a1.setMeetingDays("B");
		} catch (IllegalArgumentException e) {
			assertEquals(1330, a1.getStartTime());
		}
		
		a1 = null;
		try {
			a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
			a1.setMeetingDays("AW");
		} catch (IllegalArgumentException e) {
			assertEquals(1330, a1.getStartTime());
		}
		
		
	}
	
	/**
	 * Test Activity.setTitle() method
	 */
	@Test
	public void testSetTitle(){
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		try {
			a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
			a1.setTitle(null);
		} catch (IllegalArgumentException e) {
			assertEquals(1330, a1.getStartTime());
		}
		
		a1 = null;
		try {
			a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
			a1.setTitle("");
		} catch (IllegalArgumentException e) {
			assertEquals("Programming Concepts - Java", a1.getTitle());
		}
	}
	
	/**
	 * Test Activity.getMeetingString() method
	 */
	@Test
	public void testGetMeetingString(){
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1200, 1201);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1159, 1200);
		assertEquals("MW 12:00PM-12:01PM", a1.getMeetingString());
		assertEquals("MW 11:59AM-12:00AM", a2.getMeetingString());
	}
	
	/**
	 * Test Activity.equals() method.
	 */
	@Test
	public void testEquals(){
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1200, 1201);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1200, 1201);
		assertTrue(a1.equals(a2));
	}
	

}
