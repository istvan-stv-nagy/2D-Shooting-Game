package entity.enemy;

import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;

public class EnemyShielded extends Enemy {

    private static final String SHIELD_TEXTURE = "enemy_shield";
    private static final int SHIELD_TIME = 1000;

    private boolean activeShield;
    private long time;

    public EnemyShielded(float x, float y) {
        super(x, y, EnemyType.SHIELDED);
        activeShield = true;
        time = 0;
    }

    /**
     * If the elapsed time is greater or equal than the SHIELD_TIME the shield toggles
     */
    @Override
    public void update() {
        super.update();
        long currentTime = System.currentTimeMillis();
        if (currentTime - time >= SHIELD_TIME) {
            time = currentTime;
            activeShield = !activeShield;
        }
    }

    /**
     * Enemy takes damage only of the shield is not active
     * @param amount the amount of damage
     */
    @Override
    public void damage(int amount) {
        if (!activeShield)
            super.damage(amount);
    }

    @Override
    public void render() {
        super.render();
        if (activeShield) drawQuadTex(getImage(SHIELD_TEXTURE), x, y, 100, 100);
    }
}
