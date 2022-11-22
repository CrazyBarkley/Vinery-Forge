package daniking.vinery.block;

import daniking.vinery.block.entity.GeckoStorageBlockEntity;
import daniking.vinery.util.VineryUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DisplayRackBlock extends WineRackBlock {

    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0, 0.25, 0.5, 1, 0.3125, 1), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.125, 0.1875, 0.625, 0.1875, 0.25, 0.9375), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.8125, 0.1875, 0.625, 0.875, 0.25, 0.9375), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.125, 0.0625, 0.9375, 0.1875, 0.25, 1), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.8125, 0.0625, 0.9375, 0.875, 0.25, 1), BooleanOp.OR);

        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            map.put(direction, VineryUtils.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    public DisplayRackBlock(Properties settings) {
        super(settings, 2, 4);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide)
            return InteractionResult.SUCCESS;
        final ItemStack stack = player.getItemInHand(hand);
        GeckoStorageBlockEntity blockEntity = (GeckoStorageBlockEntity) world.getBlockEntity(pos);
        if (blockEntity != null) {
            if (!stack.isEmpty()) {
                if (blockEntity.getNonEmptySlotCount() < maxStorage) {
                    blockEntity.addItemStack(new ItemStack((stack.getItem())));
                    stack.shrink(1);
                    player.setItemInHand(hand, stack);
                    ((ServerPlayer) player).connection.send(blockEntity.getUpdatePacket());
                    return InteractionResult.SUCCESS;
                }
            } else if (player.isShiftKeyDown() && blockEntity.getNonEmptySlotCount() > 0) {
                player.setItemInHand(hand, blockEntity.getFirstNonEmptyStack().copy());
                blockEntity.removeFirstNonEmptyStack();
                ((ServerPlayer) player).connection.send(blockEntity.getUpdatePacket());
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.vinery.canbeplaced.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }
}