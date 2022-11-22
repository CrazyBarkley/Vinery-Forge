package daniking.vinery.block;

import daniking.vinery.registry.DamageSourceRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;


import java.util.List;

public class StackableLogBlock extends SlabBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
    public static final BooleanProperty FIRED = BooleanProperty.create("fired");

    public StackableLogBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(FIRED, false).setValue(WATERLOGGED, false));
    }



    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        final ItemStack stack = player.getItemInHand(hand);
        final SlabType stackSize = state.getValue(TYPE);
        if (stack.is(Items.FLINT_AND_STEEL) && stackSize == SlabType.DOUBLE) {
            world.setBlock(pos, state.setValue(FIRED, true), Block.UPDATE_ALL);
            world.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        } else if (stack.getItem() instanceof ShovelItem && stackSize == SlabType.DOUBLE && state.getValue(FIRED)) {
            world.setBlockAndUpdate(pos, state.setValue(FIRED, false));
            world.playSound(player, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
            final boolean clientSide = world.isClientSide();
            if (clientSide) {
                for (int i = 0; i < 20; ++i) {
                    CampfireBlock.makeParticles(world, pos, false, false);
                }
            }
            return InteractionResult.sidedSuccess(clientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos blockPos = ctx.getClickedPos();
        BlockState blockState = ctx.getLevel().getBlockState(blockPos);
        if (blockState.is(this)) {
            return blockState.setValue(TYPE, SlabType.DOUBLE).setValue(FIRED, false).setValue(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
            BlockState blockState2 = this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
            Direction direction = ctx.getClickedFace();
            return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getClickLocation().y() - (double)blockPos.getY() > 0.5)) ? blockState2 : blockState2.setValue(TYPE, SlabType.TOP);
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FIRED, BlockStateProperties.WATERLOGGED);
    }


        public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if ((state.getValue(FIRED))) {
            if (random.nextInt(10) == 0) {
                world.playLocalSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + random.nextFloat(), random.nextFloat() * 0.7F + 0.6F, false);
                double x = (double) pos.getX() + random.nextDouble();
                double y = (double) pos.getY() + random.nextDouble() * 0.5D + 1.5D;
                double z = (double) pos.getZ() + random.nextDouble();
                world.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, (double)(random.nextFloat() / 2.0F), 5.0E-5, (double)(random.nextFloat() / 2.0F));
                world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4, (double)pos.getZ() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.005, 0.0);
                world.addParticle(ParticleTypes.SMOKE, x, y, z, -0.03 + random.nextDouble() * 0.06, +random.nextDouble() * 0.1, -0.03 + random.nextDouble() * 0.06);
                world.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, -0.03 + random.nextDouble() * 0.06, +random.nextDouble() * 0.1, -0.03 + random.nextDouble() * 0.06);
            }
        }
    }



    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.vinery.log.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("block.vinery.log.tooltip.shift_1"));
            tooltip.add(Component.translatable("block.vinery.log.tooltip.shift_2"));
        } else {
            tooltip.add(Component.translatable("block.vinery.log.tooltip.tooltip_shift"));
        }
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = world.getBlockState(pos).getValue(FIRED);
        if (isLit && !entity.fireImmune() && entity instanceof LivingEntity livingEntity &&
                !EnchantmentHelper.hasFrostWalker(livingEntity)) {
            entity.hurt(DamageSourceRegistry.STOVE_BLOCK, 1.f);
        }

        super.stepOn(world, pos, state, entity);
    }
}
