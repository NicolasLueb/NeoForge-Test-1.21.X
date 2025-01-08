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

public class PhotonCollectorBlock extends Block {

    public PhotonCollectorBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.5f)
                .requiresCorrectToolForDrops());
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);

        // Debugging: Log when the tick occurs
        System.out.println("Photon Collector ticked at: " + pos);

        // Look for ItemEntities in a 3x3 area above the collector
        AABB collectionArea = new AABB(pos.getX() - 1, pos.getY() + 1, pos.getZ() - 1, pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1);

        // Get ItemEntities in the bounding box
        level.getEntitiesOfClass(ItemEntity.class, collectionArea, entity -> true)
                .forEach(itemEntity -> {
                    // Debugging: Log each item being checked
                    System.out.println("Checking item: " + itemEntity.getItem().getItem());

                    // Check if the item is a photon item
                    if (itemEntity.getItem().getItem() == ModItems.PHOTON_ITEM.get()) {
                        // Collect the photon (remove it from the world)
                        itemEntity.discard();  // Remove the item from the world

                        // Debugging: Log when a photon is collected
                        System.out.println("Photon collected at: " + pos);
                        // Handle photon collection (e.g., increase a stored photon count or convert to energy)
                    }
                });
    }
}
