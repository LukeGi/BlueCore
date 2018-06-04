package teamblep.blepcore.common.item.tools;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ToolAxe extends ToolBase {
    public ToolAxe(String name) {
        super(name);
    }

    @Override public boolean isEffective(IBlockState block) {
        Material material = block.getMaterial();
        return material.equals(Material.LEAVES) || material.equals(Material.WOOD);
    }

    @Override public float getEffectiveSpeed() {
        return 3.0F;
    }

    @Override public float getIneffectiveSpeed() {
        return 0.5F;
    }

    @Override
    public boolean rightClickBlockAction(EntityPlayer player, EnumHand hand, World world, BlockPos pos,
                                         IBlockState blockState, EnumFacing side, Vec3d hit) {
        player.sendStatusMessage(new TextComponentString("You right clicked a block").setStyle(
                new Style().setColor(TextFormatting.GREEN)), false);
        return false;
    }

    @Override
    public boolean leftClickBlockAction(EntityPlayer player, EnumHand hand, World world, BlockPos pos,
                                        IBlockState blockState, EnumFacing side, Vec3d hit) {
        player.sendStatusMessage(new TextComponentString("You left clicked a block").setStyle(
                new Style().setColor(TextFormatting.GREEN)), false);
        return false;
    }

    @Override public boolean rightClickAirAction(EntityPlayer player, EnumHand hand, World world) {
        player.sendStatusMessage(
                new TextComponentString("You right clicked a air").setStyle(new Style().setColor(TextFormatting.RED)),
                false);
        return false;
    }

    @Override public boolean leftClickAirAction(EntityPlayer player, EnumHand hand, World world) {
        player.sendStatusMessage(
                new TextComponentString("You left clicked a air").setStyle(new Style().setColor(TextFormatting.RED)),
                false);
        return false;
    }
}
