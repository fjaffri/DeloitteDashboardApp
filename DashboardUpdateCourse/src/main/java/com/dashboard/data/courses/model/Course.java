package com.dashboard.data.courses.model;

public class Course {

	private String percentMarked;
	private String courseCatagory;
	private String courseId;
	private String courseTitle;
	private String dateCompleted;
	private String dateEnrolled;
	private String dateFirstCompleted;
	private String dateLastAccesed;
	private String dateStarted;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String YearOfRangeDate;
	private String courseDurationCPEHours;
	private String courseDuration;
	private String minutesVideoConsumed;

	public String getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getPercentMarked() {
		return percentMarked;
	}

	public void setPercentMarked(String percentMarked) {
		this.percentMarked = percentMarked;
	}

	public String getCourseCatagory() {
		return courseCatagory;
	}

	public void setCourseCatagory(String courseCatagory) {
		this.courseCatagory = courseCatagory;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getDateEnrolled() {
		return dateEnrolled;
	}

	public void setDateEnrolled(String dateEnrolled) {
		this.dateEnrolled = dateEnrolled;
	}

	public String getDateFirstCompleted() {
		return dateFirstCompleted;
	}

	public void setDateFirstCompleted(String dateFirstCompleted) {
		this.dateFirstCompleted = dateFirstCompleted;
	}

	public String getDateLastAccesed() {
		return dateLastAccesed;
	}

	public void setDateLastAccesed(String dateLastAccesed) {
		this.dateLastAccesed = dateLastAccesed;
	}

	public String getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(String dateStarted) {
		this.dateStarted = dateStarted;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getYearOfRangeDate() {
		return YearOfRangeDate;
	}

	public void setYearOfRangeDate(String yearOfRangeDate) {
		YearOfRangeDate = yearOfRangeDate;
	}

	public String getCourseDurationCPEHours() {
		return courseDurationCPEHours;
	}

	public void setCourseDurationCPEHours(String courseDurationCPEHours) {
		this.courseDurationCPEHours = courseDurationCPEHours;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getMinutesVideoConsumed() {
		return minutesVideoConsumed;
	}

	public void setMinutesVideoConsumed(String minutesVideoConsumed) {
		this.minutesVideoConsumed = minutesVideoConsumed;
	}

	@Override
	public String toString() {
		return "Course [percentMarked=" + percentMarked + ", courseCatagory=" + courseCatagory + ", courseId="
				+ courseId + ", courseTitle=" + courseTitle + ", dateCompleted=" + dateCompleted + ", dateEnrolled="
				+ dateEnrolled + ", dateFirstCompleted=" + dateFirstCompleted + ", dateLastAccesed=" + dateLastAccesed
				+ ", dateStarted=" + dateStarted + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userEmail=" + userEmail + ", YearOfRangeDate=" + YearOfRangeDate + ", courseDurationCPEHours="
				+ courseDurationCPEHours + ", courseDuration=" + courseDuration + ", minutesVideoConsumed="
				+ minutesVideoConsumed + "]";
	}

	
}
