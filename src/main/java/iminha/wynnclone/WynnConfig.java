package iminha.wynnclone;

import net.minecraftforge.common.ForgeConfigSpec;

public class WynnConfig {
    public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec spec;

    public static final ForgeConfigSpec.ConfigValue<Integer> minDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> maxDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> toolDamagePentalty;
    public static final ForgeConfigSpec.ConfigValue<Float> minMiningSpeed;
    public static final ForgeConfigSpec.ConfigValue<Float> maxMiningSpeed;

    static {
        builder.push("Loot settings");

        minDamage = builder.comment("The minimum damage that a weapon can be given.")
                .define("minDamage", 4);
        maxDamage = builder.comment("The maximum damage that a weapon can be given.")
                .define("maxDamage", 10);
        toolDamagePentalty = builder.comment("Damage penalty for tools. Effectively reduces the min and max damage for tools by this amount.")
                .define("toolPenalty", 3);
        minMiningSpeed = builder.comment("The minimum mining speed a tool can be given. See https://minecraft.fandom.com/wiki/Breaking#Speed for vanilla values.")
                .define("minMineSpeed", 4.0f);
        maxMiningSpeed = builder.comment("The maximum mining speed a tool can be given. See https://minecraft.fandom.com/wiki/Breaking#Speed for vanilla values.")
                .define("maxMineSpeed", 12.0f);;

        builder.pop();
        spec = builder.build();
    }
}
