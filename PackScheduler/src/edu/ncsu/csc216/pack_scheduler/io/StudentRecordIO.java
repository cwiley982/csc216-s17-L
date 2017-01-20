package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


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
	 * Reads a record of students and lists students in an array list
	 * 
	 * @param fileName
	 *            the file to be read
	 * @return list of students
	 * @throws FileNotFoundException
	 *             if file cannot be accessed
	 */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Student> students = new ArrayList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < students.size(); i++) {
					Student s = students.get(i);
					if (student.getId().equals(s.getId())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException | NullPointerException e) {
				//skip the line
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
	 * @param studentDirectory
	 *            array list of students
	 * @throws IOException
	 *             if file can't be written to
	 */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {
		PrintStream writer = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			writer.println(studentDirectory.get(i).toString());
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
		Scanner info = new Scanner(studentInfo);
		info.useDelimiter(",");
		Student s = null;

		try {
			String firstName = info.next();
			String lastName = info.next();
			String id = info.next();
			String email = info.next();
			String password = info.next();
			
			if (info.hasNextInt()) {
				int maxCredits = info.nextInt();
				info.close();
				s = new Student(firstName, lastName, id, email, password, maxCredits);
			} else {
				info.close();
				s = new Student(firstName, lastName, id, email, password);
			}
		} catch (NoSuchElementException e) {
			//skip the line
		}
		return s;
	}
}