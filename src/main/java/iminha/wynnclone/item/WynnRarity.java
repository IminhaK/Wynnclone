package iminha.wynnclone.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public enum WynnRarity {
    DEPRESSING(ChatFormatting.GRAY, "wynn.tooltip.rarity.depressing", 0, 0),
    NORMAL(ChatFormatting.WHITE, "wynn.tooltip.rarity.normal", 1, 1),
    UNIQUE(ChatFormatting.YELLOW, "wynn.tooltip.rarity.unique", 2, 2),
    RARE(ChatFormatting.LIGHT_PURPLE, "wynn.tooltip.rarity.rare", 3,3 ),
    LEGENDARY(ChatFormatting.AQUA, "wynn.tooltip.rarity.legendary", 4, 4),
    FABLED(ChatFormatting.RED, "wynn.tooltip.rarity.fabled", 5, 5),
    MYTHIC(ChatFormatting.DARK_PURPLE, "wynn.tooltip.rarity.mythic", 6, 6),
    SET(ChatFormatting.GREEN, "wynn.tooltip.rarity.set", -1, 7); //unused

    private final ChatFormatting color;
    private final String tooltip;
    private final int maxModifiers;
    private final int id;

    WynnRarity(ChatFormatting color, String tooltip, int maxModifers, int id) {
        this.color = color;
        this.tooltip = tooltip;
        this.maxModifiers = maxModifers;
        this.id = id;
    }

    public ChatFormatting getColor() {
        return this.color;
    }

    public Component getTooltip() {
        Component translatedTooltip = new TranslatableComponent(tooltip);
        Component translatedItem = new TranslatableComponent("wynn.tooltip.item");
        return (new TextComponent("")).append(
                getColor().toString()
                + translatedTooltip.getString()
                + " "
                + translatedItem.getString()
                + ChatFormatting.RESET);
    }
    public int getMaxModifiers() {return maxModifiers;}
    public int getId() {return this.id;}
}
