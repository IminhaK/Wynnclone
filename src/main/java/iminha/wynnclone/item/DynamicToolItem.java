package iminha.wynnclone.item;

import iminha.wynnclone.WynnItemInfoHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DynamicToolItem extends DynamicItem {

    private final Tag<Block> effectiveBlocks;

    public DynamicToolItem(Item.Properties properties, Tag<Block> effectiveBlocks) {
        super(properties);
        this.effectiveBlocks = effectiveBlocks;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return this.effectiveBlocks.contains(state.getBlock()) ? WynnItemInfoHelper.getTagFloatValue(stack, TAG_MINE_SPEED) : 1.0f;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        if(!world.isClientSide() && state.getDestroySpeed(world, pos) != 0.0f) {
            stack.getTag().getCompound(TAG_ATTRIBUTES).putInt(TAG_DURABILITY, WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) - 1);
            if(WynnItemInfoHelper.getTagIntValue(stack, TAG_DURABILITY) <= 0) {
                entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); //Assume tool is being used in main hand
                stack.shrink(1);
            }
        }

        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(effectiveBlocks) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(Tiers.DIAMOND, state); //All tools are diamond tier. TODO:Rare modifier to increase tier
    }
}
