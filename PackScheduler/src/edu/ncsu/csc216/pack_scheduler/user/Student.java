package edu.ncsu.csc216.pack_scheduler.user;

public class Student {

	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String password;
	private int maxCredits;
	public static final int MAX_CREDITS = 18;
	
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCredits(MAX_CREDITS);
	}
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName,lastName,id,email,password,MAX_CREDITS);
	}
	

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException();
		}
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException();
		}
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	private void setId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (email == null || email.equals("") ) {
			throw new IllegalArgumentException();
		}
		if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
			throw new IllegalArgumentException();
		}
		String a = "";
		for (int i = email.length() - 1 ; i >= 0; i--) {
			a += email.charAt(i);
		}
		if (a.indexOf(".") > a.indexOf("@")) {
			throw new IllegalArgumentException();
		}
		
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException();
		}
		this.password = password;
	}

	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException();
		}
		this.maxCredits = maxCredits;
	}
	public static int getMaxCredits() {
		return MAX_CREDITS;
	}
	@Override
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
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email + "," + password +"," + maxCredits;
	}
	
	

	
	
	
	
	

}
