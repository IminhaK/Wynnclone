package iminha.wynnclone.item;

import net.minecraft.network.chat.TranslatableComponent;

public enum WynnRarity {
    DEPRESSING("\u00a77", new TranslatableComponent("wynn.tooltip.rarity.depressing")),
    NORMAL("\u00a7f", new TranslatableComponent("wynn.tooltip.rarity.normal")),
    UNIQUE("\u00a7e", new TranslatableComponent("wynn.tooltip.rarity.unique")),
    RARE("\u00a7d", new TranslatableComponent("wynn.tooltip.rarity.rare")),
    LEGENDARY("\u00a7b", new TranslatableComponent("wynn.tooltip.rarity.legendary")),
    FABLED("\u00a7c", new TranslatableComponent("wynn.tooltip.rarity.fabled")),
    MYTHIC("\u00a75", new TranslatableComponent("wynn.tooltip.rarity.mythic")),
    SET("\u00a7a", new TranslatableComponent("wynn.tooltip.rarity.set"));

    private final String color;
    private final TranslatableComponent tooltip;

    private WynnRarity(String color, TranslatableComponent tooltip) {
        this.color = color;
        this.tooltip = tooltip;
    }

    public String getColor() {
        return this.color;
    }

    public TranslatableComponent getTooltip() {
        return this.tooltip;
    }
}
