package daniking.vinery.block.entity;

import daniking.vinery.Vinery;
import daniking.vinery.block.CookingPotBlock;
import daniking.vinery.client.gui.handler.CookingPotGuiHandler;
import daniking.vinery.recipe.CookingPotRecipe;
import daniking.vinery.registry.VineryBlockEntityTypes;
import daniking.vinery.registry.VineryRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

public class CookingPotEntity extends BlockEntity implements BlockEntityTicker<CookingPotEntity>, Container, MenuProvider {
	
	private NonNullList<ItemStack> inventory;
	private static final int MAX_CAPACITY = 8;
	public static final int MAX_COOKING_TIME = 600; // Time in ticks (30s)
	private int cookingTime = MAX_COOKING_TIME;
	private static final int BOTTLE_INPUT_SLOT = 6;
	private static final int OUTPUT_SLOT = 7;
	private static final int INGREDIENTS_AREA = 2 * 3;
	
	private boolean isBeingBurned = false;
	private int totalCookingTime;
	
	private final ContainerData delegate;
	
	public CookingPotEntity(BlockPos pos, BlockState state) {
		super(VineryBlockEntityTypes.COOKING_POT_BLOCK_ENTITY.get(), pos, state);
		this.inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
		this.delegate = new ContainerData() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> CookingPotEntity.this.cookingTime;
					case 1 -> CookingPotEntity.this.totalCookingTime;
					case 2 -> CookingPotEntity.this.isBeingBurned ? 1 : 0;
					default -> 0;
				};
			}
			
			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> CookingPotEntity.this.cookingTime = value;
					case 1 -> CookingPotEntity.this.totalCookingTime = value;
					case 2 -> CookingPotEntity.this.isBeingBurned = value != 0;
				}
			}
			
			@Override
			public int getCount() {
				return 3;
			}
		};
	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.inventory);
		nbt.putInt("CookingTime", this.cookingTime);
		nbt.putBoolean("isBeingBurned", this.isBeingBurned);
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		this.cookingTime = nbt.getInt("CookingTime");
		this.isBeingBurned = nbt.getBoolean("isBeingBurned");
		ContainerHelper.saveAllItems(nbt, this.inventory);
	}
	
	public boolean isBeingBurned() {
		if (getLevel() == null)
			throw new NullPointerException("Null world invoked");
		final BlockState belowState = this.getLevel().getBlockState(getBlockPos().below());
		final var optionalList = Registry.BLOCK.getTag(Vinery.ALLOWS_COOKING_ON_POT);
		final var entryList = optionalList.orElse(null);
		if (entryList == null) {
			return false;
		} else if (!entryList.contains(belowState.getBlock().builtInRegistryHolder())) {
			return false;
		} else
			return belowState.getValue(BlockStateProperties.LIT);
	}
	
	private boolean canCraft(CookingPotRecipe recipe) {
		if (recipe == null || recipe.getResultItem().isEmpty()) {
			return false;
		} else if (!this.getItem(BOTTLE_INPUT_SLOT).is(recipe.getContainer().getItem())) {
			return false;
		} else if (this.getItem(OUTPUT_SLOT).isEmpty()) {
			return true;
		} else {
			final ItemStack recipeOutput = recipe.getResultItem();
			final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
			final int outputSlotCount = outputSlotStack.getCount();
			if (!outputSlotStack.sameItem(recipeOutput)) {
				return false;
			} else if (outputSlotCount < this.getMaxStackSize() && outputSlotCount < outputSlotStack.getMaxStackSize()) {
				return true;
			} else {
				return outputSlotCount < recipeOutput.getMaxStackSize();
			}
		}
	}
	
	private void craft(CookingPotRecipe recipe) {
		if (!canCraft(recipe)) {
			return;
		}
		final ItemStack recipeOutput = recipe.getResultItem();
		final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
		if (outputSlotStack.isEmpty()) {
			setItem(OUTPUT_SLOT, recipeOutput.copy());
		} else if (outputSlotStack.is(recipeOutput.getItem())) {
			outputSlotStack.grow(recipeOutput.getCount());
		}
		final NonNullList<Ingredient> ingredients = recipe.getIngredients();
		// each slot can only be used once because in canMake we only checked if decrement by 1 still retains the recipe
		// otherwise recipes can break when an ingredient is used multiple times
		boolean[] slotUsed = new boolean[INGREDIENTS_AREA];
		for (int i = 0; i < recipe.getIngredients().size(); i++) {
			Ingredient ingredient = ingredients.get(i);
			// Looks for the best slot to take it from
			final ItemStack bestSlot = this.getItem(i);
			if (ingredient.test(bestSlot) && !slotUsed[i]) {
				slotUsed[i] = true;
				bestSlot.shrink(1);
			} else {
				// check all slots in search of the ingredient
				for (int j = 0; j < INGREDIENTS_AREA; j++) {
					ItemStack stack = this.getItem(j);
					if (ingredient.test(stack) && !slotUsed[j]) {
						slotUsed[j] = true;
						stack.shrink(1);
					}
				}
			}
		}
		this.getItem(BOTTLE_INPUT_SLOT).shrink(1);
	}

	@Override
	public void tick(Level world, BlockPos pos, BlockState state, CookingPotEntity blockEntity) {
		if (world.isClientSide()) {
			return;
		}
		this.isBeingBurned = isBeingBurned();
		if (!this.isBeingBurned)
			return;
		boolean dirty = false;
		final var recipe = world.getRecipeManager().getRecipeFor(VineryRecipeTypes.COOKING_POT_RECIPE_TYPE.get(), this, world).orElse(null);
		boolean canCraft = canCraft(recipe);
		if (canCraft) {
			++this.cookingTime;
			if (this.cookingTime == this.totalCookingTime) {
				this.cookingTime = 0;
				craft(recipe);
				dirty = true;
			}
		} else if (!canCraft(recipe)) {
			this.cookingTime = 0;
		}
		if (canCraft) {
			world.setBlock(pos, this.getBlockState().getBlock().defaultBlockState().setValue(CookingPotBlock.HAS_CHERRIES_INSIDE, true), Block.UPDATE_ALL);
			dirty = true;
		} else if (state.getValue(CookingPotBlock.HAS_CHERRIES_INSIDE)) {
			world.setBlock(pos, this.getBlockState().getBlock().defaultBlockState().setValue(CookingPotBlock.HAS_CHERRIES_INSIDE, false), Block.UPDATE_ALL);
			dirty = true;
		}
		if (dirty)
			setChanged();
	}
	
	@Override
	public int getContainerSize() {
		return inventory.size();
	}
	
	@Override
	public boolean isEmpty() {
		return inventory.stream().allMatch(ItemStack::isEmpty);
	}
	
	@Override
	public ItemStack getItem(int slot) {
		return this.inventory.get(slot);
	}
	
	@Override
	public ItemStack removeItem(int slot, int amount) {
		return ContainerHelper.removeItem(this.inventory, slot, amount);
	}
	
	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.inventory, slot);
	}
	
	@Override
	public void setItem(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
		if (totalCookingTime == 0) {
			this.totalCookingTime = MAX_COOKING_TIME;
		}
		this.setChanged();
	}


	@Override
	public boolean stillValid(Player player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return player.distanceToSqr((double) this.worldPosition.getX() + 0.5, (double) this.worldPosition.getY() + 0.5, (double) this.worldPosition.getZ() + 0.5) <= 64.0;
		}
	}

	@Override
	public void clearContent() {
		inventory.clear();
	}
	
	@Override
	public Component getDisplayName() {
		return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
	}
	
	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
		return new CookingPotGuiHandler(syncId, inv, this, this.delegate);
	}
}


