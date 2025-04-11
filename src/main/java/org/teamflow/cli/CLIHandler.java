package org.teamflow.cli;

import java.util.ArrayList;
import java.util.Arrays;

public class CLIHandler
{
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
    }
}
