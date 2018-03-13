package generator;

import entity.enemy.*;
import entity.projectile.Projectile;
import entity.projectile.ProjectileType;
import entity.ship.Ship;
import game.Game;
import game.Level;

import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private int waitingTime;

    private List<Enemy> enemies;
    private List<Projectile> enemyProjectiles;
    private Ship ship;
    private int generatedEnemies;

    private long lastSpawnTime;

    private Level level;


    public EnemySpawner(Level level, Ship ship, List<Enemy> enemies, List<Projectile> enemyProjectiles) {
        this.enemies = enemies;
        this.waitingTime = 0;
        this.lastSpawnTime = 0;
        this.level = level;
        this.generatedEnemies = 0;
        this.enemyProjectiles = enemyProjectiles;
        this.ship = ship;
    }

    public void spawn() {
        if (generatedEnemies < level.getEnemyNumber()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastSpawnTime >= waitingTime) {
                lastSpawnTime = currentTime;
                Random rand = new Random();

                int enemyIndex = Math.abs(rand.nextInt()) % level.getEnemyTypes().size();
                EnemyType enemyType = level.getEnemyTypes().get(enemyIndex);
                float newX = Math.abs(rand.nextInt()) % (Game.SCREEN_WIDTH - enemyType.getWidth());
                if (enemyType == EnemyType.DIVIDER) {
                    newX = Math.min(newX, Game.SCREEN_WIDTH - enemyType.getWidth() - EnemyType.BASIC.getWidth());
                    newX = Math.max(newX, EnemyType.BASIC.getWidth());
                }
                switch (enemyType) {
                    case BASIC:
                        enemies.add(new Enemy(newX, 0, EnemyType.BASIC));
                        break;
                    case DIVIDER:
                        enemies.add(new EnemyDivider(newX, 0, enemies));
                        break;
                    case ZAGGER:
                        enemies.add(new EnemyZagger(newX, 0));
                        break;
                    case SHIELDED:
                        enemies.add(new EnemyShielded(newX, 0));
                        break;
                    case SHOOTER:
                        enemies.add(new EnemyShooter(newX, 0, ProjectileType.ENEMY, enemyProjectiles));
                        break;
                    case FOLLOWER:
                        enemies.add(new EnemyFollower(newX, 0, ship));
                        break;
                    default:
                        break;
                }
                generatedEnemies++;
                waitingTime = Math.abs(rand.nextInt()) % (level.getMaxSpawnTime() - level.getMinSpawnTime() + 1) + level.getMinSpawnTime();
            }
        }
    }

    public int getGeneratedEnemies() {
        return generatedEnemies;
    }
}
