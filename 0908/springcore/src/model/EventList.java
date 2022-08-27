package model;

import java.time.LocalDate;
import java.time.LocalTime;

import anotation.Column;
import anotation.Entity;
import anotation.Id;

@Entity(name = "Eventlist")
public class EventList {
	@Id(name = "ID")
	private Integer id;

	@Column(name = "EventName")
	private String eventName;

	@Column(name = "Description")
	private String description;

	@Column(name = "Time")
	private String time;

	@Column(name = "Place")
	private String place;

	@Column(name = "DepartName")
	private String department;

	@Column(name = "Creator")
	private String createtor;

	@Column(name = "Status")
	private Boolean status;

	@Column(name = "Joined")
	private int joined;

	@Column(name = "NotJoined")
	private int notJoined;

	@Column(name = "StartDate")
	private LocalDate startDate;

	@Column(name = "StartHour")
	private LocalTime startHour;

	@Column(name = "EndDate")
	private LocalDate endDate;

	@Column(name = "EndHour")
	private LocalTime endHour;

	private boolean statusDelete;
	private int eventType;
	
	

	public EventList(Integer id, String eventName, String description, String time, String place, String department,
			String createtor, Boolean status, int joined, int notJoined, LocalDate startDate, LocalTime startHour,
			LocalDate endDate, LocalTime endHour, boolean statusDelete, int eventType) {
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.time = time;
		this.place = place;
		this.department = department;
		this.createtor = createtor;
		this.status = status;
		this.joined = joined;
		this.notJoined = notJoined;
		this.startDate = startDate;
		this.startHour = startHour;
		this.endDate = endDate;
		this.endHour = endHour;
		this.statusDelete = statusDelete;
		this.eventType = eventType;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public EventList(Integer id, String eventName, String description, String time, String place, String department,
			String createtor, Boolean status, int joined, int notJoined, LocalDate startDate, LocalTime startHour,
			LocalDate endDate, LocalTime endHour, boolean statusDelete) {
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.time = time;
		this.place = place;
		this.department = department;
		this.createtor = createtor;
		this.status = status;
		this.joined = joined;
		this.notJoined = notJoined;
		this.startDate = startDate;
		this.startHour = startHour;
		this.endDate = endDate;
		this.endHour = endHour;
		this.statusDelete = statusDelete;
	}

	public boolean isStatusDelete() {
		return statusDelete;
	}

	public void setStatusDelete(boolean statusDelete) {
		this.statusDelete = statusDelete;
	}

	public EventList() {

	}

	public EventList(Integer id, String eventName, String description, String time, String place, String department,
			String createtor, Boolean status, int joined, int notJoined, LocalDate startDate, LocalTime startHour,
			LocalDate endDate, LocalTime endHour) {
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.time = time;
		this.place = place;
		this.department = department;
		this.createtor = createtor;
		this.status = status;
		this.joined = joined;
		this.notJoined = notJoined;
		this.startDate = startDate;
		this.startHour = startHour;
		this.endDate = endDate;
		this.endHour = endHour;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCreatetor() {
		return createtor;
	}

	public void setCreatetor(String createtor) {
		this.createtor = createtor;
	}

	public int getJoined() {
		return joined;
	}

	public void setJoined(int joined) {
		this.joined = joined;
	}

	public int getNotJoined() {
		return notJoined;
	}

	public void setNotJoined(int notJoined) {
		this.notJoined = notJoined;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartHour() {
		return startHour;
	}

	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getEndHour() {
		return endHour;
	}

	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}

	@Override
	public String toString() {
		return "EventList [id=" + id + ", eventName=" + eventName + ", description=" + description + ", time=" + time
				+ ", place=" + place + ", department=" + department + ", createtor=" + createtor + ", status=" + status
				+ ", joined=" + joined + ", notJoined=" + notJoined + ", startDate=" + startDate + ", startHour="
				+ startHour + ", endDate=" + endDate + ", endHour=" + endHour + "]";
	}

}
