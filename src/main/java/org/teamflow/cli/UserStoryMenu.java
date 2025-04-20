package org.teamflow.cli;

import org.teamflow.controllers.EpicController;
import org.teamflow.controllers.UserStoryController;
import org.teamflow.models.Epic;
import org.teamflow.models.UserStory;

public class UserStoryMenu extends Menu {
    private EpicController epicController;
    private UserStoryController userStoryController;
    public UserStoryMenu(CLIHandler cliHandler) {
        super(cliHandler);
        this.epicController = cliHandler.getEpicController();
        this.userStoryController = cliHandler.getUserStoryController();
    }

    @Override
    public void displayMenu() {
        clearConsole();
        while (cliHandler.isRunning()) {
            System.out.printf("Huidige Epic: %s%n", cliHandler.getEpicController().getCurrentEpicName());
            System.out.println("1. Voeg UserStory toe");
            System.out.println("2. Laat UserStories zien");
            System.out.println("3. Selecteer Epic");
            System.out.println("4. Terug naar main menu");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    voegUserStoryToe();
                    break;
                case "2":
                    listUserStories();
                    break;
                case "3":
                    selectEpic();
                    break;
                case "4":
                    cliHandler.getMainMenu().displayMenu();
                    break;
                default:
                    System.out.println("Onbekende optie.");
            }
        }
    }

    private void voegUserStoryToe() {
        if (epicController.getEpics().isEmpty()) {
            System.out.println("U heeft geen Epics");
            return;
        } else if (epicController.getCurrentEpic() == null) {
            System.out.println("U heeft nog geen Epic Geselecteert.");
            selectEpic();
            while (true) {
                System.out.println("Klopt dit?");
                System.out.println("1. Ja");
                System.out.println("2. Nee");
                System.out.print(">");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        break;
                    case "2":
                        selectEpic();
                        break;
                    default:
                        System.out.println("Onbekende optie.");
                        break;
                }
                if (choice.equals("1")){
                    break;
                }
            }


        }
        clearConsole();
        System.out.println("=== Voeg User Story Toe ===");
        System.out.print("Voer de titel in voor de nieuwe User Story: ");
        String titel = scanner.nextLine().trim();

        if (titel.isEmpty()) {
            System.out.println("Titel mag niet leeg zijn.");
        } else {
            // Haal de actieve Epic uit de controller
            Epic current = epicController.getCurrentEpic();
            if (current == null) {
                System.out.println("Je moet eerst een Epic aanmaken of selecteren (optie 1).");
            } else {

                boolean success = userStoryController.createUserStory(titel);
                if (success) {
                    epicController.saveEpics();

                    System.out.println("User Story toegevoegd onder Epic '"
                            + current.getTitel() + "': " + titel);
                } else {
                    System.out.println("UserStory '" + titel + "' bestaat al.");
                }
            }
        }

        System.out.println("\nDruk ENTER om terug te keren naar het hoofdmenu.");
        scanner.nextLine();
        // mainMenu blijft draaien, dus je returnt automatisch weer naar het hoofdscherm
    }

    private void listUserStories() {
        if (userStoryController.getUserStories().isEmpty()) {
            System.out.println("leeg");
        } else {
            System.out.println("user stories:");
            for (int i = 0; i < userStoryController.getUserStories().size(); i++) {
                UserStory userStory = userStoryController.getUserStories().get(i);
                System.out.printf("%d. %s%n", 1 + i, userStory.getTitel());
            }
        }
        System.out.println();
    }

    public void selectEpic() {
        clearConsole();
        while (cliHandler.isRunning()) {
            System.out.println("Beschikbare Epics:");
            for (int i = 0; i < epicController.getEpics().size(); i++) {
                System.out.println(i + ": " + epicController.getEpics().get(i).getTitel());
            }
            System.out.printf("%d: Ga terug", epicController.getEpics().size());

            System.out.print("Druk de nummer van de Epic die je wilt selecteren: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 0 && index < epicController.getEpics().size()) {
                Epic selectedEpic = epicController.getEpics().get(index);
                epicController.setCurrentEpic(selectedEpic);
                System.out.println("Geselecteerde Epic: " + selectedEpic.getTitel());
                break;
            } else if (index == epicController.getEpics().size()) {
                cliHandler.getMainMenu().displayMenu();
                break;
            } else {
                System.out.println("Invalide nummer. Probeer opnieuw.");
            }
        }
    }
}

