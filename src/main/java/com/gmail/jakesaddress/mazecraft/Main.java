package com.gmail.jakesaddress.mazecraft;

import com.gmail.jakesaddress.maze.Maze;
import com.gmail.jakesaddress.mazecraft.command.MazeCommand;
import com.gmail.jakesaddress.mazecraft.command.MazeGenerateCommand;
import com.gmail.jakesaddress.mazecraft.command.MazeHelpCommand;
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

@Plugin(id = "mazecraft",
  name = "MazeCraft",
  version = "0.1",
  description = "A maze generation plugin",
  authors = "Cluracan")
public class Main {

  private static Main instance;

  @Inject
  private Logger logger;

  private Maze maze;

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
  }

  private void registerCommands() {
    CommandSpec mazeHelpCommandSpec = CommandSpec.builder()
      .description(Text.of("Shows help information"))
      .extendedDescription(Text.of("Displays usage and syntax information for the /maze command"))
      .permission("mazecraft.command.help")
      .executor(new MazeHelpCommand())
      .build();

    CommandSpec mazeGenerateCommandSpec = CommandSpec.builder()
      .description(Text.of("Generates the maze specified"))
      .extendedDescription(Text.of("Maze must be configured using other commands before running this command"))
      .permission("mazecraft.command.generate")
      .executor(new MazeGenerateCommand())
      .build();

    CommandSpec mazeCommandSpec = CommandSpec.builder()
      .description(Text.of("MazeCraft base command"))
      .extendedDescription(Text.of("MazeCraft is licensed under the terms of the GPLv3"))
      .permission("mazecraft.command")
      .child(mazeGenerateCommandSpec)
      .child(mazeHelpCommandSpec)
      .executor(new MazeCommand())
      .build();

    Sponge.getCommandManager().register(this, mazeCommandSpec, "maze", "mazecraft");
  }

  static Main getInstance() {
    return instance;
  }

  Logger getLogger() {
    return logger;
  }

}
