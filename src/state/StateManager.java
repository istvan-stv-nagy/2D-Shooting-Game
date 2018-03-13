package state;

import assets.CraftingBook;
import assets.Item;
import assets.ItemType;
import entity.map.Galaxy;
import entity.map.Planet;
import game.Base;
import game.Mission;
import game.Profile;
import loader.AudioMaster;
import loader.CraftingRecipeLoader;
import loader.Data;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import ui.elements.ProfileWindow;
import ui.interfaces.*;

import static loader.GameLoader.loadGame;
import static loader.GameLoader.saveGame;

public class StateManager{

    private State state;

    private MainMenuInterface mainMenuInterface;

    private LoadInterface loadInterface;

    private Base base;
    private BaseInterface baseInterface;
    private Profile profile;
    private ProfileWindow profileWindow;

    private InventoryInterface inventoryInterface;

    private CraftingInterface craftingInterface;
    private CraftingBook book;

    private ShipyardInterface shipyardInterface;

    private Galaxy galaxy;
    private GalaxyInterface galaxyInterface;

    private Planet targetPlanet;
    private PlanetInterface planetInterface;

    private Mission mission;
    private MissionInterface missionInterface;

    private MissionEndInterface missionEndInterface;


    private boolean paused;

    public StateManager() {
        state = State.MAIN_MENU;
        mainMenuInterface = new MainMenuInterface();
        paused = false;
    }

    public void update() {
        switch (state) {
            case MAIN_MENU:
                mainMenuInterface.update();
                switch (mainMenuInterface.getCommand()) {
                    case "null" : break;
                    case "play" :
                        state = State.BASE;
                        if (base == null) {
                            base = new Base();
                            baseInterface = new BaseInterface();
                        }
                        if (book == null) {
                            book = new CraftingBook();
                            CraftingRecipeLoader.load(book);
                        }
                        if (profile == null) {
                            profile = new Profile();
                        }
                        profileWindow = new ProfileWindow(profile);
                        break;

                    case "load" :
                        state = State.LOADGAME;
                        loadInterface = new LoadInterface();
                        break;

                    case "exit" :
                        if (base!=null && galaxy != null && profile!=null)
                            saveGame(new Data(base, galaxy, profile));
                        AL.destroy();
                        Display.destroy();
                        System.exit(1);
                        break;
                }
                break;

            case BASE:
                baseInterface.update();
                switch (baseInterface.getCommand()) {
                    case "null" : break;
                    case "go" :
                        state = State.GALAXY;
                        if (galaxy == null) galaxy = new Galaxy(1);
                        galaxyInterface = new GalaxyInterface(galaxy);
                        break;
                    case "inventory" :
                        state = State.INVENTORY;
                        inventoryInterface = new InventoryInterface(base.getInventory());
                        break;
                    case "check-ships" :
                        state = State.SHIPYARD;
                        shipyardInterface = new ShipyardInterface(base.getShips());
                        break;
                    case "craft" :
                        state = State.CRAFTING;
                        craftingInterface = new CraftingInterface(book, base.getInventory());
                        break;
                    case "back" :
                        state = State.MAIN_MENU;
                        break;
                }
                break;

            case GALAXY:
                galaxyInterface.update();
                targetPlanet = galaxyInterface.getSelectedPlanet();
                if (targetPlanet != null) {
                    state = State.PLANET;
                    planetInterface = new PlanetInterface(targetPlanet);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    state = State.BASE;
                }
                break;

            case PLANET:
                planetInterface.update();
                switch (planetInterface.getCommand()) {
                    case "start":
                        state = State.MISSION;
                        mission = new Mission(base.getShip(), targetPlanet);
                        missionInterface = new MissionInterface(mission);
                        AudioMaster.stopMusic("background_music");
                        AudioMaster.playMusic("mission_music");
                        break;
                    case "back":
                        state = State.GALAXY;
                        galaxyInterface.resetSelectedPlanet();
                        break;
                }
                break;

            case INVENTORY:
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    state = State.BASE;
                }
                break;

            case SHIPYARD:
                shipyardInterface.update();
                switch (shipyardInterface.getCommand()) {
                    case "null" : break;
                    case "select-ship" :
                        base.setActiveShip(shipyardInterface.getSelectedShip());
                        break;
                    case "tank-up" :
                        if (base.getInventory().hasItem(ItemType.FUEL, 1)) {
                            shipyardInterface.getSelectedShip().fillTank(ItemType.FUEL.getUnit());
                            base.getInventory().use(new Item(ItemType.FUEL), 1);
                        }
                        break;
                    case "back" :
                        state = State.BASE;
                        break;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    state = State.BASE;
                }
                break;

            case CRAFTING:
                craftingInterface.update();
                switch (craftingInterface.getCommand()) {
                    case "null" : break;
                    case "craft" :
                        if (craftingInterface.getSelectedReceip().isCraftable(base.getInventory())) {
                            craftingInterface.getSelectedReceip().craft(base.getInventory());
                            craftingInterface.updateItemWindow();
                            AudioMaster.playSoundEffect("craft");
                        }
                        break;
                    case "back" :
                        state = State.BASE;
                        break;
                }
                break;

            case MISSION:
                if (!paused) {
                    mission.update();
                    if (mission.isFinished()) {
                        state = State.MISSIONEND;
                        missionEndInterface = new MissionEndInterface(missionInterface.getBackground(), true);
                    }
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
                    paused = true;
                }
                else if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    paused = false;
                }
                break;

            case MISSIONEND:
                missionEndInterface.update();
                if (missionEndInterface.getCommand().equals("back")) {
                    state = State.BASE;
                    //load cargo into inventory
                    base.loadCargo(mission.getShip());
                    //get experience points
                    profile.addExperience(mission.getShip().getExperience());
                    //set planet to finished
                    galaxy.setFinished(targetPlanet);
                    AudioMaster.stopMusic("mission_music");
                    AudioMaster.playMusic("background_music");
                }
                break;

            case LOADGAME:
                loadInterface.update();
                String command = loadInterface.getCommand();
                if (command.contains("load-file")) {
                    state = State.BASE;
                    Data data = loadGame(command.substring("load-file".length()));
                    base = data.getBase();
                    galaxy = data.getGalaxy();
                    profile = data.getProfile();
                    baseInterface = new BaseInterface();
                    profileWindow = new ProfileWindow(profile);
                    if (book == null) {
                        book = new CraftingBook();
                        CraftingRecipeLoader.load(book);
                    }
                }
                break;

            default:
                break;
        }
    }

    public void render() {
        switch (state) {
            case MAIN_MENU:
                mainMenuInterface.render();
                break;

            case BASE:
                baseInterface.render();
                profileWindow.render();
                break;

            case INVENTORY:
                inventoryInterface.render();
                break;

            case SHIPYARD:
                shipyardInterface.render();
                break;

            case GALAXY:
                galaxyInterface.render();
                break;

            case PLANET:
                planetInterface.render();
                break;

            case MISSION:
                missionInterface.render();
                mission.render();
                break;

            case MISSIONEND:
                missionEndInterface.render();
                break;

            case LOADGAME:
                loadInterface.render();
                break;

            case CRAFTING:
                craftingInterface.render();
                break;

            default:
                break;
        }
    }

    public void close() {
        if (base!=null && galaxy != null && profile!=null)
            saveGame(new Data(base, galaxy, profile));
    }
    
}
