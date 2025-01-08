package net.nicolas.tkfuturemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.RandomSource;
import net.nicolas.tkfuturemod.item.ModItems;

public class AtomCoreBlock extends Block {

    private boolean isExcited = false; // Keeps track of whether the atom is excited

    public AtomCoreBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(3.0f)
                .requiresCorrectToolForDrops() // Requires specific tools to break
                .lightLevel(state -> 15));
    }

    public void exciteAtom(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            this.isExcited = true; // The atom is now excited
            System.out.println("Atom excited at: " + pos); // Debugging: Log when the atom is excited
            level.scheduleTick(pos, this, 100);  // Set a delay before relaxation (excited state duration)
            System.out.println("Excited atom scheduled to relax at: " + pos);  // Debugging: Log when the tick is scheduled
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);

        if (this.isExcited) {
            System.out.println("Atom relaxing at: " + pos);  // Debugging: Log when the atom is relaxing
            this.releasePhoton(level, pos);  // Release a photon
            this.isExcited = false;  // Relax the atom (reset the excited state)
            System.out.println("Atom is now relaxed at: " + pos);  // Debugging: Log when the atom is relaxed
        }
    }

    private void releasePhoton(Level level, BlockPos pos) {
        // Calculate the position to drop the photon at the bottom of the block
        BlockPos bottomPos = pos.below(); // Get the block position below (bottom of the block)

        // Create a photon item and drop it in the world at the bottom of the atom core block
        ItemStack photonItem = new ItemStack(ModItems.PHOTON_ITEM.get());
        Block.popResource(level, bottomPos, photonItem);

        // Debugging output
        System.out.println("Photon released at: " + bottomPos);  // Debugging: Log when a photon is released
    }
}
