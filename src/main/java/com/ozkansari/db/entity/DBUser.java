package com.ozkansari.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "dbuser")
@NamedQuery(name = "DBUser.findByTheUsersName", query = "from DBUser u where u.username = ?1")
public class DBUser extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = -2952735933715107252L;
	
	@Column(unique = true) 
	private String username;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String firstname;
	
	private String lastname;

	public DBUser() {
	}

	public DBUser(Integer id) {
		this.setId(id);
	}
	
	public DBUser(String username, String createdBy,
			Date createdDate) {
		this();
		this.username = username;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "DBUser [username=" + username + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", getId()=" + getId() + "]";
	}

}
