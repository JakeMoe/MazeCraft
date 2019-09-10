/*
 * This file is part of MazeCraft.
 *
 * MazeGenerateCommand.java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MazeGenerateCommand.java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MazeGenerateCommand.java.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.gmail.jakesaddress.mazecraft.command;

import com.gmail.jakesaddress.maze.Maze;
import com.gmail.jakesaddress.maze.MazeCell;
import com.gmail.jakesaddress.mazecraft.Main;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.UUID;

public class MazeGenerateCommand implements CommandExecutor {

  @Override
  public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
    if (src instanceof Player) {

      Player player = (Player) src;
      UUID uuid = player.getUniqueId();

      Location<World> startLoc = Main.getStartLoc(uuid).orElse(null);
      Location<World> endLoc = Main.getEndLoc(uuid).orElse(null);

      if (startLoc == null || endLoc == null) {
        player.sendMessage(Text.of("Define corners before generating"));
        return CommandResult.empty();
      }

      World world = startLoc.getExtent();

      player.sendMessage(Text.of("World is " + world.getName()));

      int startX = Math.min(startLoc.getBlockX(), endLoc.getBlockX());
      int endX = Math.max(startLoc.getBlockX(), endLoc.getBlockX());
      int startY = Math.min(startLoc.getBlockY(), endLoc.getBlockY());
      int endY = Math.max(startLoc.getBlockY(), endLoc.getBlockY());
      int startZ = Math.min(startLoc.getBlockZ(), endLoc.getBlockZ());
      int endZ = Math.max(startLoc.getBlockZ(), endLoc.getBlockZ());

      int sizeX = endX - startX + 1;
      int sizeY = endY - startY + 1;
      int sizeZ = endZ - startZ + 1;

      if (sizeX % 2 != 1) { sizeX--; }
      if (sizeZ % 2 != 1) { sizeZ--; }

      Maze maze = new Maze((sizeX - 1) / 2, (sizeZ - 1) / 2);

      for (int row = 0; row < maze.getRows(); row++) {
        for (int col = 0; col < maze.getCols(); col++) {

          Location<World> cell = new Location<>(world, startX + (2 * col) + 1, startY, startZ + (2 * row) + 1);
          MazeCell mazeCell = maze.getCell(col, row);
          cell.setBlockType(BlockTypes.AIR);

          cell.getBlockRelative(Direction.NORTHEAST).setBlockType(BlockTypes.DIRT);
          cell.getBlockRelative(Direction.SOUTHEAST).setBlockType(BlockTypes.DIRT);
          cell.getBlockRelative(Direction.SOUTHWEST).setBlockType(BlockTypes.DIRT);
          cell.getBlockRelative(Direction.NORTHWEST).setBlockType(BlockTypes.DIRT);

          if (mazeCell.hasNorthWall()) {
            cell.getBlockRelative(Direction.NORTH).setBlockType(BlockTypes.DIRT);
          } else {
            cell.getBlockRelative(Direction.NORTH).setBlockType(BlockTypes.AIR);
          }

          if (mazeCell.hasEastWall()) {
            cell.getBlockRelative(Direction.EAST).setBlockType(BlockTypes.DIRT);
          } else {
            cell.getBlockRelative(Direction.EAST).setBlockType(BlockTypes.AIR);
          }

          if (mazeCell.hasSouthWall()) {
            cell.getBlockRelative(Direction.SOUTH).setBlockType(BlockTypes.DIRT);
          } else {
            cell.getBlockRelative(Direction.SOUTH).setBlockType(BlockTypes.AIR);
          }

          if (mazeCell.hasWestWall()) {
            cell.getBlockRelative(Direction.WEST).setBlockType(BlockTypes.DIRT);
          } else {
            cell.getBlockRelative(Direction.WEST).setBlockType(BlockTypes.AIR);
          }

        }
      }

    } else if (src instanceof ConsoleSource) {
      ConsoleSource consoleSource = (ConsoleSource) src;
      consoleSource.sendMessage(Text.of("This command can only be run by a player"));
    } else {
      Main.getInstance().getLogger().info("This command can only be run by a player");
    }
    return CommandResult.success();
  }

}
