package net.labymod.addons.brainpower.widgets;

import net.labymod.addons.brainpower.BrainPowerAddon;
import net.labymod.addons.brainpower.PlayerManager;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine.State;
import java.util.List;

public class NearPlayersWidget extends TextHudWidget<TextHudWidgetConfig> {

  private TextLine[] textLine;
  private static final int NUM = 3;

  PlayerManager playerManager;
  BrainPowerAddon addon;

  private int debug = 0;


  public NearPlayersWidget(BrainPowerAddon addon, PlayerManager playerManager) {
    super("near_player_widget");
    // Bind the Widget to our created category in our main class
    this.bindCategory(addon.widgetCategory());
    this.playerManager = playerManager;
    this.addon = addon;
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    this.textLine = new TextLine[NUM];

    for(int i = 0; i < NUM; i++){
      this.textLine[i] = createLine("Player","");
    }
  }

  @Override
  public void onTick(boolean isEditorContext) {
    if(!this.labyAPI.minecraft().isIngame()) return;

    List<Player> players = this.playerManager.get();

    if (debug <= 0){
      for (Player player : players) {
        if(this.addon.configuration().enabled().get()){
          this.addon.logger().info(player.getName());
        }
      }
      debug = 100;
    }
    else{
      debug--;
    }

    for(int i = 0; i < NUM; i++){
      String value = null;
      if(i < players.size()){
        value = String.format("%s is %.2f blocks away", players.get(i).getName(), this.playerManager.distanceToClientPlayer(players.get(i)));
      }
      this.textLine[i].updateAndFlush(value);
      this.textLine[i].setState(value != null ? State.VISIBLE : State.HIDDEN);
    }
  }
}
