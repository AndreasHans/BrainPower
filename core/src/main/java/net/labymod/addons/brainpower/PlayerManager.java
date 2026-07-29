package net.labymod.addons.brainpower;

import net.labymod.api.Laby;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.Player;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerManager {

  private final BrainPowerAddon addon;

  public PlayerManager(BrainPowerAddon addon){
    this.addon = addon;
  }

  public List<Player> get(){
    List<Player> players = this.getAllPlayers();
    List<Player> players2 = players.stream().filter(player -> !player.getName().equals(this.clientPlayerName())).toList();
    List<Player> players3 = players2.stream().filter(player -> !player.getName().startsWith("§")).toList();
    return getSortedPlayers(players3);
  }

  private List<Player> getAllPlayers(){
    return this.addon.labyAPI().minecraft().clientWorld().getPlayers();
  }

  private Player getClientPlayer(){
    return Laby.labyAPI().minecraft().getClientPlayer();
  }

  private String clientPlayerName(){
    Player player = this.getClientPlayer();
    if (player == null) return "";
    return player.getName();
  }

  public double distanceToClientPlayer(Entity entity){
    return Math.sqrt(entity.getDistanceSquared(this.getClientPlayer()));
  }

  private List<Player> getSortedPlayers(List<Player> players){
    Player clientPlayer = this.getClientPlayer();
    if(clientPlayer != null){
      return players.stream().sorted(
          Comparator.comparingDouble(entity-> distanceBetween(clientPlayer, entity))).toList();
    }
    return new ArrayList<>();
  }

  private double distanceBetween(Entity entity1, Entity entity2){
    return Math.sqrt(entity1.getDistanceSquared(entity2));
  }


}