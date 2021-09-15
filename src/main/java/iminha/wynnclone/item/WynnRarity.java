package iminha.wynnclone.item;

import net.minecraft.network.chat.TranslatableComponent;

public enum WynnRarity {
    DEPRESSING("\u00a77", new TranslatableComponent("wynn.tooltip.rarity.depressing"), 0),
    NORMAL("\u00a7f", new TranslatableComponent("wynn.tooltip.rarity.normal"), 1),
    UNIQUE("\u00a7e", new TranslatableComponent("wynn.tooltip.rarity.unique"), 2),
    RARE("\u00a7d", new TranslatableComponent("wynn.tooltip.rarity.rare"), 3),
    LEGENDARY("\u00a7b", new TranslatableComponent("wynn.tooltip.rarity.legendary"), 4),
    FABLED("\u00a7c", new TranslatableComponent("wynn.tooltip.rarity.fabled"), 5),
    MYTHIC("\u00a75", new TranslatableComponent("wynn.tooltip.rarity.mythic"), 6),
    SET("\u00a7a", new TranslatableComponent("wynn.tooltip.rarity.set"), -1);

    private final String color;
    private final TranslatableComponent tooltip;
    private final int maxModifiers;

    WynnRarity(String color, TranslatableComponent tooltip, int maxModifers) {
        this.color = color;
        this.tooltip = tooltip;
        this.maxModifiers = maxModifers;
    }

    public String getColor() {
        return this.color;
    }

    public TranslatableComponent getTooltip() {
        return this.tooltip;
    }
}
