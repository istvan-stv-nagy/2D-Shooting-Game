package helpers;

import game.Game;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import ui.Fonts;

import java.io.IOException;
import java.io.InputStream;

import static game.Game.SCREEN_HEIGHT;
import static game.Game.SCREEN_WIDTH;
import static org.lwjgl.opengl.GL11.*;

public class Artist {

    private static final String TITLE = "Star Wars | Beyond Explored!";

    public static void beginSession() {
        Display.setTitle(TITLE);
        try {
            Display.setDisplayMode(new DisplayMode(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,SCREEN_WIDTH, SCREEN_HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void drawQuad(float x, float y, float width, float height, Color color) {
        glDisable(GL_TEXTURE_2D);
        glColor3f(color.r,color.g,color.b);
        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x + width, y);
        glVertex2f(x+width, y+height);
        glVertex2f(x, y+height);
        glEnd();
        glEnable(GL_TEXTURE_2D);
    }

    public static void drawQuad(float x, float y, float width, float height, Color color, float blend) {
        glDisable(GL_TEXTURE_2D);
        glColor4f(color.r,color.g,color.b, blend);
        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x + width, y);
        glVertex2f(x+width, y+height);
        glVertex2f(x, y+height);
        glEnd();
        glEnable(GL_TEXTURE_2D);
    }

    public static void drawRectangle(float x, float y, int w, int h, Color color, int lineWidth) {
        glDisable(GL_TEXTURE_2D);
        glColor3f(color.r,color.g,color.b);
        glLineWidth(lineWidth);
        glBegin(GL_LINE_LOOP);
        glVertex2f(x,y);
        glVertex2f(x+w,y);
        glVertex2f(x+w,y+h);
        glVertex2f(x,y+h);
        glEnd();
        glEnable(GL_TEXTURE_2D);
    }

    public static void drawLine(float x1, float y1, float x2, float y2, Color color, int lineWidth) {
        glDisable(GL_TEXTURE_2D);
        glColor3f(color.r,color.g,color.b);
        glLineWidth(lineWidth);
        glBegin(GL_LINES);
        glVertex2f(x1,y1);
        glVertex2f(x2,y2);
        glEnd();
        glEnable(GL_TEXTURE_2D);
    }

    public static void drawQuadTex(Texture tex, float x, float y, float width, float height){
        glColor4f(1f,1f,1f,1f);
        tex.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(width, 0);
        glTexCoord2f(1,1);
        glVertex2f(width, height);
        glTexCoord2f(0,1);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }

    public static void drawBar(float x, float y, int maxWidth, int height, float value, float maxPossibleValue, float maxValue, Color lineColor, Color background, Color foreground) {
        drawQuad(x,y,maxWidth,height,background);
        drawQuad(x,y,maxWidth*(value / maxValue), height, foreground);
        if (maxValue != maxPossibleValue) drawQuad(x + (maxPossibleValue/maxValue)*maxWidth, y, 4, height, lineColor);
        drawRectangle(x, y, maxWidth, height, lineColor,2);
    }

    public static void drawBar(float x, float y, int maxWidth, int height, String text, float value, float maxPossibleValue, float maxValue, Color lineColor, Color background, Color foreground) {
        drawBar(x, y, maxWidth, height, value, maxPossibleValue, maxValue, lineColor, background, foreground);
        drawText(x, y, text, Fonts.BAR.font, Color.white);
    }

    public static void drawQuadTex(Texture tex, float x, float y, float width, float height, float row, float col, float size){
        glColor4f(1f,1f,1f,1f);
        tex.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(col/size,row/size);
        glVertex2f(0,0);
        glTexCoord2f((col+1)/size,row/size);
        glVertex2f(width, 0);
        glTexCoord2f((col+1)/size,(row+1)/size);
        glVertex2f(width, height);
        glTexCoord2f(col/size,(row+1)/size);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }

    public static void drawQuadTex(Texture tex, float x, float y, float width, float height, float ix, float iy, float iw, float ih){
        glColor4f(1f,1f,1f,1f);
        tex.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(ix,iy);
        glVertex2f(0,0);
        glTexCoord2f(ix+iw,iy);
        glVertex2f(width, 0);
        glTexCoord2f(ix+iw,iy+ih);
        glVertex2f(width, height);
        glTexCoord2f(ix,iy+ih);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }

    public static void drawText(float x, float y, String text, TrueTypeFont font, Color color) {
        font.drawString(x, y, text, color);
    }

    public static Texture loadTexture(String path, String fileType) {
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(fileType, in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tex;
    }

    public static Texture loadPNG(String name) {
        Texture tex;
        tex = loadTexture("res/textures/" + name + ".png", "PNG");
        return tex;
    }

    public static Texture loadPNG(String name, String path) {
        Texture tex;
        tex = loadTexture(path + name + ".png", "PNG");
        return tex;
    }

}
