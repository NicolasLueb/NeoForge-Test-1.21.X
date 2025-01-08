package net.nicolas.tkfuturemod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.nicolas.tkfuturemod.FutureMod;


public class ModTags {
    public static class Blocks {

        private static TagKey<Block> CreateTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FutureMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ACCEPTED_ITEMS = CreateTag("accepted_items");

        private static TagKey<Item> CreateTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FutureMod.MOD_ID, name));
        }
    }

}
