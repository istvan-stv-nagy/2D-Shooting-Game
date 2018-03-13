package game;

import entity.enemy.EnemyType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level implements java.io.Serializable{

    private int id;
    private int enemyNumber;
    private int minSpawnTime;
    private int maxSpawnTime;
    private String description;
    private String descriptionWords[];
    private List<EnemyType> enemyTypes;

    public Level(int id) {
        this.id = id;
        loadLevel();
    }

    private void loadLevel() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/res/levels/level" + id));
            enemyNumber = Integer.parseInt(br.readLine());
            minSpawnTime = Integer.parseInt(br.readLine());
            maxSpawnTime = Integer.parseInt(br.readLine());
            String[] types = br.readLine().split(" ");
            enemyTypes = new ArrayList<>();
            for (int i = 0; i < types.length; i++) {
                switch (types[i]) {
                    case "BASIC" : enemyTypes.add(EnemyType.BASIC); break;
                    case "DIVIDER" : enemyTypes.add(EnemyType.DIVIDER); break;
                    case "ZAGGER" : enemyTypes.add(EnemyType.ZAGGER); break;
                    case "SHIELDED" : enemyTypes.add(EnemyType.SHIELDED); break;
                    case "SHOOTER" : enemyTypes.add(EnemyType.SHOOTER); break;
                    case "FOLLOWER" : enemyTypes.add(EnemyType.FOLLOWER); break;
                    default: break;
                }
            }
            String line;
            StringBuilder builder = new StringBuilder();
            builder.append("");
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            this.description = builder.toString();
            this.descriptionWords = description.split(" ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int getEnemyNumber() {
        return enemyNumber;
    }

    public int getMinSpawnTime() {
        return minSpawnTime;
    }

    public int getMaxSpawnTime() {
        return maxSpawnTime;
    }

    public List<EnemyType> getEnemyTypes() {
        return enemyTypes;
    }

    public String[] getDescriptionWords() {
        return descriptionWords;
    }
}
