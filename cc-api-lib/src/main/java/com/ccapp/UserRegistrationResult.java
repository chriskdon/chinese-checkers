package com.ccapp;

public class UserRegistrationResult {
	public String username;
	public long userId;

	public UserRegistrationResult() {}
	public UserRegistrationResult(String username, long userId) {
		this.username = username;
		this.userId = userId;
	}
}