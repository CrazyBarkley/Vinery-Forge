package daniking.vinery.registry;

import daniking.vinery.Vinery;
import daniking.vinery.recipe.CookingPotRecipe;
import daniking.vinery.recipe.FermentationBarrelRecipe;
import daniking.vinery.recipe.WoodFiredOvenRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VineryRecipeTypes {


    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Vinery.MODID);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vinery.MODID);

    public static final RecipeType<WoodFiredOvenRecipe> WOOD_FIRED_OVEN_RECIPE_TYPE = create("wood_fired_oven_cooking");
    public static final RecipeSerializer<WoodFiredOvenRecipe> WOOD_FIRED_OVEN_RECIPE_SERIALIZER = create("wood_fired_oven_cooking", new WoodFiredOvenRecipe.Serializer());
    public static final RecipeType<FermentationBarrelRecipe> FERMENTATION_BARREL_RECIPE_TYPE = create("wine_fermentation");
    public static final RecipeSerializer<FermentationBarrelRecipe> FERMENTATION_BARREL_RECIPE_SERIALIZER = create("wine_fermentation", new FermentationBarrelRecipe.Serializer());
    public static final RecipeType<CookingPotRecipe> COOKING_POT_RECIPE_TYPE = create("pot_cooking");
    public static final RecipeSerializer<CookingPotRecipe> COOKING_POT_RECIPE_SERIALIZER = create("pot_cooking", new CookingPotRecipe.Serializer());

    private static <T extends Recipe<?>> RecipeSerializer<T> create(String name, RecipeSerializer<T> serializer) {
        RECIPE_SERIALIZERS.register(name, () -> serializer);
        return serializer;
    }

    private static <T extends Recipe<?>> RecipeType<T> create(String name) {
        final RecipeType<T> type = new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        RECIPE_TYPES.register(name, () -> type);
        return type;
    }

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
    }


}
