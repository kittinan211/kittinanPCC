package com.pccth.pccsdmgrservice.model;

import javax.persistence.*;
import java.io.Serializable;

public class loginEmployee {
	 private String username;
	 private String password;
	 
	 public loginEmployee() {}
	 
	public loginEmployee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
