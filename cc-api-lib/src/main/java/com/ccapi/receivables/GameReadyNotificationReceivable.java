package com.ccapi.receivables;

public class GameReadyNotificationReceivable extends GameDetailsReceivable {
  public GameReadyNotificationReceivable(){}

  public GameReadyNotificationReceivable(long gameId) {
    this.gameId = gameId;
  }  
}