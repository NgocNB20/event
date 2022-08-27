package model;

import java.io.Serializable;
import java.util.Map;

public class Detail implements Serializable {
	private int id;
	private int userId;
	private String fullname;
	private String email;
	private Boolean isJoined;
	private int AttachedPersonAdult;
	private int AttachedPersonChild;
	private String Note;
	private Map<Integer, Integer> voteMap;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<Integer, Integer> getVoteMap() {
		return voteMap;
	}

	public void setVoteMap(Map<Integer, Integer> voteMap) {
		this.voteMap = voteMap;
	}

	public Detail() {
		// TODO Auto-generated constructor stub
	}

	public Detail(int id, int userId, String fullname, String email, Boolean isJoined,
			int attachedPersonAdult, int attachedPersonChild, String note, String userName) {
		super();
		this.id = id;
		this.userId = userId;
		this.fullname = fullname;
		this.email = email;
		this.isJoined = isJoined;
		AttachedPersonAdult = attachedPersonAdult;
		AttachedPersonChild = attachedPersonChild;
		this.Note = note;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsJoined() {
		return isJoined;
	}

	public void setIsJoined(Boolean isJoined) {
		this.isJoined = isJoined;
	}

	public int getAttachedPersonAdult() {
		return AttachedPersonAdult;
	}

	public void setAttachedPersonAdult(int attachedPersonAdult) {
		AttachedPersonAdult = attachedPersonAdult;
	}

	public int getAttachedPersonChild() {
		return AttachedPersonChild;
	}

	public void setAttachedPersonChild(int attachedPersonChild) {
		AttachedPersonChild = attachedPersonChild;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	@Override
	public String toString() {
		return "Detail [id=" + id + ", userId=" + userId + ", fullname=" + fullname + ", email=" + email + ", isJoined="
				+ isJoined + ", AttachedPersonAdult=" + AttachedPersonAdult + ", AttachedPersonChild="
				+ AttachedPersonChild + ", Note=" + Note + ", voteMap=" + voteMap + ", userName=" + userName + "]";
	}




}