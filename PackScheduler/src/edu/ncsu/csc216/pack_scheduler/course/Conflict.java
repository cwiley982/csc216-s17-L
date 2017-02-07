/**
 *  import package
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Define the conflict activity within the schedule
 * @author Kevin Li
 *
 */ 
public interface Conflict {
	/**
	 * To check if two activities are conflict.
	 * @param possibleConflictingActivity the target activity to be checked	
	 * @throws ConflictException 
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
	
	 
	 

}
