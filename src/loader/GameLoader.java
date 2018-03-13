package loader;

import java.io.*;

public class GameLoader {

    public static void saveGame(Data data) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/res/saves/save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.println("saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Data loadGame(String fileName) {
        Data d = null;
        try {
            FileInputStream fileIn = new FileInputStream("src/res/saves/" + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            d = (Data) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return d;
    }

}
