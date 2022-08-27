package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VoteDetail implements Serializable{
	private int userId;
	private int eventId;
	private String eventName;
	private LocalDate startDate;
	private String OptionImage;
	private Boolean voted;
	private Boolean isJoined;
	public VoteDetail() {
		// TODO Auto-generated constructor stub
	}
	public VoteDetail(int userId, int eventId, String eventName, LocalDate startDate, String optionImage,
			Boolean voted, Boolean isJoined) {
		super();
		this.userId = userId;
		this.eventId = eventId;
		this.eventName = eventName;
		this.startDate = startDate;
		OptionImage = optionImage;
		this.voted = voted;
		this.isJoined = isJoined;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public String getOptionImage() {
		return OptionImage;
	}
	public void setOptionImage(String optionImage) {
		OptionImage = optionImage;
	}
	public Boolean getVoted() {
		return voted;
	}
	public void setVoted(Boolean voted) {
		this.voted = voted;
	}
	public Boolean getIsJoined() {
		return isJoined;
	}
	public void setIsJoined(Boolean isJoined) {
		this.isJoined = isJoined;
	}

}
