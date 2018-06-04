package teamblep.blepcore.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import teamblep.blepcore.common.energy.EnergyStorageBase;

import javax.annotation.Nullable;

public class TileEntityBase extends TileEntity {

    protected void onChanged() {
        IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
        markDirty();
    }

    @Nullable @Override public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
    }

    @Override public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override public void handleUpdateTag(NBTTagCompound tag) {
        readFromNBT(tag);
        getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
    }

    @Override public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    protected EnergyStorageBase getEnergyBase(@Nullable EnumFacing side) {
        IEnergyStorage m_energy = getEnergy(side);
        return m_energy instanceof EnergyStorageBase ? (EnergyStorageBase) m_energy : null;
    }

    protected IEnergyStorage getEnergy(@Nullable EnumFacing side) {
        return this.getCapability(CapabilityEnergy.ENERGY, side);
    }

    protected IItemHandler getInventory(@Nullable EnumFacing side) {
        return this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
    }

    protected IFluidHandler getTank(@Nullable EnumFacing side) {
        return this.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
    }
}
