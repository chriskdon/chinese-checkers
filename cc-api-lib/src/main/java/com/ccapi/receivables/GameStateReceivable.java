package com.ccapi.receivables;

import com.ccapi.*;

public class GameStateReceivable extends GameDetailsReceivable {
  public GameStateReceivable(){}
  
  public GameStateReceivable(long gameId) {
    this.gameId = gameId;
  }

  public PlayerInformation[] players;
  public GameState gameState;
}