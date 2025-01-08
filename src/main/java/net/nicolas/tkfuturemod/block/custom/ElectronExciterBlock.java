package net.nicolas.tkfuturemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class ElectronExciterBlock extends Block {

    public ElectronExciterBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.0f)
                .requiresCorrectToolForDrops());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);

        // Debugging: Log when the block is placed and when the first tick is scheduled
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            System.out.println("Electron Exciter placed at: " + pos);
            serverLevel.scheduleTick(pos, this, 20); // Schedule the tick to occur in 1 second
            System.out.println("First tick scheduled for Electron Exciter at: " + pos);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);

        // Debugging: Log every time the block ticks
        System.out.println("Electron Exciter ticking at: " + pos);

        // Send energy to the Atom Core to excite it
        BlockPos atomCorePos = pos.below(); // Assuming the Atom Core is below the Exciter Block
        BlockState atomCoreState = level.getBlockState(atomCorePos);

        if (atomCoreState.getBlock() instanceof AtomCoreBlock) {
            System.out.println("Exciting Atom Core at: " + atomCorePos); // Log when Atom Core is excited
            ((AtomCoreBlock) atomCoreState.getBlock()).exciteAtom(level, atomCorePos);
        }

        // Reschedule the tick after a delay
        level.scheduleTick(pos, this, 20); // Schedule the next tick
    }
}
