package model;

import anotation.Entity;

import java.time.LocalDateTime;

import anotation.Column;
import anotation.Id;

@Entity(name = "Department")
public class Department {

	@Id(name = "ID")
	private Integer id;
	@Column(name = "DepartName")
	private String departName;
	@Column(name = "IsDeleted")
	private Boolean isDeleted;
	@Column(name = "Timestamp")
	private LocalDateTime timeStamp;

	public Department() {

	}

	public Department(Integer id, String departName, Boolean isDeleted, LocalDateTime timeStamp) {
 
		this.id = id;
		this.departName = departName;
		this.isDeleted = isDeleted;
		this.timeStamp = timeStamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departName=" + departName + ", isDeleted=" + isDeleted + ", timeStamp="
				+ timeStamp + "]";
	}

	
	
}
