package model;

import java.time.LocalDateTime;

import anotation.Column;
import anotation.Entity;
import anotation.Id;

@Entity(name = "Events")
public class Event {

	@Id(name = "ID")
	private Integer id;
	@Column(name = "EventName")
	private String eventName;
	@Column(name = "EventType")
	private Integer eventType;
	@Column(name = "EventImage")
	private String eventImage;
	@Column(name = "Creator")
	private Integer createTor;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Status")
	private int status;
	
	@Column(name = "OptionType")
	private Integer optionType;
	@Column(name = "IsDeleted")
	private boolean IsDeleted;
	@Column(name = "Timestamp")
	private LocalDateTime timeStamp;

	public Event() {

	}

	public Event(Integer id, String eventName, Integer eventType, String eventImage, Integer createTor,
			String description, int status, Integer optionType, boolean isDeleted, LocalDateTime timeStamp) {
		id = id;
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventImage = eventImage;
		this.createTor = createTor;
		this.description = description;
		this.status = status;
		this.optionType = optionType;
		IsDeleted = isDeleted;
		this.timeStamp = timeStamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getEventImage() {
		return eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}

	public Integer getCreateTor() {
		return createTor;
	}

	public void setCreateTor(Integer createTor) {
		this.createTor = createTor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	 

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getOptionType() {
		return optionType;
	}

	public void setOptionType(Integer optionType) {
		this.optionType = optionType;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + ", eventType=" + eventType + ", eventImage="
				+ eventImage + ", createTor=" + createTor + ", description=" + description + ", status=" + status
				+ ", optionType=" + optionType + ", IsDeleted=" + IsDeleted + ", timeStamp=" + timeStamp + "]";
	}

 
 

}
