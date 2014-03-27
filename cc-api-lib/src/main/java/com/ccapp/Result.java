package com.ccapp;

public class Result {
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_ERROR = -1;

	public String message;
	public int code;

	public Result() {
		code = CODE_SUCCESS;
	}

	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}
}