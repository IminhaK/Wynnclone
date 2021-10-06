package iminha.wynnclone.item;

import iminha.wynnclone.WynnItemInfoHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DynamicItem extends Item implements ItemLike {

    public static final String TAG_ATTRIBUTES = "WynnAttributes";

    //Required tool
    public static final String TAG_EFFICIENCY = "efficiency"; //TODO: efficiency for tools

    //Required armor
    public static final String TAG_ARMOR = "armor"; //TODO: armor for armor
    public static final String TAG_TOUGHNESS = "toughness"; //TODO: tougness for armor

    //Required all
    public static final String TAG_RARITY = "rarity";
    public static final String TAG_DURABILITY = "durability"; //TODO: handle own durability
    public static final String TAG_ATTACK_DAMAGE = "damage";
    public static final String TAG_ATTACK_SPEED = "attack_speed";

    //Random Attributes
    public static final String TAG_SPEED = "speed"; //ms TODO: ms speed
    public static final String TAG_POWER = "power"; //Rare: multiplies damage by this number. TODO: power multiplier
    public static final String TAG_UNBREAKING = "unbreaking"; //chance for durability to not reduce TODO: unbreaking modifier

    public DynamicItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        //tooltip.set(0, new TranslatableComponent(getColorFromWynnRarity(((BaseItem)stack.getItem()).getWynnRarity())));
        return WynnItemInfoHelper.getWynnItemName(stack, super.getName(stack));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(WynnItemInfoHelper.getSpeedTooltip(stack));
        tooltip.add(new TranslatableComponent(""));
        tooltip.add(WynnItemInfoHelper.getDamageTooltip(stack));
        tooltip.add(new TranslatableComponent(""));
        tooltip.add(WynnItemInfoHelper.getWynnRarity(stack).getTooltip());
    }
}
