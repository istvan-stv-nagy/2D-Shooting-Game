package entity.explosion;

import entity.Entity;
import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;

public class Explosion extends Entity {

    private ExplosionType type;
    private int iterations;
    private int duration;
    private boolean alive;

    private long lastExplosion;

    /**
     * Generate an explosion.
     * @param x the x coordinate of the explosion
     * @param y the y coordinate of the explosion
     * @param width the size of the explosion
     * @param height the size of the explosion
     * @param type the type of the explosion
     */
    public Explosion(float x, float y, int width, int height, ExplosionType type) {
        super(x, y, width, height, 0, 0, type.getTexture());
        this.type = type;
        iterations = 0;
        alive = true;
        duration = type.getDuration();
        lastExplosion = 0;
    }

    public Explosion(float x, float y, int width, int height, ExplosionType type, int duration) {
        this(x, y, width, height, type);
        this.duration = duration;
    }

    /**
     * If the iterations are at the maximum value given by the number of iterations of the explosion type
     * the explosion will die.
     * If the elapsed time is greater or equal than the duration of a single explosion moment than the
     * iterations will be increased.
     */
    @Override
    public void update() {
        if (!(iterations < type.getIterations())) {
            alive = false;
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastExplosion >= duration) {
            lastExplosion = currentTime;
            iterations++;
        }
    }

    @Override
    public void render() {
        float row = iterations / type.getDimension();
        float col = iterations % type.getDimension();
        drawQuadTex(getImage(texture), x, y, width, height, row, col, type.getDimension());
    }

    public boolean isAlive() {
        return alive;
    }
}
