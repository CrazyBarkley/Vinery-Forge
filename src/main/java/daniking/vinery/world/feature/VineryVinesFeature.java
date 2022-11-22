package daniking.vinery.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class VineryVinesFeature extends Feature<SimpleBlockConfiguration> {

    public VineryVinesFeature(Codec<SimpleBlockConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
        WorldGenLevel structureWorldAccess = context.level();
        BlockPos blockPos = context.origin();
        if (!structureWorldAccess.isEmptyBlock(blockPos)) {
            return false;
        }
        SimpleBlockConfiguration cfg = context.config();
        for (Direction direction : Direction.values()) {
            if (direction == Direction.DOWN || !VineBlock.isAcceptableNeighbour(structureWorldAccess, blockPos.relative(direction), direction)) continue;
            structureWorldAccess.setBlock(blockPos, cfg.toPlace().getState(context.random(), blockPos).setValue(VineBlock.getPropertyForFace(direction), true), Block.UPDATE_CLIENTS);
            return true;
        }
        return false;
    }
}
