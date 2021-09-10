package iminha.wynnclone;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WynnCreativeTab extends CreativeModeTab {

    public WynnCreativeTab() {
        super(10, Wynnclone.MODID + ".wynntab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Wynnclone.testitem.get());
    }
}
