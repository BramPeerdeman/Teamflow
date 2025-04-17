package org.teamflow.cli;

import org.teamflow.controllers.EpicController;
import org.teamflow.controllers.MessageController;
import org.teamflow.controllers.TaskController;
import org.teamflow.controllers.UserStoryController;
import org.teamflow.models.Epic;
import org.teamflow.controllers.UserController;
import org.teamflow.models.Task;
import org.teamflow.models.UserStory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLIHandler
{
    private Scanner scanner;
    private EpicController epicController;
    private UserController userController;
    private TaskController taskController;
    private UserStoryController userStoryController;
    private MessageController messageController;
    private ArrayList<Epic> epics = new ArrayList<>();

    public CLIHandler () {
        taskController = new TaskController();
        scanner = new Scanner(System.in);
        userController = new UserController();
        epicController = EpicController.getInstance();
        userStoryController = UserStoryController.getInstance();
    }
    private ArrayList<String> asciiArt = new ArrayList<String>(Arrays.asList(
            "",
            "  _____                    _____ _               ",
            " |_   _|__  __ _ _ __ ___ |  ___| | _____      __",
            "   | |/ _ \\/ _` | '_ ` _ \\| |_  | |/ _ \\ \\ /\\ / /",
            "   | |  __/ (_| | | | | | |  _| | | (_) \\ V  V / ",
            "   |_|\\___|\\__,_|_| |_| |_|_|   |_|\\___/ \\_/\\_/  ",
            "",
            "       -~= The scrum based chat-tool =~-              "
    ));

    public void StartUp() {
        for (String s : asciiArt) {
            System.out.println(s);
            try {
                Thread.sleep(56,250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        while (true) {
            System.out.println("Welkom! Wil je [login] of [register]?");
            String choice = scanner.nextLine().trim();

            System.out.println("Wat is uw naam?");
            String username = scanner.nextLine().trim();

            boolean success;
            if (choice.equalsIgnoreCase("register"))
            {
                success = userController.createUser(username);
                if (success)
                {
                    System.out.printf("Gebruiker '%s' is geregistreerd!%n", username);
                    break;
                } else
                {
                    System.out.println("Deze naam is al in gebruik. Probeer een andere.");
                }
            } else if (choice.equalsIgnoreCase("login")) {
                success = userController.login(username);
                if (success) {
                    while (true) {
                        System.out.println("Ben jij de Scrum Master? (ja/nee)");
                        String answer = scanner.nextLine().trim().toLowerCase();

                        if (answer.equals("ja")) {
                            boolean set = userController.setScrumMasterFlag(username, true);
                            if (set) {
                                System.out.printf("Ingelogd als '%s' (Scrum Master)%n", username);
                                break;
                            } else {
                                System.out.println("Wil je als gewone gebruiker verder? (ja/nee)");
                                String fallback = scanner.nextLine().trim().toLowerCase();
                                if (fallback.equals("ja")) {
                                    userController.setScrumMasterFlag(username, false);
                                    System.out.printf("Ingelogd als '%s'%n", username);
                                    break;
                                } else {
                                    System.out.println("Oké, dan stoppen we de sessie.");
                                    return;
                                }
                            }
                        } else if (answer.equals("nee")) {
                            userController.setScrumMasterFlag(username, false);
                            System.out.printf("Ingelogd als '%s'%n", username);
                            break;
                        } else {
                            System.out.println("Ongeldig antwoord. Typ 'ja' of 'nee'.");
                        }
                    }
                    break;
                } else {
                    System.out.println("Gebruiker niet gevonden. Probeer het opnieuw of registreer.");
                }
            } else
                {
                System.out.println("Ongeldige optie. Typ 'login' of 'register'.");
                }
            }
        messageController = new MessageController(userController.getCurrentUser());
        mainMenu();
    }
    private void epicMenu() {
        clearConsole();
        while (true) {
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
                    mainMenu();
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
            // AANPASSING HIER: instance‑methode gebruiken
            boolean success = epicController.createEpic(name);
            if (success) {
                System.out.println("Epic toegevoegd: " + name);
            } else {
                System.out.println("Epic '" + name + "' bestaat al.");
            }
        }

        System.out.println("Druk ENTER om terug te gaan...");
        scanner.nextLine();
        mainMenu();
    }
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private void mainMenu() {
        Boolean isScrumMaster = userController.getCurrentUser().getIsScrumMaster();
        if (Boolean.TRUE.equals(isScrumMaster)) {
            System.out.println("Welkom Scrum Master!");
        }


        while (true) {
            System.out.println("1. Epics");
            System.out.println("2. UserStories");
            System.out.println("3. Taken");
            System.out.println("4. Berichten");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    epicMenu();
                    break;
                case "2":
                    userStoryMenu();
                    break;
                case "3" :
                    taskMenu();
                    break;
                default:
                    System.out.println("Onbekende optie.");
            }

        }


    }
    private void userStoryMenu() {
        clearConsole();
        System.out.println("=== Voeg User Story Toe ===");
        System.out.print("Voer de titel in voor de nieuwe User Story: ");
        String titel = scanner.nextLine().trim();

        if (titel.isEmpty()) {
            System.out.println("Titel mag niet leeg zijn. Terug naar hoofdmenu.");
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

    private void taskMenu() {
        clearConsole();
        System.out.println("=== Voeg Taak Toe ===");
        System.out.print("Voer de titel in voor de nieuwe taak: ");
        String title = scanner.nextLine().trim();
        System.out.print("Voer de beschrijving van de taak in:");
        String content = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Titel mag niet leeg zijn. Terug naar hoofdmenu.");
        } else if (content.isEmpty()) {
            System.out.println("Beschrijving is leeg, wilt u doorgaan zonder content? [Ja/Nee]");
            String antwoord = scanner.nextLine().trim();
            if (antwoord.equalsIgnoreCase("nee")) {
                System.out.println("Voer de beschrijving van de taak in:");
                content = scanner.nextLine().trim();
            }
        } else {
            // Haal de actieve us uit de controller
            UserStory current = UserStoryController.getInstance().getCurrentUserStory();
            if (current == null) {
                System.out.println("Je moet eerst een User Story aanmaken of selecteren.");
            } else {

                taskController.createTask(title, content, userController.getCurrentUser().getId());

                userStoryController.saveUserStories();

                System.out.println("Taak toegevoegd onder User Story '"
                        + current.getTitel() + "': " + title);
            }
        }
    }

    private void handleCommand(String comment) {
        switch (comment.toLowerCase()) {
            case "hello":
                System.out.printf("hoi, %s%n", userController.getCurrentUserName());
                break;
            case "list epics":
                listEpics();
                break;
        }
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
