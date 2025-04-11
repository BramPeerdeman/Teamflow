package org.teamflow.cli;

import java.util.ArrayList;
import java.util.Arrays;

public class CLIHandler
{
    private ArrayList<String> asciiArt = new ArrayList<String>(Arrays.asList(
            "",
            "                                      ",
            " |   |       |  | | __      ",
            "   | |/  \/  | '_   \| |  | |/ _ \ \ /\ / /",
            "   | |  / (| | | | | | |  | | | () \ V  V / ",
            "   ||\|\,|| || |||   ||\__/ \/\/  ",
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
