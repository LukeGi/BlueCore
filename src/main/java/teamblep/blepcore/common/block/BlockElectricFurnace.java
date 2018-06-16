package teamblep.blepcore.common.block;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamblep.blepcore.BlepCore;
import teamblep.blepcore.client.ModCreativeTabs;
import teamblep.blepcore.common.tileentity.TileEntityElectricFurnace;

public class BlockElectricFurnace extends Block implements ITileEntityProvider {

  public BlockElectricFurnace() {
    super(Material.IRON);
    this.setHardness(5F);
    this.setResistance(10F);
    this.setCreativeTab(ModCreativeTabs.MACHINE_TAB);
    this.setUnlocalizedName("blepcore:electric_furnace");
  }

  @Override
  public boolean onBlockActivated(
      World worldIn,
      BlockPos pos,
      IBlockState state,
      EntityPlayer playerIn,
      EnumHand hand,
      EnumFacing facing,
      float hitX,
      float hitY,
      float hitZ) {
    playerIn.openGui(BlepCore.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
    return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
  }

  @Nullable
  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityElectricFurnace();
  }
}
