package com.ccapi.receivables;

public class SuccessReceivable extends Receivable {
  public SuccessReceivable(String message) {
    super(CODE_SUCCESS, message);
  }

  public SuccessReceivable() {
    super(CODE_SUCCESS, "Success");
  }
}
