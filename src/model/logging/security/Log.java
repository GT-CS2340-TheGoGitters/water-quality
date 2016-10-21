package model.logging.security;

import model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 10/21/16.
 */
public class Log {
    private static List<SecurityLogEntry> entries = new ArrayList<>();

    public static void addEntry(SecurityLogEntry entry) {
        entries.add(entry);
    }

    public static SecurityLogEntry[] getEntries() {
        return entries.toArray(new SecurityLogEntry[entries.size()]);
    }
}
