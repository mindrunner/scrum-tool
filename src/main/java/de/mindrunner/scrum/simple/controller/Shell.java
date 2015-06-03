package de.mindrunner.scrum.simple.controller;

import de.mindrunner.scrum.base.controller.ScrumController;
import de.mindrunner.scrum.simple.util.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 *
 */
public final class Shell {
    private ScrumController scrumController;

    public Shell(ScrumController model) {
        this.scrumController = model;
    }

    public void startShell() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;

        do {
            System.out.print("scrum>");
            input = bufferedReader.readLine();
            ShellCommand shellCommand = new ShellCommand(input, scrumController);
            output = shellCommand.execute();
            if (!output.equals(ShellCommand.getExitResult()) && !output.equals(C.EMPTYSTR)) {
                System.out.println(output);
            }
        } while (!output.equals(ShellCommand.getExitResult()));
    }
}
