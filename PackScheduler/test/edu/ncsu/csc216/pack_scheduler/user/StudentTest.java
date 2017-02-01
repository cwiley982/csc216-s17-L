package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Student.java constructors and methods
 * 
 * @author Caitlyn Wiley
 * @author Kaiwen Li
 * @author Spencer Otten
 *
 */
public class StudentTest {

	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	public static final int MAX_CREDITS = 15;
	
	/**
	 * Tests constructing a Student with all 6 fields
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		try {
			Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			assertEquals("Stu", s.getFirstName());
			assertEquals("Dent", s.getLastName());
			assertEquals("sdent", s.getId());
			assertEquals("sdent@ncsu.edu", s.getEmail());
			assertEquals("pw", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		
	}

	/**
	 * Tests constructing a Student with all fields except max credits
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		try {
			Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
			assertEquals("Stu", s.getFirstName());
			assertEquals("Dent", s.getLastName());
			assertEquals("sdent", s.getId());
			assertEquals("sdent@ncsu.edu", s.getEmail());
			assertEquals("pw", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setting a Student's first name
	 */
	@Test
	public void testSetFirstName() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			s.setFirstName(LAST_NAME);
			assertEquals("Dent", s.getFirstName());

		} catch (IllegalArgumentException e) {
			fail();
		}
		s = null;
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests setting a Student's last name
	 */
	@Test
	public void testSetLastName() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			s.setFirstName(LAST_NAME);
			assertEquals("Dent", s.getFirstName());

		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests setting a Student's email
	 */
	@Test
	public void testSetEmail() {
		Student s = null;
		
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "sdentncsu.edu", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "sdent@ncsuedu", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "sden.t@ncsuedu", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests setting a Student's password
	 */
	@Test
	public void testSetPassword() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			s.setPassword(FIRST_NAME);
			assertEquals("Stu", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests setting max credits for a Student
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			s.setMaxCredits(12);
			assertEquals(12, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 19);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests getting a string representing the Student
	 */
	@Test
	public void testToString() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS); 
			assertEquals("Stu,Dent,sdent,sdent@ncsu.edu,pw,15", s.toString());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD); 
			assertEquals("Stu,Dent,sdent,sdent@ncsu.edu,pw,18", s.toString());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests equals() method to make sure it compares Students correctly
	 */
	@Test
	public void testEqualsObject() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS); 
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS); 
		Student s3 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12); 
		Student s4 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "123", MAX_CREDITS); 
		Student s5 = new Student(FIRST_NAME, LAST_NAME, ID, "kli11@ncsu.edu", PASSWORD, MAX_CREDITS); 
		Student s6 = new Student(FIRST_NAME, LAST_NAME, "kli11", EMAIL, PASSWORD, MAX_CREDITS); 
		Student s7 = new Student(FIRST_NAME, "Li", ID, EMAIL, PASSWORD, MAX_CREDITS); 
		Student s8 = new Student("Kevin", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS); 
		
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		
		
		
	}

	/**
	 * Tests hashCode() method
	 */
	@Test
	public void testHashCode() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS); 
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS); 
		Student s3 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12); 
		Student s4 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "123", MAX_CREDITS); 
		Student s5 = new Student(FIRST_NAME, LAST_NAME, ID, "kli11@ncsu.edu", PASSWORD, MAX_CREDITS); 
		Student s6 = new Student(FIRST_NAME, LAST_NAME, "kli11", EMAIL, PASSWORD, MAX_CREDITS); 
		Student s7 = new Student(FIRST_NAME, "Li", ID, EMAIL, PASSWORD, MAX_CREDITS); 
		Student s8 = new Student("Kevin", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS); 
		
		
		assertEquals(s1.hashCode(), s2.hashCode());
		
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
		 
	}
	
	@Test
	public void testCompareTo(){
		Student s1 = new Student("Kevin", "Li", "kli9", EMAIL, PASSWORD, MAX_CREDITS); 
		Student s2 = new Student("Kevin", "Li", "kli11", EMAIL, PASSWORD, MAX_CREDITS); 
		Student s3 = new Student("Abc", "Aa", "aaa", EMAIL, PASSWORD, MAX_CREDITS); 
		Student s4 = new Student("Xyz", "Aa", "xaa", EMAIL, PASSWORD, MAX_CREDITS); 

		assertEquals(-1, s1.compareTo(s2));
		assertEquals(1, s2.compareTo(s1));
		assertEquals(-1, s3.compareTo(s2));
		assertEquals(1, s1.compareTo(s4));
	}

}
