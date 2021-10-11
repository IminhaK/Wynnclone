package iminha.wynnclone.item;

import iminha.wynnclone.WynnConfig;
import iminha.wynnclone.WynnItemInfoHelper;
import iminha.wynnclone.Wynnclone;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static org.apache.logging.log4j.Level.WARN;

public class LootItem extends DynamicItem {

    public LootItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        //Constants TODO: NBT to determine what type of item drops
        final Item[] CHOICES = new Item[] {Wynnclone.archerweapon.get(), Wynnclone.assassinweapon.get(), Wynnclone.mageweapon.get(), Wynnclone.shamanweapon.get(), Wynnclone.warriorweapon.get()};
        final float[] SPEEDS = {-3, -2, -1, 0, 1};

        if(world.isClientSide)
            return super.use(world, player, hand);
        ItemStack itemStack = player.getItemInHand(hand);
        //if box doesn't have the needed nbt, don't bother.
        if(!itemStack.hasTag()) {
            Wynnclone.LOG.log(WARN, "Lootbox does not have NBT.");
            return super.use(world, player, hand);
        } else if(!itemStack.getTag().contains(DynamicItem.TAG_ATTRIBUTES)) {
            Wynnclone.LOG.log(WARN, "Lootbox does not have " + DynamicItem.TAG_ATTRIBUTES + " tag.");
            return super.use(world, player, hand);
        } else if(!itemStack.getTag().getCompound(DynamicItem.TAG_ATTRIBUTES).contains(DynamicItem.TAG_RARITY)) {
            Wynnclone.LOG.log(WARN, "Lootbox does not have " + DynamicItem.TAG_RARITY + " in " + DynamicItem.TAG_ATTRIBUTES + ".");
            return super.use(world, player, hand);
        }

        ItemStack randomLoot;
        Random r = player.getRandom();
        CompoundTag wynnattributes = new CompoundTag();
        int rarity;

        //Wynn attributes
        //rarity
        randomLoot = new ItemStack(CHOICES[r.nextInt(CHOICES.length)]);
        rarity = WynnItemInfoHelper.getTagIntValue(itemStack, DynamicItem.TAG_RARITY);
        wynnattributes.putInt(DynamicItem.TAG_RARITY, rarity);

        //durability
        int durability = r.nextInt(2001);
        wynnattributes.putInt(DynamicItem.TAG_MAX_DURABILITY, durability);
        wynnattributes.putInt(DynamicItem.TAG_DURABILITY, durability);

        CompoundTag fullTag = new CompoundTag();
        //Standard attributes
        ListTag minecraftAttributes = new ListTag();
        CompoundTag attackDamage = new CompoundTag();
        CompoundTag attackSpeed = new CompoundTag();

        //Attack Damage
        attackDamage.putString("AttributeName", "generic.attack_damage");
        //TODO:Slot based on item (weapon, armor etc)
        attackDamage.putString("Slot", "mainhand");
        attackDamage.putInt("Operation", 0);
        //TODO:Min/max damage based on rarity?
        attackDamage.putInt("Amount", r.nextInt(WynnConfig.maxDamage.get() - WynnConfig.minDamage.get() + 1) + WynnConfig.minDamage.get());
        attackDamage.putIntArray("UUID", new int[]{-12195,15089,212810,-30178});

        //Attack Speed
        attackSpeed.putString("AttributeName", "generic.attack_speed");
        //TODO:Slot based on item (weapon, armor etc)
        attackSpeed.putString("Slot", "mainhand");
        attackSpeed.putInt("Operation", 0);
        float speed = SPEEDS[r.nextInt(SPEEDS.length)];
        attackSpeed.putFloat("Amount", speed);
        attackSpeed.putIntArray("UUID", new int[]{-12195,15489,212810,-30978});

        minecraftAttributes.add(attackDamage);
        minecraftAttributes.add(attackSpeed);
        //Randomized attributes
        if(rarity > 0)
            for(int i = 0; i < rarity; i++) {
                //TODO:Add attributes
            }

        fullTag.put(DynamicItem.TAG_ATTRIBUTES, wynnattributes);
        fullTag.put("AttributeModifiers", minecraftAttributes);
        fullTag.putInt("HideFlags", 2);
        randomLoot.setTag(fullTag);
        player.addItem(randomLoot);

        if(!player.isCreative())
            itemStack.shrink(1);
        return super.use(world, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        //TODO: Display what item type is inside
        tooltip.add(WynnItemInfoHelper.getWynnRarity(stack).getTooltip());
    }
}
