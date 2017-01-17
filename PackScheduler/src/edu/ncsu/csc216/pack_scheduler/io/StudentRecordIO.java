package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class StudentRecordIO {
	
	public StudentRecordIO(){
		
	}

	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(fileName));
		ArrayList<Student> students = new ArrayList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < students.size(); i++) {
					Student c = students.get(i);
					if (student.getId().equals(c.getId())) {
						// it's a duplicate
						duplicate = true;
					} 
				}
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException | NullPointerException e) {

			}
		}


		fileReader.close();
		return students;

	}

	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {
		PrintStream writer = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			writer.println(studentDirectory.get(i).toString());
		}
		writer.close();

	}

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
			try {
				int maxCredits = info.nextInt();
				info.close();
				s = new Student(firstName, lastName, id, email, password, maxCredits);

			} catch (NoSuchElementException e) {
				info.close();
				s = new Student(firstName, lastName, id, email, password);

			}

		} catch (NoSuchElementException e) {

		}

		return s;
	}

}
