package org.teamflow.cli;

import org.teamflow.controllers.MessageController;
import org.teamflow.controllers.UserController;
import org.teamflow.models.Message;
import org.teamflow.models.User;

import java.util.ArrayList;

public class MessageMenu extends Menu {
    private UserController userController;
    private MessageController messageController;

    public MessageMenu(CLIHandler cliHandler) {
        super(cliHandler);
        this.userController = cliHandler.getUserController();
        this.messageController = cliHandler.getMessageController();
    }

    @Override
    public void displayMenu() {
        clearConsole();
        while (cliHandler.isRunning()) {
            System.out.println("1. Voeg Bericht toe");
            System.out.println("2. Laat Berichten zien");
            System.out.println("3. Terug naar main menu");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    voegBerichtToe();
                    break;
                case "2":
                    listBerichten();
                    break;
                case "3":
                    cliHandler.getMainMenu().displayMenu();
                    break;
                default:
                    System.out.println("Onbekende optie.");
            }
        }
    }

    private void voegBerichtToe() {
        clearConsole();
        System.out.println("=== Voeg Bericht Toe ===");
        System.out.print("Voer de inhoud van het bericht in: ");
        String inhoud = scanner.nextLine().trim();

        if (inhoud.isEmpty()) {
            System.out.println("Bericht mag niet leeg zijn.");
        } else {
            User currentUser = userController.getCurrentUser();
            boolean success = messageController.createMessage(inhoud, currentUser);
            if (success) {
                System.out.println("Bericht toegevoegd door " + currentUser.getName() + ": " + inhoud);
            } else {
                System.out.println("Er is een fout opgetreden bij het toevoegen van het bericht.");
            }
        }

        System.out.println("\nDruk ENTER om terug te keren.");
        scanner.nextLine();
    }

    private void listBerichten() {
        clearConsole();
        System.out.println("=== Berichten ===");
        ArrayList<Message> berichten = messageController.getMessages();
        if (berichten.isEmpty()) {
            System.out.println("Geen berichten beschikbaar.");
        } else {
            for (Message bericht : berichten) {
                User afzender = bericht.getAfzender();
                if (afzender != null) {
                    System.out.printf("%s: %s%n", afzender.getName(), bericht.getInhoud());
                } else {
                    System.out.println("Onbekende afzender: " + bericht.getInhoud());
                }
            }
        }
        System.out.println("\nDruk ENTER om terug te keren.");
        scanner.nextLine();
    }
}




