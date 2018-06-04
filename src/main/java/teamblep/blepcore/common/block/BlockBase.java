package teamblep.blepcore.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.common.util.ITileEntityContainer;

public class BlockBase extends Block {

    public BlockBase(Material blockMaterialIn, MapColor blockMapColorIn, String name) {
        super(blockMaterialIn, blockMapColorIn);
        setup(name);
    }

    public BlockBase(Material materialIn, String name) {
        super(materialIn);
        setup(name);
    }

    @SuppressWarnings("unchecked") private void setup(String name) {
        this.setRegistryName(BlepCore.MOD_ID, name);
        this.setUnlocalizedName(BlepCore.MOD_ID + ":" + name);
        if (this instanceof ITileEntityContainer) {
            TileEntity.register(BlepCore.MOD_ID + ":" + name, ((ITileEntityContainer) this).getTileClass());
        }
    }
}
