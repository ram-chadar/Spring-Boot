package com.jbk.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uid")
	private int uid;
   
	@Column(name="uname")
	private String uname;
	
	@Column(name="uphn")
	private String uphn;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date created_date;
	
	@Column(name="modified_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date modified_date;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUphn() {
		return uphn;
	}

	public void setUphn(String uphn) {
		this.uphn = uphn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	
	
	
	
	
	
	
	
	
	
	
}
