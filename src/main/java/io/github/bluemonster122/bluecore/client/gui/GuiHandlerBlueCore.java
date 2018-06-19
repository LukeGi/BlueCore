package io.github.bluemonster122.bluecore.client.gui;

import io.github.bluemonster122.bluecore.common.container.ContainerElectricFurnace;
import io.github.bluemonster122.bluecore.common.tileentity.TileEntityElectricFurnace;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerBlueCore implements IGuiHandler {

  @Nullable
  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
    if (tileEntity instanceof TileEntityElectricFurnace) {
      return new ContainerElectricFurnace(player.inventory, (TileEntityElectricFurnace) tileEntity);
    }
    return null;
  }

  @Nullable
  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
    if (tileEntity instanceof TileEntityElectricFurnace) {
      return new GuiElectricFurnace(new ContainerElectricFurnace(player.inventory, (TileEntityElectricFurnace) tileEntity));
    }
    return null;
  }
}
