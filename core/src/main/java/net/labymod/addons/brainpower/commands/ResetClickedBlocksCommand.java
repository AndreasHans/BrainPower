package net.labymod.addons.brainpower.commands;

import net.labymod.addons.brainpower.ClickedBlockManager;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;

public class ResetClickedBlocksCommand extends Command {

  public ResetClickedBlocksCommand() {
    super("reset");

  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (prefix.equalsIgnoreCase("reset")) {
      ClickedBlockManager.reset();
      this.displayMessage(Component.text("Reset clicked blocks!", NamedTextColor.AQUA));
      return true;
    }
    return false;
  }
}