package loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationLoader {

    private static final String PATH = "src/res/configuration/conf.txt";

    private static int maxLevel;
    private static int[] levels;

    public static void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH));
            maxLevel = Integer.parseInt(br.readLine());
            levels = new int[maxLevel];
            String[] exps = br.readLine().split(" ");
            for (int i = 0; i < maxLevel; i++) {
                levels[i] = Integer.parseInt(exps[i]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getMaxLevel() {
        return maxLevel;
    }

    public static int[] getLevels() {
        return levels;
    }
}
