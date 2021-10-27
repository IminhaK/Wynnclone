package iminha.wynnclone.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;

public class DynamicPickaxeItem extends DynamicToolItem {

    //TODO: read effieincy and apply it
    public DynamicPickaxeItem(Item.Properties properties) {
        super(properties, BlockTags.MINEABLE_WITH_PICKAXE);
    }
}
