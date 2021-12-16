package ua.com.alevel.log;

import ua.com.alevel.log.entries.Entry;
import ua.com.alevel.controller.menu.Menu;
import ua.com.alevel.messages.ErrorMessages;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ErrorLog {
    public static ArrayList<Entry> errorLog = new ArrayList<>();

    private ErrorLog() {
    }

    public static void showErrorLog() throws IOException, ParseException {
        if (ErrorLog.checkErrorLog(errorLog)) {
            System.out.printf("%-8s" + " | " + "%-12s" + " | " + "%-60s" + " | " + "%s" + "\n",
                    "Time", "Operation", "Input", "Error");

            for (Entry errorLogs : errorLog) {
                System.out.printf("%-8s" + " | " + "%-12s" + " | " + "%-60s" + " | " + "%s" + "\n",
                        errorLogs.time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        errorLogs.operation, errorLogs.input, errorLogs.output);
            }
            System.out.println();
        } else {
            ErrorMessages.getEmptyErrorLogError();
            ErrorLog.errorLog.add(new Entry("Menu", "er", "Error Log is empty"));
        }
        Menu.runLogsSubMenu();
    }

    public static Boolean checkErrorLog(ArrayList<Entry> errorLog) {
        return !errorLog.isEmpty();
    }
}
