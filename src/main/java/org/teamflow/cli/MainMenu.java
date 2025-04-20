package org.teamflow.cli;

import org.teamflow.controllers.*;

public class MainMenu extends Menu {
    public MainMenu(CLIHandler cliHandler) {
        super(cliHandler);
    }

    @Override
    public void displayMenu() {
        Boolean isScrumMaster = cliHandler.getUserController().getCurrentUser().getIsScrumMaster();
        if (Boolean.TRUE.equals(isScrumMaster)) {
            System.out.println("Welkom Scrum Master!");
        }

        while (cliHandler.isRunning()) {
            System.out.println("1. Epics");
            System.out.println("2. UserStories");
            System.out.println("3. Taken");
            System.out.println("4. Berichten");
            System.out.println("10. Sluit Applicatie");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    cliHandler.getEpicMenu().displayMenu();
                    break;
                case "2":
                    cliHandler.getUserStoryMenu().displayMenu();
                    break;
                case "3":
                    cliHandler.getTaskMenu().displayMenu();
                    break;
                case "4":
                    cliHandler.getMessageMenu().displayMenu();
                    break;
                case "10":
                    cliHandler.setRunning(false);
                    System.out.println("Afsluiten...");
                    break;
                default:
                    System.out.println("Onbekende optie.");
            }
        }
    }
}

