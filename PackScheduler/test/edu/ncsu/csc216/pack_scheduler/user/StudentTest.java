package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {

	/** Test first name */
	private static final String firstName = "Stu";
	/** Test last name */
	private static final String lastName = "Dent";
	/** Test id */
	private static final String id = "sdent";
	/** Test email */
	private static final String email = "sdent@ncsu.edu";
	/** Test password */
	private static final String password = "pw";
	/** Test max credits */
	public static final int MAX_CREDITS = 15;
	@Test
	public void testStudentStringStringStringStringStringInt() {
		try {
			Student s = new Student(firstName, lastName, id, email, password, MAX_CREDITS);
			assertEquals("Stu", s.getFirstName());
			assertEquals("Dent", s.getLastName());
			assertEquals("sdent", s.getId());
			assertEquals("sdent@ncsu.edu", s.getEmail());
			assertEquals("pw", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		
	}

	@Test
	public void testStudentStringStringStringStringString() {
		try {
			Student s = new Student(firstName, lastName, id, email, password);
			assertEquals("Stu", s.getFirstName());
			assertEquals("Dent", s.getLastName());
			assertEquals("sdent", s.getId());
			assertEquals("sdent@ncsu.edu", s.getEmail());
			assertEquals("pw", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testSetFirstName() {
		Student s = null;
		try {
			s = new Student(firstName, lastName, id, email, password, MAX_CREDITS);
			s.setFirstName(lastName);
			assertEquals("Dent", s.getFirstName());

		} catch (IllegalArgumentException e) {
			fail();
		}
		s = null;
		try {
			s = new Student(null, lastName, id, email, password, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student("", lastName, id, email, password, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	@Test
	public void testSetLastName() {
		Student s = null;
		try {
			s = new Student(firstName, lastName, id, email, password, MAX_CREDITS);
			s.setFirstName(lastName);
			assertEquals("Dent", s.getFirstName());

		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(firstName, null, id, email, password, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(firstName, "", id, email, password, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	@Test
	public void testSetEmail() {
		Student s = null;
		
		try {
			s = new Student(firstName, lastName, id, email, password, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, null, password, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, "", password, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, "sdentncsu.edu", password, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, "sdent@ncsuedu", password, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, "sden.t@ncsuedu", password, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	@Test
	public void testSetPassword() {
		Student s = null;
		try {
			s = new Student(firstName, lastName, id, email, password, MAX_CREDITS);
			s.setPassword(firstName);
			assertEquals("Stu", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, email, null, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, email, "", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	@Test
	public void testSetMaxCredits() {
		Student s = null;
		try {
			s = new Student(firstName, lastName, id, email, password, MAX_CREDITS);
			s.setMaxCredits(12);
			assertEquals(12, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, email, password, 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, email, password, 19);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	@Test
	public void testToString() {
		Student s = null;
		try {
			s = new Student(firstName, lastName, id, email, password, MAX_CREDITS); 
			assertEquals("Stu,Dent,sdent,sdent@ncsu.edu,pw,15", s.toString());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		s = null;
		try {
			s = new Student(firstName, lastName, id, email, password); 
			assertEquals("Stu,Dent,sdent,sdent@ncsu.edu,pw,18", s.toString());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testEqualsObject() {
		Student s1 = new Student(firstName, lastName, id, email, password, MAX_CREDITS); 
		Student s2 = new Student(firstName, lastName, id, email, password, MAX_CREDITS); 
		Student s3 = new Student(firstName, lastName, id, email, password, 12); 
		Student s4 = new Student(firstName, lastName, id, email, "123", MAX_CREDITS); 
		Student s5 = new Student(firstName, lastName, id, "kli11@ncsu.edu", password, MAX_CREDITS); 
		Student s6 = new Student(firstName, lastName, "kli11", email, password, MAX_CREDITS); 
		Student s7 = new Student(firstName, "Li", id, email, password, MAX_CREDITS); 
		Student s8 = new Student("Kevin", lastName, id, email, password, MAX_CREDITS); 
		
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		
		
		
	}

	@Test
	public void testHashCode() {
		Student s1 = new Student(firstName, lastName, id, email, password, MAX_CREDITS); 
		Student s2 = new Student(firstName, lastName, id, email, password, MAX_CREDITS); 
		Student s3 = new Student(firstName, lastName, id, email, password, 12); 
		Student s4 = new Student(firstName, lastName, id, email, "123", MAX_CREDITS); 
		Student s5 = new Student(firstName, lastName, id, "kli11@ncsu.edu", password, MAX_CREDITS); 
		Student s6 = new Student(firstName, lastName, "kli11", email, password, MAX_CREDITS); 
		Student s7 = new Student(firstName, "Li", id, email, password, MAX_CREDITS); 
		Student s8 = new Student("Kevin", lastName, id, email, password, MAX_CREDITS); 
		
		
		assertEquals(s1.hashCode(),s2.hashCode());
		
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
		
	}

}
