package com.ccapi.receivables;

public class ErrorReceivable extends Receivable {
  public ErrorReceivable() {
    super(CODE_ERROR, "Error");
  }

  public ErrorReceivable(String message) {
    super(CODE_ERROR, message);
  }
}