package loader;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioMaster {

    private static final String PATH_EFFECTS = "src/res/audio/effects";
    private static final String PATH_SONGS = "src/res/audio/songs";

    private static Map<String, Audio> effects;
    private static Map<String, Audio> songs;

    public static void init() {
        effects = new HashMap<>();
        songs = new HashMap<>();
        File folder = new File(PATH_EFFECTS);
        File[] files = folder.listFiles();
        for (int i=0; i<files.length; i++) {
            if (files[i].isFile()) {
                try {
                    effects.put(files[i].getName().replace(".wav", ""), AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(PATH_EFFECTS +"/" + files[i].getName())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        folder = new File(PATH_SONGS);
        files = folder.listFiles();
        for (int i=0; i<files.length; i++) {
            if (files[i].isFile()) {
                try {
                    songs.put(files[i].getName().replace(".ogg", ""), AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(PATH_SONGS +"/" + files[i].getName())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void playSoundEffect(String name) {
        effects.get(name).playAsSoundEffect(1f, 1f, false);
    }

    public static void playMusic(String name) {
        songs.get(name).playAsMusic(1f, 1f, true);
    }

    public static void stopMusic(String name) {
        songs.get(name).stop();
    }

}
