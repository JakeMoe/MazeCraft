/*
 * This file is part of MazeCraft.
 *
 * MazeCell.java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MazeCell.java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MazeCell.java.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.gmail.jakesaddress.maze;

public class MazeCell {

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

  public boolean hasEastWall() {
    return eastWall;
  }

  public boolean hasNorthWall() {
    return northWall;
  }

  public boolean hasSouthWall() {
    return southWall;
  }

  public boolean hasWestWall() {
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
