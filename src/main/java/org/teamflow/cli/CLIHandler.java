package org.teamflow.cli;

import org.teamflow.models.User;
import org.teamflow.controllers.UserController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLIHandler
{
    private Scanner scanner;
    private UserController userController;

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
        System.out.println("Wat is uw naam?");
        String username = scanner.nextLine();
        userController.createUser(username);
        System.out.printf("Uw naam is %s. Klopt dit?%n", userController.getCurrentUserName());
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
        }
    }
}
