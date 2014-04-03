package com.ccapi.postdata;

import com.ccapi.*;

public class PlayerMovePostData {
  public PlayerMovePostData(){}

  public PlayerMovePostData(long gameId, long userId, boolean isWinner, Move move) {
    this.gameId = gameId;
    this.userId = userId;
    this.isWinner = isWinner;
    this.move = move;
  }

  public long gameId;
  public long userId;
  public boolean isWinner;

  public Move move;
}