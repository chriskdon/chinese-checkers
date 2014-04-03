package com.ccapi.receivables;

import com.ccapi.*;

public class PlayerMoveReceivable extends GameDetailsReceivable {
  public PlayerMoveReceivable(){}

  public PlayerMoveReceivable(long gameId, long userId, Long winnerUserId, Move move) {
    this.gameId = gameId;
    this.userId = userId;
    this.winnerUserId = winnerUserId;
    this.move = move;
  }

  public long userId;
  public Long winnerUserId; // Null if there is none

  public Move move;
}