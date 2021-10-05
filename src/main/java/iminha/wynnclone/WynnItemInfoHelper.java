package iminha.wynnclone;

import iminha.wynnclone.item.DynamicItem;
import iminha.wynnclone.item.WynnRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
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
        if(stack.hasTag() && stack.getTag().hasUUID(DynamicItem.TAG_ATTRIBUTES)) {
            if(stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).hasUUID(DynamicItem.TAG_RARITY)) {
                switch (stack.getTag().getInt(DynamicItem.TAG_RARITY)) {
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
        if(stack.hasTag() && stack.getTag().hasUUID(DynamicItem.TAG_ATTRIBUTES)) {
            if(stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).hasUUID(key)) {
                value = stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).getInt(key);
            }
        }

        return value;
    }

    public static float getTagFloatValue(ItemStack stack, String key) {
        float value = 0;
        if(stack.hasTag() && stack.getTag().hasUUID(DynamicItem.TAG_ATTRIBUTES)) {
            if(stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).hasUUID(key)) {
                value = stack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).getFloat(key);
            }
        }

        return value;
    }
}
