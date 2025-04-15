package org.teamflow.cli;

import org.teamflow.models.Epic;
import org.teamflow.models.User;
import org.teamflow.controllers.UserController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLIHandler
{
    private Scanner scanner;
    private UserController userController;
    private ArrayList<Epic> epics = new ArrayList<>();

    public CLIHandler () {
        scanner = new Scanner(System.in);
        userController = new UserController();
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
            String choice = scanner.nextLine();

            System.out.println("Wat is uw naam?");
            String username = scanner.nextLine();

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
            } else if (choice.equalsIgnoreCase("login"))
            {
                success = userController.login(username);
                if (success)
                {
                    System.out.printf("Ingelogd als '%s'%n", username);
                    break;
                } else
                {
                    System.out.println("Gebruiker niet gevonden. Probeer het opnieuw of registreer.");
                }
            } else
            {
                System.out.println("Ongeldige optie. Typ 'login' of 'register'.");
            }
        }
        mainMenu();
    }
    private void mainMenu() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleCommand(input);
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
        if (epics.isEmpty()) {
            System.out.println("leeg");
        } else {
            System.out.println("epics:");
            for (int i = 0; i < epics.size(); i++) {
                Epic epic = epics.get(i);
                System.out.printf("%d. %s%n", 1 + i, epic.getTitel());
            }
        }
    }
}
