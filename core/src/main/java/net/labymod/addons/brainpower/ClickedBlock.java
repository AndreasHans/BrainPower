package net.labymod.addons.brainpower;

import org.jetbrains.annotations.NotNull;

public record ClickedBlock(int x, int y, int z) implements Comparable<ClickedBlock> {

  @Override
  public int compareTo(@NotNull ClickedBlock o) {
    if(this.x == o.x && this.y == o.y && this.z == o.z) return 0;
    else if(this.x < o.x) return -1;
    else if(this.x > o.x) return 1;
    else if(this.y < o.y) return -1;
    else if(this.y > o.y) return 1;
    else if(this.z < o.z) return -1;
    return 1;
  }
}
