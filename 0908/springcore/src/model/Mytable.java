package model;

import java.time.LocalDateTime;
import anotation.Column;
import anotation.Entity;
import anotation.Id;

@Entity(name = "myTable")
public class Mytable {
	@Id(name = "MYTableID")
	private Integer id;
	@Column(name = "createDate")
	private LocalDateTime createDate;
	public Mytable(Integer id, LocalDateTime createDate) {
		super();
		this.id = id;
		this.createDate = createDate;
	}
	public Mytable() {
		 
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public Mytable(LocalDateTime createDate) {
		super();
		this.createDate = createDate;
	}
	
	
 
}
