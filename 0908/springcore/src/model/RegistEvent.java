package model;

import java.time.LocalDateTime;

import anotation.Column;
import anotation.Entity;
import anotation.Id;

@Entity(name = "RegistEvents")
public class RegistEvent {

	@Id(name = "ID")
	private Integer Id;
	@Column(name = "Username")
	private String username;
	@Column(name = "EventID")
	private Integer eventID;
	@Column(name = "IsJoined")
	private boolean isJoined;
	@Column(name = "VoteOptionID")
	private Integer voteOptionID;
	
	@Column(name = "Voted")
	private boolean voted;
	@Column(name = "AttachedPersonAdult")
	private Integer attachedPersonAdult;
	@Column(name = "AttachedPersonChild")
	private Integer attachedPersonChild;
	@Column(name = "IsDeleted")
	private boolean isDeleted;
	@Column(name = "Timestamp")
	private LocalDateTime timeStamp;

	
	
	public RegistEvent() {

	}

	public RegistEvent(Integer id, String username, Integer eventID, boolean isJoined, Integer voteOptionID,
			boolean voted, Integer attachedPersonAdult, Integer attachedPersonChild, boolean isDeleted,
			LocalDateTime timeStamp) {
		super();
		Id = id;
		this.username = username;
		this.eventID = eventID;
		this.isJoined = isJoined;
		this.voteOptionID = voteOptionID;
		this.voted = voted;
		this.attachedPersonAdult = attachedPersonAdult;
		this.attachedPersonChild = attachedPersonChild;
		isDeleted = isDeleted;
		this.timeStamp = timeStamp;
	}

}
