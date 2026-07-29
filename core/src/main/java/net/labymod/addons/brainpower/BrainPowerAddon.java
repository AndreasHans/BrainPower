package net.labymod.addons.brainpower;

import net.labymod.addons.brainpower.listener.BrainPowerGameTickListener;
import net.labymod.addons.brainpower.widgets.NearPlayersWidget;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class BrainPowerAddon extends LabyAddon<BrainPowerConfiguration> {

  private HudWidgetCategory widgetCategory;

  @Override
  protected void enable() {

    PlayerManager playerManager = new PlayerManager(this);

    this.registerSettingCategory();
    this.registerListener(new BrainPowerGameTickListener(this));

    labyAPI().hudWidgetRegistry().categoryRegistry().register(this.widgetCategory = new HudWidgetCategory("brain_power"));
    labyAPI().hudWidgetRegistry().register(new NearPlayersWidget(this, playerManager));

    this.logger().info("Enabled the Addon");
  }

  public HudWidgetCategory widgetCategory() {
    return widgetCategory;
  }

  @Override
  protected Class<BrainPowerConfiguration> configurationClass() {
    return BrainPowerConfiguration.class;
  }
}
