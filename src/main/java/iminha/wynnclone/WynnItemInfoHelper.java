package iminha.wynnclone;

import iminha.wynnclone.item.WeaponItem;
import iminha.wynnclone.item.WynnRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
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
        int rarityInt;
        if(stack.getTag() == null)
            return WynnRarity.DEPRESSING;
        rarityInt = stack.getTag().getInt(WeaponItem.TAG_RARITY);

        switch(rarityInt) {
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
}
