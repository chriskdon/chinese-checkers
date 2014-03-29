package com.ccapi.receivables;

public class JoinGameReceivable extends GameDetailsReceivable {
  public boolean isReady = false; // Is the game ready to be played.

  public JoinGameReceivable(){}

  public JoinGameReceivable(long gameId) {
    this.gameId = gameId;
  }
}