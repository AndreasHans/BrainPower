package net.labymod.addons.brainpower;

import net.labymod.addons.brainpower.listener.BrainPowerGameTickListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class BrainPowerAddon extends LabyAddon<BrainPowerConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
    this.registerListener(new BrainPowerGameTickListener(this));

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<BrainPowerConfiguration> configurationClass() {
    return BrainPowerConfiguration.class;
  }
}
