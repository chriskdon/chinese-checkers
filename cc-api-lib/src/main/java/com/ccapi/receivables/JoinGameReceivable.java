package com.ccapi.receivables;

import com.ccapi.*;

public class JoinGameReceivable extends Receivable {
  public GameListItem gameListItem;

  public JoinGameReceivable(){}

  public JoinGameReceivable(GameListItem item) {
    this.gameListItem = item;
  }
}