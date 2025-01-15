package net.nicolas.tkfuturemod.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.nicolas.tkfuturemod.FutureMod;
import net.nicolas.tkfuturemod.block.entity.PhotonCollectorBlockEntity;
import net.nicolas.tkfuturemod.item.ModItems;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final net.neoforged.neoforge.registries.DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            net.neoforged.neoforge.registries.DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FutureMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PhotonCollectorBlockEntity>> PHOTON_COLLECTOR_BLOCK =
            BLOCK_ENTITY_TYPES.register("photon_collector_block",
                    () -> BlockEntityType.Builder.of(PhotonCollectorBlockEntity::new,
                            ModBlocks.PHOTON_COLLECTOR_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}