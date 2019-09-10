/*
 * This file is part of MazeCraft.
 *
 * MazeDefineListener.java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MazeDefineListener.java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MazeDefineListener.java.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.gmail.jakesaddress.mazecraft.listener;

import com.gmail.jakesaddress.mazecraft.Main;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.UUID;

public class MazeDefineListener {

  @Listener
  public void onInteractBlockEventPrimaryMainHand(InteractBlockEvent.Primary.MainHand event, @First Player player) {

    UUID uuid = player.getUniqueId();

    if (Main.isSettingEnd(uuid)) {
      Location<World> startLoc = Main.getStartLoc(uuid).get();
      Location<World> endLoc = event.getTargetBlock().getLocation().get();
      if (Math.abs(startLoc.getX() - endLoc.getX()) >= 6 &&
          Math.abs(startLoc.getZ() - endLoc.getZ()) >= 6) {
        Main.setEndLoc(uuid, endLoc);
        Main.setSettingEnd(uuid, false);
        player.sendMessage(Text.of("Corners set to " + Main.getStartLoc(uuid).get().getBlockPosition().toString() + " and " + Main.getEndLoc(uuid).get().getBlockPosition().toString()));
      } else {
        player.sendMessage(Text.of("Corners must be at least 7 blocks apart; cancelling."));
        Main.setSettingEnd(uuid, false);
      }
    } else if (Main.isSettingStart(uuid)) {
      Main.setStartLoc(uuid, event.getTargetBlock().getLocation().get());
      Main.setSettingStart(uuid, false);
      Main.setSettingEnd(uuid, true);
      player.sendMessage(Text.of("First block set, select block for opposite corner"));
    }

  }

}
