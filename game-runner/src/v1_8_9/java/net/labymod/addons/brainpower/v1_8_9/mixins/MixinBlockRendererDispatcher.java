package net.labymod.addons.brainpower.v1_8_9.mixins;

import net.labymod.addons.brainpower.ClickedBlock;
import net.labymod.addons.brainpower.ClickedBlockManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockRendererDispatcher.class)
public class MixinBlockRendererDispatcher {

  @Inject(
      method = "renderBlock",
      at = @At("HEAD"),
      cancellable = true
  )
  private void onRenderBlock(IBlockState state, BlockPos pos, IBlockAccess blockAccess, WorldRenderer worldRenderer, CallbackInfoReturnable<Boolean> cir) {

    ClickedBlock clickedBlock =  new ClickedBlock(pos.getX(), pos.getY(), pos.getZ());

    if(ClickedBlockManager.isEnabled() && state != null && ClickedBlockManager.containsBlockPos(clickedBlock)){
      System.out.println("[Render] size:" + ClickedBlockManager.size());
      IBlockState otherBlockState = getNewBlockState(state);
      BlockRendererDispatcher dispatcher = (BlockRendererDispatcher) (Object) this;
      boolean rendered = dispatcher.getBlockModelRenderer().renderModel(
          blockAccess,
          dispatcher.getModelFromBlockState(otherBlockState, blockAccess, pos),
          otherBlockState,
          pos,
          worldRenderer,
          true
      );
      cir.setReturnValue(rendered);
    }
  }

  private IBlockState getNewBlockState(IBlockState state) {

    Block block = state.getBlock();

    if(block.isFullCube()){
      return Blocks.redstone_block.getDefaultState();
    } else if(block.isOpaqueCube()){
      return Blocks.redstone_block.getDefaultState();
    }
    else if(block.isVisuallyOpaque()){
      return Blocks.redstone_block.getDefaultState();
    }
    else if (block instanceof BlockSlab) {
      return Blocks.wooden_slab.getDefaultState().withProperty(BlockSlab.HALF, state.getValue(BlockSlab.HALF));
    }
    else if(block instanceof BlockStairs){
      return Blocks.acacia_stairs.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING));
    }
    else if(block instanceof BlockDoor){
      return Blocks.acacia_door.getDefaultState().withProperty(BlockDoor.FACING, state.getValue(BlockDoor.FACING));
    }


    boolean isGlass = block instanceof net.minecraft.block.BlockGlass
        || block == Blocks.stained_glass
        || block instanceof net.minecraft.block.BlockPane;

    if(isGlass){
      return Blocks.redstone_block.getDefaultState();

    }

    return Blocks.deadbush.getDefaultState();
  }
}
