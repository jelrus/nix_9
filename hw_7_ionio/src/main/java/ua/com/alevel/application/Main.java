package ua.com.alevel.application;

import ua.com.alevel.controller.impl.UnifiedController;

public class Main {

    public static void main(String[] args) throws Exception {

        UnifiedController.wipePreviousRecords();
        UnifiedController.createNewFiles();
        UnifiedController.run();
    }
}