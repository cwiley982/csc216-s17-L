package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Keeps a record of students
 * 
 * @author Caitlyn Wiley
 * @author Kaiwen Li
 * @author Spencer Otten
 */
public class StudentRecordIO {


	/**
	 * Reads a record of students and lists students in a sorted list
	 * 
	 * @param fileName
	 *            the file to be read
	 * @return list of students
	 * @throws FileNotFoundException
	 *             if file cannot be accessed
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Student s = null;
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {

			try {
				s = processStudent(fileReader.nextLine());
				
				students.add(s);
			} catch (IllegalArgumentException e) {
				// skip
			}
		

		}
		fileReader.close();
		return students;
	}

	/**
	 * Writes the list of students in the array to a file
	 * 
	 * @param fileName
	 *            file to write to
	 * @param students
	 *            array list of students
	 * @throws IOException
	 *             if file can't be written to
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> students) throws IOException {
		PrintStream writer = new PrintStream(new File(fileName));
		for (int i = 0; i < students.size(); i++) {
			writer.println(students.get(i).toString());
		}
		writer.close();

	}

	/**
	 * Reads a line from the file and creates a new student if all fields
	 * contain valid input
	 * 
	 * @param studentInfo
	 *            line from file
	 * @return Student created with info from string
	 */
	private static Student processStudent(String studentInfo) {
		String firstName;
		String lastName;
		String id;
		String email;
		String password;
		int maxCredits;
		Scanner info = new Scanner(studentInfo);
		Student s = null;
		
		info.useDelimiter(",");
		
		try {
			firstName = info.next();
			lastName = info.next();
			id = info.next();
			email = info.next();
			password = info.next();
			maxCredits = info.nextInt();
			s = new Student(firstName, lastName, id, email, password, maxCredits);

		} catch (NoSuchElementException e) {
			info.close();
			throw new IllegalArgumentException();
		}
		info.close();
		return s;
	}
}