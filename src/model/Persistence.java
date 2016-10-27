package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jack on 10/22/16.
 */
public class Persistence {

    public static Logger LOGGER = Logger.getLogger("Persistence");

    private Object backing;

    public Persistence(Object backing) {
        this.backing = backing;
    }

    public void saveToBinary(File f) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(backing);
            oos.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write binary!", e);
        }
    }

    public void loadFromBinary(File f) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            backing = (Object) ois.readObject();
            ois.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to input Bianry", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Failed to find Binary", e);
        }
    }

    public Object getBacking() {
        return this.backing;
    }
}
