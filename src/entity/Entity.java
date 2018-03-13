package entity;

import game.Game;
import org.lwjgl.input.Mouse;

import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;


public abstract class Entity implements java.io.Serializable {

    protected float x, y;
    protected int width, height;
    protected float velX, velY;
    protected String texture;

    public Entity() {}

    public Entity(float x, float y, int width, int height, float velX, float velY, String texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.texture = texture;
    }

    public void update() {
        x += velX;
        y += velY;
    }

    public float centerX() {
        return x + width/2;
    }

    public float centerY() {
        return y + height/2;
    }

    public void render() {
        drawQuadTex(getImage(texture), x, y, width, height);
    }

    public boolean collides(Entity e) {
        return !(x + width < e.getX()) && !(y + height < e.getY()) && !(y > e.getY() + e.getHeight()) && !(x > e.getX() + e.getWidth());
    }

    public boolean isMouseOver() {
        int mouseX = Mouse.getX();
        int mouseY = Game.SCREEN_HEIGHT - Mouse.getY();
        return x <= mouseX && mouseX <= x + width && y <= mouseY && mouseY <= y + height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public String getTexture() {
        return texture;
    }
}
