package model;

import anotation.Column;
import anotation.Entity;

@Entity(name = "SearchEventList")
public class SearchEventList {
	private Integer id;
	@Column(name = "EventName")
	private String eventName;
	@Column(name = "StartDate")
	private String startDate;
	@Column(name = "EndDate")
	private String endDate;
	@Column(name = "Place")
	private String place;
	@Column(name = "DepartID") 
	private String departID;
	@Column(name = "Creator")
	private String creator;
	@Column(name = "Status")
	private String status;
	private String eventType;
	private String startTime;
	private String endTime;
	 

	public SearchEventList() {

	}
 

	@Override
	public String toString() {
		return "SearchEventList [id=" + id + ", eventName=" + eventName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", place=" + place + ", departID=" + departID + ", creator=" + creator + ", status="
				+ status + ", eventType=" + eventType + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}


 

	public SearchEventList(Integer id, String eventName, String startDate, String endDate, String place,
			String departName, String creator, String status, String eventType, String startTime, String endTime) {

		this.id = id;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.departID = departName;
		this.creator = creator;
		this.status = status;
		this.eventType = eventType;
		this.startTime = startTime;
		this.endTime = endTime;
	}

 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDepartID() {
		return departID;
	}

	public void setDepartID(String departID) {
		this.departID = departID;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
