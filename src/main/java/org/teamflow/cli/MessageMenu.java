package org.teamflow.cli;

import org.teamflow.controllers.*;
import org.teamflow.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MessageMenu extends Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final MessageController messageController;
    private final UserController userController;
    private final EpicController epicController;
    private final UserStoryController userStoryController;
    private final TaskController taskController;

    public MessageMenu(CLIHandler cliHandler) {
        super(cliHandler);
        this.messageController = cliHandler.getMessageController();
        this.userController = cliHandler.getUserController();
        this.epicController = cliHandler.getEpicController();
        this.userStoryController = cliHandler.getUserStoryController();
        this.taskController = cliHandler.getTaskController();
    }

    @Override
    public void displayMenu() {
        clearConsole();
        while (true) {
            System.out.println("=== Bericht Menu ===");
            System.out.println("1. Voeg Bericht toe");
            System.out.println("2. Toon Berichten");
            System.out.println("3. Terug");
            System.out.print("> ");
            switch (scanner.nextLine()) {
                case "1" -> berichtToevoegen();
                case "2" -> toonBerichten();
                case "3" -> { return; }
                default -> System.out.println("Ongeldige optie.");
            }
        }
    }

    private void berichtToevoegen() {
        System.out.println("Kies type koppeling: 1. Epic  2. User Story  3. Task");
        String type = scanner.nextLine();
        Integer epicId = null, userStoryId = null, taskId = null;

        switch (type) {
            case "1" -> {
                showEpics();
                System.out.print("Voer Epic ID in: ");
                epicId = Integer.parseInt(scanner.nextLine());
            }
            case "2" -> {
                showUserStories();
                System.out.print("Voer User Story ID in: ");
                userStoryId = Integer.parseInt(scanner.nextLine());
            }
            case "3" -> {
                showTasks();
                System.out.print("Voer Task ID in: ");
                taskId = Integer.parseInt(scanner.nextLine());
            }
            default -> {
                System.out.println("Ongeldig type.");
                return;
            }
        }

        System.out.print("Voer je bericht in: ");
        String inhoud = scanner.nextLine();

        User currentUser = userController.getCurrentUser();

        boolean pinned = false;
        if (currentUser.isScrumMaster()) {
            System.out.print("Wil je dit bericht pinnen? (ja/nee): ");
            String pinInput = scanner.nextLine();
            pinned = pinInput.equalsIgnoreCase("ja");
        }

        if (messageController.createMessage(inhoud, currentUser, epicId, userStoryId, taskId, pinned)) {
            System.out.println("Bericht succesvol toegevoegd.");
        }
    }


    private void toonBerichten() {
        System.out.println("Toon berichten voor: 1. Epic  2. User Story  3. Task");
        String keuze = scanner.nextLine();

        switch (keuze) {
            case "1" -> {
                if (epicController.getEpics().isEmpty()) {
                    System.out.println("Geen beschikbare Epics.");
                    break;
                } else {
                    showEpics();
                }
                System.out.print("Epic ID: ");
                toon(messageController.getMessagesForEpic(Integer.parseInt(scanner.nextLine())));
            }
            case "2" -> {
                if (userStoryController.getUserStories().isEmpty()) {
                    System.out.println("Geen beschikbare User Stories.");
                    break;
                } else {
                    showUserStories();
                }
                System.out.print("User Story ID: ");
                toon(messageController.getMessagesForUserStory(Integer.parseInt(scanner.nextLine())));
            }
            case "3" -> {
                if (taskController.getTasks().isEmpty()) {
                    System.out.println("Geen beschikbare Tasks.");
                    break;
                } else {
                    showTasks();
                }
                System.out.print("Task ID: ");
                toon(messageController.getMessagesForTask(Integer.parseInt(scanner.nextLine())));
            }
            default -> System.out.println("Ongeldige keuze.");
        }
    }

    private void toon(ArrayList<Message> berichten) {
        if (berichten.isEmpty()) {
            System.out.println("Geen berichten gevonden.");
        } else {
            for (Message b : berichten) {
                String pinMark = b.isPinned() ? "📌 " : "";
                System.out.printf("[%s] %s%s: %s%n",
                        b.getTimestamp(),
                        b.getAfzender().getName(),
                        b.isPinned() ? " 📌" : "",
                        b.getInhoud()
                );
            }
        }
    }


    private void showEpics() {
        System.out.println("Beschikbare Epics:");
        for (Epic e : epicController.getEpics()) {
            System.out.printf("ID: %d - %s%n", e.getId(), e.getTitel());
        }
    }

    private void showUserStories() {
        System.out.println("Beschikbare User Stories:");
        for (UserStory u : userStoryController.getUserStories()) {
            System.out.printf("ID: %d - %s%n", u.getId(), u.getTitel());
        }
    }

    private void showTasks() {
        System.out.println("Beschikbare Tasks:");
        for (Task t : taskController.getTasks()) {
            System.out.printf("ID: %d - %s%n", t.getId(), t.getTitle());
        }
    }

}
