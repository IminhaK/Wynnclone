package iminha.wynnclone;

import net.minecraftforge.common.ForgeConfigSpec;

public class WynnConfig {
    public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec spec;

    public static final ForgeConfigSpec.ConfigValue<Integer> minDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> maxDamage;

    static {
        builder.push("Loot settings");

        minDamage = builder.comment("The minimum damage that a weapon can be given.").define("minDamage", 4);
        maxDamage = builder.comment("The maximum damage that a weapon can be given.").define("maxDamage", 10);

        builder.pop();
        spec = builder.build();
    }
}
