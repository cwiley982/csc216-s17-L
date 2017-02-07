package edu.ncsu.csc216.wolf_scheduler.course;
/**
 * Event class 
 * @author Kaiwen Li
 *
 */
public class Event extends Activity {
	/** Weekly repeat */
	private int weeklyRepeat;
	/** Detail of the event */
	private String eventDetails;
	
	/**
	 * Constructor
	 * @param title title of the event
	 * @param meetingDays meeting days of the event
	 * @param startTime start time of the event
	 * @param endTime end time of the event
	 * @param weeklyRepeat weekly repeat
	 * @param eventDetails detail of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}


	/**
	 * Get weeklly repeat
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}


	/**
	 * Set new weekly Repeat
	 * @param weeklyRepeat the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}


	/**
	 * get the Event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		
		return eventDetails;
	}


	/**
	 * Set the event details
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}
	
	/**
	 * Set the meeting day
	 * @param meetingDays meeting days to set.
	 */
	public void setMeetingDays(String meetingDays){
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException();
		}
		if (!meetingDays.matches("[MTWHFUS]+")) {
			throw new IllegalArgumentException();
		}
		
		this.meetingDays = meetingDays;
		
	}
	
	/**
	 * Return meeting String.
	 * @return meeting string
	 */
	public String getMeetingString(){
		return super.getMeetingString() + " (every " + weeklyRepeat + " weeks)";
	}

	/**
	 * Return the event info
	 * @return event info in String format
	 */
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + ","
				+ weeklyRepeat + "," + eventDetails;
	}
	
	/**
	 * Return short display array
	 * @return short display array
	 */
	public String[] getShortDisplayArray(){
		String[] da = new String[4];
		da[0] = "";
		da[1] = "";
		da[2] = getTitle();
		da[3] = getMeetingString();
		return da;
	}
	
	/**
	 * Return long display Array
	 * @return long display array
	 */
	public String[] getLongDisplayArray(){
		String[] da = new String[7];
		da[0] = "";
		da[1] = "";
		da[2] = getTitle();
		da[3] = "";
		da[4] = "";
		da[5] = getMeetingString();
		da[6] = getEventDetails();
		return da;
	}
	
	/**
	 * check if the activity is duplicate
	 * @param activity the activity to be checked
	 * @return true if two activities are the same, false other wise
	 */
	public boolean isDuplicate(Activity activity){
		if (this == activity)
			return true;
		if (activity == null)
			return false;
		if (getClass() != activity.getClass())
			return false;
		Event other = (Event) activity;
		if (weeklyRepeat != other.weeklyRepeat)
			return false;
		if (getEndTime() != other.getEndTime())
			return false;
		if (eventDetails == null) {
			if (other.eventDetails != null)
				return false;
		} else if (!eventDetails.equals(other.eventDetails))
			return false;
		if (getMeetingDays() == null) {
			if (other.getMeetingDays() != null)
				return false;
		} else if (!getMeetingDays().equals(other.getMeetingDays()))
			return false;
		if (getStartTime() != other.getStartTime())
			return false;
		if (getTitle() == null) {
			if (other.getTitle() != null)
				return false;
		} else if (!getTitle().equals(other.getTitle()))
			return false;
		return true;
	}


	
	
	
	
}
