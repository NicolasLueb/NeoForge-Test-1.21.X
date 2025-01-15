package net.nicolas.tkfuturemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.nicolas.tkfuturemod.block.entity.PhotonCollectorBlockEntity;
import net.nicolas.tkfuturemod.util.ModTags;

public class PhotonCollectorBlock extends Block implements EntityBlock {

    public PhotonCollectorBlock() {
        super(BlockBehaviour.Properties.of().strength(2.5f).requiresCorrectToolForDrops());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack stack = itemEntity.getItem();
            if (isValidItem(stack)) {
                itemEntity.discard(); // Remove the item from the world
                System.out.println("Photon collected at: " + pos);

                if (!level.isClientSide()) {
                    PhotonCollectorBlockEntity blockEntity =
                            (PhotonCollectorBlockEntity) level.getBlockEntity(pos);
                    if (blockEntity != null) {
                        blockEntity.addPhoton();
                    }
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }

    private boolean isValidItem(ItemStack item) {
        boolean isValid = item.is(ModTags.Items.ACCEPTED_ITEMS);
        System.out.println("Item is valid: " + isValid);
        return isValid;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PhotonCollectorBlockEntity(pos, state);
    }
}
