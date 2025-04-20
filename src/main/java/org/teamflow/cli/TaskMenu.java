package org.teamflow.cli;

import org.teamflow.controllers.EpicController;
import org.teamflow.controllers.TaskController;
import org.teamflow.controllers.UserController;
import org.teamflow.controllers.UserStoryController;
import org.teamflow.models.Epic;
import org.teamflow.models.Task;
import org.teamflow.models.UserStory;

import java.util.ArrayList;

public class TaskMenu extends Menu {
    private EpicController epicController;
    private UserStoryController userStoryController;
    private TaskController taskController;
    private UserController userController;

    public TaskMenu(CLIHandler cliHandler) {
        super(cliHandler);
        this.epicController = cliHandler.getEpicController();
        this.userStoryController = cliHandler.getUserStoryController();
        this.taskController = cliHandler.getTaskController();
        this.userController = cliHandler.getUserController();
    }

    @Override
    public void displayMenu() {
        clearConsole();
        while (cliHandler.isRunning()) {
            System.out.printf("Huidige Epic: %s%n", cliHandler.getEpicController().getCurrentEpicName());
            System.out.printf("Huidige User Story: %s%n", cliHandler.getUserStoryController().getCurrentUserStoryName());
            System.out.println("1. Voeg Taak toe");
            System.out.println("2. Laat Taken zien");
            System.out.println("3. Selecteer User Story");
            System.out.println("4. Terug naar main menu");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    voegTaakToe();
                    break;
                case "2":
                    listTaken();
                    break;
                case "3":
                    selectUserStory();
                    break;
                case "4":
                    cliHandler.getMainMenu().displayMenu();
                    break;
                default:
                    System.out.println("Onbekende optie.");
            }
        }
    }

    private void voegTaakToe() {
        if (userStoryController.getUserStories().isEmpty()) {
            System.out.println("U heeft geen UserStories");
            return;
        } else if (userStoryController.getCurrentUserStory() == null) {
            System.out.println("U heeft nog geen User Story geselecteerd.");
            selectUserStory();
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
                        selectUserStory();
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
        System.out.println("=== Voeg Taak Toe ===");
        System.out.print("Voer de titel in voor de nieuwe Taak: ");
        String titel = scanner.nextLine().trim();

        if (titel.isEmpty()) {
            System.out.println("Titel mag niet leeg zijn.");
        } else {
            System.out.print("Voer de inhoud in voor de nieuwe Taak: ");
            String content = scanner.nextLine().trim();
            UserStory current = userStoryController.getCurrentUserStory();
            if (current == null) {
                System.out.println("Je moet eerst een User Story aanmaken of selecteren (optie 3).");
            } else {
                Task task = taskController.createTask(titel, content, userController.getCurrentUser().getId());
                current.addTask(task);
                userStoryController.saveUserStories();
                System.out.println("Taak toegevoegd onder User Story '" + current.getTitel() + "': " + titel);
            }
        }

        System.out.println("\nDruk ENTER om terug te keren naar het hoofdmenu.");
        scanner.nextLine();
    }

    private void listTaken() {
        UserStory currentUserStory = userStoryController.getCurrentUserStory();
        if (currentUserStory == null) {
            System.out.println("Geen User Story geselecteerd.");
            return;
        }

        ArrayList<Task> taken = currentUserStory.gettaskList();
        if (taken.isEmpty()) {
            System.out.println("leeg");
        } else {
            System.out.printf("taken %s:", userStoryController.getCurrentUserStory());
            for (int i = 0; i < taken.size(); i++) {
                Task task = taken.get(i);
                System.out.printf("%d. %s%n", 1 + i, task.getTitle());
            }
        }
        System.out.println();
    }


    public void selectUserStory() {
        clearConsole();
        while (cliHandler.isRunning()) {
            System.out.println("Beschikbare User Stories:");
            for (int i = 0; i < userStoryController.getUserStories().size(); i++) {
                System.out.println(i + ": " + userStoryController.getUserStories().get(i).getTitel());
            }
            System.out.printf("%d: Ga terug%n", userStoryController.getUserStories().size());

            System.out.print("Druk de nummer van de User Story die je wilt selecteren: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 0 && index < userStoryController.getUserStories().size()) {
                UserStory selectedUserStory = userStoryController.getUserStories().get(index);
                userStoryController.setCurrentUserStory(selectedUserStory);
                System.out.println("Geselecteerde User Story: " + selectedUserStory.getTitel());
                break;
            } else if (index == userStoryController.getUserStories().size()) {
                cliHandler.getMainMenu().displayMenu();
                break;
            } else {
                System.out.println("Invalide nummer. Probeer opnieuw.");
            }
        }
    }
}




