package net.nicolas.tkfuturemod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nicolas.tkfuturemod.block.ModBlockEntities;

public class PhotonCollectorBlockEntity extends BlockEntity {
    private int photonCount = 0;

    public PhotonCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PHOTON_COLLECTOR_BLOCK.get(), pos, state);
    }

    public void addPhoton() {
        try {
            this.photonCount++;
            setChanged();
            System.out.println("Photon count updated: " + photonCount);
        } catch (Exception e) {
            System.err.println("Error while adding photon: " + e.getMessage());
        }
    }

    public int getPhotonCount() {
        return this.photonCount;
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        try {
            if (pTag.contains("photonCount")) {
                this.photonCount = pTag.getInt("photonCount");
            }
        } catch (Exception e) {
            System.err.println("Error while loading photon count: " + e.getMessage());
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        try {
            pTag.putInt("photonCount", this.photonCount);
        } catch (Exception e) {
            System.err.println("Error while saving photon count: " + e.getMessage());
        }
    }
}
