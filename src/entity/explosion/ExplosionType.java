package entity.explosion;

public enum ExplosionType {

    BASIC(5, 24, 30, "explosion1"),
    FIRE(4, 15, 40, "explosion2"),
    BLACKYELLOW(4, 15, 40, "explosion3"),
    ORANGE(4, 15, 50, "explosion4");

    private String texture;
    private int iterations;
    private int duration;
    private int dimension;

    ExplosionType(int dimension, int iterations, int duration, String texture) {
        this.texture = texture;
        this.iterations = iterations;
        this.dimension = dimension;
        this.duration = duration;
    }

    public String getTexture() {
        return texture;
    }

    public int getIterations() {
        return iterations;
    }

    public int getDimension() {
        return dimension;
    }

    public int getDuration() {
        return duration;
    }
}
