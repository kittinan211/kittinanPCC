package com.pccth.pccsdmgrservice.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageUrl;
    @Column(nullable = false, updatable = false)
    private String employeeCode;
    private String department;
	private boolean using2FA;
	private String secret;
	
    public Employee() {}
    
	public Employee(Long id, String username, String password, String name, String email, String jobTitle, String phone,
			String imageUrl, String employeeCode, String department, boolean using2fa, String secret) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.jobTitle = jobTitle;
		this.phone = phone;
		this.imageUrl = imageUrl;
		this.employeeCode = employeeCode;
		this.department = department;
		using2FA = using2fa;
		this.secret = secret;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isUsing2FA() {
		return using2FA;
	}

	public void setUsing2FA(boolean using2fa) {
		using2FA = using2fa;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	    public String toString() {
	        return "Employee{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", email='" + email + '\'' +
	                ", jobTitle='" + jobTitle + '\'' +
	                ", phone='" + phone + '\'' +
	                ", imageUrl='" + imageUrl + '\'' +
	                '}';
	    }

}
