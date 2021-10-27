package iminha.wynnclone.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;

public class DynamicPickaxeItem extends DynamicToolItem {

    public DynamicPickaxeItem(Item.Properties properties) {
        super(properties, BlockTags.MINEABLE_WITH_PICKAXE);
    }
}
