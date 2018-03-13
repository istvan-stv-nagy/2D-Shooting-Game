package game;

import loader.ConfigurationLoader;

public class Profile implements java.io.Serializable {

    private int level;
    private int experience;
    private String texture;
    private boolean maxLevelReached;

    public Profile() {
        this.level = 1;
        this.experience = 0;
        this.texture = "profile";
        maxLevelReached = false;
    }

    public void addExperience(int amount) {
        if (maxLevelReached)
            return;
        experience += amount;
        while (experience >= ConfigurationLoader.getLevels()[level]) {
            level++;
            if (level == ConfigurationLoader.getMaxLevel())
                maxLevelReached = true;
        }

    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getNextLevelExperience() {
        return ConfigurationLoader.getLevels()[level];
    }

    public int getPrevLevelExperience() {
        return ConfigurationLoader.getLevels()[level-1];
    }

    public String getTexture() {
        return texture;
    }
}
