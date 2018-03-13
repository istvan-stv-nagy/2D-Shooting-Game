package assets;

import java.util.ArrayList;
import java.util.List;

public class CraftingBook {

    private List<CraftingRecipe> recipes;

    /**
     * CraftingBook contains all the crafting recipes in a list
     */
    public CraftingBook() {
        recipes = new ArrayList<>();
    }

    public void add(CraftingRecipe recipe) {
        recipes.add(recipe);
    }

    public CraftingRecipe getRecipe(Item targetItem) {
        for (CraftingRecipe r : recipes) {
            if (r.getTargetItem().equals(targetItem))
                return r;
        }
        return null;
    }

    public List<CraftingRecipe> getRecipes() {
        return recipes;
    }
}
