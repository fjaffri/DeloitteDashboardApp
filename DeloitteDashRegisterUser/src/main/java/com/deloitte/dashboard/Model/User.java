package com.deloitte.dashboard.Model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	public String _id;
	private String firstName;
	private String lastName;
	private String geoLocation;
	private String memberFirm;
	private String business;
	private String region;
	private String consultingLevel;
	private String accountCapability;
	private String currentPmName;
	private String currentPmEmail;
	private String coach;
	private String coachEmail;
	private String leader;
	private String learningCoach;
	private String acknowledgemnet;
	private String fullyAvailable;
	private String reason;
	List<Course> course;	
	
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
	public String getMemberFirm() {
		return memberFirm;
	}
	public void setMemberFirm(String memberFirm) {
		this.memberFirm = memberFirm;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getConsultingLevel() {
		return consultingLevel;
	}
	public void setConsultingLevel(String consultingLevel) {
		this.consultingLevel = consultingLevel;
	}
	public String getAccountCapability() {
		return accountCapability;
	}
	public void setAccountCapability(String accountCapability) {
		this.accountCapability = accountCapability;
	}
	public String getCurrentPmName() {
		return currentPmName;
	}
	public void setCurrentPmName(String currentPmName) {
		this.currentPmName = currentPmName;
	}
	public String getCurrentPmEmail() {
		return currentPmEmail;
	}
	public void setCurrentPmEmail(String currentPmEmail) {
		this.currentPmEmail = currentPmEmail;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String getCoachEmail() {
		return coachEmail;
	}
	public void setCoachEmail(String coachEmail) {
		this.coachEmail = coachEmail;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getLearningCoach() {
		return learningCoach;
	}
	public void setLearningCoach(String learningCoach) {
		this.learningCoach = learningCoach;
	}
	public String getAcknowledgemnet() {
		return acknowledgemnet;
	}
	public void setAcknowledgemnet(String acknowledgemnet) {
		this.acknowledgemnet = acknowledgemnet;
	}
	public String getFullyAvailable() {
		return fullyAvailable;
	}
	public void setFullyAvailable(String fullyAvailable) {
		this.fullyAvailable = fullyAvailable;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "User [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", geoLocation="
				+ geoLocation + ", memberFirm=" + memberFirm + ", business=" + business + ", region=" + region
				+ ", consultingLevel=" + consultingLevel + ", accountCapability=" + accountCapability
				+ ", currentPmName=" + currentPmName + ", currentPmEmail=" + currentPmEmail + ", coach=" + coach
				+ ", coachEmail=" + coachEmail + ", leader=" + leader + ", learningCoach=" + learningCoach
				+ ", acknowledgemnet=" + acknowledgemnet + ", fullyAvailable=" + fullyAvailable + ", reason=" + reason
				+ ", course=" + course + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
