package io.github.bluemonster122.bluecore.common.block;

import io.github.bluemonster122.bluecore.BlueCore;
import io.github.bluemonster122.bluecore.common.tileentity.TileEntityElectricFurnace;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import io.github.bluemonster122.bluecore.client.ModCreativeTabs;

public class BlockElectricFurnace extends Block implements ITileEntityProvider {

  public BlockElectricFurnace() {
    super(Material.IRON);
    this.setDefaultState(this.blockState.getBaseState().withProperty(Properties.FACING4, EnumFacing.NORTH));
    this.setHardness(5F);
    this.setResistance(10F);
    this.setCreativeTab(ModCreativeTabs.MACHINE_TAB);
    this.setUnlocalizedName("bluecore:electric_furnace");
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(Properties.FACING4, EnumFacing.getHorizontal(meta % 4));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(Properties.FACING4).getHorizontalIndex();
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    playerIn.openGui(BlueCore.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
    return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer.Builder(this).add(Properties.FACING4).build();
  }

  @Override
  public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
    EnumFacing blockFacing = placer.getHorizontalFacing().getOpposite();
    return this.blockState.getBaseState().withProperty(Properties.FACING4, blockFacing);
  }

  @Nullable
  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityElectricFurnace();
  }
}
