package daniking.vinery.block;

import daniking.vinery.block.entity.GeckoStorageBlockEntity;
import daniking.vinery.item.DrinkBlockItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WineRackBlock extends BaseEntityBlock {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	protected final int maxStorage;
	private final int modelPostFix;

	public WineRackBlock(Properties settings, int maxStorage, int modelPostFix) {
		super(settings);
		this.maxStorage = maxStorage;
		this.modelPostFix = modelPostFix;
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (world.isClientSide)
			return InteractionResult.SUCCESS;
		final ItemStack stack = player.getItemInHand(hand);
		GeckoStorageBlockEntity blockEntity = (GeckoStorageBlockEntity) world.getBlockEntity(pos);
		if (blockEntity != null) {
			if (stack.getItem() instanceof DrinkBlockItem) {
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

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> list = new ArrayList<>();
		list.add(new ItemStack(this.asItem()));
		GeckoStorageBlockEntity blockEntity = (GeckoStorageBlockEntity) builder.getParameter(LootContextParams.BLOCK_ENTITY);
		if (blockEntity != null) {
			list.addAll(blockEntity.getInvStackList());
		}
		return list;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new GeckoStorageBlockEntity(pos, state);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	public int getModelPostFix() {
		return modelPostFix;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
		tooltip.add(Component.translatable("block.vinery.winerack.tooltip.shift_1").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
			if (Screen.hasShiftDown()) {
		tooltip.add(Component.translatable("block.vinery.winerack.tooltip.shift_1"));
	} else {
		tooltip.add(Component.translatable("block.vinery.breadblock.tooltip.tooltip_shift"));
		}
	}
}