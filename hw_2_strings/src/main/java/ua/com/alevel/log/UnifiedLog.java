package ua.com.alevel.log;

import ua.com.alevel.log.entries.Entry;
import ua.com.alevel.menu.Menu;
import ua.com.alevel.messages.ErrorMessages;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class UnifiedLog {

    public static ArrayList<Entry> unifiedLog = new ArrayList<>();

    private UnifiedLog() {
    }

    public static void showUnifiedLog() throws IOException, ParseException {
        unifiedLog.addAll(ErrorLog.errorLog);
        unifiedLog.addAll(Log.log);
        Collections.sort(unifiedLog);

        if (UnifiedLog.checkUnifiedLog(unifiedLog) || Log.checkLog(Log.log) || ErrorLog.checkErrorLog(ErrorLog.errorLog)) {
            System.out.printf("%-8s" + " | " + "%-12s" + " | " + "%-60s" + " | " + "%s" + "\n",
                    "Time", "Operation", "Input", "Output/Error");
            for (Entry unifiedLogs : unifiedLog) {
                System.out.printf("%-8s" + " | " + "%-12s" + " | " + "%-60s" + " | " + "%s" + "\n",
                        unifiedLogs.time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        unifiedLogs.operation, unifiedLogs.input, unifiedLogs.output);
            }
            System.out.println();
        } else if (!UnifiedLog.checkUnifiedLog(unifiedLog)) {
            ErrorMessages.getEmptyUnifiedLogError();
            ErrorLog.errorLog.add(new Entry("Menu", "ul", "Unified Log is Empty"));
        }
        Menu.runLogsSubMenu();
    }

    public static Boolean checkUnifiedLog(ArrayList<Entry> unifiedLog) {
        return !unifiedLog.isEmpty();
    }
}
