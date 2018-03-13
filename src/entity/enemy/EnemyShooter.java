package entity.enemy;

import entity.projectile.Projectile;
import entity.projectile.ProjectileType;
import loader.AudioMaster;

import java.util.List;

public class EnemyShooter extends Enemy {

    private static final int FIRING_SPEED = 800;

    private ProjectileType projectileType;
    private List<Projectile> projectiles;

    private long lastShotTime;

    public EnemyShooter(float x, float y, ProjectileType projectileType, List<Projectile> projectiles) {
        super(x, y, EnemyType.SHOOTER);
        this.projectiles = projectiles;
        this.projectileType = projectileType;
        lastShotTime = 0;
    }

    @Override
    public void update() {
        super.update();
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime >= FIRING_SPEED) {
            lastShotTime = currentTime;
            shoot();
        }
    }

    private void shoot() {
        projectiles.add(new Projectile(x + (width - projectileType.getWidth())/2, y + height, 0, projectileType));
        AudioMaster.playSoundEffect("enemy_shooting");
    }
}
