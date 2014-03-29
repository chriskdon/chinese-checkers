package com.ccapi.receivables;

public class UserRegistrationReceivable extends Receivable {
	public String username;
	public long userId;

	public UserRegistrationReceivable() {}
	public UserRegistrationReceivable(String username, long userId) {
		this.username = username;
		this.userId = userId;
	}
}