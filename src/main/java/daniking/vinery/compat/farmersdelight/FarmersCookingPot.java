package daniking.vinery.compat.farmersdelight;

import daniking.vinery.block.entity.CookingPotEntity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

public class FarmersCookingPot {
    public static Recipe<?> getRecipe(Level world, Container inventory){
        return world.getRecipeManager().getRecipeFor((RecipeType<? extends Recipe<Container>>) Registry.RECIPE_TYPE.get(new ResourceLocation("farmersdelight", "cooking")), inventory, world).orElse(null);

    }

    public static boolean canCraft(Recipe<?> recipe, CookingPotEntity entity){
        if(recipe instanceof CookingPotRecipe r){
            if (!entity.getItem(CookingPotEntity.BOTTLE_INPUT_SLOT).is(r.getOutputContainer().getItem())) {
                return false;
            } else if (entity.getItem(CookingPotEntity.OUTPUT_SLOT).isEmpty()) {
                return true;
            } else {
                final ItemStack recipeOutput = r.getResultItem();
                final ItemStack outputSlotStack = entity.getItem(CookingPotEntity.OUTPUT_SLOT);
                final int outputSlotCount = outputSlotStack.getCount();
                if (!outputSlotStack.sameItem(recipeOutput)) {
                    return false;
                } else if (outputSlotCount < entity.getMaxStackSize() && outputSlotCount < outputSlotStack.getMaxStackSize()) {
                    return true;
                } else {
                    return outputSlotCount < recipeOutput.getMaxStackSize();
                }
            }
        }
        return false;
    }

    public static ItemStack getContainer(Recipe<?> recipe){
        if(recipe instanceof CookingPotRecipe c){
            return c.getOutputContainer();
        }
        else return ItemStack.EMPTY;
    }

    public static Class<CookingPotRecipe> getRecipeClass(){
        return CookingPotRecipe.class;
    }
}
