package org.teamflow.cli;

import org.teamflow.controllers.MessageController;
import org.teamflow.controllers.UserController;

import java.util.ArrayList;
import java.util.Arrays;

public class StartupMenu extends Menu {

    public StartupMenu(CLIHandler cliHandler) {
        super(cliHandler);
    }

    @Override
    public void displayMenu() {
        ArrayList<String> asciiArt = new ArrayList<>(Arrays.asList(
                "",
                "  _____                    _____ _               ",
                " |_   _|__  __ _ _ __ ___ |  ___| | _____      __",
                "   | |/ _ \\/ _` | '_ ` _ \\| |_  | |/ _ \\ \\ /\\ / /",
                "   | |  __/ (_| | | | | | |  _| | | (_) \\ V  V / ",
                "   |_|\\___|\\__,_|_| |_| |_|_|   |_|\\___/ \\_/\\_/  ",
                "",
                "       -~= The scrum based chat-tool =~-              "
        ));

        for (String s : asciiArt) {
            System.out.println(s);
            try {
                Thread.sleep(56, 250);
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
            if (choice.equalsIgnoreCase("register")) {
                success = cliHandler.getUserController().createUser(username);
                if (success) {
                    System.out.printf("Gebruiker '%s' is geregistreerd!%n", username);
                    break;
                } else {
                    System.out.println("Deze naam is al in gebruik. Probeer een andere.");
                }
            } else if (choice.equalsIgnoreCase("login")) {
                success = cliHandler.getUserController().login(username);
                if (success) {
                    while (true) {
                        System.out.println("Ben jij de Scrum Master? (ja/nee)");
                        String answer = scanner.nextLine().trim().toLowerCase();

                        if (answer.equals("ja")) {
                            boolean set = cliHandler.getUserController().setScrumMasterFlag(username, true);
                            if (set) {
                                System.out.printf("Ingelogd als '%s' (Scrum Master)%n", username);
                                break;
                            } else {
                                System.out.println("Wil je als gewone gebruiker verder? (ja/nee)");
                                String fallback = scanner.nextLine().trim().toLowerCase();
                                if (fallback.equals("ja")) {
                                    cliHandler.getUserController().setScrumMasterFlag(username, false);
                                    System.out.printf("Ingelogd als '%s'%n", username);
                                    break;
                                } else {
                                    System.out.println("Oké, dan stoppen we de sessie.");
                                    return;
                                }
                            }
                        } else if (answer.equals("nee")) {
                            cliHandler.getUserController().setScrumMasterFlag(username, false);
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
            } else {
                System.out.println("Ongeldige optie. Typ 'login' of 'register'.");
            }
        }

        cliHandler.getMainMenu().displayMenu();
    }
}
