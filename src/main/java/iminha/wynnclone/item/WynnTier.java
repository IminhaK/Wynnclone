package iminha.wynnclone.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum WynnTier implements Tier {

    WYNN;

    WynnTier() {}

    public int getUses() {
        return 10000;
    }

    public float getSpeed() {
        return 0;
    }

    public float getAttackDamageBonus() {
        return 0;
    }

    public int getLevel() {
        return 0;
    }

    public int getEnchantmentValue() {
        return 0;
    }

    public Ingredient getRepairIngredient() {
        return null;
    }
}
