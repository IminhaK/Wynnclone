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
        final Item[] CHOICES = new Item[] {Wynnclone.archerweapon.get(), Wynnclone.assassinweapon.get(), Wynnclone.mageweapon.get(), Wynnclone.shamanweapon.get(), Wynnclone.warriorweapon.get(), Wynnclone.pickaxetool.get()};
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
        int durability = r.nextInt(2001); //TODO:Config min/max durability
        wynnattributes.putInt(DynamicItem.TAG_MAX_DURABILITY, durability);
        wynnattributes.putInt(DynamicItem.TAG_DURABILITY, durability);

        //mining speed
        if(randomLoot.getItem() instanceof DynamicToolItem) {
            float mineSpeed = (float)(r.nextInt((int)(WynnConfig.maxMiningSpeed.get() * 10) - (int)(WynnConfig.minMiningSpeed.get() * 10) + 10) + WynnConfig.minMiningSpeed.get().intValue()) / 10; //random number in range with one decimal place.
            wynnattributes.putFloat(DynamicItem.TAG_MINE_SPEED, mineSpeed);
        }

        //Randomized attributes
        if(rarity > 0)
            for(int i = 0; i < rarity; i++) {
                String rAttr = RANDOM_ATTRIBUTES.get(r.nextInt(RANDOM_ATTRIBUTES.size() - 1));

                switch (rAttr) {
                    case TAG_SPEED -> {
                        if(!wynnattributes.contains(TAG_SPEED))
                            wynnattributes.putInt(TAG_SPEED, 10);
                        else
                            wynnattributes.putInt(TAG_SPEED, wynnattributes.getInt(TAG_SPEED) + 10);
                    }
                    case TAG_POWER -> {
                        if(!wynnattributes.contains(TAG_POWER))
                            wynnattributes.putInt(TAG_POWER, 10);
                        else
                            wynnattributes.putInt(TAG_POWER, wynnattributes.getInt(TAG_POWER) + 10);
                    }
                    case TAG_UNBREAKING -> {
                        if(!wynnattributes.contains(TAG_UNBREAKING))
                            wynnattributes.putInt(TAG_UNBREAKING, 10);
                        else
                            wynnattributes.putInt(TAG_UNBREAKING, wynnattributes.getInt(TAG_UNBREAKING) + 10);
                    }
                    case TAG_TIER_UP -> {
                        //TODO:Only add once, reroll if twice. Tools only
                        Wynnclone.LOG.log(org.apache.logging.log4j.Level.WARN, "Lootbox picked Tier Up. Skipping.");
                    }
                }
            }

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
        if(randomLoot.getItem() instanceof DynamicToolItem) { //Tool
            attackDamage.putDouble("Amount", r.nextInt(WynnConfig.maxDamage.get() - WynnConfig.minDamage.get() + 1) + WynnConfig.minDamage.get() - WynnConfig.toolDamagePentalty.get());
        } else { //Weapon
            attackDamage.putDouble("Amount", r.nextInt(WynnConfig.maxDamage.get() - WynnConfig.minDamage.get() + 1) + WynnConfig.minDamage.get());
        }
        attackDamage.putIntArray("UUID", new int[]{-12195,15089,212810,-30178});
        //Power multiplier
        if(wynnattributes.contains(TAG_POWER)) {
            attackDamage.putDouble("Amount", (double)attackDamage.getInt("Amount") * ((wynnattributes.getInt(TAG_POWER) / 100.0) + 1));
        }

        //Attack Speed
        attackSpeed.putString("AttributeName", "generic.attack_speed");
        //TODO:Slot based on item (weapon, armor etc)
        attackSpeed.putString("Slot", "mainhand");
        attackSpeed.putInt("Operation", 0);
        float speed = SPEEDS[r.nextInt(SPEEDS.length)];
        attackSpeed.putFloat("Amount", speed);
        attackSpeed.putIntArray("UUID", new int[]{-12195,15489,212810,-30978});

        //Movement speed
        if(wynnattributes.contains(TAG_SPEED)) {
            CompoundTag moveSpeed = new CompoundTag();

            moveSpeed.putString("AttributeName", "generic.movement_speed");
            //TODO:Slot based on item (weapon, armor etc)
            moveSpeed.putString("Slot", "mainhand");
            moveSpeed.putInt("Operation", 1);
            moveSpeed.putDouble("Amount", (double)wynnattributes.getInt(TAG_SPEED) / 100); //Base player ms is 0.1 and sprint is 0.15
            moveSpeed.putIntArray("UUID", new int[]{-121929,16126,185211,-32252});
            minecraftAttributes.add(moveSpeed);
        }

        minecraftAttributes.add(attackDamage);
        minecraftAttributes.add(attackSpeed);

        CompoundTag fullTag = new CompoundTag();
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
