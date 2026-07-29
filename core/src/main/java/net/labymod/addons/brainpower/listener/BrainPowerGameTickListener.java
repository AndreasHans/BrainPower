package net.labymod.addons.brainpower.listener;

import net.labymod.addons.brainpower.BrainPowerAddon;
import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;

public class BrainPowerGameTickListener {

  private final BrainPowerAddon addon;

  public BrainPowerGameTickListener(BrainPowerAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (event.phase() != Phase.PRE) {
      return;
    }
  }
}
