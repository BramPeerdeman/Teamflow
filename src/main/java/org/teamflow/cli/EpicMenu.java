package org.teamflow.cli;

import org.teamflow.controllers.EpicController;
import org.teamflow.models.Epic;

import java.util.ArrayList;

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
            System.out.println("3. Verwijder Epic");
            System.out.println("4. Terug naar main menu");
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
                    verwijderEpic();
                    break;
                case "4":
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

    private void verwijderEpic()
    {
        if (epicController.getEpics().isEmpty())
        {
            System.out.println("Er zijn geen epics om te verwijderen.");
        } else
        {
            listEpics(); // Reuse your existing method
            System.out.print("Voer het nummer in van de Epic om te verwijderen: ");
            String input = scanner.nextLine().trim();

            try
            {
                int index = Integer.parseInt(input) - 1;
                ArrayList<Epic> epics = epicController.getEpics();

                if (index >= 0 && index < epics.size())
                {
                    Epic toDelete = epics.get(index);
                    System.out.print("Weet je zeker dat je '" + toDelete.getTitel() + "' wilt verwijderen? (j/n): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();

                    if (confirm.equals("j"))
                    {
                        epicController.deleteEpic(toDelete);
                        System.out.println("Epic '" + toDelete.getTitel() + "' is verwijderd.");
                    } else
                    {
                        System.out.println("Verwijderen geannuleerd.");
                    }
                } else
                {
                    System.out.println("Ongeldig nummer.");
                }
            } catch (NumberFormatException e)
            {
                System.out.println("Voer een geldig nummer in.");
            }
        }

        System.out.println("Druk ENTER om terug te gaan...");
        scanner.nextLine();
    }
}




