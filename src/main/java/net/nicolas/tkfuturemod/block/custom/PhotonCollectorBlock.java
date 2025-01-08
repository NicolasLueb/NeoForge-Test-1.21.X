package net.nicolas.tkfuturemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import net.nicolas.tkfuturemod.item.ModItems;
import net.nicolas.tkfuturemod.util.ModTags;
import net.minecraft.world.entity.Entity;

public class PhotonCollectorBlock extends Block {

    public PhotonCollectorBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.5f)
                .requiresCorrectToolForDrops());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, 20);// Schedule tick every 20 ticks
        System.out.println("First Tick at: " + pos);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof ItemEntity itemEntity) {

        System.out.println("Photon Triggered: " + pos);

                    // Check if the item is a photon item
            if (isValidItem(itemEntity.getItem())) {
                        // Collect the photon (remove it from the world)
                itemEntity.discard();  // Remove the item from the world

                        System.out.println("Photon collected at: " + pos);
                        // Handle photon collection (e.g., increase a stored photon count or convert to energy)
                    }
                };
        super.stepOn(level, pos, state, entity);
    }

    private boolean isValidItem(ItemStack item) {
        boolean isValid = item.is(ModTags.Items.ACCEPTED_ITEMS);
        System.out.println("Item is valid: " + isValid);  // Log result
        return isValid;
    }
}
