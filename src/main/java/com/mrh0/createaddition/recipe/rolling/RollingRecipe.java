package com.mrh0.createaddition.recipe.rolling;

import com.mrh0.createaddition.CreateAddition;
import com.mrh0.createaddition.recipe.CARecipeSerializer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class RollingRecipe implements IRecipe<RecipeWrapper> {

	protected final ItemStack output;
	protected final ResourceLocation id;
	protected final Ingredient ingredient;
	
	public static IRecipeType<RollingRecipe> TYPE = new RollingRecipeType();
	public static IRecipeSerializer<?> SERIALIZER = Registry.RECIPE_SERIALIZER.getOrDefault(new ResourceLocation(CreateAddition.MODID, "rolling"));

	protected RollingRecipe(Ingredient ingredient, ItemStack output, ResourceLocation id) {
		this.output = output;
		this.id = id;
		this.ingredient = ingredient;
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) {
		if (inv.isEmpty())
			return false;
		return ingredient.test(inv.getStackInSlot(0));
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		return this.output;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height > 0;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		
		return SERIALIZER;
	}

	@Override
	public IRecipeType<?> getType() {
		return TYPE;
	}
	
	@Override
	public ItemStack getIcon() {
		return this.output;
	}
	
	@Override
	public boolean isDynamic() {
		return true;
	}
	
	public static void register() {};
}
