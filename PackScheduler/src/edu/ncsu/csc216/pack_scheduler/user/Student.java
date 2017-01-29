package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Creates a student with all info necessary
 * 
 * @author Caitlyn Wiley
 * @author Kaiwen Li
 * @author Spencer Otten
 *
 */
public class Student {

	/** First name of the student */
	private String firstName;
	/** Last name of the student */
	private String lastName;
	/** Student's unity id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;
	/** Max credits student can take */
	private int maxCredits;
	/** Default max credits */
	public static final int MAX_CREDITS = 18;

	/**
	 * Creates a student with specified max credits
	 * 
	 * @param firstName
	 *            first name of student
	 * @param lastName
	 *            last name of student
	 * @param id
	 *            unity id of student
	 * @param email
	 *            email of student
	 * @param password
	 *            student's password
	 * @param maxCredits
	 *            max credits for student
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCredits(maxCredits);
	}

	/**
	 * Creates a student with the default max credits
	 * 
	 * @param firstName first name of student
	 * @param lastName last name of student
	 * @param id unity id of student
	 * @param email email of student 
	 * @param password student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * Gets the student's first name
	 * 
	 * @return first name of student
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student
	 * 
	 * @param firstName of the student
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("No first name set.");
		}
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the student
	 * 
	 * @return student's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the student's last name
	 * 
	 * @param lastName of the student
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("No last name set.");
		}
		this.lastName = lastName;
	}

	/**
	 * Gets the unity id of the student
	 * 
	 * @return id of the student
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the student's unity id
	 * 
	 * @param id
	 *            of the student
	 */
	private void setId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("No id set.");
		}
		this.id = id;
	}

	/**
	 * Gets the students email
	 * 
	 * @return email of student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the students email
	 * 
	 * @param email
	 *            of the student
	 */
	public void setEmail(String email) {
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("No email entered.");
		}
		if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
			throw new IllegalArgumentException("Incorrect email adress.");
		}
		String a = "";
		for (int i = email.length() - 1; i >= 0; i--) {
			a += email.charAt(i);
		}
		if (a.indexOf(".") > a.indexOf("@")) {
			throw new IllegalArgumentException();
		}

		this.email = email;
	}

	/**
	 * Gets the student's password
	 * 
	 * @return student's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password
	 * 
	 * @param password
	 *            for the student's account
	 */
	public void setPassword(String password) {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Invalid Password");
		}
		this.password = password;
	}

	/**
	 * Sets the max credits a student can take
	 * 
	 * @param maxCredits
	 *            the student can take
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Max credits must be a positive number between 3 and 18.");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Gets the max credits for the student
	 * 
	 * @return max credits a student can take
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	@Override
	/**
	 * Hashes the password
	 * 
	 * @return the hashed password
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		return result;
	}

	@Override
	/**
	 * Determines if one field is equal to another field
	 * 
	 * @return boolean - true if fields are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	@Override
	/**
	 * Creates a string containing fields for the student object
	 * 
	 * @return the string containing student information
	 */
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email + "," + password + "," + maxCredits;
	}

}
