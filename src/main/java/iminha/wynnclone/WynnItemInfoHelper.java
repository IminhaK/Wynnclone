package iminha.wynnclone;

import iminha.wynnclone.item.DynamicItem;
import iminha.wynnclone.item.WynnRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class WynnItemInfoHelper {


    /*
    NORMAL = 1
    UNIQUE = 2
    RARE = 3
    LEGENDARY = 4
    FABLED = 5
    MYTHIC = 6
    SET = 7 //unused
    DEPRESSING = default/0
     */
    public static WynnRarity getWynnRarity(ItemStack stack) {
        if(stack.hasTag() && stack.getTag().contains(DynamicItem.TAG_ATTRIBUTES)) {
            if(stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).contains(DynamicItem.TAG_RARITY)) {
                switch (stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).getInt(DynamicItem.TAG_RARITY)) {
                    case 1:
                        return WynnRarity.NORMAL;
                    case 2:
                        return WynnRarity.UNIQUE;
                    case 3:
                        return WynnRarity.RARE;
                    case 4:
                        return WynnRarity.LEGENDARY;
                    case 5:
                        return WynnRarity.FABLED;
                    case 6:
                        return WynnRarity.MYTHIC;
                    case 7:
                        return WynnRarity.SET; //unused
                    default:
                        return WynnRarity.DEPRESSING;
                }
            }
        }

        return WynnRarity.DEPRESSING;
    }

    public static TranslatableComponent getSpeedTooltip(ItemStack stack) {
        Component tooltip = new TranslatableComponent("ERROR");
        double speed = 0;
        //TODO:Infer equipment slot from item type
        if(stack.hasTag() && !stack.getAttributeModifiers(EquipmentSlot.MAINHAND).isEmpty()) {
            for(AttributeModifier a : stack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_SPEED))
                speed += a.getAmount();

            //TODO:Switch for different speeds to set tooltip.
            switch((int)speed) {
                case -3:
                    tooltip = new TranslatableComponent("wynn.tooltip.speed.superslow");
                    break;
                case -2:
                    tooltip = new TranslatableComponent("wynn.tooltip.speed.veryslow");
                    break;
                case -1:
                    tooltip = new TranslatableComponent("wynn.tooltip.speed.slow");
                    break;
                case 0:
                    tooltip = new TranslatableComponent("wynn.tooltip.speed.normal");
                    break;
                case 1:
                    tooltip = new TranslatableComponent("wynn.tooltip.speed.fast");
                    break;
                default:
                    tooltip = new TranslatableComponent("ERROR");
            }
        }
        return new TranslatableComponent(ChatFormatting.GRAY.toString() + tooltip.getString() + " " + (new TranslatableComponent("wynn.tooltip.speed.speed")).getString() + ChatFormatting.RESET.toString());
    }

    public static TranslatableComponent getDamageTooltip(ItemStack stack) {
        int damage = -1;

        return new TranslatableComponent(ChatFormatting.GOLD.toString() + (new TranslatableComponent("wynn.tooltip.damage.damage")).getString() + ": " + damage + ChatFormatting.RESET.toString());
    }

    public static Component getWynnItemName(ItemStack stack, Component currentName) {
        //TODO:Add words based on modifiers.
        MutableComponent name = (new TextComponent("")).append( //Same way getDisplayName creates the name
                getWynnRarity(stack).getColor().toString() //color code
                + currentName.getString() //existing (re)named item
                + ChatFormatting.RESET.toString()); //reset color for lore

        if(stack.hasCustomHoverName()) //TODO: Still have color when renamed
            name.withStyle(ChatFormatting.ITALIC);

        return name;
    }

    public static int getTagIntValue(ItemStack stack, String key) {
        int value = 0;
        if(stack.hasTag() && stack.getTag().contains(DynamicItem.TAG_ATTRIBUTES)) {
            if(stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).contains(key)) {
                value = stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).getInt(key);
            }
        }

        return value;
    }

    public static float getTagFloatValue(ItemStack stack, String key) {
        float value = 0;
        if(stack.hasTag() && stack.getTag().contains(DynamicItem.TAG_ATTRIBUTES)) {
            if(stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).contains(key)) {
                value = stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).getFloat(key);
            }
        }

        return value;
    }
}
