package loader;

import org.newdawn.slick.opengl.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static helpers.Artist.loadPNG;

public class ImageLoader {

    private static Map<String, Texture> images;

    /*
    public static void init() {
        images = new HashMap<>();
        for (int i=0; i<=3; i++)
            loadImage("background" + i);
        loadImage("copper");
        loadImage("coal");
        loadImage("enemy");
        loadImage("enemy_divider");
        loadImage("enemy_zagger");
        loadImage("enemy_shield");
        loadImage("enemy_shielded");
        loadImage("explosion1");
        loadImage("explosion2");
        loadImage("fire");
        loadImage("iron");
        loadImage("titanium");

        for (int i=1; i<=3; i++)
            loadImage("ship" + i);

        for (int i=0; i<=5; i++)
            loadImage("planet" + i);

    }*/
    public static void init() {
        images = new HashMap<>();
        File folder = new File("src/res/textures");
        File[] files = folder.listFiles();
        for (int i=0; i<files.length; i++) {
            if (files[i].isFile()) {
                loadImage(files[i].getName().replace(".png", ""), "src/res/textures/");
            }
            else if (files[i].isDirectory())
                readDirectory("src/res/textures/" + files[i].getName() + "/");
        }
    }

    private static void readDirectory(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (int i=0; i<files.length; i++) {
            if (files[i].isFile()) {
                loadImage(files[i].getName().replace(".png", ""), path);
            }
        }
    }

    private static void loadImage(String name, String path) {
        images.put(name, loadPNG(name, path));
    }

    public static Texture getImage(String name) {
        return images.get(name);
    }

}
