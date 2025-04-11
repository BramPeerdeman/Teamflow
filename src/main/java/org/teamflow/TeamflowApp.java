package org.teamflow;

import org.teamflow.cli.CLIHandler;

public class TeamflowApp
{
    public static void main (String [] args)

    {
        CLIHandler cliHandler = new CLIHandler();
        cliHandler.StartUp();
    }
}