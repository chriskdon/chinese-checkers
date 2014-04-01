package com.ccapi;

public class PlayerInformation {
  public PlayerInformation() {}

  public PlayerInformation(long userId, String username, int number) {
    this.userId = userId;
    this.username = username;
    this.number = number;
  }

  public long userId;
  public int number;
  public String username;
}