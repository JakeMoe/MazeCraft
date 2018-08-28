package com.gmail.jakesaddress.maze;

class MazeCell {

  private boolean eastWall;
  private boolean northWall;
  private boolean southWall;
  private boolean visited;
  private boolean westWall;
  private int col;
  private int row;

  MazeCell(int col, int row) {
    this.col = col;
    this.row = row;
    eastWall = true;
    northWall = true;
    southWall = true;
    westWall = true;
    visited = false;
  }

  int getCol() {
    return col;
  }

  int getRow() {
    return row;
  }

  boolean hasEastWall() {
    return eastWall;
  }

  boolean hasNorthWall() {
    return northWall;
  }

  boolean hasSouthWall() {
    return southWall;
  }

  boolean hasWestWall() {
    return westWall;
  }

  boolean isVisited() {
    return visited;
  }

  void setEastWall(boolean eastWall) {
    this.eastWall = eastWall;
  }

  void setNorthWall(boolean northWall) {
    this.northWall = northWall;
  }

  void setSouthWall(boolean southWall) {
    this.southWall = southWall;
  }

  void setWestWall(boolean westWall) {
    this.westWall = westWall;
  }

  void setVisited(boolean visited) {
    this.visited = visited;
  }

}
