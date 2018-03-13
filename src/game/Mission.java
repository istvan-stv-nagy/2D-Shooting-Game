package game;

import entity.enemy.Enemy;
import entity.explosion.Explosion;
import entity.item.FallingItem;
import entity.map.Planet;
import entity.projectile.Projectile;
import entity.ship.Ship;
import generator.EnemySpawner;
import generator.MaterialGenerator;

import java.util.ArrayList;
import java.util.List;

public class Mission {

    private Ship ship;
    private List<Enemy> enemies;
    private List<Explosion> explosions;
    private List<Projectile> enemyProjectiles;
    private List<FallingItem> fallingItems;
    private EnemySpawner spawner;
    private CollisionDetector collisionDetector;

    private Level level;
    private boolean finished;

    public Mission(Ship ship, Planet targetPlanet) {
        this.ship = ship;
        this.ship.reset();
        enemies = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
        explosions = new ArrayList<>();
        fallingItems = new ArrayList<>();
        level = new Level(targetPlanet.getLevelID());
        spawner = new EnemySpawner(level, ship, enemies, enemyProjectiles);
        MaterialGenerator materialGenerator = new MaterialGenerator(targetPlanet.getDropProbability(), targetPlanet.getResources());
        collisionDetector = new CollisionDetector(materialGenerator);
        finished = false;
    }

    public void update() {
        //update
        if (level.getEnemyNumber() == spawner.getGeneratedEnemies() && enemies.size() == 0 && fallingItems.size() == 0) {
            finished = true;
            return;
        }

        ship.update();
        for (Enemy e : enemies)
            e.update();
        for (Explosion e : explosions)
            e.update();
        for (FallingItem i : fallingItems)
            i.update();
        for (Projectile p : enemyProjectiles) {
            p.update();
        }

        //spawn
        spawner.spawn();

        //check collisions
        collisionDetector.checkCollision(ship, enemies, explosions, fallingItems);
        collisionDetector.checkCollision(ship, fallingItems, enemyProjectiles);


        //cleanup
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (!e.isAlive() || !e.isInRange())
                enemies.remove(i);
        }
        for (int i = 0; i < ship.getProjectiles().size(); i++) {
            Projectile p = ship.getProjectiles().get(i);
            if (!p.isAlive() || !p.isInRange())
                ship.getProjectiles().remove(i);
        }
        for (int i = 0; i < explosions.size(); i++) {
            Explosion e = explosions.get(i);
            if (!e.isAlive()) {
                explosions.remove(i);
            }
        }
        for (int i = 0; i < fallingItems.size(); i++) {
            FallingItem item = fallingItems.get(i);
            if (!item.isInRange() || !item.isAlive()) {
                fallingItems.remove(i);
            }
        }
        for (int i = 0; i < enemyProjectiles.size(); i++) {
            Projectile p = enemyProjectiles.get(i);
            if (!p.isAlive() || (p.getY() + p.getWidth() > Game.SCREEN_HEIGHT))
                enemyProjectiles.remove(i);
        }
    }

    public void render() {
        ship.render();
        for (Enemy e : enemies) {
            e.render();
        }
        for (Explosion e : explosions) {
            e.render();
        }
        for (FallingItem i : fallingItems) {
            i.render();
        }
        for (Projectile p : enemyProjectiles) {
            p.render();
        }
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isFinished() {
        return finished;
    }
}
