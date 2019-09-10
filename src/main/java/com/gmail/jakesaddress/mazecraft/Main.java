/*
 * This file is part of MazeCraft.
 *
 * Main.java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Main.java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Main.java.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.gmail.jakesaddress.mazecraft;

import com.gmail.jakesaddress.maze.Maze;
import com.gmail.jakesaddress.mazecraft.command.MazeCommand;
import com.gmail.jakesaddress.mazecraft.command.MazeDefineCommand;
import com.gmail.jakesaddress.mazecraft.command.MazeGenerateCommand;
import com.gmail.jakesaddress.mazecraft.command.MazeHelpCommand;
import com.gmail.jakesaddress.mazecraft.listener.MazeDefineListener;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameConstructionEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Plugin(id = "mazecraft",
  name = "MazeCraft",
  version = "0.2",
  description = "A maze generation plugin",
  authors = "Cluracan")
public class Main {

  private static Main instance;

  @Inject
  private Logger logger;

  private Maze maze;

  private static HashMap<UUID, Boolean> settingEnd;
  private static HashMap<UUID, Boolean> settingStart;
  private static HashMap<UUID, Location<World>> startLoc;
  private static HashMap<UUID, Location<World>> endLoc;

  public Main() {
    settingEnd = new HashMap<>();
    settingStart = new HashMap<>();
    startLoc = new HashMap<>();
    endLoc = new HashMap<>();
  }

  @Listener
  public void onGameConstruction(GameConstructionEvent event) {
    instance = this;
  }

  @Listener
  public void onGamePreInitialization(GamePreInitializationEvent event) {
    logger.info("MazeCraft v0.1 has started");
  }

  @Listener
  public void onGameInitialization(GameInitializationEvent event) {
    registerCommands();
    registerListeners();
  }

  private void registerCommands() {
    CommandSpec mazeDefineCommandSpec = CommandSpec.builder()
      .description(Text.of("Defines the corners of the maze."))
      .extendedDescription(Text.of("Defines the corners of the maze to be generated"))
      .permission("mazecraft.command.define")
      .executor(new MazeDefineCommand())
      .build();

    CommandSpec mazeGenerateCommandSpec = CommandSpec.builder()
      .description(Text.of("Generates the maze specified"))
      .extendedDescription(Text.of("Maze must be configured using other commands before running this command"))
      .permission("mazecraft.command.generate")
      .executor(new MazeGenerateCommand())
      .build();

    CommandSpec mazeHelpCommandSpec = CommandSpec.builder()
      .description(Text.of("Shows help information"))
      .extendedDescription(Text.of("Displays usage and syntax information for the /maze command"))
      .permission("mazecraft.command.help")
      .executor(new MazeHelpCommand())
      .build();

    CommandSpec mazeCommandSpec = CommandSpec.builder()
      .description(Text.of("MazeCraft base command"))
      .extendedDescription(Text.of("MazeCraft is licensed under the terms of the GPLv3"))
      .permission("mazecraft.command")
      .child(mazeDefineCommandSpec, "define")
      .child(mazeGenerateCommandSpec, "generate")
      .child(mazeHelpCommandSpec, "help")
      .executor(new MazeCommand())
      .build();

    Sponge.getCommandManager().register(this, mazeCommandSpec, "maze", "mazecraft");
  }

  private void registerListeners() {
    Sponge.getEventManager().registerListeners(this, new MazeDefineListener());
  }

  public static Main getInstance() {
    return instance;
  }

  public Logger getLogger() {
    return logger;
  }

  public static Optional<Location<World>> getEndLoc(UUID uuid) {
    return Optional.ofNullable(endLoc.get(uuid));
  }

  public static Optional<Location<World>> getStartLoc(UUID uuid) {
    return Optional.ofNullable(startLoc.get(uuid));
  }

  public static boolean isSettingEnd(UUID uuid) {
    return settingEnd.getOrDefault(uuid, false);
  }

  public static boolean isSettingStart(UUID uuid) {
    return settingStart.getOrDefault(uuid, false);
  }

  public static void setEndLoc(UUID uuid, Location<World> loc) {
    endLoc.put(uuid, loc);
  }

  public static void setSettingEnd(UUID uuid, boolean end) {
    settingEnd.put(uuid, end);
  }

  public static void setSettingStart(UUID uuid, boolean start) {
    settingStart.put(uuid, start);
  }

  public static void setStartLoc(UUID uuid, Location<World> loc) {
    startLoc.put(uuid, loc);
  }

}
