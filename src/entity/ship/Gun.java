package entity.ship;

import entity.projectile.Projectile;
import entity.projectile.ProjectileType;
import loader.AudioMaster;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.input.Keyboard.KEY_SPACE;

public class Gun implements  java.io.Serializable{

    private Ship ship;
    private int firingSpeed;
    private int bulletCount;
    private long lastShotTime;
    private List<Projectile> projectiles;

    public Gun(Ship ship) {
        this.ship = ship;
        firingSpeed = 100;
        bulletCount = 5;
        lastShotTime = 0;
        projectiles = new ArrayList<>();
    }

    public void shoot() {
        if (Keyboard.isKeyDown(KEY_SPACE)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastShotTime >= firingSpeed) {
                AudioMaster.playSoundEffect("shooting");
                ProjectileType projectileType = ProjectileType.getRandomProjectile();
                projectiles.add(new Projectile(ship.getX() + (ship.getWidth() - projectileType.getWidth())/2, ship.getY() - projectileType.getHeight(), 0, projectileType));
                float increment = 0.05f;
                for (int i = 1; i < bulletCount; i+=2) {
                    projectiles.add(new Projectile(ship.getX() + (ship.getWidth() - projectileType.getWidth())/2, ship.getY() - projectileType.getHeight(), increment*projectileType.getSpeedY(), projectileType));
                    projectiles.add(new Projectile(ship.getX() + (ship.getWidth() - projectileType.getWidth())/2, ship.getY() - projectileType.getHeight(), -increment*projectileType.getSpeedY(), projectileType));
                    increment += 0.10f;
                }
                lastShotTime = currentTime;
            }
        }
    }

    public void reset() {
        projectiles = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.update();
            if (!p.isInRange()) {
                projectiles.remove(p);
            }
        }
    }

    public void render() {
        for (Projectile p : projectiles) {
            p.render();
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getFiringSpeed() {
        return firingSpeed;
    }
}
