package model.logging.security;

import model.Persistence;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Log implements Serializable{
    private static List<SecurityLogEntry> entries = new ArrayList<>();

    private static final File logFile = new File("LogSaveState.dat");


    public static void addEntry(SecurityLogEntry entry) {
        entries.add(entry);
    }

    public static SecurityLogEntry[] getEntries() {
        return entries.toArray(new SecurityLogEntry[entries.size()]);
    }

    public static void saveAccountsToBinary() {
        Persistence p = new Persistence(entries);
        p.saveToBinary(logFile);
    }

    public static void loadAccountsFromBinary() {
        Persistence p = new Persistence(entries);
        p.loadFromBinary(logFile);
        entries = (List) p.getBacking();
    }
}
