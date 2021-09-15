package iminha.wynnclone.item;

import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ShamanBaseItem extends WeaponBaseItem {
    public ShamanBaseItem(Item.Properties properties, WynnRarity wynnRarity) {
        super(properties, wynnRarity);
    }

    /*@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        System.out.println("clicked item");

        //Create or get NBT
        CompoundTag nbt;
        ItemStack stack = player.getItemInHand(hand);
        if(stack.hasTag()) {
            nbt = stack.getTag();
        } else {
            nbt = new CompoundTag();
        }

        //Add use
        if(nbt.contains("uses")) {
            nbt.putInt("uses", nbt.getInt("uses") + 1);
        } else {
            nbt.putInt("uses", 1);
        }

        stack.setTag(nbt);
        return super.use(world, player, hand);
    }*/

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = super.getAttributeModifiers(slot, stack);
        if(slot.equals(EquipmentSlot.MAINHAND)) {
            attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier("wynn:attackdamage", 2.0f, AttributeModifier.Operation.ADDITION));
        }

        return attributes;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if(stack.hasTag() && stack.getTag().contains("uses")) {
            tooltip.add(new TranslatableComponent("Uses:" + stack.getTag().getInt("uses")));
        }
    }
}
