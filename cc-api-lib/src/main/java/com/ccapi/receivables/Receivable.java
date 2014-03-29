package com.ccapi.receivables;

public class Receivable {
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_ERROR = -1;

	public String message;
	public int code;

	public Receivable() {
		code = CODE_SUCCESS;
	}

	public Receivable(int code, String message) {
		this.code = code;
		this.message = message;
	}
}