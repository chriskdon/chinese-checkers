package com.ccapi;

public class GameState {
  public int currentPlayerNumber;
  public Long winnerUserId;
  public PieceInformation[] pieces;
  public boolean isReady = false;
  public int numPlayers = 0;

  public GameState(){}

  public GameState(int currentPlayerNumber, Long winnerUserId, boolean isReady, int numPlayers) {
    this.currentPlayerNumber = currentPlayerNumber;
    this.winnerUserId = winnerUserId;
    this.isReady = isReady;
    this.numPlayers = numPlayers;
  }
}