package iminha.wynnclone.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public enum WynnRarity {
    DEPRESSING(ChatFormatting.GRAY, new TranslatableComponent("wynn.tooltip.rarity.depressing"), 0, 0),
    NORMAL(ChatFormatting.WHITE, new TranslatableComponent("wynn.tooltip.rarity.normal"), 1, 1),
    UNIQUE(ChatFormatting.YELLOW, new TranslatableComponent("wynn.tooltip.rarity.unique"), 2, 2),
    RARE(ChatFormatting.LIGHT_PURPLE, new TranslatableComponent("wynn.tooltip.rarity.rare"), 3,3 ),
    LEGENDARY(ChatFormatting.AQUA, new TranslatableComponent("wynn.tooltip.rarity.legendary"), 4, 4),
    FABLED(ChatFormatting.RED, new TranslatableComponent("wynn.tooltip.rarity.fabled"), 5, 5),
    MYTHIC(ChatFormatting.DARK_PURPLE, new TranslatableComponent("wynn.tooltip.rarity.mythic"), 6, 6),
    SET(ChatFormatting.GREEN, new TranslatableComponent("wynn.tooltip.rarity.set"), -1, 7); //unused

    private final ChatFormatting color;
    private final TranslatableComponent tooltip;
    private final int maxModifiers;
    private final int id;

    WynnRarity(ChatFormatting color, TranslatableComponent tooltip, int maxModifers, int id) {
        this.color = color;
        this.tooltip = tooltip;
        this.maxModifiers = maxModifers;
        this.id = id;
    }

    public ChatFormatting getColor() {
        return this.color;
    }

    public Component getTooltip() {
        MutableComponent coloredTooltip = (new TextComponent("")).append(
                getColor().toString()
                + tooltip.getString()
                + ChatFormatting.RESET);

        return coloredTooltip;
    }
    public int getMaxModifiers() {return maxModifiers;}
    public int getId() {return this.id;}
}
