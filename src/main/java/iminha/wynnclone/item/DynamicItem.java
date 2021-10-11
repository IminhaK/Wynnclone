package iminha.wynnclone.item;

import iminha.wynnclone.WynnItemInfoHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
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
    public static final String TAG_MAX_DURABILITY = "max_durability";

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

    //test
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getItemInHand(hand).getTag().getCompound(TAG_ATTRIBUTES).putInt(TAG_DURABILITY, player.getItemInHand(hand).getTag().getCompound(TAG_ATTRIBUTES).getInt(TAG_DURABILITY) - 1);

        return super.use(world, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(WynnItemInfoHelper.getSpeedTooltip(stack)); // speed
        tooltip.add(new TranslatableComponent("")); //
        tooltip.add(WynnItemInfoHelper.getDamageTooltip(stack)); //damage
        tooltip.add(new TranslatableComponent("")); //
        tooltip.add(new TranslatableComponent("[" + WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) + "/" + WynnItemInfoHelper.getTagIntValue(stack, TAG_MAX_DURABILITY) + "]")); //durability
        tooltip.add(WynnItemInfoHelper.getWynnRarity(stack).getTooltip()); //rarity
    }
}
