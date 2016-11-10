package model;

import java.io.*;

public class Persistence {
    private Object backing;

    public Persistence(Object backing) {
        this.backing = backing;
    }

    public void saveToBinary(File f) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(backing);
            oos.close();
        } catch (IOException e) {
            System.out.println("Failed to save file");
            e.printStackTrace();
        }
    }

    public void loadFromBinary(File f) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            backing = ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load file");
            e.printStackTrace();
        }
    }

    public Object getBacking() {
        return this.backing;
    }
}
