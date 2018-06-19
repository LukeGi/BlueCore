package io.github.bluemonster122.bluecore.client.gui.handler;

import io.github.bluemonster122.bluecore.client.gui.GuiElectricFurnace;
import io.github.bluemonster122.bluecore.common.container.ContainerElectricFurnace;
import io.github.bluemonster122.bluecore.common.tileentity.TileEntityElectricFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiHandlerElectricFurnace extends GuiHandlerMachine<ContainerElectricFurnace, GuiElectricFurnace, TileEntityElectricFurnace> {

  @Override
  protected ContainerElectricFurnace getContainer(EntityPlayer player, World world, BlockPos pos) {
    return new ContainerElectricFurnace(player.inventory, (TileEntityElectricFurnace) world.getTileEntity(pos));
  }

  @Override
  protected GuiElectricFurnace getGui(EntityPlayer player, World world, BlockPos blockPos) {
    return new GuiElectricFurnace(getContainer(player, world, blockPos));
  }
}
