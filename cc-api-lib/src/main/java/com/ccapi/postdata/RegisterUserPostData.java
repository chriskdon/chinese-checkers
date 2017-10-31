package com.ccapi.postdata;

public class RegisterUserPostData {
  public String username;
  public String gcmRegistrationId;

  public RegisterUserPostData(){}

  public RegisterUserPostData(String username, String gcmRegistrationId) {
    this.username = username;
    this.gcmRegistrationId = gcmRegistrationId;
  }
}