package com.ccapi.receivables;

/**
 * Sent to the user when a game ends.
 */
public class GameOverNotificationReceivable extends GameDetailsReceivable {
  public long winnerId;
  public String username;

  public GameOverNotificationReceivable(){}

  public GameOverNotificationReceivable(long gameId, long winnerId, String username) {
    this.gameId = gameId;
    this.winnerId = winnerId;
    this.username = username;
  }
}