package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Create an activity
 * 
 * @author Kaiwen Li
 *
 */
public abstract class Activity implements Conflict {

	/** Upper time. */
	private static final int UPPER_TIME = 2400;
	/** Upper hour. */
	private static final int UPPER_HOUR = 60;
	/** Activity's title. */
	protected String title;
	/** Activity's meeting days */
	protected String meetingDays;
	/** Activity's starting time */
	protected int startTime;
	/** Activity's ending time */
	protected int endTime;

	/**
	 * Abstract, get the short display array
	 * 
	 * @return short display array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Abstract, get the long display array
	 * 
	 * @return long display array
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Abstract, check if two activities are the same
	 * 
	 * @param activity
	 *            activity to be checked
	 * @return true if they are the same, false otherwise
	 */

	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Return Activity's title.
	 * 
	 * @return the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set Activity's title.
	 * 
	 * @param title
	 *            the new Activity title to set.
	 */
	public void setTitle(String title) {

		if (title == null) {
			throw new IllegalArgumentException();
		}
		if (title.equals("")) {
			throw new IllegalArgumentException();
		}

		this.title = title;
	}

	/**
	 * Return Activity's meeting days
	 * 
	 * @return the meeting day
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Set Activity's meeting days.
	 * 
	 * @param meetingDays
	 *            the new meeting days to set.
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException();
		}
		if (!meetingDays.matches("[MTWHFA]+")) {
			throw new IllegalArgumentException();
		}

		if (meetingDays.contains("A") && meetingDays.length() > 1) {
			throw new IllegalArgumentException();
		}
		this.meetingDays = meetingDays;
	}

	/**
	 * Return meeting days info as a string.
	 * 
	 * @return meeting days information.
	 */
	public String getMeetingString() {
		String stampm = "";
		String edampm = "";
		int sthr = 0;
		String stmin = String.format("%02d", startTime % 100);
		int edhr = 0;
		String edmin = String.format("%02d", endTime % 100);
		if (meetingDays.equals("A")) {
			return "Arranged";
		}
		if (startTime > 1200) {
			stampm = "PM";
			sthr = startTime / 100 - 12;

		} else {
			stampm = "AM";
			sthr = startTime / 100;
			if (sthr == 12) {
				stampm = "PM";
			}
		}
		if (endTime > 1200) {
			edampm = "PM";
			edhr = endTime / 100 - 12;
			if (edhr == 0) {
				edhr = 12;
			}
		} else {
			edampm = "AM";
			edhr = endTime / 100;
		}

		return meetingDays + " " + sthr + ":" + stmin + stampm + "-" + edhr + ":" + edmin + edampm;
	}

	/**
	 * Return Activity's start time.
	 * 
	 * @return start time of the Activity.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Return Activity's end time.
	 * 
	 * @return end time of the Activity.
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Set the Activity's start time and end time.
	 * 
	 * @param startTime
	 *            the new start time to set.
	 * @param endTime
	 *            the new end time to set.
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < 0 || startTime >= UPPER_TIME || endTime < 0 || endTime >= UPPER_TIME) {

			throw new IllegalArgumentException();
		}
		if (startTime % 100 >= UPPER_HOUR || endTime % 100 >= UPPER_HOUR) {

			throw new IllegalArgumentException();
		}

		if (endTime < startTime) {
			throw new IllegalArgumentException();

		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		return true;
	}

	/**
	 * Check if two activities are conflict with each other
	 * 
	 * @param possibleConflictingActivity
	 *            the activity to checked
	 * @throws ConflictException
	 *             if courses conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if (getMeetingDays().equals("A") || possibleConflictingActivity.getMeetingDays().equals("A")) {
			return;
		}

		for (int i = 0; i < getMeetingDays().length(); i++) {
			for (int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				if (getMeetingDays().charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(j)) {
					if (getStartTime() < possibleConflictingActivity.getEndTime()
							&& getStartTime() > possibleConflictingActivity.getStartTime()) {
						throw new ConflictException();
					}
					if (getEndTime() < possibleConflictingActivity.getEndTime()
							&& getEndTime() > possibleConflictingActivity.getStartTime()) {
						throw new ConflictException();
					}

					if (getStartTime() < possibleConflictingActivity.getStartTime()
							&& getEndTime() > possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}

					if (getStartTime() == possibleConflictingActivity.getStartTime()
							|| getEndTime() == possibleConflictingActivity.getEndTime()
							|| getStartTime() == possibleConflictingActivity.getEndTime()
							|| getEndTime() == possibleConflictingActivity.getStartTime()) {
						throw new ConflictException();
					}

				}
			}
		}

	}

}