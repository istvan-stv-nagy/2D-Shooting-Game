package entity.enemy;

import java.util.List;

public class EnemyDivider extends Enemy {

    private List<Enemy> enemies;

    public EnemyDivider(float x, float y, List<Enemy> enemies) {
        super(x, y, EnemyType.DIVIDER);
        this.enemies = enemies;
    }

    /**
     * When the enemy dies, it generates two basic enemies either side.
     */
    @Override
    public void die() {
        this.alive = false;
        enemies.add(new Enemy(x - EnemyType.BASIC.getWidth(), y, EnemyType.BASIC));
        enemies.add(new Enemy(x + width, y, EnemyType.BASIC));
    }
}
