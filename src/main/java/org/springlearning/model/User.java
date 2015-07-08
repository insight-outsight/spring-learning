package org.springlearning.model;

public class User {

	private String name;
	private UserLevelEnum userLevel;
	
	public User() {
		super();
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserLevelEnum getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevelEnum userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", userLevel=" + userLevel + "]";
	}
	
}
