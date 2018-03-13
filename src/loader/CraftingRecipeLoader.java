package loader;

import assets.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CraftingRecipeLoader {

    private static final String FILENAME = "src/res/crafting/list.csv";

    public static void load(CraftingBook book) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                Item targetItem = new Item(ItemType.stringToItem(info[0]));
                ArrayList<Resource> resources = new ArrayList<>();
                for (int i = 1; i<info.length; i+=2) {
                    resources.add(new Resource(new Item(ItemType.stringToItem(info[i])), Integer.parseInt(info[i+1])));
                }
                book.add(new CraftingRecipe(targetItem, resources));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
