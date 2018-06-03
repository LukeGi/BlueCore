package teamblep.blepcore.client.gui.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamblep.blepcore.client.gui.GuiElectricFurnace;
import teamblep.blepcore.common.container.ContainerElectricFurnace;
import teamblep.blepcore.common.tileentity.TileEntityElectricFurnace;

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
