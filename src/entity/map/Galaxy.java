package entity.map;

import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static loader.GalaxyLoader.load;
import static helpers.Artist.drawLine;

public class Galaxy implements java.io.Serializable {

    private int id;
    private Map<Planet, ArrayList<Planet>> graph;

    public Galaxy(int id) {
        this.id = id;
        graph = new HashMap<>();
        load(this);
    }

    public int getId() {
        return id;
    }

    public Map<Planet, ArrayList<Planet>> getGraph() {
        return graph;
    }

    public void add(Planet p) {
        graph.put(p, new ArrayList<>());
    }

    public void add(Planet p, Planet neighbor) {
        if (graph.get(p) != null)
            graph.get(p).add(neighbor);
    }

    public Planet getSelectedPlanet() {
        for (Planet p : graph.keySet()) {
            if (p.isMouseOver())
                return p;
        }
        return null;
    }

    public void render() {
        for (Planet p : graph.keySet()) {
            for (Planet neighbor : graph.get(p)) {
                if (p.canBeExplored() && neighbor.canBeExplored())
                    drawLine(p.getCenterX(), p.getCenterY(), neighbor.getCenterX(), neighbor.getCenterY(), Color.yellow, 3);
                else
                    drawLine(p.getCenterX(), p.getCenterY(), neighbor.getCenterX(), neighbor.getCenterY(), Color.gray, 3);
            }
        }
        for (Planet p : graph.keySet()) {
            p.render();
        }
    }

    public void setFinished(Planet p) {
        p.setFinished(true);
        for (Planet n : graph.get(p))
            n.setCanBeVisited(true);
    }
}
