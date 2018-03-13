package loader;

import entity.map.Galaxy;
import game.Base;
import game.Profile;

public class Data implements java.io.Serializable {

    private Base base;
    private Galaxy galaxy;
    private Profile profile;

    public Data(Base base, Galaxy galaxy, Profile profile) {
        this.base = base;
        this.galaxy = galaxy;
        this.profile = profile;
    }

    public Base getBase() {
        return base;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public Profile getProfile() {
        return profile;
    }
}
