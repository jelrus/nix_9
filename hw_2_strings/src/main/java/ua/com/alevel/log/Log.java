package ua.com.alevel.log;

import ua.com.alevel.log.entries.Entry;
import ua.com.alevel.controller.menu.Menu;
import ua.com.alevel.messages.ErrorMessages;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Log{
    public static ArrayList<Entry> log = new ArrayList<>();

    private Log() {
    }

    public static void showLog() throws IOException, ParseException {
        if (Log.checkLog(log)) {
            System.out.printf("%-8s" + " | " + "%-12s" + " | " + "%-60s" + " | " + "%s" + "\n",
                    "Time", "Operation", "Input", "Output");

            for (Entry logs : log) {
                System.out.printf("%-8s" + " | " + "%-12s" + " | " + "%-60s" + " | " + "%s" + "\n",
                        logs.time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        logs.operation, logs.input, logs.output);
            }
            System.out.println();
        } else {
            ErrorMessages.getEmptyLogError();
            ErrorLog.errorLog.add(new Entry("Menu", "l", "Log is empty"));
        }
        Menu.runLogsSubMenu();
    }

    public static Boolean checkLog(ArrayList<Entry> logs) {
        return !logs.isEmpty();
    }
}
