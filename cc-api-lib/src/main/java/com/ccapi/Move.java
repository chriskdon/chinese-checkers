package com.ccapi;

public class Move {
  public Move() {}

  public Move(int startRow, int startIndex, int endRow, int endIndex) {
    this.startRow = startRow;
    this.startIndex = startIndex;

    this.endRow = endRow;
    this.endIndex = endIndex;
  }

  public int startRow, startIndex, endRow, endIndex;
}