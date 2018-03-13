package entity.enemy;

public class EnemyZagger extends Enemy {

    private static final int AMPLITUDE = 50;
    private static final int SPEED = 20;

    private long time;
    private float originalX;
    private float velocityX;

    public EnemyZagger(float x, float y) {
        super(x, y, EnemyType.ZAGGER);
        time = 0;
        originalX = x;
        velocityX = 0;
    }

    /**
     * y position is updated as default using the y velocity
     * The x position is updates using the original x and adding to it an offset(velocityX)
     * which can be between -AMPLITUDE and +AMPLITUDE
     */
    @Override
    public void update() {
        y += velY;
        long currentTime = System.currentTimeMillis();
        if (currentTime - time > SPEED) {
            time = currentTime;
            velocityX += velX;
            if (Math.abs(velocityX) >= AMPLITUDE) velX = -velX;
        }
        x = originalX + velocityX;
    }
}
