package ui;

import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public enum Fonts {

    DEFAULT(new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 22), false), 22),
    INFO(new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 16), false), 16),
    TITLE(loadFont("src/res/fonts/space-age.regular.ttf", 50f), 50),
    SUBTITLE(new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 20), false), 20),
    PLANET(loadFont("src/res/fonts/redfive.regular.ttf", 20f), 20),
    BUTTON(loadFont("src/res/fonts/redfive.regular.ttf", 38f), 38),
    BAR(loadFont("src/res/fonts/redfive.regular.ttf", 14f), 14);

    public TrueTypeFont font;
    public int size;

    Fonts(TrueTypeFont font, int size) {
        this.font = font;
        this.size = size;
    }

    private static TrueTypeFont loadFont(String path, float size) {
        TrueTypeFont font = null;
        try {
            InputStream in = ResourceLoader.getResourceAsStream(path);
            Font spaceAwtFont = Font.createFont(Font.TRUETYPE_FONT, in);
            spaceAwtFont = spaceAwtFont.deriveFont(size);
            font = new org.newdawn.slick.TrueTypeFont(spaceAwtFont, false);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return font;
    }
}
