package org.teamflow.cli;

import org.teamflow.controllers.EpicController;
import org.teamflow.models.Epic;

public class EpicMenu extends Menu {
    private EpicController epicController;

    public EpicMenu(CLIHandler cliHandler) {
        super(cliHandler);
        this.epicController = cliHandler.getEpicController();
    }

    @Override
    public void displayMenu() {
        clearConsole();
        while (cliHandler.isRunning()) {
            System.out.println("1. Voeg Epic toe");
            System.out.println("2. Laat Epics zien");
            System.out.println("3. Terug naar main menu");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    voegEpicToe();
                    break;
                case "2":
                    listEpics();
                    break;
                case "3":
                    cliHandler.getMainMenu().displayMenu();
                    break;
                default:
                    System.out.println("Onbekende optie.");
            }
        }
    }

    private void voegEpicToe() {
        System.out.println("=== Voeg Epic Toe ===");
        System.out.print("Naam Epic: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Naam mag niet leeg zijn.");
        } else {
            boolean success = epicController.createEpic(name);
            if (success) {
                System.out.println("Epic toegevoegd: " + name);
            } else {
                System.out.println("Epic '" + name + "' bestaat al.");
            }
        }

        System.out.println("Druk ENTER om terug te gaan...");
        scanner.nextLine();
        cliHandler.getMainMenu().displayMenu();
    }

    private void listEpics() {
        if (epicController.getEpics().isEmpty()) {
            System.out.println("leeg");
        } else {
            System.out.println("epics:");
            for (int i = 0; i < epicController.getEpics().size(); i++) {
                Epic epic = epicController.getEpics().get(i);
                System.out.printf("%d. %s%n", 1 + i, epic.getTitel());
            }
        }
        System.out.println();
    }
}




