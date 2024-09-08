package com.teas.library;

public class User {

	private int id;
	private String name;
	private long mob;
	private String email;
	private String password;
	//setter and Getter of the User Class


	public User(String name,long mob,String email,String password) {
    	this.name=name;
    	this.mob=mob;
    	this.email=email;
    	this.password=password;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
}
