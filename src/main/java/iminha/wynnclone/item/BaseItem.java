package iminha.wynnclone.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BaseItem extends Item implements ItemLike {

    private final WynnRarity wynnRarity;

    public BaseItem(Item.Properties properties, WynnRarity wynnRarity) {
        super(properties);
        this.wynnRarity = wynnRarity;
    }

    public WynnRarity getWynnRarity() {
        return wynnRarity;
    }

    @Override
    public Component getName(ItemStack stack) {
        //TODO:Have the name only be created once
        Component oldname = super.getName(stack); //Already translated
        //tooltip.set(0, new TranslatableComponent(getColorFromWynnRarity(((BaseItem)stack.getItem()).getWynnRarity())));
        return new TranslatableComponent(((BaseItem)stack.getItem()).getWynnRarity().getColor()
                + oldname.getString()
                + "\u00a7r");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(((BaseItem)stack.getItem()).getWynnRarity().getTooltip());
    }
}
