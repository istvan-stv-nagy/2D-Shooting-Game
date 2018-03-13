package entity.enemy;

import entity.ship.Ship;

public class EnemyFollower extends Enemy {

    private Ship ship;

    public EnemyFollower(float x, float y, Ship ship) {
        super(x, y, EnemyType.FOLLOWER);
        this.ship = ship;
    }

    @Override
    public void update() {
        if (Math.abs(y - ship.getY()) >= velY) {
            if (y - ship.getY() < 0) y += velY;
            else y -= velY;
        }

        float angle = (ship.centerX() - x) / Math.abs(ship.centerY() - y);
        float xVelocity = angle * velY;
        float realVelocity;
        if (xVelocity < 0) realVelocity = Math.max(xVelocity, -velX);
        else realVelocity = Math.min(xVelocity, velX);
        x += realVelocity;
    }
}
