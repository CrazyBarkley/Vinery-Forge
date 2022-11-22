package daniking.vinery.block.entity;

import daniking.vinery.block.WineBoxBlock;
import daniking.vinery.block.WineRackBlock;
import daniking.vinery.registry.VineryBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GeckoStorageBlockEntity extends BlockEntity implements Container, IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private NonNullList<ItemStack> inventory;

    public GeckoStorageBlockEntity(BlockPos pos, BlockState state) {
        super(VineryBlockEntityTypes.WINE_RACK_GECKO_ENTITY, pos, state);
        this.inventory = NonNullList.withSize(18, ItemStack.EMPTY);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
    }

    @Override
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return this.getInvStackList().stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.getInvStackList().get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack itemStack = ContainerHelper.removeItem(this.getInvStackList(), slot, amount);
        if (!itemStack.isEmpty()) {
            this.setChanged();
        }

        return itemStack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.getInvStackList(), slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.getInvStackList().set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.5, (double)this.worldPosition.getZ() + 0.5) > 64.0);
        }
    }

    @Override
    public void clearContent() {
        this.getInvStackList().clear();
    }

    public NonNullList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    public ItemStack getFirstNonEmptyStack() {
        for (ItemStack itemStack : this.getInvStackList()) {
            if (!itemStack.isEmpty()) {
                return itemStack;
            }
        }

        return ItemStack.EMPTY;
    }

    public void removeFirstNonEmptyStack() {
        for (int i = 0; i < this.getInvStackList().size(); ++i) {
            ItemStack itemStack = this.getInvStackList().get(i);
            if (!itemStack.isEmpty()) {
                this.getInvStackList().set(i, ItemStack.EMPTY);
                return;
            }
        }
    }


    @Override
    public void registerControllers(AnimationData data) {
        // NO-ANIMATION
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public String getModelName() {
        if (this.getBlockState().getBlock() instanceof WineBoxBlock) {
            return this.getBlockState().getValue(WineBoxBlock.OPEN) ? "wine_box_filled_open" : "wine_box_filled_closed";
        }
        return "wine_rack_" + ((WineRackBlock) this.getBlockState().getBlock()).getModelPostFix();
    }

    public int getNonEmptySlotCount() {
        int count = 0;
        for (ItemStack itemStack : this.getInvStackList()) {
            if (!itemStack.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public void addItemStack(ItemStack stack) {
        for (int i = 0; i < this.getInvStackList().size(); ++i) {
            ItemStack itemStack = this.getInvStackList().get(i);
            if (itemStack.isEmpty()) {
                this.getInvStackList().set(i, stack);
                return;
            }
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }
}