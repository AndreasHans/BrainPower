package net.labymod.addons.brainpower;


import java.util.HashSet;

public class ClickedBlockManager {

  private static final HashSet<ClickedBlock> blocksPos = new HashSet<>();
  public static BrainPowerAddon addon;

  public synchronized static void addBlock(ClickedBlock blockPos) {
    if (blockPos != null){
      blocksPos.add(blockPos);
    }
  }

  public synchronized static int size() {
    return blocksPos.size();
  }

  public synchronized static boolean isEnabled() {
    if(addon == null){
      return false;
    }
    boolean enabled = addon.configuration().blockEventEnabled().get();
    return enabled;
  }

  public synchronized static void reset(){
    blocksPos.clear();
  }

  public synchronized static boolean isEmpty(){
    return blocksPos.isEmpty();
  }

  public synchronized static boolean containsBlockPos(ClickedBlock blockPos) {
    if (blockPos == null){
      return false;
    }
    return blocksPos.contains(blockPos);
  }
}
