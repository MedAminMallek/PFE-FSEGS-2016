package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Session")
public class Session implements Serializable{
	
	@Id
	private String id;
	private String session;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Session(String id, String session) {
		super();
		this.id = id;
		this.session = session;
		
	}
	public Session() {
		super();
	}
	

}
