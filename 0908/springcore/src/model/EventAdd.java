package model;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import anotation.Column;

public class EventAdd {
	private Integer id;

	private String eventName;
	private String description;
	private String department;
	private String startDate;
	private String endDate;
	private String place;
	private String departID;
	private String creator;
	private String status;
	private String eventType;
	private String optionType;
	private String startHour;
	private String startMinute;
	private String endHour;
	private String endMinute;
	private String dataMembers;
	private String dataDepartments;
	private MultipartFile imageMain;
	private String placeVoteOption[];
	private MultipartFile[] imageVoteOption;
	private String dateVoteOption[];
	private String voteOptionId;

	public EventAdd() {

	}

 

	public EventAdd(Integer id, String eventName, String description, String department, String startDate,
			String endDate, String place, String departID, String creator, String status, String eventType,
			String optionType, String startHour, String startMinute, String endHour, String endMinute,
			String dataMembers, String dataDepartments, MultipartFile imageMain, String[] placeVoteOption,
			MultipartFile[] imageVoteOption, String[] dateVoteOption, String voteOptionId) {
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.department = department;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.departID = departID;
		this.creator = creator;
		this.status = status;
		this.eventType = eventType;
		this.optionType = optionType;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.dataMembers = dataMembers;
		this.dataDepartments = dataDepartments;
		this.imageMain = imageMain;
		this.placeVoteOption = placeVoteOption;
		this.imageVoteOption = imageVoteOption;
		this.dateVoteOption = dateVoteOption;
		this.voteOptionId = voteOptionId;
	}



	public String getVoteOptionId() {
		return voteOptionId;
	}



	public void setVoteOptionId(String voteOptionId) {
		this.voteOptionId = voteOptionId;
	}



	public String[] getDateVoteOption() {
		return dateVoteOption;
	}

	public void setDateVoteOption(String[] dateVoteOption) {
		this.dateVoteOption = dateVoteOption;
	}

	public MultipartFile[] getImageVoteOption() {
		return imageVoteOption;
	}

	public void setImageVoteOption(MultipartFile[] imageVoteOption) {
		this.imageVoteOption = imageVoteOption;
	}

	public String[] getPlaceVoteOption() {
		return placeVoteOption;
	}

	public void setPlaceVoteOption(String[] placeVoteOption) {
		this.placeVoteOption = placeVoteOption;
	}

	public Integer getId() {
		return id;
	}

	public String getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getDataMembers() {
		return dataMembers;
	}

	public void setDataMembers(String dataMembers) {
		this.dataMembers = dataMembers;
	}

	public String getDataDepartments() {
		return dataDepartments;
	}

	public void setDataDepartments(String dataDepartments) {
		this.dataDepartments = dataDepartments;
	}

	public MultipartFile getImageMain() {
		return imageMain;
	}

	public void setImageMain(MultipartFile imageMain) {
		this.imageMain = imageMain;
	}

	@Override
	public String toString() {
		return "EventAdd [id=" + id + ", eventName=" + eventName + ", description=" + description + ", department="
				+ department + ", startDate=" + startDate + ", endDate=" + endDate + ", place=" + place + ", departID="
				+ departID + ", creator=" + creator + ", status=" + status + ", eventType=" + eventType
				+ ", optionType=" + optionType + ", startHour=" + startHour + ", startMinute=" + startMinute
				+ ", endHour=" + endHour + ", endMinute=" + endMinute + ", dataMembers=" + dataMembers
				+ ", dataDepartments=" + dataDepartments + ", imageMain=" + imageMain + ", placeVoteOption="
				+ Arrays.toString(placeVoteOption) + ", imageVoteOption=" + Arrays.toString(imageVoteOption)
				+ ", dateVoteOption=" + Arrays.toString(dateVoteOption) + "]";
	}

}
