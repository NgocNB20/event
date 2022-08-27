package model;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class VoteOption {
	private Integer Id;
	private Integer eventId;
	private String startDate;
	private String place;
	private String optionImage;
	private MultipartFile imageMuFile;
	private boolean IsDeleted;
	private LocalDateTime timeStamp;

	public VoteOption() {

	}

	public VoteOption(Integer id, Integer eventId, String startDate, String place, String optionImage,
			MultipartFile imageMuFile, boolean isDeleted, LocalDateTime timeStamp) {
		Id = id;
		this.eventId = eventId;
		this.startDate = startDate;
		this.place = place;
		this.optionImage = optionImage;
		this.imageMuFile = imageMuFile;
		IsDeleted = isDeleted;
		this.timeStamp = timeStamp;
	}

	public MultipartFile getImageMuFile() {
		return imageMuFile;
	}

	public void setImageMuFile(MultipartFile imageMuFile) {
		this.imageMuFile = imageMuFile;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getOptionImage() {
		return optionImage;
	}

	public void setOptionImage(String optionImage) {
		this.optionImage = optionImage;
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
		return "VoteOption [Id=" + Id + ", eventId=" + eventId + ", startDate=" + startDate + ", place=" + place
				+ ", optionImage=" + optionImage + ", IsDeleted=" + IsDeleted + ", timeStamp=" + timeStamp + "]";
	}

}
