package iminha.wynnclone.item;

import iminha.wynnclone.WynnItemInfoHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DynamicItem extends Item implements ItemLike {

    public static final String TAG_ATTRIBUTES = "WynnAttributes";

    //Required tool
    public static final String TAG_MINE_SPEED = "mine_speed";

    //Required armor
    public static final String TAG_ARMOR = "armor"; //TODO: armor for armor
    public static final String TAG_TOUGHNESS = "toughness"; //TODO: tougness for armor

    //Required mainhands
    public static final String TAG_BASE_DAMAGE = "base_damage";

    //Required all
    public static final String TAG_RARITY = "rarity";
    public static final String TAG_DURABILITY = "durability";
    public static final String TAG_MAX_DURABILITY = "max_durability";

    //Random Attributes
    public static final String TAG_SPEED = "speed"; //ms TODO: apply move speed
    public static final String TAG_POWER = "power"; //Rare: multiplies damage by this number. TODO: apply power multiplier
    public static final String TAG_UNBREAKING = "unbreaking"; //chance for durability to not reduce
    public static final String TAG_TIER_UP = "tier_up"; //increase tool tier to netherite //TODO: increase tool tier (probably in tool class)
    public static final List<String> RANDOM_ATTRIBUTES = new ArrayList<String>() {{
        add(TAG_SPEED);
        add(TAG_POWER);
        add(TAG_UNBREAKING);
        add(TAG_TIER_UP);
    }};

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
        damageItem(stack, 1, attacker, a -> {});
        return super.hurtEnemy(stack, attacked, attacker);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if(WynnItemInfoHelper.getTagIntValue(stack, TAG_UNBREAKING) > 0 && entity.getRandom().nextInt(100) > WynnItemInfoHelper.getTagIntValue(stack, TAG_UNBREAKING)) {
            stack.getTag().getCompound(TAG_ATTRIBUTES).putInt(TAG_DURABILITY, WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) - amount);
        }
        if(WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) <= 0) {
            entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); //Assume tool is being used in main hand
            stack.shrink(1);
        }
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(WynnItemInfoHelper.getSpeedTooltip(stack)); // speed
        if(stack.getItem() instanceof DynamicToolItem)
            tooltip.add(WynnItemInfoHelper.getMineSpeedTooltip(stack));
        tooltip.add(new TranslatableComponent("")); //
        tooltip.add(WynnItemInfoHelper.getDamageTooltip(stack)); //damage
        tooltip.add(new TranslatableComponent("")); //
        if(WynnItemInfoHelper.getTagIntValue(stack, TAG_SPEED) > 0) { //Move speed
            tooltip.add(new TranslatableComponent(ChatFormatting.GREEN.toString() + "+" + WynnItemInfoHelper.getTagIntValue(stack, TAG_SPEED) + "% " + ChatFormatting.GRAY.toString() + (new TranslatableComponent("wynn.tooltip.ms")).getString() + ChatFormatting.RESET.toString()));
            tooltip.add(new TranslatableComponent("")); //
        }
        if(WynnItemInfoHelper.getTagIntValue(stack, TAG_POWER) > 0) { //Power
            tooltip.add(new TranslatableComponent(ChatFormatting.GREEN.toString() + "+" + WynnItemInfoHelper.getTagIntValue(stack, TAG_POWER) + "% " + ChatFormatting.GRAY.toString() + (new TranslatableComponent("wynn.tooltip.power")).getString() + ChatFormatting.RESET.toString()));
            tooltip.add(new TranslatableComponent("")); //
        }
        if(WynnItemInfoHelper.getTagIntValue(stack, TAG_UNBREAKING) > 0) { //Unbreaking
            tooltip.add(new TranslatableComponent(ChatFormatting.GREEN.toString() + "+" + WynnItemInfoHelper.getTagIntValue(stack, TAG_UNBREAKING) + "% " + ChatFormatting.GRAY.toString() + (new TranslatableComponent("wynn.tooltip.unbreaking")).getString() + ChatFormatting.RESET.toString()));
            tooltip.add(new TranslatableComponent("")); //
        }
        if(WynnItemInfoHelper.getTagIntValue(stack, TAG_TIER_UP) > 0) { //Mining Tier
            tooltip.add(new TranslatableComponent(ChatFormatting.GREEN.toString() + "+" + WynnItemInfoHelper.getTagIntValue(stack, TAG_TIER_UP) + "% " + ChatFormatting.GRAY.toString() + (new TranslatableComponent("wynn.tooltip.tierup")).getString() + ChatFormatting.RESET.toString()));
            tooltip.add(new TranslatableComponent("")); //
        }
        tooltip.add(new TranslatableComponent("[" + WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) + "/" + WynnItemInfoHelper.getTagIntValue(stack, TAG_MAX_DURABILITY) + "]")); //durability
        tooltip.add(WynnItemInfoHelper.getWynnRarity(stack).getTooltip()); //rarity
    }
}
