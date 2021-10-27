package iminha.wynnclone.item;

import iminha.wynnclone.WynnItemInfoHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DynamicItem extends Item implements ItemLike {

    public static final String TAG_ATTRIBUTES = "WynnAttributes";

    //Required tool
    public static final String TAG_MINE_SPEED = "mine_speed"; //TODO: efficiency for tools

    //Required armor
    public static final String TAG_ARMOR = "armor"; //TODO: armor for armor
    public static final String TAG_TOUGHNESS = "toughness"; //TODO: tougness for armor

    //Required all
    public static final String TAG_RARITY = "rarity";
    public static final String TAG_DURABILITY = "durability";
    public static final String TAG_MAX_DURABILITY = "max_durability";

    //Random Attributes
    public static final String TAG_SPEED = "speed"; //ms TODO: move speed
    public static final String TAG_POWER = "power"; //Rare: multiplies damage by this number. TODO: power multiplier
    public static final String TAG_UNBREAKING = "unbreaking"; //chance for durability to not reduce TODO: unbreaking modifier
    public static final String TAG_TIER_UP = "tier_up"; //increase tool tier to netherite TODO: increase tool tier

    public DynamicItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        //tooltip.set(0, new TranslatableComponent(getColorFromWynnRarity(((BaseItem)stack.getItem()).getWynnRarity())));
        return WynnItemInfoHelper.getWynnItemName(stack, super.getName(stack));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity attacked, LivingEntity attacker) {
        stack.getTag().getCompound(TAG_ATTRIBUTES).putInt(TAG_DURABILITY, WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) - 1);
        if(WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) <= 0) {
            attacker.broadcastBreakEvent(EquipmentSlot.MAINHAND); //Assume tool is being used in main hand
            stack.shrink(1);
        }
        return super.hurtEnemy(stack, attacked, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(WynnItemInfoHelper.getSpeedTooltip(stack)); // speed
        if(stack.getItem() instanceof DynamicToolItem)
            tooltip.add(WynnItemInfoHelper.getMineSpeedTooltip(stack));
        tooltip.add(new TranslatableComponent("")); //
        tooltip.add(WynnItemInfoHelper.getDamageTooltip(stack)); //damage
        tooltip.add(new TranslatableComponent("")); //
        tooltip.add(new TranslatableComponent("[" + WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) + "/" + WynnItemInfoHelper.getTagIntValue(stack, TAG_MAX_DURABILITY) + "]")); //durability
        tooltip.add(WynnItemInfoHelper.getWynnRarity(stack).getTooltip()); //rarity
    }
}
