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

//    public void saveToText(File f) {
//        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
//            pw.println(backing.size());
//            for (WaterReport w : backing) {
//                w.saveToText(pw);
//            }
//            pw.close();
//        } catch (IOException e) {
//            LOGGER.log(Level.SEVERE, "Problem saving file", e);
//        }
//    }
//
//    public void loadFromText(File f) {
//        String s = null;
//        backing.clear();
//        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
//            while((s = br.readLine()) != null) {
//                WaterReport w = WaterReport.makeFromString(s);
//                backing.add(w);
//            }
//            br.close();
//        }
//    }

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
