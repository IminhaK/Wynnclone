package iminha.wynnclone.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import iminha.wynnclone.WynnItemInfoHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;

public class DynamicItem extends Item implements ItemLike {

    public static final String TAG_ATTRIBUTES = "WynnAttributes";

    //Required tool
    public static final String TAG_EFFICIENCY = "efficiency";

    //Required armor
    public static final String TAG_ARMOR = "armor";
    public static final String TAG_TOUGHNESS = "toughness";

    //Required all
    public static final String TAG_RARITY = "rarity";
    public static final String TAG_DURABILITY = "durability"; //handle own durability
    public static final String TAG_ATTACK_DAMAGE = "damage";
    public static final String TAG_ATTACK_SPEED = "attack_speed";

    //Random Attributes
    public static final String TAG_SPEED = "speed"; //ms
    public static final String TAG_POWER = "power"; //Rare: multiplies damage by this number.
    public static final String TAG_UNBREAKING = "unbreaking"; //chance for durability to not reduce

    public DynamicItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        //TODO:Use a capability instead of directly modifying attributes
        Multimap<Attribute, AttributeModifier> multimap = grabStackModifiers(slot, stack, super.getAttributeModifiers(slot, stack), "Weapon modifier");
        return multimap;
    }

    public static Multimap<Attribute, AttributeModifier> grabStackModifiers(EquipmentSlot slot, ItemStack stack, Multimap<Attribute, AttributeModifier> multimap, String key) {
        return grabStackModifiers(slot, EquipmentSlot.MAINHAND, stack, multimap, key);
    }

    public static Multimap<Attribute, AttributeModifier> grabStackModifiers(EquipmentSlot slot, EquipmentSlot effectiveSlot, ItemStack stack, Multimap<Attribute, AttributeModifier> initial, String key) {
        Multimap<Attribute, AttributeModifier> modifiers = initial;

        if(slot == effectiveSlot) {
            int rarity = WynnItemInfoHelper.getTagIntValue(stack, TAG_RARITY);
            int durability = WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY);
            int attackDamage = WynnItemInfoHelper.getTagIntValue(stack, TAG_ATTACK_DAMAGE);
            float attackSpeed = WynnItemInfoHelper.getTagFloatValue(stack, TAG_ATTACK_SPEED);

            applyAttribute(modifiers, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID, key, attackDamage);
            applyAttribute(modifiers, Attributes.ATTACK_SPEED, BASE_ATTACK_SPEED_UUID, key, attackSpeed);
        }

        return modifiers;
    }

    private static void applyAttribute(Multimap<Attribute, AttributeModifier> modifiers, Attribute attribute, UUID uuid, String modifierKey, double value) {
        double attributeValue = 0;

        Collection<AttributeModifier> currentModifiers = new ArrayList<>(modifiers.get(attribute));

        for(AttributeModifier m : currentModifiers) {
            attributeValue += m.getAmount();
        }

        //TODO:Add new attribute to modifers
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
        modifiers.entries().stream()
                .filter(entry -> !entry.getKey().equals(attribute))
                .collect(Collector.of(ImmutableMultimap.Builder::new, ImmutableMultimap.Builder::put,
                        (L, R) -> {
                    L.putAll(R.build());
                    return L;
                        }));

        builder.put(attribute, new AttributeModifier(uuid, modifierKey, value + attributeValue, AttributeModifier.Operation.ADDITION));
        modifiers = builder.build();
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
