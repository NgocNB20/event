package model;

 

import anotation.Column;
import anotation.Entity;
import anotation.Id;

@Entity(name = "Users")
public class User {

	@Id(name = "ID")
	private Integer id;
	@Column(name = "Username")
	private String username;
	@Column(name = "Fullname")
	private String fullName;
	@Column(name = "DepartID")
	private Integer departID;

	@Column(name = "Email")
	private String email;
	@Column(name = "Password")
	private String password;
	@Column(name = "IsDeleted")
	private Boolean isDeleted;
	private String departName;

	public User() {

	}

	public User(Integer id, String username, String fullName, Integer departID, String email, String password,
			Boolean isDeleted, String departName) {
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.departID = departID;
		this.email = email;
		this.password = password;
		this.isDeleted = isDeleted;
		this.departName = departName;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getDepartID() {
		return departID;
	}

	public void setDepartID(Integer departID) {
		this.departID = departID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fullName=" + fullName + ", departID=" + departID
				+ ", email=" + email + ", password=" + password + ", isDeleted=" + isDeleted + ", departName="
				+ departName + "]";
	}

}
