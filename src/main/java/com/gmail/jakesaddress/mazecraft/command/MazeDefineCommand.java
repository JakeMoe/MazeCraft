/*
 * This file is part of MazeCraft.
 *
 * MazeDefineCommand.java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MazeDefineCommand.java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MazeDefineCommand.java.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.gmail.jakesaddress.mazecraft.command;

import com.gmail.jakesaddress.mazecraft.Main;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.UUID;

public class MazeDefineCommand implements CommandExecutor {

  @Override
  public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
    if (src instanceof Player) {
      Player player = (Player) src;
      UUID uuid = player.getUniqueId();
      Main.setStartLoc(uuid, null);
      Main.setEndLoc(uuid, null);
      Main.setSettingStart(uuid, true);
      Main.setSettingEnd(uuid, false);
      player.sendMessage(Text.of("Click starting block for maze"));
    } else if (src instanceof ConsoleSource) {
      ConsoleSource consoleSource = (ConsoleSource) src;
      consoleSource.sendMessage(Text.of("This command can only be run by a player"));
    } else {
      Main.getInstance().getLogger().info("This command can only be run by a player");
    }
    return CommandResult.success();
  }

}
