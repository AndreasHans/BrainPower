package net.labymod.addons.brainpower.v1_8_9.mixins;

import net.labymod.addons.brainpower.ClickedBlock;
import net.labymod.addons.brainpower.ClickedBlockManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {


  @Inject(method = "onBlockActivated", at = @At("HEAD"))
  private void onTick(World world, BlockPos blockPos, IBlockState iBlockState,
      EntityPlayer entityPlayer, EnumFacing enumFacing, float v, float v1, float v2,
      CallbackInfoReturnable<Boolean> booleanCallbackInfoReturnable) {

    System.out.println("[Block]: onBlockActivated");

    if(ClickedBlockManager.isEnabled()) {
      System.out.println("[Block]: ClickedBlockManager.isEnabled = True");
      if(blockPos != null){
        System.out.println("[Block]: blockPos != null = True");
        System.out.println("[Block] Block interacted " + blockPos.getX() + " " + + blockPos.getY() + " " + blockPos.getZ());
        ClickedBlock clickedBlock = new ClickedBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        ClickedBlockManager.addBlock(clickedBlock);
        world.markBlockForUpdate(blockPos);

        System.out.println("[Block] size:" + ClickedBlockManager.size());

      }
      else{
        System.out.println("[Block]: blockPos != null: false");
      }
    }
    else{
      System.out.println("[Block]: ClickedBlockManager.isEnabled:" + ClickedBlockManager.isEnabled());
    }
  }
}
