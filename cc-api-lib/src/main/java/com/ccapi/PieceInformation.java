package com.ccapi;

public class PieceInformation {
  public PieceInformation(){}

  public PieceInformation(int playerNumber, int row, int index) {
    this.playerNumber = playerNumber;
    this.row = row;
    this.index = index;
  }

  public int row, index;
  public int playerNumber;
}