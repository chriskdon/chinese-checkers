package com.ccapi;

public class GameState {
  public int currentPlayerNumber;
  public Long winnerUserId;
  public PieceInformation[] pieces;

  public GameState(){}

  public GameState(int currentPlayerNumber, Long winnerUserId) {
    this.currentPlayerNumber = currentPlayerNumber;
    this.winnerUserId = winnerUserId;
  }
}