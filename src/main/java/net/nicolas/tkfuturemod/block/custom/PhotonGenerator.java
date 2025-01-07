package net.nicolas.tkfuturemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.nicolas.tkfuturemod.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class PhotonGenerator extends Block {

    public PhotonGenerator() {
        super(BlockBehaviour.Properties.of()
                .strength(3.5f) // Set the block's hardness
                .requiresCorrectToolForDrops() // Requires specific tools to break
                .lightLevel(state -> 10)); // Emits a light level of 10
    }

    @Override
    public void tick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (!level.isClientSide) {
            // Generate photons
            generatePhotons(level, pos);

            // Reschedule the next tick
            level.scheduleTick(pos, this, 20); // Reschedule every 20 ticks (1 second)
        }
    }

    private void generatePhotons(ServerLevel level, BlockPos pos) {
        // Drop the photon item at the block's position
        ItemStack photonItemStack = new ItemStack(ModItems.PHOTON_ITEM.get());
        Block.popResource(level, pos, photonItemStack);

        // Debugging output
        System.out.println("Photon generated at: " + pos);
    }

    @Override
    public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);

        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            // Schedule the first tick when the block is placed
            serverLevel.scheduleTick(pos, this, 20); // Schedule after 1 second
            System.out.println("PhotonGenerator placed at: " + pos);
        }
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos neighborPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, neighborPos, isMoving);

        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            // Reschedule a tick if a neighbor block changes
            serverLevel.scheduleTick(pos, this, 20);
            System.out.println("PhotonGenerator neighbor changed at: " + pos);
        }
    }
}
