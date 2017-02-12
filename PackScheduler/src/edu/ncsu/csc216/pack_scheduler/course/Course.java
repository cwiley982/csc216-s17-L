package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Allow user to create a Course object For further usage.
 * 
 * @author Kaiwen Li
 *
 */
public class Course extends Activity implements Comparable<Course> {
	/** Length of a section. */
	private static final int SECTION_LENGTH = 3;
	/** Maximum length for a name. */
	private static final int MAX_NAME_LENGTH = 6;
	/** Minimum length for a name. */
	private static final int MIN_NAME_LENGTH = 4;
	/** Maximum amount of credit hour for a course. */
	private static final int MAX_CREDITS = 5;
	/** Minimum amount of credit hour for a course. */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name
	 *            name of Course
	 * @param title
	 *            title of Course
	 * @param section
	 *            section of Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's unity id
	 * @param meetingDays
	 *            meeting days for Course as series of chars
	 * @param startTime
	 *            start time for Course
	 * @param endTime
	 *            end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		if (title == null || section == null || credits < MIN_CREDITS || credits > MAX_CREDITS || instructorId == null
				|| meetingDays == null || startTime > 2400 || endTime < startTime) {

			throw new IllegalArgumentException("Invalid parameter(s)");
		}
		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits,
	 * instructorId, and meetingDays for courses that are arranged.
	 * 
	 * @param name
	 *            name of Course
	 * @param title
	 *            title of Course
	 * @param section
	 *            section of Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's unity id
	 * @param meetingDays
	 *            meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {

		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Generate a hashCode for course using all fields.
	 * 
	 * @return hashCode for course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj
	 *            the object to compare
	 * @return true if the objects are the same on all fields
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (!instructorId.equals(other.instructorId))
			return false;
		if (!meetingDays.equals(other.meetingDays))
			return false;
		if (!section.equals(other.section))
			return false;
		if (startTime != other.startTime)
			return false;
		if (!title.equals(other.title))
			return false;
		return true; 
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (meetingDays.equals("A")) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}

	/**
	 * Return course's name.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if name is null or length is less than 4 or greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Return course's section.
	 * 
	 * @return the section.
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Set course's section.
	 * 
	 * @param section
	 *            the new course section to set.
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException();
		}
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException();
		}
		this.section = section;
	}

	/**
	 * Return the course's credit hours.
	 * 
	 * @return the credit hours.
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Set course's credit hour.
	 * 
	 * @param credits
	 *            the new course credit hour to set.
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Return Instructor's unity id.
	 * 
	 * @return the instructor's unity id.
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Set Instrucctor's unity id.
	 * 
	 * @param instructorId
	 *            the new unity id to set.
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	/**
	 * Return the short display array.
	 * 
	 * @return return short display array
	 */
	public String[] getShortDisplayArray() {
		String[] da = new String[4];
		da[0] = getName();
		da[1] = getSection();
		da[2] = getTitle();
		da[3] = getMeetingString();
		return da;
	}

	/**
	 * Return the long display array
	 * 
	 * @return return long display array
	 */
	public String[] getLongDisplayArray() {
		String[] da = new String[7];
		da[0] = getName();
		da[1] = getSection();
		da[2] = getTitle();
		da[3] = Integer.toString(getCredits());
		da[4] = getInstructorId();
		da[5] = getMeetingString();
		da[6] = "";
		return da;

	}

	/**
	 * Check if the activity is duplicate
	 * 
	 * @param activity
	 *            activity to be checked
	 * @return true if two are the same, false otherwise
	 */
	public boolean isDuplicate(Activity activity) {
		if (this == activity)
			return true;
		if (activity == null)
			return false;
		if (getClass() != activity.getClass())
			return false;
		Course other = (Course) activity;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (!instructorId.equals(other.instructorId))
			return false;
		if (!meetingDays.equals(other.meetingDays))
			return false;
		if (!name.equals(other.name))
			return false;
		if (!section.equals(other.section))
			return false;
		if (startTime != other.startTime)
			return false;
		if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Compares this course to another course
	 * @param o the object to be compared to
	 * @return -1 if this course is before the other, 0 if they are equal, 1 if
	 *         it is after
	 */
	public int compareTo(Course o) {
		String c1 = getName();
		String c2 = o.getName();
		String s1 = getSection();
		String s2 = o.getSection();

		if (c1.compareTo(c2) == 0) {
			return s1.compareTo(s2);
		} else {
			return c1.compareTo(c2);
		}

	}

}
