package com.ccapi.receivables;

import com.ccapi.*;

public class PlayerMoveReceivable extends GameDetailsReceivable {
  public long userId;
  public Long winnerUserId; // Null if there is none

  public Move move;
}