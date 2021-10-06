package iminha.wynnclone;

import iminha.wynnclone.item.DynamicItem;
import iminha.wynnclone.item.WynnRarity;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fmllegacy.RegistryObject;

public class WynnCreativeTab extends CreativeModeTab {

    public WynnCreativeTab() {
        super(10, Wynnclone.MODID + ".wynntab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Wynnclone.lootbox.get());
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> itemsInTab) {
        for(RegistryObject<Item> r : Wynnclone.ITEMS.getEntries()) {
            Item i = r.get();
            if(!r.equals(Wynnclone.lootbox)) {
                itemsInTab.add(new ItemStack(i));
            } else { //Lootboxes
                ItemStack newBox;
                for(WynnRarity w : WynnRarity.values()) {
                    CompoundTag wynnattributes = new CompoundTag();
                    newBox = new ItemStack(i);
                    wynnattributes.putInt(DynamicItem.TAG_RARITY, w.getId());
                    CompoundTag fullTag = new CompoundTag();
                    fullTag.put(DynamicItem.TAG_ATTRIBUTES, wynnattributes);
                    newBox.setTag(fullTag);
                    itemsInTab.add(newBox);
                }
            }
        }
    }
}
