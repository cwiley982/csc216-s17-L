package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;


/**
 * Tests StudentRecordIO class
 * 
 * @author Caitlyn Wiley
 * @author Kaiwen Li
 * @author Spencer Otten
 *
 */
public class StudentRecordIOTest {
	/** Valid student records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid student records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	
	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";
	
	private final String s1 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private final String s2 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private final String s3 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private final String s4 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private final String s5 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private final String s6 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private final String s7 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private final String s8 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private final String s9 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private final String s10 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	
	private final String[] validStudents = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
	
	/**
	 * Sets up hashCode for passwords so hashed passwords can accurately be read and stored
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	       
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Tests readStuedntRecords() with a file of valid students
	 */
	@Test
	public void testReadValidStudentRecords() {
		try {
			ArrayList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());
			
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
//			assertEquals("King", students.get(0).getLastName());
//			assertEquals("Schwartz", students.get(1).getLastName());
//			assertEquals("Hansen", students.get(2).getLastName());
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	/**
	 * Tests readStuedntRecords() with a file of invalid students
	 */
	@Test
	public void testReadInvalidStudentRecords(){
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * Checks two files against each other to see if they contain the same information
	 * @param expFile what the actual file will be checked against
	 * @param actFile the file written to by writeStudentRecords()
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}
	
	/**
	 * Tests writeStudentRecords() to see if it correctly writes Students to a file
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    ArrayList<Student> students = new ArrayList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	    checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	    
	}

}
