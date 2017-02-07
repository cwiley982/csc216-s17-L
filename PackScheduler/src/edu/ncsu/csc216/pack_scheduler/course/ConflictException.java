package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Allow the program to throw a ConflictException.
 * @author Kevin Li
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
	
	/**
	 * Constructor with custom message
	 * @param message custom message
	 */
	public ConflictException(String message) {
		super(message);
		
	}
	
	
	
	

}
