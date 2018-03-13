package game;

import assets.Item;
import assets.ItemType;
import entity.enemy.Enemy;
import entity.explosion.Explosion;
import entity.item.FallingItem;
import entity.projectile.Projectile;
import entity.ship.Ship;
import generator.MaterialGenerator;
import loader.AudioMaster;

import java.util.List;

public class CollisionDetector {

    private MaterialGenerator materialGenerator;

    public CollisionDetector(MaterialGenerator materialGenerator) {
        this.materialGenerator = materialGenerator;
    }

    public void checkCollision(Ship ship, List<Enemy> enemies, List<Explosion> explosions, List<FallingItem> fallingItems) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            for (int j = 0; j < ship.getProjectiles().size(); j++) {
                Projectile p = ship.getProjectiles().get(j);
                if (e.collides(p)) {
                    p.die();
                    if (e.isAlive()) {
                        e.damage(p.getType().getDamage());
                        explosions.add(new Explosion(p.getX(),p.getY(),30,30,e.getType().getExplosionType(), 5));
                    }
                    if (!e.isAlive() && !e.isKilled()) {
                        e.kill();
                        AudioMaster.playSoundEffect("explosion");
                        ship.addExperience(e.getType().getExp());
                        explosions.add(new Explosion(e.getX(), e.getY(), e.getWidth(), e.getHeight(), e.getType().getExplosionType()));
                        ItemType itemType = materialGenerator.generate();
                        if (itemType != null)
                            fallingItems.add(new FallingItem(e.getX(), e.getY(), 0, e.getVelY(), new Item(itemType)));
                    }
                }
            }
            if (e.collides(ship)) {
                e.die();
                AudioMaster.playSoundEffect("explosion");
                if (ship.hasArmour()) ship.hit(e.getType().getDamage());
                else ship.damage(e.getType().getDamage());
                explosions.add(new Explosion(e.getX(), e.getY(), e.getWidth(), e.getHeight(), e.getType().getExplosionType()));
            }
        }
    }

    public void checkCollision(Ship ship, List<FallingItem> fallingItems, List<Projectile> enemyProjectiles) {
        for (FallingItem i : fallingItems) {
            if (i.collides(ship)) {
                ship.collectItem(i.getItem(), 1);
                AudioMaster.playSoundEffect("collect");
                i.die();
            }
        }
        for (Projectile p : enemyProjectiles) {
            if (p.collides(ship)) {
                p.die();
                if (ship.hasArmour()) ship.hit(p.getType().getDamage());
                else ship.damage(p.getType().getDamage());
            }
        }
    }


}
