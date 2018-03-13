package game;

import loader.AudioMaster;
import loader.ConfigurationLoader;
import loader.ImageLoader;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.SoundStore;
import state.StateManager;

import static helpers.Artist.beginSession;
import static org.lwjgl.opengl.GL11.*;

public class Boot {

    public Boot() {

        beginSession();

        ImageLoader.init();

        AudioMaster.init();

        ConfigurationLoader.load();

        StateManager manager = new StateManager();

        while (!Display.isCloseRequested()) {

            manager.update();

            //rendering
            GL11.glClearColor(255,255,255,255);
            glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

            manager.render();

            Display.update();
            Display.sync(Game.FPS);
            SoundStore.get().poll(0);
        }

        manager.close();
        AL.destroy();
        Display.destroy();

    }


    public static void main(String args[]) {
        new Boot();
    }

}
