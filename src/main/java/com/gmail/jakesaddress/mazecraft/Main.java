package com.gmail.jakesaddress.mazecraft;

import com.gmail.jakesaddress.maze.Maze;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameConstructionEvent;
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

  public Main() {
    instance = this;
  }

  @Listener
  public void onGameConstruction(GameConstructionEvent event) {
    CommandSpec commandSpec = CommandSpec.builder()
      .description(Text.of("MazeCraft Command"))
      .permission("mazecraft.command")
      .executor(new Commands())
      .build();
    Sponge.getCommandManager().register(this, commandSpec, "mazecraft", "maze");
  }

  static Main getInstance() {
    return instance;
  }

  Logger getLogger() {
    return logger;
  }

}
