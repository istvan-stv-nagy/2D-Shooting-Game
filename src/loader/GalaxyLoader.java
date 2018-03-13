package loader;

import assets.ItemType;
import entity.map.Galaxy;
import entity.map.Planet;
import game.Level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GalaxyLoader {

    public static void load(Galaxy g) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/res/galaxies/galaxy" + g.getId()));
            int planetNumber = Integer.parseInt(br.readLine());
            Planet[] planets = new Planet[planetNumber];
            for (int i = 0; i < planetNumber; i++) {
                String[] planetDescription = br.readLine().split(" ");
                int level = Integer.parseInt(planetDescription[0]);
                int x = Integer.parseInt(planetDescription[1]);
                int y = Integer.parseInt(planetDescription[2]);
                int width = Integer.parseInt(planetDescription[3]);
                int height = Integer.parseInt(planetDescription[4]);
                String textureName = planetDescription[5];
                String name = planetDescription[6];
                int dropProbability = Integer.parseInt(planetDescription[7]);
                Planet p = new Planet(new Level(level), x, y, width, height, textureName, name, dropProbability);
                int prevLowerBound = 0;
                if (dropProbability > 0) {
                    for (int j = 8; j < planetDescription.length; j += 2) {
                        int value = Integer.parseInt(planetDescription[j + 1]);
                        p.add(ItemType.stringToItem(planetDescription[j]), prevLowerBound + 1, prevLowerBound + value);
                        prevLowerBound += value;
                    }
                }
                g.add(p);
                planets[i] = p;
            }
            planets[0].setCanBeVisited(true);
            String neighborInfo;
            while ((neighborInfo = br.readLine()) != null) {
                String[] ns = neighborInfo.split(" ");
                int i = Integer.parseInt(ns[0]);
                int j = Integer.parseInt(ns[1]);
                g.add(planets[i], planets[j]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
