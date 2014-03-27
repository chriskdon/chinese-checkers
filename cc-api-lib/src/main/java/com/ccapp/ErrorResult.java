package com.ccapp;

public class ErrorResult {
	public static final int CODE_GENERIC = 1;

	public String message;
	public int code;

	public ErrorResult() {}
	public ErrorResult(int code, String message) {
		this.code = code;
		this.message = message;
	}
}