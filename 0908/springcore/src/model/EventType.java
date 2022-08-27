package model;

import java.time.LocalDateTime;

import anotation.Column;
import anotation.Entity;
import anotation.Id;

@Entity(name = "MstEventType")
public class EventType {

	@Id(name = "ID")
	private Integer id;
	@Column(name = "EventType")
	private String eventName;
	@Column(name = "IsDeleted")
	private boolean IsDeleted;
	@Column(name = "Timestamp")
	private LocalDateTime timeStamp;

	public EventType() {

	}

	public EventType(Integer id, String eventName, boolean isDeleted, LocalDateTime timeStamp) {
		this.id = id;
		this.eventName = eventName;
		IsDeleted = isDeleted;
		this.timeStamp = timeStamp;
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
		return "EventType [id=" + id + ", eventName=" + eventName + ", IsDeleted=" + IsDeleted + ", timeStamp="
				+ timeStamp + ", getId()=" + getId() + ", getEventName()=" + getEventName() + ", isIsDeleted()="
				+ isIsDeleted() + ", getTimeStamp()=" + getTimeStamp() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
