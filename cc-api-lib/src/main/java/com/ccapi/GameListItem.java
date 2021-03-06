package com.ccapi;

public class GameListItem {
  public long gameId;
  public boolean isReady;
  public int currentPlayerNumber;
  public int playerNumber;
  public String winnerUsername;
  public Integer winnerNumber;
  public int numberOfPlayers;

  public GameListItem(){}

  public GameListItem(long gameId, boolean isReady, int currentPlayerNumber, int playerNumber, String winnerUsername,
                      Integer winnerNumber, int numberOfPlayers) {

      this.gameId = gameId;
      this.isReady = isReady;
      this.currentPlayerNumber = currentPlayerNumber;
      this.playerNumber = playerNumber;
      this.winnerUsername = winnerUsername;
      this.winnerNumber = winnerNumber;
      this.numberOfPlayers = numberOfPlayers;
  }

  /**
   * Is there a winner.
   */
  public boolean isWinner() {
    return winnerUsername != null && winnerUsername.length() > 0;
  }

  public void setWinner(boolean value){}

  /**
   * Is is the requesting player's turn.
   */
  public boolean isPlayerTurn() {
    return currentPlayerNumber == playerNumber;
  }

  public void setPlayerTurn(boolean value) {}
}