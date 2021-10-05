package iminha.wynnclone.item;

import iminha.wynnclone.WynnItemInfoHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class WeaponItem extends Item implements ItemLike {

    public static final String TAG_RARITY = "rarity";

    public static final String TAG_ATTACK_DAMAGE = "damage";

    public WeaponItem(Item.Properties properties) {
        //TODO:Wynn tier
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        //tooltip.set(0, new TranslatableComponent(getColorFromWynnRarity(((BaseItem)stack.getItem()).getWynnRarity())));
        return WynnItemInfoHelper.getWynnItemName(stack, super.getName(stack));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(WynnItemInfoHelper.getWynnRarity(stack).getTooltip());
    }
}
