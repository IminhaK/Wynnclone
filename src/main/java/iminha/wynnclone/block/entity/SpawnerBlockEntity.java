package iminha.wynnclone.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SpawnerBlockEntity extends BlockEntity {

    public SpawnerBlockEntity(BlockEntityType<SpawnerBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
