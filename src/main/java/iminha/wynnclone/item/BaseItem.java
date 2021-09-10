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

    public String getWynnRarityColorFromStack(ItemStack stack) {
        if(stack.getItem() instanceof BaseItem) {
            WynnRarity wynnRarity = ((BaseItem) stack.getItem()).getWynnRarity();
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
        //Item is not a wynn item.
        System.out.println("Attempted to find Wynnrarity of non wynn item: " + super.getName(stack).getString());
        return null;
    }

    public Component getTranslatedWynnRarityFromStack(ItemStack stack) {
        if(stack.getItem() instanceof BaseItem) {
            WynnRarity wynnRarity = ((BaseItem) stack.getItem()).getWynnRarity();
            switch (wynnRarity) {
                case DEPRESSING:
                    return new TranslatableComponent("wynn.tooltip.rarity.depressing");
                case UNIQUE:
                    return new TranslatableComponent("wynn.tooltip.rarity.unique");
                case RARE:
                    return new TranslatableComponent("wynn.tooltip.rarity.rare");
                case LEGENDARY:
                    return new TranslatableComponent("wynn.tooltip.rarity.legendary");
                case FABLED:
                    return new TranslatableComponent("wynn.tooltip.rarity.fabled");
                case MYTHIC:
                    return new TranslatableComponent("wynn.tooltip.rarity.mythic");
                case SET:
                    return new TranslatableComponent("wynn.tooltip.rarity.set");
                case NORMAL:
                default:
                    return new TranslatableComponent("wynn.tooltip.rarity.normal");
            }
        }
        //Item is not wynn item
        System.out.println("Attempted to find Wynnrarity of non wynn item: " + super.getName(stack).getString());
        return null;
    }

    @Override
    public Component getName(ItemStack stack) {
        //TODO:Have the name only be created once
        Component oldname = super.getName(stack); //Already translated
        //tooltip.set(0, new TranslatableComponent(getColorFromWynnRarity(((BaseItem)stack.getItem()).getWynnRarity())));
        return new TranslatableComponent(getWynnRarityColorFromStack(stack)
                + oldname.getString()
                + "\u00a7r");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        //TODO: add description based on rarity
        tooltip.add(getTranslatedWynnRarityFromStack(stack));
    }
}
