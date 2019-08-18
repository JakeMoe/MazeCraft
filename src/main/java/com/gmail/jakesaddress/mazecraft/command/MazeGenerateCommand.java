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

import com.gmail.jakesaddress.mazecraft.Main;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.UUID;

public class MazeGenerateCommand implements CommandExecutor {

  @Override
  public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
    if (src instanceof Player) {

      Player player = (Player) src;
      UUID uuid = player.getUniqueId();

      Location<World> startLoc = Main.getStartLoc(uuid);
      Location<World> endLoc = Main.getEndLoc(uuid);

      if (startLoc == null || endLoc == null) {
        player.sendMessage(Text.of("Define corners before generating"));
        return CommandResult.success();
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
