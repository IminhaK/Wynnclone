package iminha.wynnclone.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class BaseItem extends Item implements ItemLike {

    private final WynnRarity wynnRarity;

    public BaseItem(Item.Properties properties, WynnRarity wynnRarity) {
        super(properties);
        this.wynnRarity = wynnRarity;
    }

    public WynnRarity getWynnRarity() {
        return wynnRarity;
    }

    public String getColorFromWynnRarity(WynnRarity wynnRarity) {
        switch (wynnRarity) {
            case DEPRESSING:
                return "\u00a77";
            case UNIQUE:
                return "\u00a7e";
            case RARE:
                return "\u00a7d";
            case LEGENDARY:
                return "\u00a7b";
            case FABLED:
                return "\u00a7c";
            case MYTHIC:
                return "\u00a75";
            case SET:
                return "\u00a7a";
            case NORMAL:
            default:
                return "\u00a7f";
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        //TODO:Have the name only be created once
        Component oldname = super.getName(stack);
        //tooltip.set(0, new TranslatableComponent(getColorFromWynnRarity(((BaseItem)stack.getItem()).getWynnRarity())));
        return new TranslatableComponent(getColorFromWynnRarity(((BaseItem)stack.getItem()).getWynnRarity())
                + oldname.getString()
                + "\u00a7r");
    }
}
