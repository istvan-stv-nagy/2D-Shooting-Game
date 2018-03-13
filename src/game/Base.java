package game;

import assets.Inventory;
import entity.ship.Ship;
import entity.ship.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Base implements java.io.Serializable {

    private Ship ship;
    private List<Ship> ships;
    private Inventory inventory;

    public Base() {
        ship = new Ship((Game.SCREEN_WIDTH - ShipType.BASIC.getWidth()) / 2, Game.SCREEN_HEIGHT - ShipType.BASIC.getHeight(), ShipType.BASIC, "N19");
        ship.setSelected(true);
        inventory = new Inventory();
        ships = new ArrayList<>();
        ships.add(ship);
        ships.add(new Ship((Game.SCREEN_WIDTH - ShipType.BASIC.getWidth()) / 2, Game.SCREEN_HEIGHT - ShipType.RAIDER.getHeight(), ShipType.RAIDER, "X-RET88"));
        ships.add(new Ship((Game.SCREEN_WIDTH - ShipType.BASIC.getWidth()) / 2, Game.SCREEN_HEIGHT - ShipType.CRUISER.getHeight(), ShipType.CRUISER, "ARG-77"));
    }

    public void setActiveShip(Ship ship) {
        this.ship.setSelected(false);
        this.ship = ship;
        this.ship.setSelected(true);
    }

    public Ship getShip() {
        return ship;
    }

    public void loadCargo(Ship ship) {
        inventory.load(ship.getInventory());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
