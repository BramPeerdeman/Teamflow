package org.teamflow.cli;

import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner = new Scanner(System.in);
    protected CLIHandler cliHandler;

    public Menu(CLIHandler cliHandler) {
        this.cliHandler = cliHandler;
    }

    public abstract void displayMenu();

    protected void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

