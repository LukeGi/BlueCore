package teamblep.blepcore.common.item;

import net.minecraft.item.Item;
import teamblep.blepcore.BlepCore;

public class ItemBase extends Item {
    public ItemBase(String name) {
        setup(name);
    }

    private void setup(String name) {
        this.setRegistryName(BlepCore.MOD_ID, name);
        this.setUnlocalizedName(BlepCore.MOD_ID + ":" + name);
    }
}
